<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/semantic.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/icon.min.css">
</head>
<body>
	
      <div class="ui menu m-top stackable" id="nav">
      
	  <div class="header item">
	  <a href="${pageContext.request.contextPath}/">
	    OnClick</a>
	  </div>
	  <a class="item">
	    About Us
	  </a>
	  <a class="item active" href="#1">
	    Ciorbe si supe
	  </a>
	  <a class="item" href="#2">
	    Specialitati
	  </a>
	<div class="right menu">
            <div class="item fourty">
            <a href="${pageContext.request.contextPath }/showRegistrationForm" class="ui primary button">Sign up</a>
        </div>
        <div class="item fourty">
        <c:if test="${pageContext.request.remoteUser == null}">
        <a href="${pageContext.request.contextPath }/showLoginPage" class="positive ui button" id="loginButton">Login</a>
        </c:if>
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

	<h2 class="ui dividing padleft header weight" id="1">ciorbe si supe</h2>
	
	<div class="ui container padd">
       <div class="ui three stackable cards">
		<c:forEach var="tempMenu1" items="${menu1 }" >
		<c:url var="add" value="/addToCart">
			<c:param name="itemId" value="${tempMenu1.id }" />
			<c:param name="theTable" value="ciorbe_supe" />
		</c:url>
			<div class="ui fluid card">
              <div class="image">
                <img src="${pageContext.request.contextPath }/resources/img/fill290240.png">
              </div>
              <div class="content">
                <span class="header" id="short">${tempMenu1.name }</span>
                <div class="meta">
                  <span class="date">Cantitate: ${tempMenu1.weight }g</span>
                </div>
              </div>
              <div class="extra content">
                  <i class="grey large money bill alternate outline icon"></i>
                  <span class="bold right floated">${tempMenu1.price }RON</span>
              </div>
              <div class="extra content">
                      <a href="${add }"><button class="ui fluid olive button">ADD</button></a>
              </div>
            </div>
		</c:forEach>
		</div>
	</div>
	
	<h2 class="ui dividing padleft header weight" id="2">specialitati</h2>  	
		<div class="ui container padd">
       <div class="ui three stackable cards">
		<c:forEach var="tempMenu2" items="${menu2 }" >
		<c:url var="add" value="/addToCart">
			<c:param name="itemId" value="${tempMenu2.id }" />
			<c:param name="theTable" value="specialitati" />
		</c:url>
			<div class="ui fluid card">
              <div class="image">
                <img src="${pageContext.request.contextPath }/resources/img/fill290240.png">
              </div>
              <div class="content">
                <span class="header" id="short">${tempMenu2.name }</span>
                <div class="meta">
                  <span class="date">Cantitate: ${tempMenu2.weight }g</span>
                </div>
              </div>
              <div class="extra content">
                  <i class="grey large money bill alternate outline icon"></i>
                  <span class="bold right floated">${tempMenu2.price }RON</span>
              </div>
              <div class="extra content">
                      <a href="${add }"><button class="ui fluid olive button">ADD</button></a>
              </div>
            </div>
		</c:forEach>
			</div>
		</div>

	<div style="margin-top:25px;"></div>
	
	<h2>Session id is: </h2> ${sessionId }
	
	<div style="margin-top:25px;"></div>
	
	  <table>
		<tr>
			<th>Nume</th>
			<th>Cantitate</th>
			<th>Pret</th>
		</tr>
		
	</table>
	
	<div style="margin-top:25px;"></div>
	<br><br>
	<security:authorize access="hasRole('ADMIN')">
	<a href="${pageContext.request.contextPath }/cook/" id="cookLink">Cook</a>
	</security:authorize>
	
	
    <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
</body>
</html>