<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생 등록</title>
	<!-- student-form.css 연결 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/student-form.css">
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
            <h2>학생 등록</h2>
            <p>
                새로운 학생 정보를 입력하세요.
            </p>
        </div>
        <!-- 학생 등록 폼 영역 -->
        <div class="form-box">
	        <!-- 오류 메시지 -->
	        <div class="error-message">
	            ${error}
	        </div>
            <form action="${pageContext.request.contextPath}/student-add.do" method="post">
	            <!-- 학번 입력 -->
				<div class="form-group">
				    <label for="studentNo">학번</label>
				    <input type="text" id="studentNo" name="studentNo" value="${studentNo}" placeholder="학번을 입력하세요." required>
				</div>
                <!-- 이름 입력 -->
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" id="name" name="name" placeholder="학생 이름을 입력하세요." required>
                </div>
                <!-- 학과 입력 -->
                <div class="form-group">
                    <label for="department">학과</label>
                    <select id="department" name="department">
                        <option value="멀티미디어학과">멀티미디어학과</option>
                        <option value="컴퓨터학과">컴퓨터학과</option>
                        <option value="정보보호학과">정보보호학과</option>
                    </select>
                </div>
                <!-- 상태 선택 -->
                <div class="form-group">
                    <label for="status">상태</label>
                    <select id="status" name="status">
                        <option value="재학">재학</option>
                        <option value="휴학">휴학</option>
                    </select>
                </div>
                <!-- 버튼 영역 -->
                <div class="form-buttons">
                    <!-- 등록 버튼 -->
                    <button type="submit" class="submit-btn">등록</button>
                    <!-- 취소 버튼 -->
                    <a href="${pageContext.request.contextPath}/students.do" class="cancel-btn">취소</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>