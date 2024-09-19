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
<link href="${pageContext.request.contextPath}/css/my/mybeforereserv.css" rel="stylesheet" type="text/css">

<script>
$(document).ready(function() {
	
    // 이벤트 핸들러 등록
     $('#pet_id').on('change', submitForm);
     $('#isConsult').on('change', submitForm);
     $('#dateRange').on('change', submitForm);
     $('ul#paging li').on('click', submitForm);

    function submitForm() {
    	const isChecked = $('#isConsult').is(':checked'); // 체크박스 상태 가져오기
        console.log('isConsult 체크박스 상태:', isChecked);
        var vIsChecked = 'false';
        if (isChecked) {
            vIsChecked = 'true';
          } else {
            vIsChecked = 'false';
          }
        
        var page = $("ul#paging li").val();
        console.log('page:', page);
        if(page==0) page = 1;
        
 		var param = {pet_id:$("#pet_id").val(), isConsult:vIsChecked, dateRange:$("#dateRange").val(), page:page};
		console.log(param)
        $.ajax({
            url: 'myBeforeReserv',
            method: 'POST',
            async:true,
            data : {param:JSON.stringify(param)},
            success: function(data) {
            	const result = data.result;
            	const iRow = data.iRow;
            	const pageInfo = data.pageInfo;
            	
                const resultBody = $('#resultBody');
                resultBody.empty(); // 기존 결과 삭제

                let cnt = 0;
                let fcnt = 10;
                if (result.length < 10) cnt = result.length;
                for (let i = iRow; cnt < fcnt; i++) {
                    const reserv = result[i];
                    const row = $('<tr></tr>');
                    row.click(function() {
                        window.location.href = `./reservDetail?reserv_id=${reserv.reserv_id}`;
                    });

                    row.html(`<td>\${reserv.reserv_date}</td>`)
                    if (reserv.pet_picture == null) { row.append(`<td><img src="img/pet_default.png" style="width:50px;"></td>`);}
                    else { row.append(`<td><img src="image?file=${reserv.pet_picture}&id=${pet_id}&type=pet" style="width:50px;"></td>`);}
                    row.append(`<td class="textalign_left">\${reserv.pet_name}</td>
                        		<td>\${reserv.h_name}</td>
                        		<td\>${reserv.reserv_status}</td>
                    			`);
                    resultBody.append(row);
                    cnt++;
                }
                 const paging = $('#paging');
                 paging.empty();
                 
                 if (pageInfo.curPage>1) { paging.html(`<li id="page" name="page" value="\${pageInfo.curPage - 1}">&lt;</li>&nbsp;`);}
                 else { paging.html(`<li id="page">&lt;</li>&nbsp;`); }
                 
//                  paging.append(`\${pageInfo.curPage }`);
                 
                 for (var i = pageInfo.startPage; i <= pageInfo.endPage; i++) {
                	 console.log(pageInfo.curPage);
                	 if(i === pageInfo.curPage ){paging.append(`<li id="page" name="page" value="\${i}" class="select">\${i }</li>`);}
                	 else {paging.append(`<li id="page" name="page" value="\${i}" class="btn">\${i }</li>`);}
//                 	 else {paging.append(`<p name="page" value="${i}" class="btn">\${i }</p>`);}
                 }
                 if(pageInfo.curPage < pageInfo.allPage){paging.append(`<li id="page" name="page" value="\${pageInfo.curPage + 1}" class="btn">&gt;</li>`);}
                 else{paging.append(`<li id="page">&gt;</li>`);}
                 
            },
            error: (function(xhr, status, error) {
                console.error('Error:', error);
            })
        });
    }
    
    submitForm();

});
function loadPage(pageNum) {
	let f = document.searchform;
	    
	    let obj;
	    obj = document.createElement('input');
	    obj.setAttribute('type', 'hidden');
	    obj.setAttribute('name', 'page');
	    obj.setAttribute('value', pageNum);
	
		
	    
	    f.appendChild(obj);
	    f.setAttribute('method', 'post');
	    f.setAttribute('action', 'myBeforeReserv');
	    document.body.appendChild(f);
	    f.submit();
}
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
			<input type="checkbox" id="isConsult" name="isConsult"> <label for="isConsult">진료완료만 보기</label>
		</div>
		<div class="petSelect">
			<select id="pet_id" name = "pet_id">
				<option value="" selected>반려동물별</option>
				<c:forEach items="${petList }" var="pet">
					<option value="${pet.pet_id}">${pet.pet_name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="dateSelect">
			<ul>
				<li>
					<button type="button" class="minibtn datebutton">기간 입력</button>
					<ol class="hoverdate">
						<li class="calender">
						<input class="datecalendar" id="dateRange" name="dateRange" type="text">
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
<div>
<ul id="paging">
</ul>
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