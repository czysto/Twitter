<%@ page import="com.sdacademy.twitter.utils.Utils" %>
<%@ page import="com.sdacademy.twitter.dao.TweetDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <title>Edit tweet</title>
</head>
<body>
<%
    Utils.activateSession(request);
    TweetDao tweetDao = TweetDao.getInstance();
    Long id = Long.valueOf(request.getParameter("id"));
    request.setAttribute("tweet", tweetDao.get(id).get());
%>
<c:if test="${!sessionOk}">
    <h3 style="color: red">Aby edytować tweety należy się zalogować do systemu!</h3>
</c:if>
<c:if test="${sessionOk}">
    <c:choose>
        <c:when test="${tweet != null}">
            <form method="post" action="editTweet">
                <textarea name="message"><c:out value="${tweet.message}"/></textarea>
                <input type="hidden" name="tweetId" value='<c:out value="${tweet.id}"/>'>
                <input type="submit" name="submit" value="Save"/>
            </form>
        </c:when>
        <c:otherwise>
            <h2 style="color: brown">Tweet o podanym ID nie istnieje!</h2>
        </c:otherwise>
    </c:choose>
</c:if>
</body>
</html>
