<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>예약상세정보</title>
  <style>
/*     body { */
/*       font-family: Arial, sans-serif; */
/*       margin: 40px; */
/*     } */
/*     #reservationDetail { */
/*       width: 300px; */
/*       margin: auto; */
/*     } */
/*     #reservationDetail .pet-selection { */
/*       list-style-type: none; */
/*       padding: 0; */
/*     } */
/*     #reservationDetail .pet-selection li { */
/*       display: flex; */
/*       align-items: center; */
/*       margin-bottom: 10px; */
/*     } */
/*     #reservationDetail .pet-selection img { */
/*       width: 40px; */
/*       height: 40px; */
/*       border-radius: 50%; */
/*       margin-right: 10px; */
/*     } */
/*     #reservationDetail .reserve-item, */
/*     #reservationDetail .reserve-date, */
/*     #reservationDetail .guardian-info { */
/*       margin-bottom: 20px; */
/*     } */
/*     #reservationDetail input[type="text"], */
/*     #reservationDetail input[type="date"], */
/*     #reservationDetail input[type="time"] { */
/*       width: 100%; */
/*       padding: 5px; */
/*       margin-top: 5px; */
/*       box-sizing: border-box; */
/*       background-color: #f0f0f0; */
/*       border: 1px solid #ccc; */
/*       cursor: not-allowed; */
/*     } */
/*     #reservationDetail input[type="checkbox"] { */
/*       margin-right: 5px; */
/*       cursor: not-allowed; */
/*     } */

  </style>
</head>
<body>

<div id="reservationDetail" class="mypage-content">
  <div class="pet-selection">
    <h3>펫 선택</h3>
    <ul>
      <li><input type="radio" name="pet" checked disabled> <img src="https://via.placeholder.com/40" alt="또치"> 또치</li>
      <li><input type="radio" name="pet" disabled> <img src="https://via.placeholder.com/40" alt="두치"> 두치</li>
      <li><input type="radio" name="pet" disabled> <img src="https://via.placeholder.com/40" alt="한치"> 한치</li>
    </ul>
  </div>

  <div class="reserve-item">
    <h3>예약 항목</h3>
    <input type="checkbox" checked disabled> 진료
    <input type="checkbox" disabled> 상담
    <input type="checkbox" disabled> 미용
    <input type="checkbox" disabled> 기타
    <input type="text" value="기타 입력된 내용" readonly>
  </div>

  <div class="reserve-date">
    <h3>예약 날짜</h3>
    <p>병원이름: 도마뱀 병원</p>
    <label for="date">예약 날짜</label>
    <input type="date" id="date" value="2024-08-08" readonly>
    <label for="time">예약 시간</label>
    <input type="time" id="time" value="09:00" readonly>
  </div>

  <div class="guardian-info">
    <h3>보호자 정보</h3>
    <label for="name">이름</label>
    <input type="text" id="name" value="르브론" readonly>
    <label for="phone">휴대폰</label>
    <input type="text" id="phone" value="010-1234-5678" readonly>
    <label for="email">이메일</label>
    <input type="text" id="email" value="gohome@kosta.org" readonly>
  </div>
</div>

</body>
</html>