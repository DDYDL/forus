<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 라이브러리 (SweetAlert2, Flatpickr -->

<%--<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>--%>

<%

%>

<script>
    function initializeReservationModal() {
        Swal.fire({
            title:`병원 예약 - ${hospital.h_name}`,
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
                return {selectedDate, selectedTime};
            }
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: '예약 정보 확인',
                    html: `
                        <p>선택된 날짜: ${"${result.value.selectedDate}"}</p>
                        <p>선택된 시간: ${"${result.value.selectedTime}"}</p>
                    `,
                    confirmButtonText: '예약 완료'
                });
            }
        });

        // 플리피커(Flatpickr) 초기화
        flatpickr("#reservation-date-picker", {
            inline: true, // 캘린더가 인라인으로 표시되도록 설정
            dateFormat: "Y-m-d", // 날짜 형식 설정
            minDate: "today", // 선택 가능 최소 날짜를 오늘로 설정
            maxDate: new Date().fp_incr(7), // 선택 가능 최대 날짜를 오늘로부터 7일 후로 설정
            onChange: function (selectedDates, dateStr) {
                updateAvailableTimeSlots(dateStr); // 날짜 선택 시 시간 슬롯 업데이트
            }
        });
    }

    function updateAvailableTimeSlots(dateStr) {
        const availableTimes = calculateAvailableTimes(dateStr);
        const $timeSlotsContainer = $('#time-slots-container');
        $timeSlotsContainer.html(generateTimeSlotHTML(availableTimes));
    }

    function generateTimeSlotHTML(availableTimes) {
        return availableTimes.map(time => createTimeSlotLabel(time)).join('');
    }

    function createTimeSlotLabel(time) {
        const disabledAttribute = getDisabledAttribute(time.isReserved);
        return `
             <label class="time-slot-label">
                <input type="radio" name="timeSlot" value="${"${time.label}"}" ${"${disabledAttribute}"}>
                <span class="time-slot-button">${"${time.label}"}</span>
            </label>
        `;
    }

    function getDisabledAttribute(isDisabled) {
        return isDisabled ? 'disabled' : '';
    }

    function calculateAvailableTimes(dateStr) {
        const allTimes = [
            "09:00", "09:30",
            "10:00", "10:30",
            "11:00", "11:30",
            "12:00", "12:30",
            "13:00", "13:30",
            "14:00", "14:30",
            "15:00", "15:30",
            "16:00", "16:30",
            "17:00", "17:30",
            "18:00"
        ];

        const reservedTimes = ["10:00", "14:00", "16:30"];  // 예시로 이미 예약된 시간들

        return allTimes.map(time => ({
            label: time,
            isReserved: reservedTimes.includes(time)
        }));
    }
</script>