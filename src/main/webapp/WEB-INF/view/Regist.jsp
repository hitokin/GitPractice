<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dto.KadaiAccount" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			KadaiAccount ac = (KadaiAccount)session.getAttribute("input_data");
	%>
	ログインに失敗しました
	<a href="LoginFormServlet">ログイン</a>
	<%} %>
</body>
</html>