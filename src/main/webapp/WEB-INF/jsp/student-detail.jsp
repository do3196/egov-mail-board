<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 성적 목록 반복 출력을 위한 JSTL 추가 2026.10.25 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!-- 평균을 소수점 한 자리로 표시하기 위해 추가 2026.10.25 - 추가 -->
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <!-- HTML 문서의 문자 인코딩을 UTF-8로 설정 -->
    <meta charset="UTF-8">
    <!-- 브라우저 탭에 표시되는 제목 -->
    <title>학생 상세정보</title>
</head>
<body>
	<!-- 전체 학생 상세정보 영역 -->
	<div class="container">
	    <!-- 페이지 제목 -->
	    <h2>학생 상세정보</h2>	
	    <!-- 학생 정보를 표 형태로 출력 -->
	    <table border="1">
	        <!-- 학번 출력 -->
	        <tr>
	            <!-- 항목 이름 -->
	            <th>학번</th>
	            <!--Controller에서 전달한 student 객체의 studentNo 값을 출력 -->
	            <td>${student.studentNo}</td>
	        </tr>
	        <!-- 이름 출력 -->
	        <tr>
	            <th>이름</th>
	            <!-- student 객체의 name 값을 출력 -->
	            <td>${student.name}</td>
	        </tr>
	        <!-- 학과 출력 -->
	        <tr>
	            <th>학과</th>
	            <!-- student 객체의 department 값을 출력 -->
	            <td>${student.department}</td>
	        </tr>
	        <!-- 상태 출력 -->
	        <tr>	
	            <th>상태</th>	
	            <!-- student 객체의 status 값을 출력 -->
	            <td>${student.status}</td>
	        </tr>
	    </table>
	    <!-- 성적정보 영역 추가 2026.10.25 -->
	    <h3>성적 정보</h3>
	    <!-- 과목별 성적 출력 테이블 -->
	    <table border="1">	
	        <thead>
	            <tr>
	                <th>과목</th>
	                <th>성적</th>
	            </tr>
	        </thead>
	        <tbody>
	    <!-- Controller에서 전달받은 scores 목록을 한 개씩 반복 -->
			<c:forEach var="score" items="${scores}">
				<tr>
	                <!-- 과목명 -->
					<td>${score.subject}</td>
					<!-- 성적 -->
					<td>${score.score}</td>
				</tr>
			</c:forEach>
			<!-- 성적이 없는 경우 -->
			<c:if test="${empty scores}">
				<tr>
					<td colspan="2">
					등록된 성적 정보가 없습니다.
					</td>
				</tr>
			</c:if>
<!-- 평균 출력 부분 추가 2026.10.25 - 추가 -->
			<c:if test="${not empty scores}">
				<tr>
					<th>평균</th>
					<th>
<!-- Controller에서 전달받은 average 값을 소수점 한 자리로 출력 -->
						<fmt:formatNumber value="${average}" pattern="0.0" />
					</th>
				</tr>
			</c:if>
<!-- 평균 출력 부분 끝 -->
			</tbody>
		</table>
<!-- 성적정보 영역 끝 2026.10.25 - 추가 -->
	    <!-- 학생 목록 화면으로 이동 -->
	    <br>
	    <a href="${pageContext.request.contextPath}/students.do">목록으로</a>	
	</div>
</body>
</html>