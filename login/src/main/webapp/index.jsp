<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SheeTI</title>
</head>
<body>
<h1 style='text-align:center'><%= "SHEETI" %>
</h1>
<br/>

<form action="/login_war_exploded/login-servlet" method="post" align='center'>
    <label for="username">Il tuo username</label><br>
    <input type="text" id="username" name="username"><br>
    <label for="pwd">La tua password:</label><br>
    <input type="password" id="pwd" name="pwd"><br><br>
    <input type="submit" value="Entra">
</form>
</body>
</html>