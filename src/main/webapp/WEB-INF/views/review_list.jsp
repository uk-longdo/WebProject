<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/board.css?ver=1">

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
            <div id="boardlist">
            	<div class="listmain">
            		<h1 id="heder">REVIEW</h1><br>
            		<table class="listtable">
            			<tr>
            				<th>글번호</th>
         					<th>글제목</th>
         					<th>글쓴이</th>
         					<th>좋아요</th>
         					<th>조회수</th>
         					<th>날짜</th>
      					</tr>
      					
      					<c:forEach var="bdata" items="${result}">
      					<tr onclick="location.href='review_read?idx=${bdata.review_idx}&user_id=${sessionScope.loginid}'" >
         					<td>${bdata.review_idx}</td>
         					<td><%-- <a href = "read?idx=${bdata.idx}">${bdata.review_title}</a> --%>${bdata.review_title} </td>
				         	<td>${bdata.user_id}</td>
				         	<td>${bdata.review_good}</td>
				         	<td>${bdata.review_viewcnt}</td>
				         	<td>${bdata.review_credate}</td>
      					</tr>
   						</c:forEach>
      				</table>
      				<div class="paging">
      				<c:forEach var="i" begin="1" end="${pageCount}" step="1">
			        <c:choose>
			         <c:when test="${i eq pageNum}">
			            [${i}]   
			         	</c:when>
			        	 <c:otherwise>
			            	<a href="review_list?pageNum=${i}">[${i}]</a>
			        	 </c:otherwise>
			    	  </c:choose>		
					</c:forEach>
      				</div>
      				<div class="buttons">
      					<c:choose>
      					<c:when test="${sessionScope.loginid ne null}">
      					<input class="insertbutton" type="button" value="리뷰작성" onclick="location.href='review?user_id=${sessionScope.loginid}'">
      					</c:when>
      					</c:choose>
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