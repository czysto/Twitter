<%@ page pageEncoding="UTF-8" contentType="text/html" %>
<%@ page import="com.sdacademy.twitter.utils.Utils" %>
<%@ page import="com.sdacademy.twitter.dao.TweetDao" %>
<%@ page import="com.sdacademy.twitter.model.Tweet" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sdacademy.twitter.dao.CommentDao" %>
<%@ page import="com.sdacademy.twitter.model.Comment" %>
<%@ page import="com.sdacademy.twitter.dao.UserDao" %>
<%@ page import="com.sdacademy.twitter.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <title>SDA Twitter Registration Form</title>
</head>
<body>
<%
    java.util.Date today = new java.util.Date();
    String text = "Today's date is: " + today.toString();
%>
<%
    Utils.activateSession(request);
    UserDao userDao = UserDao.getInstance();
    Optional<List<User>> allUsers = userDao.getAll();
    if (allUsers.isPresent()) {
        request.setAttribute("usersList", allUsers.get());
    }
%>
<%
    Utils.activateSession(request);
    CommentDao commentDao = CommentDao.getInstance();
    Optional<List<Comment>> allComment = commentDao.getAll();
    if (allComment.isPresent()) {
        request.setAttribute("commentList", allComment.get());
    }
%>
<%
    Utils.activateSession(request);
    TweetDao tweetDao = TweetDao.getInstance();
    Optional<List<Tweet>> allTweets = tweetDao.getAll();
    if (allTweets.isPresent()) {
        request.setAttribute("tweetList", allTweets.get());
    }
%>
<jsp:useBean id="myDate" class="java.util.Date"/>
<div style="text-align: right;">
    <h6>
        <c:if test="${sessionOk}">
            <a href="logout">Logout</a>
        </c:if>
        <c:if test="${!sessionOk}">
            <a href="login.jsp">Login</a> / <a href="register.jsp">Register</a>
        </c:if>
    </h6>
</div>

<div style="text-align: center">
    <h1>Tweets</h1>
    <c:if test="${sessionOk}">
        <h4><a href="addTweet.jsp">Add Tweet</a></h4>
    </c:if>
    <table border="1" style="width: 95%; text-align: center">
        <tr>
            <td>Author</td>
            <td>Message</td>
            <td>Published</td>
            <c:if test="${sessionOk}">
                <td>Options</td>
            </c:if>
        </tr>
        <c:forEach items="${tweetList}" var="tweet">
            <tr>
                <td><c:out value="${tweet.user.nick}"/></td>
                <td><c:out value="${tweet.message}"/></td>
                <c:set target="${myDate}" property="time" value="${tweet.creationTS}"/>
                <td><c:out value="${myDate}"/></td>
                <c:if test="${sessionOk}">
                    <c:if test="${userId == tweet.user.id}">
                        <td>
                            <a href='removeTweet?id=<c:out value="${tweet.id}"/>'>Remove</a>
                            <a href='editTweet.jsp?id=<c:out value="${tweet.id}"/>'>Edit</a>
                        </td>
                    </c:if>
                    <c:if test="${userId != tweet.user.id}">
                        <td>&nbsp;</td>
                    </c:if>
                </c:if>
                <c:if test="${sessionOk}">

                    <td>
                        <a href='addComment.jsp?id=<c:out value="${tweet.id}"/>'>Comment</a>
                    </td>
                    <%--<td>--%>
                    <%--<a href='retweet.jsp?id=<c:out value="${tweet.id}"/>'>ReTweet</a>--%>
                    <%--</td>--%>
                </c:if>
            </tr>
        </c:forEach>

        <c:forEach items="${commentList}" var="comment">
            <c:if test="${tweet.id == comment.id}">

                <tr>
                    <td>
                        <c:out value="${comment.author.nick}"/>
                    </td>

                    <td>
                        <c:out value="${comment.message}"/>
                        <jsp:useBean id="myDate1" class="java.util.Date"/>
                        <c:set target="${myDate1}" property="time" value="${comment.creationTS}"/>
                    </td>

                    <td><c:out value="${myDate1}"/></td>
                    <c:if test="${sessionOk}">
                        <c:if test="${(userId == comment.author.id) or (userId == tweet.user.id)}">
                            <td>
                                <a href='removeComment?id=<c:out value="${comment.id}"/>'>Remove</a>
                                <a href='editComment.jsp?id=<c:out value="${comment.id}"/>'>Edit</a>
                            </td>
                        </c:if>
                        <c:if test="${userId != tweet.user.id}">
                            <td>&nbsp;</td>
                        </c:if>
                    </c:if>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>
