<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.steamowls.steam.booking.vo.BookingVo"%>
<%@page import="shop.steamowls.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<BookingVo> list = (ArrayList<BookingVo>) request.getAttribute("list");
	BookingVo bookingVo = (BookingVo) request.getAttribute("bookingVo");
	BookingVo productInfoVo = (BookingVo) request.getAttribute("productInfoVo");
	LoginManager lm = LoginManager.getInstance();
	String sq = lm.getMemberSq(session);
	DecimalFormat won = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>예약내역</title>
	<link href="/css/picture/icons8_owl.ico" rel="shortcut icon" type="image/x-icon">
	<link rel="stylesheet" href="/css/base.css">
	<link rel="stylesheet" href="/css/mypage/Bhistory.css">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
</head>

<body>
		<header>
			<jsp:include page="/views/common/header-logout.jsp"></jsp:include>
		</header>
		<h1 id="page__intro">리뷰 게시판</h1>
		
	<%if(list.size()  == 0) {%>
		<section>
			<div class="nothing__container">
				<i class="fab fa-earlybirds"></i>
				<p>예약하신 상품이  존재하지 않습니다.</p>
			</div>
		</section>
		
		<% }else { %> 
		
		
		<section>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<article>
				<div class="booking__container">
					<div class="booking__img">
						<img alt="상품" src="<%=list.get(i).getProduct_imagePath()%>">
					</div>
					<div class="booking__top">
						<div class="booking__title">
							<h3 class="booking__title">예약상품</h3>
							<p class="booking__text">
								<%=list.get(i).getProduct_name()%>
							</p>
						</div>
						<div class="booking__intro">
							<h3 class="booking__title">예약일자</h3>
							<p class="booking__text">
								<%=list.get(i).getBooking_date()%>
								<%=list.get(i).getBooking_start()%>시
							</p>
						</div>
					</div>
					<div class="booking__bottom">
						<div class="booking__people">
							<h3 class="booking__title">예약인원</h3>
							<p class="booking__text">
								<%=list.get(i).getBooking_people()%>명
							</p>
						</div>
						<div class="booking__price">
							<h3 class="booking__title">결제금액</h3>
							<p class="booking__text">
								<%=won.format(list.get(i).getProduct_price() * list.get(i).getBooking_people())%>원</p>
						</div>
					</div>
					<div class="btn__container">
						<a id="ckpoint" href="/mypage/BCancel?booking_sq=<%=list.get(i).getBooking_sq()%>">예약취소</a>
					</div>
				</div>
			</article>
			<%
				}
			%>
		</section>
		<% } %>
		
		<footer>
		<jsp:include page="/views/common/footer.jsp"></jsp:include>
		</footer>		
</body>

</html>