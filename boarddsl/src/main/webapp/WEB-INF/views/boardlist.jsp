<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style type="text/css">
	h2, form {
		text-align: center;
	}
	table {
		margin: auto;
		width: 800px;
	}
	#tr_top {
		background: orange;
		text-align: center;
	}
	#emptyArea {
		margin: auto;
		width: 800px;
		text-align: center;
	}
	#emptyArea a {
		display: inline-block;
		width: 20px;
		height: 20px;
		text-decoration: none;
	}
	#emptyArea .btn {
		background: lightgray;
	}
	#emptyArea .select {
		background: lightblue;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#type").val("${type}").prop("selected", true);
})

function submit(page) {
	$("#page").val(page);
	$("#search").submit();
}
</script>
</head>
<body>
<jsp:include page="main.jsp" />
<h2>글 목록&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${user ne Empty }"><a href="boardwrite">글쓰기</a></c:if>
</h2>
<form action="boardlist" method="get" id="search">
	<input type="hidden" name="page" value="1" id="page"/>
	<select name="type" id="type">
		<option value="subject" selected="selected">제목</option>
		<option value="writer">작성자</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="word" value="${word }" />
	<input type="submit" value="검색"/>
</form>
<br>
<table>
	<tr id="tr_top">
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
		<th>조회수</th>
		<c:forEach items="${boardList }" var="board">
			<tr>
				<td>${board.num }</td>
				<td><a href="boarddetail/${board.num}">${board.subject }</a></td>
				<td>${board.writer }</td>
				<td>${board.writeDate }</td>
				<td>${board.viewCount }</td>
			</tr>
		</c:forEach>
	</tr>
</table>
<br>
<div id="emptyArea">
	<c:choose>
		<c:when test="${pageInfo.curPage == 1 }">
			<button>&lt;</button>  <!-- 현재페이지가 1이면 비활성화 -->
		</c:when>
		<c:otherwise>
			<button onclick="submit(${pageInfo.curPage-1})">&lt;</button>
		</c:otherwise>
	</c:choose>
	<c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" var="i">
		<c:choose>
			<c:when test="${i == pageInfo.curPage }">
				<button class="select" onclick="submit(${i})">${i }</button>
			</c:when>
			<c:otherwise>
				<button class="btn" onclick="submit(${i})">${i }</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageInfo.curPage == pageInfo.allPage }">
			<button>&gt;</button>  <!-- 시작페이지가 1이면 비활성화 -->
		</c:when>
		<c:otherwise>
			<button onclick="submit(${pageInfo.curPage+1})">&gt;</button>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>