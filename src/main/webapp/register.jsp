<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
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
<h1>Register to SDA Twitter</h1>
<div>
    <c:set var="error" value="${param['err']}"/>
    <h6 style="color: red">
        <c:choose>
            <c:when test="${error eq 'nonick'}">Nie podano nick'a</c:when>
            <c:when test="${error eq 'nomail'}">Nie podano adresu email</c:when>
            <c:when test="${error eq 'nopass'}">Nie podano hasła</c:when>
            <c:when test="${error eq 'passnotmatch'}">Podane hasła nie są zgodne</c:when>
        </c:choose>
    </h6>
    <form action="register" method="post">
        <table border="0">
            <tr>
                <td>Nick:</td>
                <td><input type="text" name="nick" id="nick"></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><input type="text" name="mail" id="mail"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="pass1" id="pass1"></td>
            </tr>
            <tr>
                <td>Repeat Password:</td>
                <td><input type="password" name="pass2" id="pass2"></td>
            </tr>
        </table>
        <input type="submit" value="Register">
    </form>
</div>
</body>
</html>
