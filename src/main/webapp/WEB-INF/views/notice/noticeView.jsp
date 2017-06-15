<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>View</h2>
<h3>W : ${dto.writer}</h3>
<h3>t : ${dto.title}</h3>
<h3>c : ${dto.contents}</h3>

<a href="noticeDelete?num=${dto.num}">Delete</a>
<a href="noticeUpdate?num=${dto.num}">UPDATE</a>
</body>
</html>