<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- Last Update: 26/06/2023, 11h20min -->
<!-- Last Update: 08/09/2023, 11h20min -->


<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	
	<title>System 1</title>
	
<style type="text/css">

form{
 position: absolute;
 top: 30%;
 left: 33%;
 right: 33%;
}


h2{
 position: absolute;
 top: 20%;
 left: 38%;
}
h8 {
 position: absolute;
 top: 27%;
 left: 38%;
}

.msg{
 position: absolute;
 top: 10%;
 left: 33%;
 font-size: 15px;
   color: #664d03;
    background-color: #fff3cd;
    border-color: #ffecb5;

}
</style>	
	
</head>
<body>

 
<h2>Login Page</h2>  
<!-- 
<h8>Logins/Passwords available: <br>
	admin1 / admin1 or
	admin2 / admin2
</h8>
 -->


<form action="<%=request.getContextPath() %>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>

<input type="hidden" value="<%= request.getParameter("url") %>" name="url">

<div class="mb-3">
  <label class="form-label" for="login">Login </label>
  <input class="form-control" id="login" name="login" type="text" required value="super-admin" style="width: 330px;">
   <div class="invalid-feedback">
      Required Field
    </div>
     <div class="valid-feedback">
      Ok
    </div>
 </div> 
 
 <div class="mb-3">
  <label class="form-label" for="senha">Password</label> 
  <input class="form-control" id="senha" value="superadmin" name="senha" type="password" required style="width: 330px;">
   <div class="invalid-feedback">
      Required field
    </div>
    <div class="valid-feedback">
      ok
    </div>
 </div>

  <input type="submit" value="Access" class="btn btn-primary" style="width: 120px; position: relative; left: 100px; top: 20px;">

</form>

<h5 class="msg">${msg}</h5>

   <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>


<script type="text/javascript">
//Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()




</script>
</body>
</html>