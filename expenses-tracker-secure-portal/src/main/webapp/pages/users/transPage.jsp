<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/users/nav" />
	<main class="container-fluid p-4">
		<h3>Transactions</h3>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Actions</th>
					<th>Date And Time</th>
					<th>Particulars</th>
					<th>Credit</th>
					<th>Debit</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${trans }" var="t">
					<tr>
						<td><a href="/edit/${t.txnId }"
							class="btn btn-sm btn-info mr-2">EDIT</a> <a
							href="/delete/${t.txnId }" class="btn btn-sm btn-danger">DELETE</a>
						</td>
						<td>${dtFormat.format(t.dateAndTime) }</td>
						<td>${t.header }</td>
						<c:choose>
							<c:when test="${t.tranType eq 'INCOME' }">
								<td class="text-right">${t.amount }</td>
								<td></td>
							</c:when>
							<c:otherwise>
								<td></td>
								<td class="text-right">${t.amount }</td>
							</c:otherwise>
						</c:choose>

					</tr>
				</c:forEach>
			<tfoot>
				<tr>
					<th colspan="3" class="text-left">GROSS</th>
					<th class="text-right">${tranSummary.credit }</th>
					<th class="text-right">${tranSummary.debit }</th>
				</tr>
				<tr>
					<th colspan="4" class="text-left">Net Balance</th>
					<th class="text-right">${tranSummary.balance }</th>
				</tr>
			</tfoot>
			</tbody>
		</table>
	</main>
</body>
</html>