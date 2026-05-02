<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books with Authors - Join View</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1><span class="material-symbols-rounded">account_tree</span> Inner Join Result</h1>
            <nav>
                <a href="/"><span class="material-symbols-rounded">home</span> Home</a>
                <a href="/books"><span class="material-symbols-rounded">auto_stories</span> Books</a>
                <a href="/authors"><span class="material-symbols-rounded">group</span> Authors</a>
                <a href="/books-with-authors" class="active"><span class="material-symbols-rounded">link</span> Join View</a>
            </nav>
        </header>

        <main>
            <div class="info-box">
                <p>This view displays data from a custom JPQL INNER JOIN query between Book and Author entities.</p>
            </div>

            <table class="data-table">
                <thead>
                    <tr>
                        <th>Book ID</th>
                        <th>Book Title</th>
                        <th>Genre</th>
                        <th>Publication Year</th>
                        <th>Author Name</th>
                        <th>Nationality</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${bookAuthorList}">
                        <tr>
                            <td>${item.bookId}</td>
                            <td><strong>${item.bookTitle}</strong></td>
                            <td><span class="badge">${item.genre}</span></td>
                            <td>${item.publicationYear}</td>
                            <td>${item.authorName}</td>
                            <td>${item.nationality}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</body>
</html>
