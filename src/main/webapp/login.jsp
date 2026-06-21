<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h3>ログイン</h3>
	<c:if test="${!empty message}">
		<p>${message}</p>
	</c:if>
	<form action="/shopping-tutor/LoginServlet" method="">
	<table>
		<tr>
			<td>メールアドレス：</td>
			<td>
				<input type="email" name="email" />
			</td>
		</tr>
		<tr>
			<td>パスワード：</td>
			<td>
				<input type="password" name="password" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="action" value="login" />
	<button>ログイン</button>
	</form>
</body>
</html>