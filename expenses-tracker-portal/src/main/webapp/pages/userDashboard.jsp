<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	<jsp:include page="/userNav" />
		<main class="container-fluid p-4">
			<header class="jumbotron">
				<h2>Expenses Tracking Portal</h2>
			</header>
			
			<h3> Welcome ${currentUser.userName }!</h3>
		</main>
	</body>
</html>