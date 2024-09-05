<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="css/reserv/hospital.css" rel="stylesheet">
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=94ab9074f87bbed0edf5b0d9cb32cdbd&libraries=services"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<%@ include file="../header.jsp" %>

<div id="content-container">
    <div id="filter-container">
        <div id="search-bar-container">
            <input type="text" placeholder="주소로 검색" id="search-input">

            <div id="suggestions" class="suggestions-container">
                <%-- 검색 자동완성 제안 목록이 여기에 추가됨 --%>
            </div>
            <button id="search-button">
                <img src="img/search.png" alt="검색">
            </button>
        </div>
    </div>

    <div id="map-container">
        <img src="#" alt="Map">
    </div>

    <div id="hospitals-container">
        <%-- 비동기로 받아온 병원 목록이 여기에 추가됨 --%>
    </div>
</div>
</body>
</html>

<script>
    // Kakao Places 객체 생성
    var ps = new kakao.maps.services.Places();

    // 검색 입력 필드에 이벤트 리스너 추가
    $('#search-input').on('input', function () {
        var query = $(this).val();
        if (query.trim() !== '') {
            ps.keywordSearch(query, placesSearchCB);
        } else {
            $('#suggestions').hide();
        }
    });

    // 장소 검색 콜백 함수
    function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
            var suggestions = generateSuggestionsHTML(data);
            $('#suggestions').html(suggestions).show(); // 리스트 보여줌
        } else {
            $('#suggestions').hide();
        }
    }

    // 제안 항목 클릭 이벤트 추가
    $(document).on('click', '.suggestion-item', function () {
        var placeName = $(this).data('place-name');
        var addressName = $(this).data('address-name');
        $('#search-input').val(placeName); // 선택한 이름을 검색창에 입력
        $('#suggestions').hide();


        <%--console.log(`선택된 장소: ${placeName}, 주소: ${addressName}`);--%>
    });
</script>

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
                        addHospitalMarker(hospital);
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

    // 지도에 병원 마커 추가 함수
    function addHospitalMarker(hospital) {
        var position = new kakao.maps.LatLng(hospital.h_latitude, hospital.h_longitude);
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
        var imageSize = new kakao.maps.Size(24, 35);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커 생성
        var marker = new kakao.maps.Marker({
            map: map,
            position: position,
            image: markerImage
        });

        //  (마커에 병원 이름 표시)
        var infoWindowContent = `<div style="padding:5px; font-size:12px;">${"${hospital.h_name}"}</div>`;

        // 인포윈도우 생성
        var infowindow = new kakao.maps.InfoWindow({
            content: infoWindowContent
        });

        kakao.maps.event.addListener(marker, 'mouseover', function () {
            infowindow.open(map, marker); // 마커 위에 인포윈도우 표시
        });

        kakao.maps.event.addListener(marker, 'mouseout', function () {
            infowindow.close();
        });
    }

</script>

<script>
    // 지도 생성
    var container = document.getElementById('map-container');
    var options = { // 지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 6 // 지도의 확대 레벨
    };
    // 지도를 생성
    var map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

    var markers = [];

    // HTML5의 Geolocation API를 사용하여 사용자 위치를 가져오기
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
                timeout: 5000,
                maximumAge: 0
            }
        );
    } else {
        alert('이 브라우저에서는 위치 정보가 지원되지 않습니다.');
    }
</script>


<script>
    // 검색 자동완성 제안 목록 HTML 생성 함수
    function generateSuggestionsHTML(data) {
        return data
            .map(
                (place) =>
                    `<div class="suggestion-item" data-place-name="${"${place.place_name}"}" data-address-name="${"${place.address_name}"}">${"${place.place_name}"} (${"${place.address_name}"})</div>`
            )
            .join('');
    }
    // 병원 목록 HTML 생성 함수
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