<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 라이브러리 (SweetAlert2, Flatpickr -->

<%--<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>

<style>
    #loading-spinner {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 9999;
    }

    .spinner {
        border: 8px solid #f3f3f3;
        border-top: 8px solid #3498db;
        border-radius: 50%;
        width: 60px;
        height: 60px;
        animation: spin 1s linear infinite;
    }

    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
</style>

<div id="loading-spinner" style="display: none;">
    <div class="spinner"></div>
</div>



<!-- 예약 모달 초기화 및 Swalfire 처리 -->
<script>
    function initializeReservationModal() {
        Swal.fire({
            title: `병원 예약 - ${hospital.h_name}`,
            html: `
                <div id="reservation-modal-content">
                    <input type="text" id="reservation-date-picker" placeholder="예약 날짜를 선택하세요">
                    <div id="time-slots-container"></div>
                </div>
            `,
            showCancelButton: true,
            confirmButtonText: '다음',
            preConfirm: () => {
                const selectedDate = $('#reservation-date-picker').val();
                const selectedTime = $('input[name="timeSlot"]:checked').val();
                return { selectedDate, selectedTime };
            }
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: 'reservationInfo',
                    dataType: 'json',
                    method: 'POST',
                    success: function (response) {
                        console.log(response);
                       const user = response.user;
                       const pet = response.pet;
                Swal.fire({
                    title: '예약 정보 확인',
                    html: generateReservationHtml(result.value, user, pet),
                    confirmButtonText: '예약 완료'
                });
            },
                    error: function (jqXHR, textStatus, errorThrown) {
                        // 요청이 실패할 경우 오류 메시지 표시
                        Swal.fire({
                            icon: 'error',
                            title: '오류 발생',
                            text: '예약 정보를 가져오는 중 오류가 발생했습니다. 다시 시도해 주세요.'
                        });
                    }
                });
            }
        });
        initializeDatePicker();
    }
</script>

<!-- Flatpickr 및 디바운스 처리 -->
<script>
    function showLoadingSpinner() {
        $('#loading-spinner').show();
    }

    function hideLoadingSpinner() {
        $('#loading-spinner').hide();
    }

    const debouncedFetch = _.debounce(function(dateStr) {
        showLoadingSpinner();
        getAvailableTimeSlots(dateStr);
    }, 300);

    function initializeDatePicker() {
        // 플리피커(Flatpickr) 초기화
        flatpickr("#reservation-date-picker", {
            inline: true,
            dateFormat: "Y-m-d",
            minDate: "today",
            maxDate: new Date().fp_incr(7),
            onChange: function (selectedDates, dateStr) {
                // updateAvailableTimeSlots(dateStr);
                debouncedFetch(dateStr);
            }
        });
    }
</script>

<!-- 예약 가능한 시간 슬롯 요청 -->
<script>
    function getAvailableTimeSlots(dateStr) {
        const hospitalId = ${hospital.h_id};
        $.ajax({
            url: 'reservation',
            dataType: 'json',
            type: 'GET',
            data: { hospitalId, dateStr },
            success: function (response) {
                const availableTimes = response.availableTimeSlots;
                const $timeSlotContainer = $('#time-slots-container');
                $timeSlotContainer.html(generateTimeSlotHTML(availableTimes));
                hideLoadingSpinner();
            }
        });
    }
    function updateAvailableTimeSlots(dateStr) {
        getAvailableTimeSlots(dateStr);
    }
</script>

<!-- 시간 슬롯 생성 및 HTML 생성 관련 함수 -->
<script>
    function generateTimeSlotHTML(availableTimes) {
        return availableTimes.map(time => createTimeSlotLabel(time)).join('');
    }

    function createTimeSlotLabel(time) {
        const hour = String(time.time.hour).padStart(2, '0');
        const minute = String(time.time.minute).padStart(2, '0');
        const timeLabel = `${"${hour}"}:${"${minute}"}`;
        const disabledAttribute = getDisabledAttribute(time.isAvailable);
        return `
             <label class="time-slot-label">
                <input type="radio" name="timeSlot" value="${"${timeLabel}"}" ${"${disabledAttribute}"}>
                <span class="time-slot-button">${"${timeLabel}"}</span>
            </label>
        `;
    }
    function getDisabledAttribute(isAvailable) {
        return !isAvailable ? 'disabled' : '';
    }
</script>

<script>

    function initializeCheckboxHandler() {
        $(document).on('click', '.single-select-checkbox', function() {
            $('.single-select-checkbox').not(this).prop('checked', false);
            if ($('#otherCheck').is(':checked')) {
                $('#otherText').prop('disabled', false);
            } else {
                $('#otherText').prop('disabled', true).val('');
            }
        });
    }

    // 예약 정보를 HTML로 출력하는 함수
    function generateReservationHtml(result, user, pet) {

        return `
            <div class="reservation-summary">
                <h3>예약 정보</h3>

                <div class="pet-selection">
                   <p><strong>펫 선택:</strong>${"${pet.pet_name}"}</p>
                </div>

                <div class="reservation-items">
                    <h4>예약 항목</h4>
                    <label><input type="checkbox" class="single-select-checkbox" name="reservationItem" value="진료"> 진료</label>
                    <label><input type="checkbox" class="single-select-checkbox" name="reservationItem" value="상담"> 상담</label>
                    <label><input type="checkbox" class="single-select-checkbox" name="reservationItem" value="미용"> 미용</label>
                    <label>
                        <input type="checkbox" class="single-select-checkbox" name="reservationItem" value="기타" id="otherCheck"> 기타
                        <input type="text" id="otherText" placeholder="직접 입력하기" disabled>
                    </label>
                </div>

                <div class="reservation-date">
                  <p><strong>예약 날짜:</strong> ${"${result.selectedDate}"}</p>
                  <p><strong>예약 시간:</strong> ${"${result.selectedTime}"}</p>
                </div>

                <div class="guardian-info">
                    <h4>보호자 정보</h4>
                    <p><strong>예약자 이름:</strong> ${"${user.name}"}</p>
                </div>

            </div>
        `;
    }

    // 이벤트 핸들러 초기화 호출
    $(document).ready(function() {
        initializeCheckboxHandler();
    });
</script>
