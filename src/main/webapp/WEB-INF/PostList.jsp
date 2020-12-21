<%@ page import="info.anastasios.blog.bo.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="info.anastasios.blog.bo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Member member = null;
        if (session.getAttribute("member") == null) {
            response.sendRedirect("/blog/Error?error=notLoggedIn");
        } else {
            member = (Member) session.getAttribute("member");
        }
    %>
    <meta charset="UTF-8">
    <title>Posts List</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../styles/_global.css">
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/postList.css">

</head>
<body>
<jsp:include page="../header.jsp"/>
<%
    List<Post> thePosts = (List<Post>) request.getAttribute("listPost");
%>
<main>

<h3>Hello <%= member.getFirstName().concat(" ").concat(member.getLastName()) %> :)</h3>
<div class="post-list">

    <% for (Post post : thePosts) { %>
    <div class="post">
        <p><b> <%= post.getTitle() %> </b></p>
        <p> <%= post.getBody() %> </p>
        <p>Created on <%= post.getDate() %> by
         <%= post.getMember().getFirstName().concat(" " +post.getMember().getLastName())  %> </p>
    </div>
    <% } %>

</div>
</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>
