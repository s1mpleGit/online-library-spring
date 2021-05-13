<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Admin page</title>
    <link rel="icon" href="images/favicon.ico" type="image/ico">
    <style>
        body {
            background-image: url(images/background.jpg);
            background-attachment: fixed;
            background-repeat: no-repeat;
        }

        .btn {
            display: inline-block;
            float: right;
        }

        .text {
            text-align: center;
            width: auto;
            height: auto;
            background: rgba(255, 255, 255, 0.2);
        }

        .fld {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        .msg {
            color: red;
        }

        .msg2 {
            color: forestgreen;
        }
    </style>
</head>
<header>
    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
        <div class="btn">
            <form action="<c:url value="/logout" />" method="post">
                <input type="submit" value="Logout"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/user/info" />" method="get">
                <input type="submit" value="Account"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/book/all" />" method="get">
                <input type="submit" value="Show books"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/card/all" />" method="get">
                <input type="submit" value="Show cards"/>
            </form>
        </div>
        <div class="btn">
            <form action="<c:url value="/user/block" />" method="get">
                <input type="submit" value="Show blocked"/>
            </form>
        </div>
    </c:if>
</header>
<body>
<br>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN' && empty book}">
    <h1 class="text">Hello, ${sessionScope.user.name}! Manage online library wisely</h1>
</c:if>
<br>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN' && empty book}">
    <div class="fld">
        <form action="<c:url value="/book/createAuthor" />" method="post">
            <div class="text">
                <h1>Create new author</h1>
            </div>
            <div class="msg2">
                <c:if test="${msg==true}">
                    <p>Author successfully created</p>
                </c:if>
            </div>
            <div class="msg">
                <c:if test="${msg==false}">
                    <p>Author already exist</p>
                </c:if>
            </div>
            <label>
                <input name="author" type="text" minlength="1" maxlength="20" required
                       placeholder="Enter author's name"/>
            </label>
            <input class="text" type="submit" value="Create author"/>
        </form>
    </div>
</c:if>
<c:if test="${sessionScope.user.role.toString() == 'ADMIN' && !empty book}">
    <div class="fld">
        <form action="<c:url value="/book/update" />" method="post" modelAttribute="newBook">
            <div class="text">
                <h1>Edit book info</h1>
            </div>
            <input name="id" type="hidden" value="${book.id}">
            <label>
                Current title: ${book.title} <br>
                New title<br>
                <input name="title" type="text" minlength="1" maxlength="20" required
                       placeholder="Enter book title"/>
            </label> <br>
            <label>
                New author<br>
                <select name="authorId">
                    <c:forEach items="${authors}" var="author">
                        <option value="${author.id}">${author.name}</option>
                    </c:forEach>
                </select>
            </label> <br>
            <label for="description">
                <textarea id="description" name="description" rows="5" cols="40"
                          required>${book.description}</textarea>
            </label> <br>
            <label>
                Current image: <br> <img src="${book.imageUri}" alt="no image"> <br>
                New image <br>
                <input name="imageUri" type="text" required placeholder="Enter link to image"/>
            </label> <br>
            <input class="text" type="submit" value="Update book"/>
        </form>
    </div>
</c:if>
</body>
</html>
