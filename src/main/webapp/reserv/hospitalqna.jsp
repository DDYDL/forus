<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<%
  // 임시 데이터 설정
  List<Map<String, String>> qnaList = new ArrayList<>();
  Map<String, String> qna1 = new HashMap<>();
  qna1.put("q_title", "펭귄도 진료 가능?");
  qna1.put("q_time", "2024/08/08");
  qna1.put("q_writer", "user1");
  qna1.put("a_title", "가능합니다. 조류 전문 병원입니다.");
  qnaList.add(qna1);

  Map<String, String> qna2 = new HashMap<>();
  qna2.put("q_title", "진료 예약은 어떻게 하나요?");
  qna2.put("q_time", "2024/08/10");
  qna2.put("q_writer", "user2");
  qna2.put("a_title", "전화로 예약 가능합니다.");
  qnaList.add(qna2);

  request.setAttribute("qnaList", qnaList);
%>

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
    <c:forEach var="qna" items="${qnaList}">
      <div class="qna-item">
        <div class="qna-content">
          <div class="qna-writer">${qna.q_writer}</div>
          <div class="qna-title">${qna.q_title}</div>
          <div class="qna-time">${qna.q_time}</div>
        </div>
        <div class="qna-answer" style="display: none;">
          <p><strong>ㄴ답변:</strong> ${qna.a_title}</p>
        </div>
      </div>
      <hr>
    </c:forEach>
  </div>
</div>



<script>
  $(document).ready(function() {
    // QnA 아이템 클릭 시 답변 토글
    $('.qna-item').on('click', function() {
      $(this).find('.qna-answer').slideToggle();
    });
  });
</script>

</html>