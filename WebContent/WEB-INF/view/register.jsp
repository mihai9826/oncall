<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${exists != null }">	
		<h2>Email alredy taken</h2>
</c:if>

	<form:form action="saveUser" modelAttribute="user" method="POST" class="ui form">
	<div class="ui card m-top centered">
          <div class="content center aligned">
            <div class="header"><i class="users icon"></i> Sign up form</div>
          </div>
          <div class="content">
			<div class="field">		
				<label>First Name:</label>
				<form:input path="firstName" />
			</div>
		
			<div class="field">			
				<label>Last Name:</label>
				<form:input path="lastName" />
			</div>
		
			<div class="field">			
				<label>Email:</label>
				<form:input path="userName" />
			</div>
		
			<div class="field">			
				<label>Address:</label>
				<form:input path="address" />
			</div>
					
			<div class="field">
				<label>Password:</label>
				<form:password path="password" />
			</div>
		</div>
          <div class="extra content center aligned">
          <button class="ui button">
          <i class="user plus icon"></i>
			 <input type="submit" value="Sign up" id="submit"/>
			   </button>
          </div>
     </div>	
		
		</form:form>

</body>
</html>