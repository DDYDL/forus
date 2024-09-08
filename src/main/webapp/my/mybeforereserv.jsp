<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약내역-지난예약</title>
<!-- calendar -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<!-- table -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="${pageContext.request.contextPath}/css/my/mycommon.css" rel="stylesheet" type="text/css">

<script>
document.addEventListener('DOMContentLoaded', function() {
    // 이벤트 핸들러 등록
    document.getElementById('pet_name').addEventListener('change', submitForm);
    document.getElementById('isConsult').addEventListener('change', submitForm);
    document.getElementById('startDate').addEventListener('change', submitForm);
    document.getElementById('endDate').addEventListener('change', submitForm);

    function submitForm() {
        const form = document.getElementById('searchform');
        const formData = new FormData(form);

        fetch(`/myBeforeReserv?${new URLSearchParams(formData).toString()}`, {
            method: 'GET'
        })
        .then(response => response.json())
        .then(data => {
            const resultBody = document.getElementById('resultBody');
            resultBody.innerHTML = ''; // 기존 결과 삭제

            data.forEach(reserv => {
                const row = document.createElement('tr');
                row.onclick = function() {
                    window.location.href = `./reservDetail?reserv_id=${reserv.reserv_id}`;
                };

                row.innerHTML = `
                    <td>${reserv.reserv_date}</td>
                    <td><img src="img/pet/${reserv.pet_picture}" width="80px"></td>
                    <td class="textalign_left">${reserv.pet_name}</td>
                    <td>${reserv.h_name}</td>
                    <td>${reserv.reserv_status}</td>
                `;
                
                resultBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error:', error));
    }
});
</script>

</head>
<body>
	<%@ include file="../mainmypage.jsp"%>
    <form id="searchform" action="myBeforeReserv" method="get">
	<div class="mypage-content-list">
		<div class="mytitle">
			<a href="${pageContext.request.contextPath}/myAfterReserv">이후 예약</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/myBeforeReserv">지난 예약</a>
		</div>
		<div class="listcnt">
			<input type="checkbox" id="isConsult" name="isConsult"><p>진료완료만 보기</p>
		</div>
		<div class="petSelect">
			<select id="pet_name" name = "pet_name">
				<option value="" selected>반려동물별</option>
				<option value="또치">또치</option>
				<option value="지구">지구</option>
				<option value="후추">후추</option>
			</select>
		</div>
		<div class="choose-date">
			<ul>
				<li>
					<ol class="hoverdate">
						<li class="calender">
						<input class="datecalendar" id="startDate" name="startDate"  type="text"> ~ <input class="datecalendar" id="endDate" name="endDate"  type="text"><button type="submit" class="minibtn">기간 입력</button>
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
			<tbody id="reultBody">
<%-- 			<c:forEach items="${beforeReservList }" var="reserv"> --%>
<!-- 				<tr onclick="location.href='./reservdetail?reserv_id=\${reserv.reserv_id}'"> -->
<%-- 					<td>${reserv.reserv_date }</td> --%>
<%-- 					<td><img src="img/pet/${reserv.pet_picture}" width="80px"></td> --%>
<%-- 					<td class="textalign_left">${reserv.pet_name }</td> --%>
<%-- 					<td>${reserv.h_name }</td> --%>
<%-- 					<td>${reserv.reserv_status }</td> --%>
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
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
    let dateSelector = document.querySelector('.datecalendar');
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