<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>학생 관리 시스템 로그인</title>

<style>

* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #f5f7fa;
}

.login-container {
    width: 420px;
    margin: 120px auto;
    padding: 40px;

    background-color: white;
    border-radius: 10px;

    box-shadow:
        0 3px 12px rgba(0,0,0,0.1);
}

h1 {
    text-align: center;
    color: #1f3b5b;
    margin-bottom: 10px;
}

.description {
    text-align: center;
    color: #666;
    margin-bottom: 30px;
}

label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
}

input {
    width: 100%;
    padding: 12px;

    margin-bottom: 20px;

    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    width: 100%;
    padding: 12px;

    border: none;
    border-radius: 5px;

    background-color: #1f3b5b;
    color: white;

    font-size: 16px;
    cursor: pointer;
}

button:hover {
    background-color: #162c44;
}

.error {
    color: red;
    text-align: center;
    margin-bottom: 15px;
}

</style>
</head>


<body>

<div class="login-container">

    <h1>학생 관리 시스템</h1>

    <p class="description">
        이메일과 비밀번호를 입력하세요.
    </p>

    <form action="login.do"
          method="post">

        <label>이메일</label>

        <input
            type="email"
            name="email"
            placeholder="admin@example.com"
            required>


        <label>비밀번호</label>

        <input
            type="password"
            name="password"
            placeholder="비밀번호 입력"
            required>


        <div class="error">
            ${error}
        </div>


        <button type="submit">
            로그인
        </button>

    </form>

</div>

</body>
</html>