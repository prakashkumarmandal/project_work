<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	<jsp:include page="/userNav" />
		<main class="container-fluid p-4">
			<div class="col-sm-4 mt-2 mx-auto">
				<form:form method="POST" modelAttribute="tran">
					<form:hidden path="txnId"/>
					<div class="form-group">
						<form:label path="header" class="form-label">Header</form:label>
						<form:input path="header" class="form-control" />
						<form:errors path="header" />
					</div>
					<div class="form-group">
						<form:label path="amount" class="form-label">Amount</form:label>
						<form:input path="amount" type="decimal" class="form-control" />
						<form:errors path="amount" />
					</div>
					<div class="form-group">
						<form:label path="dateAndTime" class="form-label">Date And Time</form:label>
						<form:input path="dateAndTime" type="datetime-local" class="form-control" />
						<form:errors path="dateAndTime" />
					</div>
					<div class="form-group">
						<form:label path="tranType" class="form-label">Transaction Type</form:label>
						<form:select path="tranType" class="form-control">
							<form:option value="">---SELECT--</form:option>
							<form:options items="${txnTypes }" />
						</form:select>
						<form:errors path="tranType" />
					</div>
					<button class="btn btn-primary btn-block">Save Transaction</button>
				</form:form>
			</div>
		</main>
	</body>
</html>