<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" style="overflow: hidden;">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <div class="col-xs-6">
        <h2>Add new book</h2>
        <form action="/books" method="post">
            <div class="form-group">
                <label for="name">Book name:</label>
                <input type="text" name="name" class="form-control" id="name" placeholder="ex. Steve Jobs">
            </div>
            <div class="form-group">
                <label for="message">Message:</label>
                <textarea name="message" class="form-control" id="message" rows="2" placeholder="ex. Hardcover – October 24, 2011"></textarea>
            </div>
            <div class="form-group">
                <label for="rating">Rating:</label>
                <select class="form-control" name="rating" id="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

    <div class="col-xs-6">
        <h2>All guest books</h2>
        <table class="table">
            <thead>
            <tr>
                <th>Created At</th>
                <th>Name</th>
                <th>Message</th>
                <th>Rating</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty books}">
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book["createdAt"]}</td>
                        <td>${book["name"]}</td>
                        <td>${book["message"]}</td>
                        <td>${book["rating"]}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty books}">
                <tr>
                    <td colspan="3" class="text-center">No record found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>