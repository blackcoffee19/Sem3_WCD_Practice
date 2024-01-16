<%@page import="java.util.List"%>
<%@page import="vn.aptech.entity.Phone"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Phones</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6 col-md-8 mx-auto">
				<c:if test="${sessionScope.msg!=null}">
					<p class="alert bg-success mt-3 text-uppercase text-white"><c:out value="${sessionScope.msg }"/></p>
				<c:remove var="msg"/>
				</c:if>
				<c:if test="${sessionScope.error!=null}">
					<p class="alert bg-danger mt-3 text-uppercase text-white"><c:out value="${sessionScope.error}"/></p>
				<c:remove var="error"/>
				</c:if>
				<h1 class="my-3">List Phones</h1>
				<a href="ProcessServlet?a=DisplayCreate" class="btn btn-outline-primary">Create	New</a>
				<hr />
				
				<table class="table">
					<thead>
						<tr>
							<th>pId</th>
							<th>Name</th>
							<th>Price</th>
							<th>Image</th>
							<th colspan="2">Actions</th>
						</tr>
					</thead>
					<tbody>
					<% if(request.getAttribute("phones")!=null){
						List<Phone> phones = (List)request.getAttribute("phones");
						for(Phone item : phones){
					%>	
						<tr>
							<td><%=item.getPId()%></td>
							<td><%=item.getName()%></td>
							<td>$<%=item.getPrice()%></td>
							<td><img alt="<%=item.getName() %>" src="images/<%=item.getImage()%>" width="130" class="img-fluid"/>
							</td>
							<td>
								<a href="ProcessServlet?a=DisplayUpdate&b=<%=item.getPId()%>" class="text-warning text-center">Update</a>
							</td>
							<td>
								<form action="${pageContext.request.contextPath}/ProcessServlet?a=Delete" method="post" onsubmit="return confirm('Do you want to delete this Phone?');">
									<input type="hidden" name="id" value="<%=item.getPId()%>" />
									<input type="hidden" name="image" value="<%=item.getImage()%>" />
									<input type="submit" value="Delete" class="text-danger border-0 bg-white text-decoration-underline"/>
								</form>
							</td>
						</tr>
					<%}}else{%>
					
					<%} %>
						<%-- <c:forEach items="${phones}" var="item">
							<tr>
								<td>${item.pId}</td> // Property [pId] not found on type [vn.aptech.entity.Phone]
								<td>${item.name}</td>
								<td>${item.price}</td>
								<td>
									<img alt="${item.name}" src="images/${item.image}" width="130" class="img-fluid"/>
								</td>
								<td>
									<a href="ProcessServlet?a=DisplayUpdate&b=${item.pId}" class="text-warning text-center">Update</a>
								</td>
								<td>
									<form action="${pageContext.request.contextPath}/ProcessServlet?a=Delete" method="post" onsubmit="return confirm('Do you want to delete this Phone?');">
										<input type="hidden" name="id" value="${item.pId}" />
										<input type="hidden" name="image" value="${item.image }" />
										<input type="submit" value="Delete" class="text-danger border-0 bg-white text-decoration-underline"/>
									</form>
								</td>
							</tr>
						</c:forEach> --%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>