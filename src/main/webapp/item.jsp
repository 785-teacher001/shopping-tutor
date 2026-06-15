<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h3>商品詳細</h3>

商品番号：<b>${item.code}</b><br>
商品名：<b>${item.name}</b><br>
価格（税込）：<b>${item.price}円</b><br>


</body>
</html>