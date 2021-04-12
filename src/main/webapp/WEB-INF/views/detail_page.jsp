<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/main.css">

</head>
<body>
	<div id="wrap">
		<header>
		<div id="top">
			<img class="logo" src="${path}/resources/mainimg/logo.png" onclick="location.href='main'">
        </div>
            <nav id="top_nav">
                <ul><!-- 로그인 로그아웃 부분 -->
                	<c:choose>
                		
              
						<c:when test="${sessionScope.loginid ne null}">
							<c:if test="${sessionScope.loginidgrade eq 6}">
								${sessionScope.loginid}님 관리자 로그인을 하셨습니다!
								<li><a href="listreg">재고등록</a></li>
								<li><a href="userlist">회원관리</a></li>
                    			<li><a href="dresslist">재고관리</a></li>
							</c:if>
							
							
							<c:if test="${sessionScope.loginidgrade ne 6}">
								${sessionScope.loginid}님 반갑습니다.
								<li><a href="#">ORDER</a></li>
                    			<li><a href="#">MY ACCOUNT</a></li>
                    			<li><a href="#">CART</a></li>
							</c:if>
							 <li><a href="logout">로그아웃</a></li>
						</c:when>
						
						
						<c:otherwise>
							 <li><a href="login">로그인</a></li>
							 <li><a href="#">ORDER</a></li>
                    		<li><a href="#">MY ACCOUNT</a></li>
                   	 		<li><a href="#">CART</a></li>
						</c:otherwise>	
					</c:choose>
                  <!-- 끝나는 부분 -->
                </ul>
            </nav>
	    </header>
	    <!-- 사이드 메뉴바 부분 -->
	    <div id="content">
	    	<div id="left_side">
	    		<ul class="category">
	    			<li><a href="sideAll">All</a></li>
	    			<li ><a href="sideOuter">Outer</a></li>
	    			<li ><a href="sideTop">Top</a></li>
	    			<li ><a href="sidePants">Pants</a></li>
	    			<li><!-- warp div 끝나는 부분 --></li>
	    			<li ><a href="notice_list">Notice</a></li>
	    			<li ><a href="Qaboard_list">Q&A</a></li>
	    			<li ><a href="review_list">Review</a></li>
				</ul>
            </div>
            <!--main-->
			<div id="main">
			
				<div class="wrapper">
					<div class="detaileddescription">
						<div id= "detaileddescription_text">					
		                    ${sessionScope.contentup}
						</div>
                	</div>
                	 <c:forEach var="data" items="${result2}">
                   	<div class="detailpurchase">
                   		<div id= "detailpurchaseinform">
                   				${data.dress_name}		 
                   			<p id="informp"> <br>           			 
                   			${data.dress_price}
                   			 KRW<br> </p>
                   			<br>
                   			<br>
                   			<hr>
	                        <select  class="select" id="dress_size" name="dress_size">
	                        	<option> [필수]&nbsp;&nbsp; SELECT A SIZE &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	                            <option value="">------------------------------------</option>
	                        	<c:forEach var="inven" items="${inven}">
	                        	<option value="${inven.dress_size}">${inven.dress_size}</option>
								</c:forEach>
                            </select>
                            <br>
                            <br>
                            <br>
                            <br>
                            TOTAL : 0 KRW (0개)
                            <br>
                            <br>
                            <hr>
                            <br>
                            <br>
                            <input class="button" type="button" value="ADD TO CART">
                            <br>
                            <input class="buttony" type="button" value="BUY NOW">
                        </div>
                    </div>
                  </c:forEach>
	                    <div class="detailimage">
		                    <c:forEach var="data" items="${result}">
		                    	<img class="detailimg" src="resources/${data.file_path}/${data.file_name}" alt="">
	                    	</c:forEach>
	                    </div>
                </div>
			</div>    
        </div>
		<footer>
			<div id="footer_wrap">
				<div id="footer_address">
					<pre>
					KIM EUN JI | 96rladmswl@naver.com | 1111-2222
					부산광역시 사하구
					블란코마르 170-06-*****
					</pre>
				</div>
			</div>
		</footer>
	</div><!-- warp div 끝나는 부분 -->
	  
	<!-- 스크립트  -->
	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript"></script>
</body>
</html>