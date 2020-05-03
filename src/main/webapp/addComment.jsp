<%@ page import="com.sdacademy.twitter.utils.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Add Comment</title>
</head>
<body>
<% Utils.activateSession(request); %>
<c:if test="${!sessionOk}">
    <h3 style="color: red">Aby dodać komentarz należy się zalogować do systemu!</h3>
</c:if>
<c:if test="${sessionOk}">
    <form method="post" action="addComment">
        <textarea name="message"></textarea>
        <input type="submit" name="submit" value="Add Comment" />
    </form>
</c:if>
</body>
</html>
