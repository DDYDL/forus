<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약내역-지난예약</title>
<!-- calendar -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<!-- table -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="../css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp"%>
	<div class="mypage-content-list">
		<div class="mytitle">
			<a href="myafterreserv.jsp">이후 예약</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="mybeforereserv.jsp">지난 예약</a>
		</div>
		<div class="listcnt">
			<p>총 19건</p>
		</div>
		<div class="choose-mypet">
			<select>
				<option selected>반려동물별</option>
				<option>또치</option>
				<option>지구</option>
				<option>후추</option>
			</select>
		</div>
		<div class="choose-date">
			<ul>
				<li>
					<button type="submit" class="minibtn">기간 입력</button>
					<ol class="hoverdate">
						<li><a href="#">기간 설정</a></li>
						<li class="calender"><input id="datecalendar" type="text">
							~ <input class="calender" id="datecalendar" type="text"></li>
					</ol>
				</li>
			</ul>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th id="date">날짜</th>
					<th id="petname" colspan="2">반려동물</th>
					<th id="hospitalname">병원</th>
					<th id="status">상태</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>2024.8.19</td>
					<td><img src="../img/dog1.png"></td>
					<td>또치</td>
					<td>올리브동물병원</td>
					<td>예약취소</td>
				</tr>
				<tr>
					<td>2024.8.19</td>
					<td><img src="../img/dog2.png"></td>
					<td>한치</td>
					<td>올리브동물병원</td>
					<td>진료완료</td>
				</tr>
				<tr>
					<td>2024.8.19</td>
					<td><img src="../img/dog3.png"></td>
					<td>두치</td>
					<td>올리브동물병원</td>
					<td>진료완료</td>
				</tr>
				<tr>
					<td>2024.8.19</td>
					<td><img src="../img/dog1.png"></td>
					<td>또치</td>
					<td>올리브동물병원</td>
					<td>진료완료</td>
				</tr>
				<tr>
					<td>2024.8.19</td>
					<td><img src="../img/dog1.png"></td>
					<td>또치</td>
					<td>올리브동물병원</td>
					<td>진료완료</td>
				</tr>
				<tr>
					<td>2024.8.19</td>
					<td><img src="../img/dog1.png"></td>
					<td>또치</td>
					<td>올리브동물병원</td>
					<td>진료완료</td>
				</tr>
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