<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="/adminHeader.jsp"%>	  
  </head>
  <body>
	<header>		
		<nav class="navbar navbar-default navbar-static-top" role="navigation">
			<div class="navigation">
				<div class="container">					
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse.collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<div class="navbar-brand">
							<a href="index.html"><h1><span style="color:#C71585">Aarzu</span>Compact</h1></a>
						</div>
					</div>
					
					<div class="navbar-collapse collapse">							
						<div class="menu">
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation"><a href="login.jsp">Login</a></li>
								<li role="presentation"><a href="forgotpassword.jsp">Forgot Password</a></li>																					
							</ul>
						</div>
					</div>						
				</div>
			</div>	
		</nav>		
	</header>
	
	<div class="contact">
		<div class="container">
			<div class="text-center">
				<div class="wow bounceInDown" data-wow-offset="0" data-wow-delay="0.3s">
					<h2>Forgot Password</h2>					
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="col-md-7">
<!--			<div class="map">				
				<div id="google-map" data-latitude="40.713732" data-longitude="-74.0092704"></div>
			</div>-->
		</div>		
	</div>
      
      
      <div class="container" ><div class="col-md-4"></div>
            <div class="col-md-4" >
                <%String msg = request.getParameter("msg");
                    if (msg != null) {
                %>

                <font style=" color:red; size:20px "><%=msg%></font>
                <%}%>
            </div>
        </div>
      
	
	<div class="contact-form">
		<div class="container">
		    <div class="col-md-8 col-md-offset-2">
    		    <div id="sendmessage">Your message has been sent. Thank you!</div>
                <div id="errormessage"></div>
                <form action="AdminForgotPassword"  method="get" role="form" class="contactForm">
                    
                    <div class="form-group">
                        <input type="email" class="form-control" name="adminemail" id="adminemail" required placeholder="Enter Your Register Email" data-rule="email" data-msg="Please enter a valid email" style="height:45px;"/>
                        <div class="validation"></div>
                    </div>
                    
                    
                    
                    <div class="text-center"><button type="submit" class="btn btn-primary btn-lg btnbg" style="height:65px; width: 200px">Submit Details</button></div>
                </form>
            </div>
        </div>
            <br><br><br>
            
	</div>
	
	 <%@include file="footer.jsp"%>
    
    
</body>
</html>