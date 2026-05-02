<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header><h1><span class="material-symbols-rounded">error</span> Error</h1></header>
        <main>
            <div class="alert alert-error">
                <p>${errorMessage}</p>
            </div>
            <a href="/" class="btn btn-secondary"><span class="material-symbols-rounded">home</span> Go Home</a>
        </main>
    </div>
</body>
</html>
