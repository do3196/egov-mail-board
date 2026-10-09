<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	    <!-- 학생 목록 화면으로 이동 -->
	    <br>
	    <a href="${pageContext.request.contextPath}/students.do">목록으로</a>	
	</div>
</body>
</html>