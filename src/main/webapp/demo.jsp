
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String name = "大柱子";
        request.setAttribute("username" , name );
        request.setAttribute("pwd" , "123456");
    %>

    ${username } <br>
    ${pwd } <br>



</body>
</html>
