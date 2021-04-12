<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/board.css?ver=2">

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
            		<h1 id="heder">회원관리</h1><br>
            		<table class="usertables">
            			<tr>
            				<th>선택</th>
            				<th>회원번호</th>
         					<th>아이디</th>
         					<th>닉네임</th>
         					<th>주소</th>
         					<th>연락처</th>
         					<th>등급</th>
      					</tr>
      					<form>
      					<c:forEach var="user" items="${result}">
      					<tr>
      						<td><input type="checkbox" name="ChkBxRow" id="ChkBxRow" value="${user.user_idx}"></td>
         					<td>${user.user_idx}</td>
         					<td>${user.user_id}</td>
         					<td>${user.user_name}</td>
         					<td>${user.user_addres}</td>
         					<td>${user.user_tel}</td>
         					<td>${user.user_grade}</td>
      					</tr>
   						</c:forEach>
   						</form>
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
      					<input class="insertbutton" type="button" value="회원삭제" onclick="submitCheck()">
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
		    function submitCheck(){
		    var chk = $("input[name=ChkBxRow]");
		    var len = chk.length; //체크박스의 전체 개수
		    var checkCnt = 0; //체크된 체크박스의 개수
		 
		    for (var i = 0; i < len; i++) {
		        if (chk[i].checked == true) {
		            checkCnt++; //체크된 체크박스의 개수
		        }
		        
		    }
		     
		    if(checkCnt == 0){
		        alert("선택된 사람이 없습니다.");
		        return false;
		    }
		     
		    var user_idxs= "";
		     
		    $("input[name=ChkBxRow]:checked").each(function(i) {
		         
		        if(i == checkCnt-1){
		            user_idxs += $(this).val();
		        }else{
		            user_idxs += $(this).val()+",";
		        }
		        
		    });
		    
		    var user_idx = "user_id"+user_idxs;
		    alert(user_idx);
		    
		    location.href='userdelete?user_idxs='+user_idxs;
		}
	
	</script>
	
	
</body>
</html>