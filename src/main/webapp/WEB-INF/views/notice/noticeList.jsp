<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var m = '${message}';
if(m != ""){
	alert(m);
}

</script>
</head>
<body>
	<h2>List</h2>
	<table>
		<tr>
			<td>번</td><td>제</td><td>작</td><td>시</td><td>조</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.num}</td>
			<td><a href="noticeView?num=${dto.num}">${dto.title }</a></td>
			<td>${dto.writer }</td>
			<td>${dto.reg_date }</td>
			<td>${dto.hit }</td>
		</tr>
		</c:forEach>
	</table>
	<a href="noticeWrite">Write</a>
	<a href="noticeView">View</a>
	
<script type="text/javascript">

</script>
</body>
</html>