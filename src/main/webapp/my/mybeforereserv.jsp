<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약내역-지난예약</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- calendar -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
	<!-- table -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="${pageContext.request.contextPath}/css/my/mycommon.css" rel="stylesheet" type="text/css">

<script>
$(document).ready(function() {
	const isConsultChecked = $('#isConsult').is(':checked');
    const isConsultValue = isConsultChecked ? 'on' : 'off';
    // 이벤트 핸들러 등록
     $('#pet_id').on('change', submitForm);
     $('#isConsult').on('change', submitForm);
     $('#startDate').on('change', submitForm);
     $('#endDate').on('change', submitForm);


    function submitForm() {
 		let param = {pet_id:$("#pet_id").val(),      isConsult: isConsultValue, startDate:$("#startDate").val(), endDate:$("#endDate").val()};
		console.log(param)
        $.ajax({
            url: 'myBeforeReserv',
            method: 'POST',
            dataType : "json",
            contentType:"application/json",
            data : param,
            success: function(data) {
                console.log(data);
                const resultBody = $('#resultBody');
                resultBody.empty(); // 기존 결과 삭제

                data.forEach(function(reserv) {
                    console.log("도착2");
                    const row = $('<tr></tr>');
                    row.click(function() {
                        window.location.href = `./reservDetail?reserv_id=${reserv.reserv_id}`;
                    });

                    row.html(`
                        <td>\${reserv.reserv_date}</td>
                        <td><img src="image?file=${reserv.pet_picture}" width="80px"></td>
                        <td class="textalign_left">\${reserv.pet_name}</td>
                        <td>\${reserv.h_name}</td>
                        <td>\${reserv.reserv_status}</td>
                    `);
                    resultBody.append(row);
                });
            },
            error: (function(xhr, status, error) {
                console.error('Error:', error);
            })
        });
    }
    
    submitForm();

});
</script>

</head>
<body>
	<%@ include file="../mainmypage.jsp"%>
    <form id="searchform" name="searchform" action="myBeforeReserv" method="get">
	<div class="mypage-content-list">
		<div class="mytitle">
			<a href="${pageContext.request.contextPath}/myAfterReserv">이후 예약</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/myBeforeReserv">지난 예약</a>
		</div>
		<div class="listcnt">
			<input type="checkbox" id="isConsult" name="isConsult" value="on"> <label for="isConsult">진료완료만 보기</label>
		</div>
		<div class="petSelect">
			<select id="pet_id" name = "pet_id">
				<option value="" selected>반려동물별</option>
				<c:forEach items="${petList }" var="pet">
					<option value="${pet.pet_id}">${pet.pet_name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="choose-date">
			<ul>
				<li>
					<ol class="hoverdate">
						<li class="calender">
						<input class="datecalendar" id="startDate" name="startDate" type="text"> ~ <input class="datecalendar" id="endDate" name="endDate"  type="text"><button type="button" class="minibtn">기간 입력</button>
						</li> 
					</ol>
				</li>
			</ul>
		</div>

		<table class="table table-hover mytable">
			<thead>
				<tr>
					<th id="reserv_date">날짜</th>
					<th id="pet_name" colspan="2">반려동물</th>
					<th id="h_name">병원</th>
					<th id="reserv_status">상태</th>
				</tr>
			</thead>
			<tbody id="resultBody">

			</tbody>
		</table>
		<br>
		<div id="paging">
			<a href="#">&lt;</a> &nbsp;<a href="#">1</a>&nbsp;<a href="#">2</a>&nbsp;<a
				href="#">3</a>&nbsp;<a href="#">4</a>&nbsp; <a href="#">&gt;</a>
		</div>
		<br>
		<br>
	</div>
	</form>
<script>
  document.addEventListener('DOMContentLoaded', function () {
    let datecalendar = document.querySelector('.datecalendar');
    flatpickr(datecalendar, {
      mode: 'range',
      dateFormat: 'Y-m-d',
      locale: 'ko',
      inline: true
    });
  });
</script>
</body>
</html>