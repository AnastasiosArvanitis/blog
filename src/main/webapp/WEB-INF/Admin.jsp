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
    List<Post> posts = (List<Post>) request.getAttribute("listPosts");
    List<Member> members = (List<Member>) request.getAttribute("listMembers");
%>
<main>
    <h3>Hello <%= member.getFirstName().concat(" ").concat(member.getLastName()) %> welcome to the Admin page :)</h3>

    <div class="member-posts profil-content">
        <h3>List of All the Posts</h3>
        <% for (Post post : posts) { %>
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

    <div class="member-posts profil-content">
        <h3>List of All the Members</h3>
        <table class="members-table">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
        <% for (Member memberTmp : members) { %>
                <tr>
                    <td><%= memberTmp.getId() %></td>
                    <td><%= memberTmp.getFirstName() %></td>
                    <td><%= memberTmp.getLastName() %></td>
                    <td><%= memberTmp.getEmail() %></td>
                    <td>
                        <form method="post" action="<%= request.getContextPath() %>/DeleteMember">
                            <input type="hidden" name="memberID" value="<%=memberTmp.getId()%>">
                            <input value="delete member" type="submit">
                        </form>
                    </td>
                </tr>

        <% } %>
        </table>
    </div>

</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>

















