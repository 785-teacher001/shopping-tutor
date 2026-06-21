<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="/shopping-tutor/ShowItemServlet?action=top">ようこそ</a>|
<c:forEach items="${categories}" var="category">
<a href="/shopping-tutor/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>|
</c:forEach>

<a href="/shopping-tutor/CartServlet?action=show">カートを見る</a>

<form action="/shopping-tutor/ShowItemServlet" method="get">
	<input type="text" name="keyword">
	<input type="hidden" name="action" value="search">
	<button>検索</button>
</form>

<p>ようこそ、${sessionScope.customer.name}さん</p>