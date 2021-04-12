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
            <div id="readlist">
                <div class="readmain">
                	<div class="read">
                        <div class="readheader">
                    		<h1 id="readtheder">${result.review_title}</h1><!-- 글제목 -->
                       		<small>
                       			<strong>
                            		<span class="text-grey"><a href="#" class="text-dark">${result.user_id}</a></span>
                        		</strong>
                        		<br>
                        		<div class="boxs">
      								<div>
           								<div><p>${result.review_credate}</p></div>
           								<div><p>조회수 ${result.review_viewcnt}</p></div>
     								</div>
     								<div>
         								<div><p>좋아요 ${result.review_good}</p></div>
         								<!-- 좋아요 부분 -->
         								<c:choose>
         								<c:when test="${sessionScope.loginid ne null}">
         									<div>
         									<a class="heart" href="goods?review_idx=${result.review_idx}&user_id=${sessionScope.loginid}&goodcnt=${goodcnt}">
         										<c:if test="${goodcnt == 1}">
													<img id="heart"class="good" src="${path}/resources/mainimg/full.png">
												</c:if>
												<c:if test="${goodcnt == 0}">
													<img id="heart"class="good" src="${path}/resources/mainimg/empty.png">
												</c:if>
												</a>
         									</div>
         								</c:when>
         								</c:choose>	
    								</div>
    							</div>
                        	</small>
                    	</div>
                    	<br>
                    	<div class="readcontent">${result.review_content}</div>
                        <h4 class="commentcnt">댓글 ${sessionScope.commtcnt}</h4>
                        <!-- 댓글 보여지는 부분 -->
                        
                        <div class="comment">
                        <c:forEach items="${result2}" var="replyList">
                        	<div class="commentlist">
                                <h3>댓글</h3>
                        		<strong><span class="text-grey"><a href="#" class="text-dark">${replyList.user_id}</a></span></strong>
                            	<div class="comcontent">${replyList.comment_content}</div>
                            	<form action="updatereply" class="upfrm" method="post">
                                 <div class="cmtup">
                                  <div>
                                   <textarea class="cominsertcontents" name="comment_content">${replyList.comment_content}</textarea>
	                                </div>
                                   <input type="hidden" name="comment_idx" value="${replyList.comment_idx}">
	                                <input type="hidden" name="review_idx" value="${replyList.review_idx}">
                                    <div class="subsbtn">
                                        <input type="submit" id="subbtn1" value="수정완료">
                                        <input type="button" class="subbtn2" value="취소" onclick="reback()">
	                                </div>
	                                </div>
                              	</form>
                            	<div class="contalin">
                                	<div>
                                   		<small><p>${replyList.comment_credate}</p></small>
                                	</div>
                               		<c:choose>
                               		<c:when test="${sessionScope.loginid eq replyList.user_id or sessionScope.loginidgrade eq 6}">
                               		<div>
                                    	<input type="button" class="divblock" value="수정">
                                    	<input type="button" value="삭제" class="cmtdel" onclick="location.href='deletereply?comment_idx=${replyList.comment_idx}&review_idx=${replyList.review_idx}'">
                                	</div>
                                	</c:when>
                                	</c:choose>
                            	</div>
                        	</div>
                       		</c:forEach>
                        	<!-- 댓글 작성 부분 -->
                        	<c:choose>
                        	<c:when test="${sessionScope.loginid ne null}">
                        	<form action="replyProc" method="post">
                        	<div class="commentinsert">
                        		<div class="cmtid">
                        			<strong><span class="text-grey"><a href="#" class="text-dark">${sessionScope.loginid}</a></span></strong>
                                </div>
                                <textarea class="cominsertcontent" placeholder="댓글을 입력해 주세요" name="comment_content"></textarea>
                                <div class="combtn"><input type="submit" class="cmtdel" value="작성하기"></div>  
                        	</div>
                        	<input type="hidden" value="${result.review_idx}" name="review_idx">
                        	<input type="hidden" value="${sessionScope.loginid}" name="user_id">
                        	</form>
                    		</c:when>
                    		</c:choose>
                    	</div>
                    	<div class="readbuttons">
	        				<input class="backbutton" type="button" value="목록" onclick = "location.href = 'review_list'">
	        				<c:choose>
	        				<c:when test="${sessionScope.loginid eq result.user_id or sessionScope.loginidgrade eq 6}">
	        				<input type="button" class="insertbutton" value="삭제" id="delete" onclick="location.href='delete?review_idx=${result.review_idx}'">
	        				<input class="insertbutton" type="button" value="수정" onclick = "location.href = 'review_update?idx=${result.review_idx}'">
	        				</c:when>
	        				</c:choose>
	      				</div>
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

   
    <script>
      
      $(document).ready(function(){
      		
      		$(".upfrm").hide();
      	
         $(".divblock").click(function(){
            $(".upfrm").css("display","none");
            $(this).parents(".commentlist").find("form").css("display","block");
            $(".comcontent").hide();
            $(".cmtdel").hide();
            $("this").hide();
         });
            
            
         $("#subbtn1").click(function() { 
            $(".upfrm").submit();
         });
         
          $("#delete").click(function(){
         
         	alert("삭제하시겠습니까?");
      	});
         
      });
            
      function reback(){
         $(".upfrm").hide();
         $(".comcontent").show();
         $(".cmtdel").show();
         $(".divblock").show();
      }
      
   </script>
	
</body>
</html>