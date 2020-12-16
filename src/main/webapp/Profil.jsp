        <%@ page import="info.anastasios.blog.bo.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="info.anastasios.blog.bo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Member Profil</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./styles/_global.css">
    <link rel="stylesheet" type="text/css" href="./styles/profil.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<%

    List<Post> memberPosts = (List<Post>) request.getAttribute("memberListPost");
%>
<main>
<%
    Member member = null;
    if (session.getAttribute("member") == null) {
        response.sendRedirect("/blog/UserNotFound.jsp");
    } else {
        member = (Member) session.getAttribute("member");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h2>");
        stringBuilder.append("Welcome to your profile ").append(" ");
        stringBuilder.append(member.getFirstName()).append(" ");
        stringBuilder.append(member.getLastName()).append("</h2>");
        out.println(stringBuilder.toString());
    }
%>
    <div class="member-data profil-content">
        <form method="post" action="UpdateMeber">
            <h3>Here is your data and if you want yoou can update them :)</h3>
            <p>First Name</p>
            <p><input type="text" id="firstName" name="firstName" value="<%=member.getFirstName()%>"></p>
            <p>Last Name</p>
            <p><input type="text" id="lastName" name="lastName" value="<%=member.getLastName()%>"></p>
            <p>Email</p>
            <p><input type="text" id="email" name="email" value="<%=member.getEmail()%>"></p>
            <p>Password</p>
            <p><input type="password" id="password" name="password" value="<%=member.getPassword()%>"></p>
            <p><input id="update-submit-btn" type="submit" value="Update"/></p>
        </form>
    </div>

    <div class="add-post-form profil-content">
        <form method="post" action="AddPost">
            <h3>You can add a new post!</h3>
            <p>Title</p>
            <p><input type="text" id="title" name="title"></p>
            <p>Body</p>
            <p><textarea name="postBody" id="postBody" cols="60" rows="10"></textarea><p>
            <p><input id="login-submit-btn" type="submit" value="AddPost"></p>
        </form>
    </div>

    <div class="member-posts profil-content">
        <h3>List of your posts</h3>
        <% for (Post post : memberPosts) { %>
            <div class="profil-post">
            <p><h5> <%=post.getTitle()%> </h5></p>
            <p> <%=post.getBody()%> </p>
            <p>Created on <%=post.getDate()%> by <%=post.getMember().getFirstName().concat(" " + post.getMember().getLastName())%> </p>
            </div>
        <% } %>
    </div>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
