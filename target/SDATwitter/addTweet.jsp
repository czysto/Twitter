<%@ page import="com.sdacademy.twitter.utils.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Add Tweet</title>
</head>
<body>
<% Utils.activateSession(request); %>
<c:if test="${!sessionOk}">
    <h3 style="color: red">Aby publikować tweety należy się zalogować do systemu!</h3>
</c:if>
<c:if test="${sessionOk}">
    <form method="post" action="addTweet">
        <textarea name="message"></textarea>
        <input type="submit" name="submit" value="Add Tweet" />
    </form>
</c:if>
</body>
</html>
