<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생 관리 시스템</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/student.css">
</head>
<body>
	<header>
	    <h1>Student Management System</h1>
	</header>
	<div class="container">
	    <div class="title-area">
	        <h2>학생 관리</h2>
	        <p>
	            등록된 학생 정보를 조회할 수 있습니다.
	        </p>
	    </div>
        <!-- 학생 검색 기능 추가 -->
        <div class="search-area">
            <form action="${pageContext.request.contextPath}/student-search.do" method="get">
                <!-- 검색어 입력 -->
                <input type="text" id="keyword" name="keyword" value="${keyword}" placeholder="학번 또는 이름을 입력하세요.">
                <!-- 검색 버튼 -->
                <button type="submit" class="search-btn">검색</button>
                <!-- 전체 학생 목록으로 돌아가기 -->
                <a href="${pageContext.request.contextPath}/students.do" class="all-btn">전체보기
                </a>
            </form>
        </div>
        <!-- 검색 기능 추가 끝 -->
	    <div class="toolbar">
	        <strong>
	            전체 학생 ${studentCount}명
	        </strong>
	        <a href="${pageContext.request.contextPath}/student-form.do" class="btn">
		        + 학생 등록
		    </a>
	    </div>
	    <div class="table-box">
	        <table>
	            <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>학번</th>
	                    <th>이름</th>
	                    <th>학과</th>
	                    <th>상태</th>
	                    <th>관라</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="student" items="${students}">
	                    <tr>
	                        <td>
	                            ${student.id}
	                        </td>
	                        <td>
    							<a href="${pageContext.request.contextPath}/student-detail.do?studentNo=${student.studentNo}">
				        			${student.studentNo}
								</a>
	                        </td>
	                        <td>
	                            ${student.name}
	                        </td>
	                        <td>
	                            ${student.department}
	                        </td>
	                        <td>
	                            <span class="status">
	                                ${student.status}
	                            </span>
	                        </td>
	                        <td>
						        <!-- 상세보기 -->
						        <a href="${pageContext.request.contextPath}/student-detail.do?studentNo=${student.studentNo}" class="detail-btn">
						          상세
						        </a>
						        <!-- 학생 수정 -->
						        <a href="${pageContext.request.contextPath}/student-edit.do?studentNo=${student.studentNo}" class="edit-btn">
						          수정
						        </a>
						        <!-- 학생 삭제 -->
						        <a href="${pageContext.request.contextPath}/student-delete.do?studentNo=${student.studentNo}" class="delete-btn" onclick="return confirm('정말 삭제하시겠습니까?');">
						          삭제
						        </a>
						    </td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
</body>
</html>