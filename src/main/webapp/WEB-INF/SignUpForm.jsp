<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/_global.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/index.css">
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
</head>
<body>
<jsp:include page="../header.jsp"/>
<main>
    <div class="signup-container">
    <div class="signup-form">
        <h3>Sign up :)</h3>
        <form method="post" action="<%= request.getContextPath() %>/SignUp">
            <p><label for="firstName">First Name<br> <input type="text" id="firstName" name="firstName"></label></p>
            <p><label for="lastName">Last Name<br> <input type="text" id="lastName" name="lastName"></label></p>
            <p><label for="email">Email<br> <input type="text" id="email" name="email"></label></p>
            <p><label for="password">Password:<br> <input type="password" id="password" name="password"></label></p>
            <p><input id="login-submit-btn" type="submit" value="Login"></p>
        </form>
    </div>
    </div>
</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>
