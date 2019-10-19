<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Cart</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/semantic.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/icon.min.css">
</head>
<body>
<div class="ui segment"><a href="${pageContext.request.contextPath }/"><i class="big grey home icon">Home</i></a></div>
<div class="ui container m-top unstackable">
<form:form action="${pageContext.request.contextPath }/showCart" method="POST">
	<table class="ui celled table">
		<thead><tr>
			<th>Nume</th>
			<th>Cantitate</th>
			<th>Pret</th>
			<th>Portii</th>
		</tr></thead>
		<tbody>
		<c:forEach var="temp1" items="${array}" varStatus="status">
		<c:url var="delete" value="/showCart">
			<c:param name="deleteId" value="${status.index }" />
		</c:url>
			<tr>
				<td>${temp1.name }</td>
				<td>${temp1.weight }</td>
				<td>${temp1.price }</td>
				<td><input type="text" name="servings" value="1"/><a href="${delete }" class="padleft"><i class="red link times icon"></i></a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2"></td>
			<td>Total</td>
				<td>${total} RON</td>
		</tr>
		</tbody>
		<c:url var="check" value="/checkOut">
			<c:param name="sessId" value="${sessionId }" />
		</c:url>
		<tfoot class="full-width">
                <tr>
                  <th colspan="4">
                 <a href="${check }"><div class="ui right floated small positive button" id="sendButton">
                      <i class="paper plane outline icon"></i> Send
                    </div></a>
                    <button class="ui small teal button">
                      <input type="submit" value="Update" id="submit" class="whitex"/>
                    </button>
                    
                  </th>
                </tr>
              </tfoot>
	</table>
	</form:form>
	
	</div>
	<script src="${pageContext.request.contextPath }/resources/js/cart.js"></script>
</body>
</html>