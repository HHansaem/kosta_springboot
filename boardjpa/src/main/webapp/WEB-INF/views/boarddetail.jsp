<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 상세</title>
<style type="text/css">
	h2 {
		text-align: center;
	}
	table {
		margin: auto;
		width: 450px;
	}
	.td_left {
		width: 150px;
		background: orange;
	}
	.td_right {
		width: 300px;
		background: skyblue;
	}
	#commandCell {
		text-align: center;
	}
	#content {
		height: 200px;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function() {
		$('#like').click(function() {
			$.ajax({
				url:'/boardlike',
				type:'post',
				async:true,
				data:{like:JSON.stringify({memberId:"${user}", boardNum:"${board.num}"})},
				success:function(result) {
					if(result == 'true') {
						$('#like').attr("src", "${path }/resources/image/redheart.png")
					} else if(result =='false') {
						$('#like').attr("src", "${path }/resources/image/blackheart.png")
					} else {
						alert("좋아요 오류");
					}
				}
			})
		})
	})
</script>
</head>
<body>
<jsp:include page="main.jsp" />
<h2>게시판 글 상세</h2>
<table border="1">
	<tr>
		<td class="td_left"><label>글쓴이</label></td>
		<td class="td_right"><span>${board.writer }</span></td>
	</tr>
	<tr>
		<td class="td_left"><label>제목</label></td>
		<td class="td_right"><span>${board.subject }</span></td>
	</tr>
	<tr>
		<td class="td_left"><label>내용</label></td>
		<td class="td_right"><div id="content">${board.content }</div></td>
	</tr>
	<c:if test="${board.fileNum ne null }">
		<tr>
			<td class="td_left"><label>이미지</label></td>
			<td class="td_right">
				<img src="${path }/image/${board.fileNum }" width="100px"/>
			</td>
		</tr>
	</c:if>
</table>
<div id="commandCell">
	<a href="${path }/boardmodify/${board.num }">수정</a>&nbsp;&nbsp;&nbsp;
	<a href="${path }/boardlist">목록</a>&nbsp;&nbsp;&nbsp;
	<c:if test="${user ne Empty }"> <!-- 로그인했을 때만 하트 누를 수 있게 -->
		<c:choose>
			<c:when test="${like eq 'true' }">
				<img src="${path }/resources/image/redheart.png" width="40px" height="40px" style="margin-top:5px" id="like"/>
			</c:when>
			<c:otherwise>
				<img src="${path }/resources/image/blackheart.png" width="40px" height="40px" style="margin-top:5px" id="like"/>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>
</body>
</html>