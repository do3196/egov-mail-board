<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생 정보 수정</title>
    <!-- 학생 수정 화면 전용 CSS 연결 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/student-edit.css">
</head>
<body>
	<!-- 상단 제목 영역 -->
	<header>
	    <h1>Student Management System</h1>
	</header>
	<!-- 전체 내용 영역 -->
	<div class="container">
	    <!-- 화면 제목 -->
	    <div class="title-area">
	        <h2>학생 정보 수정</h2>
	        <p>학생의 이름, 학과, 상태를 수정할 수 있습니다.</p>
	    </div>
	    <!-- 학생 수정 폼 -->
	    <div class="form-box">
	        <!-- 수정된 학생 정보를 student-update.do로 전송
	            개인정보 변경이므로 POST 방식 사용 -->
	        <form action="${pageContext.request.contextPath}/student-update.do" method="post">
	            <!-- 학번 -->
	            <div class="form-group">
	                <label for="studentNo">학번</label>
	                <!-- 학번은 학생을 구분하는 기준값이므로 수정하지 못하도록 readonly 사용 -->
	                <input type="text" id="studentNo" name="studentNo" value="${student.studentNo}" readonly>
	            </div>
	            <!-- 이름 -->
	            <div class="form-group">
	                <label for="name">이름</label>
	                <!-- 기존 학생 이름을 value에 표시 사용자가 수정 가능 -->
	                <input type="text" id="name" name="name" value="${student.name}" required>
	            </div>
	            <!-- 학과 -->
	            <div class="form-group">
	                <label for="department">학과</label>
	                <!-- 기존 학과 정보를 표시하고 사용자가 수정 가능 -->
	                <input type="text" id="department" name="department" value="${student.department}" required>
	            </div>
	            <!-- 상태 -->
	            <div class="form-group">
	                <label for="status">상태</label>
	                <select id="status" name="status">
	                    <!-- 기존 상태가 재학이면 selected 적용 -->
	                    <option value="재학" 
	                        <c:if test="${student.status == '재학'}">
	                            selected
	                        </c:if>>
	                        재학
	                    </option>
	                    <!-- 기존 상태가 휴학이면 selected 적용 -->
	                    <option value="휴학"
	                        <c:if test="${student.status == '휴학'}">
	                            selected
	                        </c:if>>
	                        휴학
	                    </option>
	                </select>
	            </div>
	            <!-- 버튼 영역 -->
	            <div class="form-buttons">
	                <!-- 수정 버튼 -->
	                <button type="submit" class="submit-btn">수정</button>
	                <!-- 취소 버튼 -->
	                <a href="${pageContext.request.contextPath}/students.do"
	                    class="cancel-btn">취소</a>
	            </div>
	        </form>
	    </div>
	</div>
</body>
</html>