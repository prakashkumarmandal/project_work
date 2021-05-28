<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
		<jsp:include page="/nav" />
		<main class="container-fluid p-4">
			<header class="jumbotron">
				<h2>Expenses Tracking Portal</h2>
			</header>
			
			<section class="col-sm-5 mx-auto mt-4">
				<c:if test="${errMsg ne null }">
					<div class="alert alert-danger">
						<strong>${errMsg }</strong>
					</div>
				</c:if>
				<form class="form-inline" action="/login" method="POST">
					<input type="email" name="eid" class="form-control" placeholder="emailId" required />
					<input type="password" name="pwd" class="form-control" placeholder="password" required />
					<button class="btn btn-sm btn-primary">Sign In</button>
				</form>
			</section>
		</main>
	</body>
</html>