<%@ page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/customer.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Customer Manage</h1>
				<form id="formCustomer" name="formCustomer" method="post" action="CustomerUI.jsp">  
					Customer Name:  
 	 				<input id="c_name" name="c_name" type="text"  class="form-control form-control-sm">
					<br>Address:   
  					<input id="c_address" name="c_address" type="text" class="form-control form-control-sm">   
  					<br>Email:   
  					<input id="c_email" name="c_email" type="text"  class="form-control form-control-sm">
  					<br>NIC:   
  					<input id="c_nic" name="c_nic" type="text"  class="form-control form-control-sm">
  					<br>Phone No:   
  					<input id="c_phone" name="c_phone" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divCustomerGrid">
					<%
					    Customer CustomerObj = new Customer();
						out.print(CustomerObj.readCustomer());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>