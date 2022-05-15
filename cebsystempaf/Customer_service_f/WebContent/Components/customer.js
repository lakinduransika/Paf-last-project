$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateCustomerForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "CustomerManage",   
			type : type,  
			data : $("#formCustomer").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onCustomerSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onCustomerSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divCustomerGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidCustomerIDSave").val("");  
	$("#formCustomer")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());     
	$("#c_name").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#c_address").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#c_email").val($(this).closest("tr").find('td:eq(2)').text());
	$("#c_nic").val($(this).closest("tr").find('td:eq(3)').text());   
	$("#c_phone").val($(this).closest("tr").find('td:eq(4)').text()); 
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "CustomerManage",   
		type : "DELETE",   
		data : "C_ID=" + $(this).data("customerid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onCustomerDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onCustomerDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divCustomerGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateCustomerForm() 
{  
	// NAME-----------------------
	if ($("#c_name").val().trim() == "")  
	{   
		return "Insert Name.";  
	}
	
	
	// ADDRESS---------------------------  
	if ($("#c_address").val().trim() == "")  
	{   
		return "Insert Address.";  
	}

	
	// EMAIL---------------------------  
	if ($("#c_email").val().trim() == "")  
	{   
		return "Insert Email.";  
	}

	
	// NIC------------------------------
	if ($("#c_nic").val().trim() == "")  
	{   
		return "Insert NIC.";  
	}
	
	// AMOUNT-------------------------------
	 var tmpAmount = $("#c_phone").val().trim();
	if (!$.isNumeric(tmpAmount)) 
	 {
	 return "Insert Amount.";
	 }
	
	
	return true; 
}