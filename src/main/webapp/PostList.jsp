<%@ page import="info.anastasios.blog.bo.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="info.anastasios.blog.bo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posts List</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./styles/_global.css">
    <link rel="stylesheet" type="text/css" href="./styles/postList.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<%

    List<Post> thePosts = (List<Post>) request.getAttribute("listPost");
%>
<main>
<%
    if (session.getAttribute("member") == null) {
        response.sendRedirect("/blog/UserNotFound.jsp");
    } else {
        Member member = (Member) session.getAttribute("member");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h3>");
        stringBuilder.append("Welcome back to our blog").append(" ");
        stringBuilder.append(member.getFirstName()).append(" ");
        stringBuilder.append(member.getLastName()).append(", here is a list of all the members posts :)</h3>");
        out.println(stringBuilder.toString());
    }
%>

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
<jsp:include page="footer.jsp"/>
</body>
</html>
