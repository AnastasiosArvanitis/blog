<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17/12/2020
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>Error</title>
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

</head>
<body>
<jsp:include page="../header.jsp"/>
<main>

    <div class="error-container">
        <h3>The page that you are looking for was not found, please try again</h3>
        <a href="<%= request.getContextPath() %>">Go to home page</a>
    </div>
</main>
<jsp:include page="../footer.jsp"/>
</body>
</html></title>
</head>
<body>

</body>
</html>
