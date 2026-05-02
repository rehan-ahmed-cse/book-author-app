<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Author</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1><span class="material-symbols-rounded">person_add</span> Add New Author</h1>
            <nav>
                <a href="/"><span class="material-symbols-rounded">home</span> Home</a>
                <a href="/books"><span class="material-symbols-rounded">auto_stories</span> Books</a>
                <a href="/authors"><span class="material-symbols-rounded">group</span> Authors</a>
                <a href="/books-with-authors"><span class="material-symbols-rounded">link</span> Join View</a>
            </nav>
        </header>

        <main>
            <form:form action="/authors/add" method="post" modelAttribute="author" class="form">
                <div class="form-group">
                    <label>Name:</label>
                    <form:input path="name" required="true"/>
                    <form:errors path="name" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label>Nationality:</label>
                    <form:input path="nationality"/>
                    <form:errors path="nationality" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label>Biography:</label>
                    <form:textarea path="biography" rows="5"/>
                    <form:errors path="biography" cssClass="error"/>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Save Author</button>
                    <a href="/authors" class="btn btn-secondary"><span class="material-symbols-rounded">cancel</span> Cancel</a>
                </div>
            </form:form>
        </main>
    </div>
</body>
</html>
