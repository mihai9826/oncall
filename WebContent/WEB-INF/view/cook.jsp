<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/semantic.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/icon.min.css">
</head>
<body>
 <div class="ui menu m-top stackable" id="nav">
      
	 
	  <a href="${pageContext.request.contextPath}/" class="header item">
	    OnClick</a>
	  <a class="item">
	    About Us
	  </a>
	<div class="right menu">
            <div class="item fourty">
            <a href="${pageContext.request.contextPath }/showRegistrationForm" class="ui primary button">Sign up</a>
        </div>
         <div class="item fourty">
        <form:form action="${pageContext.request.contextPath }/logout"
                  method="POST">
          <input type="submit" value="Logout" class="ui button"/>
        </form:form>
          
        </div>
        <div class="item fourty">
          <a href="${pageContext.request.contextPath }/showCart" class="ui basic button">
          <i class="shop icon"></i>
          Checkout</a>
        </div>
          </div>
   </div>
<div class="ui container m-top">
	<c:forEach var="theList" items="${orders }" varStatus="status">
         <table class="ui celled table">
		<thead><tr>
			<th>Nume</th>
			<th>Cantitate</th>
			<th>Pret</th>
			<th>Portii</th>
		</tr></thead>
		<tbody>
		<c:forEach var="tempItem" items="${theList }" varStatus="status1">
			<c:if test="${status1.last != true }">            
			<tr>
				<td>${tempItem.name }</td>
				<td>${tempItem.weight }</td>
				<td>${tempItem.price }</td>
				<td>${tempItem.servings }</td>
			</tr>						
		</c:if>
		<c:if test="${status1.last != false }">            
			<tr>
				<td></td>
				<td>Total</td>
				<td colspan="2">${tempItem.price} RON</td>
			</tr>						
		</c:if>
		</c:forEach>
		<tr>
		<td></td>
		<td>Adresa</td>
		<td colspan="2">${orderList[status.index].details.address }</td>
		</tr>
		</tbody>

	<c:url var="deliv" value="/cook/delivered">
			<c:param name="orderId" value="${orderList[status.index].orderId }" />
			<c:param name="orderIndex" value="${status.index }" />
		</c:url>
		<tfoot class="full-width">
                <tr>
                  <th colspan="4">
                 <a href="${deliv }"><div class="primary ui button">
                     Delivered
                    </div></a>
                  </th>
                </tr>
              </tfoot>
		</table>
	<div style="margin-top:35px;"></div>
	</c:forEach>
</div>
</body>
</html>