$(function() {

  $("#regForm input,#regForm textarea").jqBootstrapValidation({
    preventSubmit: true,
    submitError: function($form, event, errors) {
      // additional error messages or events
    },
    submitSuccess: function($form, event) {
      event.preventDefault(); // prevent default submit behaviour
      // get values from FORM
      var fname = $("input#fname").val();
      var lname = $("input#lname").val();
      var username = $("input#username").val();
      var password = $("input#password").val();
      var code = $("input#code").val();
      var email = $("input#email").val();
      var phone = $("input#phone").val();
      var address = $("textarea#address").val();
      //var firstName = name; // For Success/Failure Message
      // Check for white space in name for Success/Fail message
      if ((fname.indexOf(' ') >= 0)||(lname.indexOf(' ') >= 0)) {
        fname = fname.split(' ').slice(0, -1).join(' ');
        lname = lname.split(' ').slice(0, -1).join(' ');
      }
      $this = $("#sendMessageButton");
      $this.prop("disabled", true); // Disable submit button until AJAX call is complete to prevent duplicate messages
      $.ajax({
        url: "././mail/register.php",
        type: "POST",
        data: {
          fname: fname,
          lname: lname,
          phone: phone,
          email: email,
          username: username,
          password: password,
          code: code,
          address: address
        },
        cache: false,
        success: function() {
			//console.log(success);
          // Success message
          $('#successnow').html("<div class='alert alert-success'>");
          $('#successnow > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#successnow > .alert-success')
            .append("<strong>Your account has been created. </strong>");
          $('#successnow > .alert-success')
            .append('</div>');
          //clear all fields
          $('#regForm').trigger("reset");
        },
        error: function() {
          // Fail message
          $('#successnow').html("<div class='alert alert-danger'>");
          $('#successnow > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            .append("</button>");
          $('#successnow > .alert-danger').append($("<strong>").text("Sorry. Please try again later!"));
          $('#successnow > .alert-danger').append('</div>');
          //clear all fields
          $('#regForm').trigger("reset");
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
$('#fname').focus(function() {
  $('#successnow').html('');
});
