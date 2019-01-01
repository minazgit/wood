<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/adminHeader.jsp"%>	   
    </head>
    <body>
      
        <header>		
            <nav class="navbar navbar-default navbar-static-top" role="navigation" >
                <div class="navigation">

                    <div class="container" >	
                       
                        <div class="navbar-header"  >
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse.collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <div class="navbar-brand" >
                                <a href="index.html">
                                    <!--<span><img src="images/3.jpg"></span>-->
                                    <h1><span style="color:#8B4513">Wood</span>Measure </h1>
                                </a>
                            </div>
                        </div>

                        <div class="navbar-collapse collapse" >							
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
                        <h2>Admin Login</h2>					
                    </div>
                    <div class="wow bounceInDown" data-wow-offset="0" data-wow-delay="0.6s">                   
                    </div>
                </div>
            </div>
        </div>

        <div class="container" ><div class="col-md-4"></div>
            <div class="col-md-4" >
                <%String msg = request.getParameter("msglogin");
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
                    <form action="LoginServlet" method="get" role="form" class="contactForm">                    
                        <div class="form-group">
                            <input type="email" class="form-control" name="adminemail" id="adminemail" required  placeholder="Enter Your Email" data-rule="email" data-msg="Please enter a valid email" style="height:38px;"/>
                            <div class="validation"></div>
                        </div>

                        <div class="form-group">
                            <input type="password" name="adminpassword" class="form-control" id="adminpassword" required placeholder="Enter Password" data-rule="minlen:4" data-msg="Please enter at least 4 chars" style="height:38px;" />
                            <div class="validation"></div>
                        </div>

                        <div class="text-center"><button type="submit" class="btn btn-primary btn-lg btnbg" style="height:60px; width: 200px">Submit Details</button></div>
                    </form>
                </div>
            </div>
            <br><br><br><br>
        </div>

        <%@include file="footer.jsp"%>
 <%@include file="footerFiles.jsp"%>
    </body>
</html>