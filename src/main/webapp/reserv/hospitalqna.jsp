<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>



<!-- QnA 섹션 -->
<div id="hospital-qna-section">
    <h3>QnA</h3>

    <div id="qna-input-container">
        <input type="text" id="qna-input" placeholder="질문을 작성해주세요">
        <button id="qna-submit-btn">QnA 등록</button>
    </div>

    <div class="qna-header">
        <div>작성자</div>
        <div>제목</div>
        <div>작성일</div>
    </div>

    <div class="qna-list">
        <div id="qna-list"></div>
    </div>
</div>
</html>


<script>
  const hospitalId = ${hospital.h_id};
</script>


<script>
    $(document).ready(function () {
        // QnA 아이템 클릭 시 답변 토글
        $('.qna-item').on('click', function () {
            $(this).find('.qna-answer').slideToggle();
        });
    });
</script>

<script>
    $(document).ready(function () {
        // QnA 등록 버튼 클릭 시
        $('#qna-submit-btn').on('click', function () {
            var qnaInput = $('#qna-input').val();
            console.log(qnaInput);
            console.log(hospitalId);
            if (qnaInput === '') {
                alert('질문을 입력해주세요.');
                return;
            }
            $.ajax({
                url: 'hospitalQuestion',
                type: 'POST',
              contentType: 'application/x-www-form-urlencoded', // URL 인코딩 방식 사용
              data: {
                qnaInput: qnaInput,
                hospitalId: hospitalId
              },
                success: function (data) {
                    alert('QnA가 등록되었습니다.');
                    loadQnAList();
                },
                error: function () {
                    alert('QnA 등록에 실패했습니다.');
                }
            });
        });
    })

</script>

<script>
    $(document).ready(function () {
        loadQnAList();
    });

    function loadQnAList() {
        $.ajax({
            url: 'hospitalQuestion?hospitalId=' + hospitalId,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                $('#qna-list').empty();

                data.forEach(function (qna) {
                    const qnaItem = createQnAItem(qna);

                <%--    const qnaItem = `--%>
                <%--    <div class="qna-item">--%>
                <%--        <div class="qna-content">--%>
                <%--            <div class="qna-writer">${"${qna.user_id}"}</div>--%>
                <%--            <div class="qna-title">${"${qna.q_title}"}</div>--%>
                <%--            <div class="qna-time">${"${qna.q_time}"}</div>--%>
                <%--        </div>--%>
                <%--        <div class="qna-answer" style="display: none;">--%>
                <%--            <p><strong>ㄴ답변:</strong> ${"${qna.a_title || '답변이 없습니다.'}"}</p>--%>
                <%--        </div>--%>
                <%--    </div>--%>
                <%--    <hr>--%>
                <%--`;--%>

                    $('#qna-list').append(qnaItem);

                    console.log("QnA Object:", qna);
                    console.log(JSON.stringify(qna))
                });

                $('.qna-item').on('click', function () {
                    $(this).find('.qna-answer').slideToggle();
                });
            },
            error: function () {
                alert('QnA 목록을 불러오는데 실패했습니다.');
            }
        });
    }


</script>

<script>
    function createQnAItem(qna) {

        return  `
                    <div class="qna-item">
                        <div class="qna-content">
                            <div class="qna-writer">${"${qna.q_writer}"}</div>
                            <div class="qna-title">${"${qna.q_title}"}</div>
                            <div class="qna-time">${"${qna.q_time}"}</div>
                        </div>
                        <div class="qna-answer" style="display: none;">
                            <p><strong>ㄴ답변:</strong> ${"${qna.a_title || '답변이 없습니다.'}"}</p>
                        </div>
                    </div>
                    <hr>
                `;
    }

</script>





