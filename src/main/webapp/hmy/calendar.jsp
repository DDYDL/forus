<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>동물 예약 시스템</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- FullCalendar CSS -->
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet'/>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>

    <!-- FullCalendar 언어 지원 -->
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>


    <link rel="stylesheet" href="css/hmy/doctorCalendar.css">


</head>

<body>
<%@ include file="../header.jsp" %>
<%@ include file="../hmymenu.jsp" %>
<c:set var="sessionUser" value="${sessionScope.user}" />

<section>
    <div>
        <button id="reservationTimeButton">예약 시간 관리</button>
    </div>
</section>


<section>
    <div id="calendar"></div>
</section>


<!-- 예약 상세 정보 사이드바 -->
<div id="overlay" onclick="closeSidebar()"></div>
<div id="sidebar">
    <h3>예약 상세 정보</h3>
    <p id="eventContent">내용이 여기에 표시됩니다.</p>
    <p><strong>예약자 이름:</strong> <span id="reserverName"></span></p>
    <p><strong>동물:</strong> <span id="animalType"></span></p>
    <p><strong>종:</strong> <span id="animalBreed"></span></p>
    <p><strong>예약 항목:</strong> <span id="reservationItem"></span></p>

    <textarea id="vetNote" placeholder="메모를 입력하세요..."></textarea>
    <button id="saveNoteButton" onclick="saveNote()">메모 저장</button>
    <button id="closeSidebarButton" onclick="closeSidebar()">닫기</button>
</div>


<script>
    var userId = ${sessionUser.id};

    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            locale: 'ko',
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridDay'
            },
            dayCellContent: function (arg) {
                return {html: arg.date.getDate()}; // 날짜 숫자만 출력
            },

            events: function (fetchInfo, successCallback, failureCallback) {
                $.ajax({
                    url: 'doctorCalendarList',
                    type: 'get',
                    dataType: 'json',
                    data: {
                        startDate: fetchInfo.startStr,
                        endDate: fetchInfo.endStr,
                        userId: userId
                    },
                    success: function (data) {
                        console.log(data);


                        var events = data.map(function (reservation) {
                            var startDateTime = formatDateTime(reservation.reservDate, reservation.reservTime);

                            return {
                                title: '예약: ' + reservation.reservId,
                                start: startDateTime,
                                end: startDateTime,
                                extendedProps: {
                                    reserverName: reservation.userId,
                                    animalType: '동물 유형',
                                    animalBreed: '동물 종',
                                    reservationItem: reservation.reservContent
                                }

                            }

                        });
                        successCallback(events);
                    },
                    error: function () {
                        failureCallback();
                    }
                })

            },
            views: {
                timeGridDay: {
                    slotDuration: '00:30:00',
                    slotLabelInterval: '00:30',
                    slotLabelFormat: {
                        hour: '2-digit',
                        minute: '2-digit',
                        meridiem: false,
                        hour12: false
                    }
                }
            },
            dateClick: function (info) {
                // 날짜 클릭 시 일별 보기로 전환~ 하는거
                calendar.changeView('timeGridDay', info.dateStr);
            },
            eventClick: function (info) {
                // 예약 클릭 시 사이드바 열기~하는거~
                document.getElementById('eventContent').innerText =
                    '예약 제목: ' + info.event.title + '\n' +
                    '시작 시간: ' + info.event.start.toLocaleString() + '\n' +
                    '종료 시간: ' + (info.event.end ? info.event.end.toLocaleString() : 'N/A');

                document.getElementById('reserverName').innerText = info.event.extendedProps.reserverName;
                document.getElementById('animalType').innerText = info.event.extendedProps.animalType;
                document.getElementById('animalBreed').innerText = info.event.extendedProps.animalBreed;
                document.getElementById('reservationItem').innerText = info.event.extendedProps.reservationItem;

                openSidebar();
            },
        });
        calendar.render();
    });

    function openSidebar() {
        document.getElementById('sidebar').style.display = 'block';
        document.getElementById('overlay').style.display = 'block';
    }

    function closeSidebar() {
        document.getElementById('sidebar').style.display = 'none';
        document.getElementById('overlay').style.display = 'none';
    }

</script>

<script>
    // 날짜와 시간 객체를 받아서 YYYY-MM-DDTHH:mm:ss 형식의 문자열로 변환하는 함수
    function formatDateTime(reservDate, reservTime) {
        return `${"${reservDate.year}"}-${"${reservDate.month.toString().padStart(2, '0')}"}-${"${reservDate.day.toString().padStart(2, '0')}"}T${"${reservTime.hour.toString().padStart(2, '0')}"}:${"${reservTime.minute.toString().padStart(2, '0')}"}:${"${reservTime.second.toString().padStart(2, '0')}"}`
    }


</script>
</body>
</html>