
<%@ page import="info.anastasios.blog.bo.Member" %>
<header>
    <div class="logo"><h2>OurBlog.com</h2></div>
    <ul>
<%
    Member member = (Member) session.getAttribute("member");
    if (member == null || !member.getIsAdmin()) {
        System.out.println("----------------- Member == null ");
        out.println("<li><a href=\"" +request.getContextPath()+ "/MyProfil\">My Profile</a></li>");
        out.println("<li><a href=\"" +request.getContextPath()+ "/Posts\">All the Posts</a></li>");
        out.println("<li><a href=\"" +request.getContextPath()+ "/Logout\">Logout</a></li>");
    } else {

        out.println("<li><a href=\"" + request.getContextPath() + "/Admin\">Admin</a></li>");
        out.println("<li><a href=\"" + request.getContextPath() + "/MyProfil\">My Profile</a></li>");
        out.println("<li><a href=\"" + request.getContextPath() + "/Posts\">All the Posts</a></li>");
        out.println("<li><a href=\"" + request.getContextPath() + "/Logout\">Logout</a></li>");
    }
%>
    </ul>
</header>
