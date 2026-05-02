<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1><span class="material-symbols-rounded">add_circle</span> Add New Book</h1>
            <nav>
                <a href="/"><span class="material-symbols-rounded">home</span> Home</a>
                <a href="/books"><span class="material-symbols-rounded">auto_stories</span> Books</a>
                <a href="/authors"><span class="material-symbols-rounded">group</span> Authors</a>
                <a href="/books-with-authors"><span class="material-symbols-rounded">link</span> Join View</a>
            </nav>
        </header>

        <main>
            <form:form action="/books/add" method="post" modelAttribute="book" class="form">
                <div class="form-group">
                    <label>Title:</label>
                    <form:input path="title" required="true"/>
                    <form:errors path="title" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label>Genre:</label>
                    <form:input path="genre"/>
                    <form:errors path="genre" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label>Publication Year:</label>
                    <form:input path="publicationYear" type="number" required="true"/>
                    <form:errors path="publicationYear" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label>ISBN:</label>
                    <form:input path="isbn"/>
                    <form:errors path="isbn" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label>Description:</label>
                    <form:textarea path="description" rows="4"/>
                    <form:errors path="description" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label>Author:</label>
                    <form:select path="author.id" required="true">
                        <form:option value="" label="-- Select Author --"/>
                        <form:options items="${authors}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="author" cssClass="error"/>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Save Book</button>
                    <a href="/books" class="btn btn-secondary"><span class="material-symbols-rounded">cancel</span> Cancel</a>
                </div>
            </form:form>
        </main>
    </div>
</body>
</html>
