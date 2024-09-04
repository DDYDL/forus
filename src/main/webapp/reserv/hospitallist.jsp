<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Title</title>
    <link href="css/reserv/hospital.css" rel="stylesheet">
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=16b7470c6f4bec9cf478c43505c8b0ae"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
<%@ include file="../header.jsp" %>

<div id="content-container">

    <div id="filter-container">
        <div id="search-bar-container">
            <input type="text" placeholder="주소로 검색" id="search-input">
            <button id="search-button">
                <img src="img/search.png" alt="검색">
            </button>
        </div>
    </div>

    <div id="map-container">
        <img src="#" alt="Map">
    </div>

    <%--지우면 안됨 ~ 비동기로 처리할때 여기 안에 들어감 --%>
    <div id="hospitals-container">

    </div>
</div>
</body>
</html>

<script>
    function updateHospitalList(lat, lon) {
        $.ajax({
            url: 'hospitalList',
            type: 'GET',
            dataType: 'json',
            data: {
                latitude: lat,
                longitude: lon,
                ajax: 'true'

            },
            success: function (data) {
                if (Array.isArray(data)) {
                    let hospitalListHTML = '';
                    data.forEach(function (hospital) {
                        hospitalListHTML += generateHospitalHTML(hospital);
                    });
                    $('#hospitals-container').html(hospitalListHTML);
                } else {
                    console.error("서버로부터 받은 데이터가 배열이 아닙니다:", data);
                }
            },
            error: function (xhr, status, error) {
                console.error("병원 목록을 불러오는 데 실패했습니다", error);
            }
        });
    }


</script>


<script>
    // 지도를 표시할 div와 지도 옵션 설정
    var container = document.getElementById('map-container'); // 지도를 담을 영역의 DOM 레퍼런스
    var options = { // 지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    // 지도를 생성
    var map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

    var markers = [];


    // HTML5의 Geolocation API를 사용하여 사용자 위치를 가져온다
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function (position) {
                // 사용자의 현재 위치(위도, 경도) 가져오기
                var lat = position.coords.latitude; // 위도
                var lon = position.coords.longitude; // 경도

                // 새로운 중심 위치 설정
                var locPosition = new kakao.maps.LatLng(lat, lon);

                // 지도 중심을 사용자의 현재 위치로 이동
                map.setCenter(locPosition);

                // 사용자의 현재 위치에 마커를 생성
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: locPosition
                });
                updateHospitalList(lat, lon);

            },
            function (error) {
                // 위치 가져오기 실패 시
                alert('위치를 가져올 수 없습니다: ' + error.message);
                // 기본 위치로 이동
                map.setCenter(new kakao.maps.LatLng(33.450701, 126.570667));
            },
            {
                enableHighAccuracy: true, // 높은 정확도 사용 시도
                timeout: 5000, // 5초 후 타임아웃
                maximumAge: 0 // 캐시된 위치 사용 안 함
            }
        );
    } else {
        alert('이 브라우저에서는 위치 정보가 지원되지 않습니다.');
    }
</script>


<script>
    function generateHospitalHTML(hospital) {
        return `
        <div class="hospital-item">
            <a href="hospitalDetail.jsp?hospitalId=${"${hospital.h_id}"}" class="hospital-link">
                <img src="img/hospital/kosta3.png" alt="${"${hospital.h_name}"}">
                <div class="hospital-info">
                    <strong>${"${hospital.h_name}"}</strong>
                    <p>${"${hospital.h_address}"}<br>진료동물: ${"${hospital.h_animal_type}"}</p>
                </div>
            </a>
        </div>`;
    }


</script>