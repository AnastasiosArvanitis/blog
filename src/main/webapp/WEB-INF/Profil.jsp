<%@ page import="info.anastasios.blog.bo.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="info.anastasios.blog.bo.Member" %>
<%@ page import="java.util.Objects" %>
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
        assert member != null;%>
    <meta charset="UTF-8">
    <title><%= member.getFirstName().concat(" ").concat(member.getLastName()) %> Profil</title>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/postList.css">

</head>
<body>
<jsp:include page="../header.jsp"/>
<%
    List<Post> memberPosts = (List<Post>) request.getAttribute("memberListPost");
%>
<main>
    <h3>Hello <%= member.getFirstName().concat(" ").concat(member.getLastName()) %> welcome to your profile :)</h3>

    <div class="member-data profil-content">
        <form method="post" action="<%= request.getContextPath() %>/UpdateMember">
            <h3>Here is your data and if you want you can update them :)</h3>
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

        <div class="chose-theme">
            <br><br>
            <h3>You can even chose your color theme!</h3>
            <form method="post" action="<%= request.getContextPath() %>/ChoseTheme">
                <label for="color">
                <input id="color" type="radio" name="colorTheme" value="color"
                    <% if (colorTheme.equals("_colorTheme")) {out.print("checked");} else {out.print("");};%>
                /> Color Theme</label>
                <label for="dark">
                <input id="dark" type="radio" name="colorTheme" value="dark"
                    <% if (colorTheme.equals("_darkTheme")) {out.print("checked");} else {out.print("");};%>
                /> Dark Theme</label>
                <input type="submit" name="changeTheme" value="Change Theme">
            </form>
        </div>
    </div>

    <div class="add-post-form profil-content">
        <form method="post" action="<%= request.getContextPath() %>/AddPost">
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
                <p>
                <form method="post" action="<%= request.getContextPath() %>/DeletePost">
                    <input type="hidden" name="postID" value="<%=post.getPostId()%>">
                    <input value="delete post" type="submit">
                </form>
                </p>
            </div>
        <% } %>
    </div>
</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>
