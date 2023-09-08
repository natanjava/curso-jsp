<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
  
  <jsp:include page="theme-loader.jsp"></jsp:include>
  
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  
                  <jsp:include page="navbarmainmenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      
                      <jsp:include page="page-header.jsp"></jsp:include>
                      
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                    
                                    
			                             <!-- Row start -->
			                            <div class="row">
			                                <!-- Color Open Accordion start -->
			                                <div class="col-lg-12">
			                                    <div class="card">
			                                        <div class="card-header">
			                                            <h5 class="card-header-text">General information</h5>
			                                        </div>
			                                        <div class="card-block accordion-block color-accordion-block">
			                                            <div class="color-accordion" id="color-accordion">
			                                                <a class="accordion-msg b-none waves-effect waves-light">About the System</a>
			                                                <div class="accordion-desc">
			                                                    <p>
			                                                        The system registers, edits, lists and deletes users in the system. At the time of registration, 
			                                                        there are fields to be validated. Registration is only finalized when all fields are validated. 
			                                                        Below the registration form, there are some users who have already been registered in the system. 
			                                                        It can generate reports on users and generate a salary comparison chart. <br><br>
			                                                        			                                                        
			                                                        The initial login to the system is done through the user super-admin (login: super-admin; 
			                                                        password: superadmin; position: ADMIN; salary: $5000.00). 
			                                                    </p>
			                                                </div>
			                                                <a class="accordion-msg bg-dark-primary b-none waves-effect waves-light">User Control - Access Restriction</a>
			                                                <div class="accordion-desc">
			                                                    <p>
			                                                        For the purpose of 'business rules': 
			                                                        Only ADMIN users can create new records. If an ADMIN is created, he cannot access to records created by another ADMIN. 
			                                                        Then each ADMIN has their own list of records and consequently reports and graphs are generated with different results.
			                                                        Not only ADMIN, but also other types of user (ASSISTENT or MANAGER) can access the system, 
			                                                        using login/password, but they cannot register new users in the system. They have no such privilege.
			                                                    </p>
			                                                </div>
			                                                <a class="accordion-msg bg-darkest-primary b-none waves-effect waves-light">ZIP CODE Validation</a>
			                                                <div class="accordion-desc">
			                                                    <p>
			                                                    In the ZIP CODE field, if the user writes a valid number for a Brazilian address, the fields 'Address, Neigborhood, City, State' 
			                                                    will be filled in automatically. You can search for ZIP CODE on Google (from any Brazilian city) before filling in this field or 
			                                                    simply write any number, and manually fill in the ‘Address, Neighborhood, City, State’ fields with any name, just for testing purposes. 
			                                                    Do not use the '-' character in the ZIPCODE field, if you copy this number from a Google search. 2 examples of ZIPCODE, from two 
			                                                    addresses in the city of Rio de Janeiro: 20021000 and 22031000.
			                                                    </p>
			                                                </div>
			                                                <a class="accordion-msg bg-darkest-primary b-none waves-effect waves-light">Tools - Technical information</a>
			                                                <div class="accordion-desc">
			                                                    <p>
			                                                    Eclipse IDE, Postgresql Database, Java Server Pages and Servlets, JavaScript, JasperReports, Bootstrap. 
			                                                    <br> Git Hub Project Link: ---
			                                                    </p>
			                                                </div>
			                                                
			                                            </div>
			                                         </div>
			                                      </div>
			                                 </div>
			                                 <!-- Color Open Accordion ends -->
			                            </div>
			                            <!-- Row end -->
                                        

                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
   
<jsp:include page="javascripfile.jsp"></jsp:include>
<script type="text/javascript">
	
	"use strict";
	$(document).ready(function(){
	        $( function() {
	
	            var icons = {
	                header: "zmdi zmdi-chevron-down",
	                activeHeader: "zmdi zmdi-chevron-up"
	            };
	            $("#multi-open" ).accordion({
	                heightStyle: "content",
	                icons: icons
	            });
	            $( "#sclae-accordion" ).accordion({
	                heightStyle: "content",
	                icons: icons
	            });
	            $( "#single-open" ).accordion({
	                heightStyle: "content",
	                icons: icons
	            });
	            $( "#color-accordion" ).accordion({
	                heightStyle: "content",
	                icons: icons
	            });
	        } );
	
	        if($(".accordion-msg").attr('aria-expanded') == 'true'){
	            $(".accordion-msg").addClass("scale_active");
	        }
	        else{
	            $(".accordion-msg").removeClass("scale_active");
	        }
	    });
	
	
	"use strict";$(document).ready(function(){
			var a={header:"zmdi zmdi-chevron-down",activeHeader:"zmdi zmdi-chevron-up"};
			$("#multi-open").accordion({heightStyle:"content",icons:a});
			$("#sclae-accordion").accordion({heightStyle:"content",icons:a});
			$("#single-open").accordion({heightStyle:"content",icons:a});
			$("#color-accordion").accordion({heightStyle:"content",icons:a});
			
			if($(".accordion-msg").attr("aria-expanded")=="true"){
				$(".accordion-msg").addClass("scale_active")
			}
			else{$(".accordion-msg").removeClass("scale_active")}});
	
</script>



</body>

</html>
    