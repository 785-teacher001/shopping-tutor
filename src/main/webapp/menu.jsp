<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="/shopping-tutor/ShowItemServlet?action=top">ようこそ</a>|
<c:forEach items="${categories}" var="category">
	<a href="/shopping-tutor/ShowItemServlet?action=list&code=${category.code}&keyword=&page=1">${category.name}</a>|
</c:forEach>

<a href="/shopping-tutor/CartServlet?action=show">カートを見る</a>

<form action="/shopping-tutor/ShowItemServlet" method="get">
	<input type="hidden" name="action" value="search">
	<input type="hidden" name="code">
	<input type="text"   name="keyword">
	<input type="hidden" name="page"   value="1">
	<button>検索</button>
</form>