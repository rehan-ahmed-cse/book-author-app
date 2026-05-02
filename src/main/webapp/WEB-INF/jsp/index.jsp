<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book & Author Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1><span class="material-symbols-rounded">library_books</span> Book & Author Management System</h1>
            <nav>
                <a href="/" class="active"><span class="material-symbols-rounded">home</span> Home</a>
                <a href="/books"><span class="material-symbols-rounded">auto_stories</span> Books</a>
                <a href="/authors"><span class="material-symbols-rounded">group</span> Authors</a>
                <a href="/books-with-authors"><span class="material-symbols-rounded">link</span> Join View</a>
            </nav>
        </header>

        <main class="dashboard">
            <div class="stats-grid">
                <div class="stat-card">
                    <h3>Total Books</h3>
                    <p class="stat-number">${bookCount}</p>
                    <a href="/books/add" class="btn btn-primary"><span class="material-symbols-rounded">add</span> Add Book</a>
                </div>
                <div class="stat-card">
                    <h3>Total Authors</h3>
                    <p class="stat-number">${authorCount}</p>
                    <a href="/authors/add" class="btn btn-primary"><span class="material-symbols-rounded">add</span> Add Author</a>
                </div>
            </div>

            <div class="quick-links">
                <h2>Quick Actions</h2>
                <div class="button-group">
                    <a href="/books" class="btn btn-secondary"><span class="material-symbols-rounded">visibility</span> View All Books</a>
                    <a href="/authors" class="btn btn-secondary"><span class="material-symbols-rounded">visibility</span> View All Authors</a>
                    <a href="/books-with-authors" class="btn btn-accent"><span class="material-symbols-rounded">account_tree</span> View Join Query Result</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
