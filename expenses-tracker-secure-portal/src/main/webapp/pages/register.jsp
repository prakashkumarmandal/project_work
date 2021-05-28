<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/nav" />
	<main class="container-fluid p-4">
		<section class="col-sm-4 mx-auto">
			<h3>User Registration</h3>
			
			<form:form modelAttribute="user" method="POST">
				<div class="form-group">
					<form:label path="userName" class="form-control-label">Full Name</form:label>
					<form:input path="userName" type="text" class="form-control"/>
					<form:errors path="userName" />
				</div>
				<div class="form-group">
					<form:label path="emailId" class="form-control-label">Email Id</form:label>
					<form:input path="emailId" type="email" class="form-control"/>
					<form:errors path="emailId" />
				</div>
				<div class="form-group">
					<form:label path="password" class="form-control-label">Password</form:label>
					<form:input path="password" type="password" class="form-control"/>
					<form:errors path="password" />
				</div>
				<button class="btn btn-primary btn-block">SignUp</button>
			</form:form>
		</section>

	</main>
</body>
</html>