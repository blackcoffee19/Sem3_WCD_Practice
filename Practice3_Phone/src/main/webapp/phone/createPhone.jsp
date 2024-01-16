<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Phone</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-6 col-md-8 mx-auto">
			<h2 class="my-5">Create new Phone</h2>
			<a href="ProcessServlet">Back</a>
			<c:if test="${sessionScope.error!=null}">
				<p class="alert bg-danger mt-3 text-uppercase text-white"><c:out value="${sessionScope.error}"/></p>
			<c:remove var="error"/>
			</c:if>
			<hr/>
			<form action="${pageContext.request.contextPath}/ProcessServlet?a=Create" method="post" enctype="multipart/form-data">
				<div class="card">
					<div class="card-body">
						<div class="mb-3">
							<label class="form-label" for="name">Phone Name</label>
							<input type="text" name="name" id="name" class="form-control" placeholder="Enter Phone Name" />
						</div>
						<div class="mb-3">
							<label class="form-label" for="price">Price</label>
							<input type="text" name="price" id="price" class="form-control" placeholder="Enter Phone Price" />
						</div>
						<div class="mb-3">
							<label class="form-label" for="photo">Image</label>
							<input type="file" name="photo" id="photo" class="form-control" />
						</div>
					</div>
					<div class="card-footer">
						<input type="submit" value="Create" class="btn btn-primary" />
					</div>					
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>