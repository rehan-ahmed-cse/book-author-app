<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Books</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1><span class="material-symbols-rounded">auto_stories</span> Books Collection</h1>
            <nav>
                <a href="/"><span class="material-symbols-rounded">home</span> Home</a>
                <a href="/books" class="active"><span class="material-symbols-rounded">auto_stories</span> Books</a>
                <a href="/authors"><span class="material-symbols-rounded">group</span> Authors</a>
                <a href="/books-with-authors"><span class="material-symbols-rounded">link</span> Join View</a>
            </nav>
        </header>

        <main>
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">${errorMessage}</div>
            </c:if>

            <div class="actions-bar">
                <a href="/books/add" class="btn btn-primary"><span class="material-symbols-rounded">add</span> Add New Book</a>
            </div>

            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Genre</th>
                        <th>Year</th>
                        <th>ISBN</th>
                        <th>Author</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td><span class="badge">${book.genre}</span></td>
                            <td>${book.publicationYear}</td>
                            <td>${book.isbn}</td>
                            <td>${book.author.name}</td>
                            <td>
                                <a href="/books/edit/${book.id}" class="btn btn-small btn-edit"><span class="material-symbols-rounded">edit</span> Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</body>
</html>
