<%@ page import="info.anastasios.blog.bo.Member" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../styles/_global.css">
</head>
<body>

<jsp:include page="../header.jsp"/>

    <%
        List<Member> theMembers = (List<Member>) request.getAttribute("listMember");
    %>

    <table border="1">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
        </tr>

        <% for (Member member : theMembers) { %>
        <tr>
            <td> <%= member.getFirstName() %> </td>
            <td> <%= member.getLastName() %> </td>
            <td> <%= member.getEmail() %> </td>
        </tr>
        <% } %>

    </table>

<jsp:include page="../footer.jsp"/>
</body>
</html>
