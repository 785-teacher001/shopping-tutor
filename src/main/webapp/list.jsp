<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
/*
request.setAttribute("count", 1);
request.setAttribute("pages", 10);
request.setAttribute("action", "search");
request.setAttribute("code", null);
request.setAttribute("keyword", "B");
*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h3>商品一覧</h3>

<p>${count}件の商品が存在しました。</p>

<c:forEach items="${items}" var="item">
    <form action="/shopping-tutor/CartServlet?action=add" method="post">
        <input type="hidden" name="item_code" value="${item.code}">
        商品番号：<b>${item.code}</b><br>
        商品名：<b>${item.name}</b><br>
        価格(税込)：<b>${item.price}円</b><br>
        個数：
        <select name="quantity">
        <option value="1">1
        <option value="2">2
        <option value="3">3
        <option value="4">4
        <option value="5">5
        </select>
        個<br>
        <a href="/shopping-tutor/ShowItemServlet?action=detail&code=${item.code}">詳細</a><br>
        <button>カートに追加</button>
    </form>
</c:forEach>

<c:forEach begin="1" end="${pages}" varStatus="page">
	<a href="/shopping-tutor/ShowItemServlet?action=${action}&code=${code}&keyword=${keyword}&page=${pageScope.page.count}">${pageScope.page.count}</a>
</c:forEach>

</body>
</html>