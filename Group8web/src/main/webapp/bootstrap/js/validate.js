$(document).ready(function(){
$("#alertSuccess").hide();
	$("#errid").hide();
	$("#errname").hide();
	$("#erremail").hide();  //Initially hiding the error spans
	$("#errpass").hide();
	//
	$("#errfname").hide();
	$("#errlname").hide();
	$("#errphone").hide();
	$("#errskills").hide();
	$("#erraddress").hide();
	$("#errcode").hide();
	
	//post defect error message
	$("#errdefectname").hide();
	$("#errdescription").hide();
	$("#errmail").hide();
	$("#errphone").hide();
	$("#errdeadline").hide();
	
	$("#Loginbtn").click(function() {
		var username=$("#lusername").val();
		var password=$("#lpassword").val(); //triggers on click of register
		
		if(username==null || username==""){
			alert("UserName cannot be Empty");
			return false;
		}else if(password=="" || password==null){
			alert("Password cannot be Empty");
			return false;			
		}else{
			$("#alertSuccess").show();
			$("#formid").submit();
			return true;
		}
	});
	
	
	$("#PostDefectbtn").click(function() {
		var defectname=$("#defectName").val();
		var category=$("#category").val(); 
		var photo=$("#photo").val();
		var description=$("#description").val(); 
		var deadline=$("#deadline").val(); 
		
		if(defectname==null || defectname==""){
			alert("Defect name cannot be empty");
			return false;
		}else if(category=="--Select--" || category==null){
			alert("Please select defect category");
			return false;			
		}else if(photo=="" || photo==null){
			alert("Defect photo cannot be empty");
			return false;			
		}else if(description=="" || description==null){
			alert("Defect description cannot be empty");
			return false;			
		}else if(deadline=="" || deadline==null){
			alert("Defect deadline cannot be empty");
			return false;			
		}else{
			$("#alertSuccess").show();
			$("#formid").submit();
			return true;
		}
	  });
	
	$("#Rbtn").click(function() {
		var userid=$("#id").val();
		var username=$("#username").val();
		var password=$("#password").val(); //triggers on click of register
		var firstname=$("#fname");
		var lastname=$("#lname");
		var phone=$("#phone");
		var skills=$("#skills");
		var address=$("#address");
		var code=$("#code");	
		var email=$("#email").val();
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		var letters = /^[A-Za-z]+$/;
		//post defect variables
		var defectname=$("#defectname");
		var category=$("#category");
		var photo=$("#photo");
		var description=$("#description");
		var deadline=$("#deadline");
		
	 if(username==null || username==""){
			alert("UserName cannot be Empty");
			return false;
		}else if(password=="" || password==null){
			alert("Password cannot be Empty");
			return false;
			
		}else if(firstname =="" || firstname==null){
			alert("Firstname cannot be Empty");
			return false;
			
		}else if(lastname=="" || lastname==null){
			alert("Lastname cannot be Empty");
			return false;
			
		}else if(phone=="" || phone==null){
			alert("Phone Number cannot be Empty");
			return false;
			
		}else if(skills=="" || skills==null){
			alert("Technician skills cannot be Empty");
			return false;
			
		}else if(address=="" || address==null){
			alert("Address cannot be Empty");
			return false;
			
		}else if(code=="" || code==null){
			alert("Postal code cannot be Empty");
			return false;
			
		}else if(email=="" || email==null){
			
			alert("Email cannot be Empty");
			return false;
		}else if(!(email.match(mailformat))){
			alert("Please Enter Valid Mail ID");

		return false;
		}else if(!(username.match(letters))){
			alert("Please Enter only Characters for Username");
			return false;
		}else{
			$("#alertSuccess").show();
			$("#formid").submit();
			return true;
		}
		
		});

	
	$("#id").change(function(){
		var userid=$("#id").val();   //here we are restricting the user at the time of typing,we called an event "Keyup"
		 if(isNaN(userid)){
				
				$("#errid").show();  //if user enters other than number then the error span will be shown
				return false;
			}else{
				$("#errid").hide();


			return true;
			}
		
		
	});
	$("#username").change(function(){
		var username=$("#username").val();
		 var letters = /^[A-Za-z]+$/;

		 if(!(username.match(letters))){
			 $("#errname").show();

			 return false;
			}else{
				$("#errname").hide();
				return true;
			}
		
	});
	$("#email").change(function(){
		var email=$("#email").val();

		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(!(email.match(mailformat))){
			 $("#erremail").show();


		return false;
		}else{
			 $("#erremail").hide();
		return true;
			
			
		}
		
	});
	
	$("#cpassword").change(function(){
		var password=$("#password").val();
		var cpassword=$("#cpassword").val();
		if(!(password==cpassword)){
			 $("#errpass").show();
			return false;	
			
		}else{
			 $("#errpass").hide();

			return true;
		}		
	});	
	
	$("#fname").change(function(){
		var firstname=$("#fname").val();
		 var letters = /^[A-Za-z]+$/;

		 if(!(firstname.match(letters))){
			 $("#errfname").show();

			 return false;
			}else{
				$("#errfname").hide();
				return true;
			}
		
	});
	
	$("#lname").change(function(){
		var lastname=$("#lname").val();
		 var letters = /^[A-Za-z]+$/;

		 if(!(lastname.match(letters))){
			 $("#errlname").show();

			 return false;
			}else{
				$("#errlname").hide();
				return true;
			}
		
	});
	
	$("#phone").change(function(){
		var phone=$("#phone").val();   //here we are restricting the user at the time of typing,we called an event "Keyup"
		 if(isNaN(phone)){
				
				$("#errphone").show();  //if user enters other than number then the error span will be shown
				return false;
			}else{
				$("#errphone").hide();


			return true;
			}
		
		
	});
	

	

	
	$("#description").change(function(){
		var defectname=$("#description").val();
		 var letters = /^[A-Za-z]+$/;

		 if(!(defectname.match(letters))){
			 $("#errdescription").show();

			 return false;
			}else{
				$("#errdescription").hide();
				return true;
			}
		
	});
	
	
	$("#Rbtn").click(function(){
		var deadlinedate=$("#deadline").val();
		 var letters = /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/;

		 if(!(deadlinedate.match(letters))){
			 $("#errdeadline").show();

			 return false;
			}else{
				$("#errdeadline").hide();
				return true;
			}
		
	});
	
	
	
	$('#Rbtn').click( function(){
        var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
        var Val_date=$('#deadline').val();
            if(Val_date.match(dateformat)){
           var seperator1 = Val_date.split('/');
           var seperator2 = Val_date.split('-');

           if (seperator1.length>1)
           {
               var splitdate = Val_date.split('/');
           }
           else if (seperator2.length>1)
           {
               var splitdate = Val_date.split('-');
           }
           var dd = parseInt(splitdate[0]);
           var mm  = parseInt(splitdate[1]);
           var yy = parseInt(splitdate[2]);
           var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
           if (mm==1 || mm>2)
           {
               if (dd>ListofDays[mm-1])
               {
                   alert('Invalid date format!');
                   return false;
               }
           }
           if (mm==2)
           {
               var lyear = false;
               if ( (!(yy % 4) && yy % 100) || !(yy % 400))
               {
                   lyear = true;
               }
               if ((lyear==false) && (dd>=29))
               {
                   alert('Invalid date format!');
                   return false;
               }
               if ((lyear==true) && (dd>29))
               {
                   alert('Invalid date format!');
                   return false;
               }
           }
       }
       else
       {
           alert("Invalid date format!");

           return false;
       }
    });
	
});