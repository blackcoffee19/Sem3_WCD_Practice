<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body>
<div class="container-fluid" style="height: 550px">
	<div class="d-flex flex-row w-100 h-100 justify-content-center align-items-center">
		<div class="w-25">
			<h1 class="mt-3">Login</h1>
			<form action="j_security_check" method="post">
				<div class="mb-3">
					<label class="form-label">Username:</label>
					<input type="text" class="form-control" name="j_username"/>
				</div>
				<div class="mb-3">
					<label class="form-label">Password:</label>
					<input type="password" class="form-control" name="j_password"/>
				</div>
				<div class="mb-3">
					<input type="submit" class="btn btn-primary" value="Login"/>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>