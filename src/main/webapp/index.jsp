<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>OurBlog</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">

    <%
        Cookie[] themeCookies = request.getCookies();
        String colorTheme = "_colorTheme";
        if (themeCookies != null) {
            for (Cookie tempCookie : themeCookies) {
                if (tempCookie.getName().equals("colorTheme")) {
                    if (tempCookie.getValue().equals("dark")) {
                        colorTheme = "_darkTheme";
                    }
                    break;
                }
            }
        }
    %>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/<%=colorTheme%>.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/_global.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/index.css">

</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="index-container">
        <div class="login-form login-btn">
            <form method="post" action=" <%= request.getContextPath() %>/Login">
                <p><label for="email">Email<br> <input type="text" id="email" name="email"></label></p>
                <p><label for="password">Password:<br> <input type="password" id="password" name="password"></label></p>
                <p><input id="login-submit-btn" type="submit" value="Login"></p>
            </form>
        </div>
        <div class="signup-link login-btn"><a href="<%= request.getContextPath() %>/SignUp"><b>Sign Up</b></a>
            if you dont have an account</div>
    </div>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
