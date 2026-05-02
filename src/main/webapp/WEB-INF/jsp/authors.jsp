<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Authors</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1><span class="material-symbols-rounded">group</span> Authors</h1>
            <nav>
                <a href="/"><span class="material-symbols-rounded">home</span> Home</a>
                <a href="/books"><span class="material-symbols-rounded">auto_stories</span> Books</a>
                <a href="/authors" class="active"><span class="material-symbols-rounded">group</span> Authors</a>
                <a href="/books-with-authors"><span class="material-symbols-rounded">link</span> Join View</a>
            </nav>
        </header>

        <main>
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>

            <div class="actions-bar">
                <a href="/authors/add" class="btn btn-primary"><span class="material-symbols-rounded">add</span> Add New Author</a>
            </div>

            <div class="card-grid">
                <c:forEach var="author" items="${authors}">
                    <div class="card">
                        <h3>${author.name}</h3>
                        <p class="meta">${author.nationality}</p>
                        <p class="description">${author.biography}</p>
                        <p class="book-count">Books: ${author.books.size()}</p>
                        <a href="/authors/edit/${author.id}" class="btn btn-small btn-edit"><span class="material-symbols-rounded">edit</span> Edit</a>
                    </div>
                </c:forEach>
            </div>
        </main>
    </div>
</body>
</html>
