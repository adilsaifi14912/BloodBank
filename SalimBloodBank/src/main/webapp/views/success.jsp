<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<div class="body_div">
			<br/> <span class="body_text_title" style="color: green; padding: 5px; background-color: yellow;"> ${model_message} </span> <br/> <br/> <br/>
			<jsp:include page="${model_pagename}.jsp" />
		</div>
	</body>
</html>