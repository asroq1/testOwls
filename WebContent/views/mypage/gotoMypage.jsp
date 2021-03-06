<%@page import="shop.steamowls.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
	String sq = lm.getMemberSq(session);
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
<link href="/css/picture/icons8_owl.ico" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="/css/base.css">
<link rel="stylesheet" href="/css/mypage/gotoMypage.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>
<body>
	<header>
		  <jsp:include page="/views/common/header-logout.jsp"></jsp:include>
	</header>
	<section>
        <div class="info-container1">
            <div class="info-box">
                <i class="far fa-calendar-alt"></i>
                <h1>예약내역</h1>
                <ul>
                    <li>
                        <a href="/mypage/Bhistory">예약내역</a>
                    </li>
                </ul>
            </div>
            <div class="info-box">
                <i class="fas fa-user"></i>
                 <h1>회원정보</h1>
                 <ul>
                    <li>
                        <a href="/mypage/McheckPw">정보수정</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="info-container2">
            <div class="info-box">
                <i class="far fa-question-circle"></i>
                <h1>문의내역</h1>
                <ul>
                    <li>
                        <a href="/mypage/question">문의내역</a>
                    </li>
                    <li>
                    	<a href="/mypage/QWriting">글쓰기</a>
                    </li>
                </ul>
           </div>           
           <div class="info-box box-blur">
           		<i class="far fa-comment-dots"></i>
            <h1>리뷰</h1>
            <ul>
                <li>
                    <a href="/mypage/Rwriting">리뷰 작성</a>
                </li>
                <li>
                    <a href="/mypage/RmyReview">나의 리뷰</a>
                </li>
            </ul>
       </div>           
        </div>
    </section>
    <footer>
		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</footer>
</body>
</html>