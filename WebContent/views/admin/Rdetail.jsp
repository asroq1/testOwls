<%@page import="shop.steamowls.common.LoginManager"%>
<%@page import="shop.steamowls.steam.admin.review.vo.ReviewVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ReviewVo reviewVo = (ReviewVo) request.getAttribute("vo");
LoginManager lm = LoginManager.getInstance();
String sq = lm.getMemberSq(session);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의관리</title>
<link rel="stylesheet" href="/css/base.css">
<link rel="stylesheet" href="/css/admin/gotoAdmin.css">
<link href="/css/picture/icons8_owl.ico" rel="shortcut icon" type="image/x-icon">
</head>
<body>
<header>
		<jsp:include page="/views/common/adminHeader.jsp"></jsp:include>
	</header>
	<nav role="navigation">
		<jsp:include page="/views/common/admin-nav.jsp"></jsp:include>
	</nav>
	
<h1>Article Detail</h1>
<br>
<h3>NO : <%=reviewVo.getReview_sq()%></h3>
<h3>SUBJECT : <%=reviewVo.getReview_subject()%></h3>
<h3>CONTENT : <%=reviewVo.getReview_content()%></h3>
<h3>STAR : <%=reviewVo.getReview_star()%></h3>
<h3>DATE : <%=reviewVo.getReview_dttm()%></h3>

<a href="#">리뷰답변</a>
<a href="/admin/Rdelete?review_sq=<%=reviewVo.getReview_sq()%>">리뷰삭제</a>

<a href="/admin/Rmanage">확인</a>
</body>
</html>