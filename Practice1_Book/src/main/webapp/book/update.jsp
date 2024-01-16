<%@page import="vn.aptech.entity.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Book</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-6 col-md-8 mx-auto">
			<h2 class="my-5">Update new Book</h2>
			<a href="ProcessServlet?a=DisplayList">Back</a>
			<hr/>
			<%if( request.getAttribute("book") !=null){ Book book = (Book) request.getAttribute("book");
			%>
			<form action="${pageContext.request.contextPath}/ProcessServlet?a=Update" method="post" >
				<div class="card">
					<div class="card-body">
						<div class="mb-3">
							<label class="form-label" for="code">Book Code <span class="text-danger">(cannot change Book Code)</span></label>
							<input type="text" name="code" value="${book.bookCode}" class="form-control"  readonly="readonly"/>
						</div>
						<div class="mb-3">
							<label class="form-label" for="title">Book Title</label>
							<input type="text" name="title" id="title" class="form-control" value="${book.title}" />
						</div>
						<div class="mb-3">
							<label class="form-label" for="price">Price</label>
							<input type="text" name="price" id="price" class="form-control" value="${book.price}" />
						</div>
						<div class="mb-3">
							<label class="form-label" for="publisher">Publisher</label>
							<input type="text" name="publisher" id="publisher" class="form-control" value="${book.publisher}" />
						</div>
					</div>
					<div class="card-footer">
						<input type="submit" value="Create" class="btn btn-primary" />
					</div>					
				</div>
			</form>
			
			
			<% }%>
		</div>
	</div>
</div>
</body>
</html>