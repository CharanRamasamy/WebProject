$(function() {

  $("#loginForm input").jqBootstrapValidation({
    preventSubmit: true,
    submitError: function($form, event, errors) {
      // additional error messages or events
    },
    submitSuccess: function($form, event) {
      event.preventDefault(); // prevent default submit behaviour
      // get values from FORM
      var name = $("input#lusername").val();
      var password = $("input#lpassword").val();
      var username = name; // For Success/Failure Message
	  console.log('username===>'+username+ ', password==>'+password );
      // Check for white space in name for Success/Fail message
      if (username.indexOf(' ') >= 0) {
        username = name.split(' ').slice(0, -1).join(' ');
      }
      $this = $("#sendLoginButton");
      $this.prop("disabled", true); // Disable submit button until AJAX call is complete to prevent duplicate messages
      $.ajax({
        url: "././mail/login.php",
        type: "POST",
        data: {
          name: username,
          password: password
        },
        cache: false,
        error: function() {
          // Success message
          $('#success').html("<div class='alert alert-success'>");
          $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#success > .alert-success')
            .append("<strong>Login Successful. </strong>");
          $('#success > .alert-success')
            .append('</div>');
          //clear all fields
          $('#loginForm').trigger("reset");
		  setTimeout(function() {
           window.location.href = './customerpage.html';
          }, 1000);
		  
        },
        success: function() {
          // Fail message
          $('#success').html("<div class='alert alert-danger'>");
          $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#success > .alert-danger').append($("<strong>").text("Sorry, Please try again later!"));
          $('#success > .alert-danger').append('</div>');
          //clear all fields
          $('#loginForm').trigger("reset");
        },
        complete: function() {
          setTimeout(function() {
            $this.prop("disabled", false); // Re-enable submit button when AJAX call is complete
          }, 1000);
        }
      });
    },
    filter: function() {
      return $(this).is(":visible");
    },
  });

  $("a[data-toggle=\"tab\"]").click(function(e) {
    e.preventDefault();
    $(this).tab("show");
  });
});


$(function() {

  $("#technicianLoginForm input").jqBootstrapValidation({
    preventSubmit: true,
    submitError: function($form, event, errors) {
      // additional error messages or events
    },
    submitSuccess: function($form, event) {
      event.preventDefault(); // prevent default submit behaviour
      // get values from FORM
      var name = $("input#lusername").val();
      var password = $("input#lpassword").val();
      var username = name; // For Success/Failure Message
    console.log('username===>'+username+ ', password==>'+password );
      // Check for white space in name for Success/Fail message
      if (username.indexOf(' ') >= 0) {
        username = name.split(' ').slice(0, -1).join(' ');
      }
      $this = $("#sendTechnicianLoginButton");
      $this.prop("disabled", true); // Disable submit button until AJAX call is complete to prevent duplicate messages
      $.ajax({
        url: "././mail/login.php",
        type: "POST",
        data: {
          name: username,
          password: password
        },
        cache: false,
          error: function() {
          // Success message
          $('#success').html("<div class='alert alert-success'>");
          $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#success > .alert-success')
            .append("<strong>Login Successful. </strong>");
          $('#success > .alert-success')
            .append('</div>');
          //clear all fields
          $('#loginForm').trigger("reset");
      setTimeout(function() {
           window.location.href = './technicianpage.html';
          }, 1000);
      
        },
        success: function() {
          // Fail message
          $('#success').html("<div class='alert alert-danger'>");
          $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#success > .alert-danger').append($("<strong>").text("Sorry, Please try again later!"));
          $('#success > .alert-danger').append('</div>');
          //clear all fields
          $('#loginForm').trigger("reset");
        },
        complete: function() {
          setTimeout(function() {
            $this.prop("disabled", false); // Re-enable submit button when AJAX call is complete
          }, 1000);
        }
      });
    },
    filter: function() {
      return $(this).is(":visible");
    },
  });

  $("a[data-toggle=\"tab\"]").click(function(e) {
    e.preventDefault();
    $(this).tab("show");
  });
});


/*When clicking on Full hide fail/success boxes */
$('#username').focus(function() {
  $('#success').html('');
});
