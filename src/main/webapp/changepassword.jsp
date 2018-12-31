<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/adminHeader.jsp"%>	

    </head>
    <body>
        <%@include file="/adminBodyHeader.jsp"%>
<%
        String email=(String)session.getAttribute("adminemail");
        if (email==null)
        {
           response.sendRedirect(application.getContextPath()+"/"+"login.jsp");
        }
        %>

        <div class="contact">
            <div class="container">
                <div class="text-center">
                    <div class="wow bounceInDown" data-wow-offset="0" data-wow-delay="0.3s">
                        <h2>Change Password</h2>					
                    </div>				
                </div>
            </div>
        </div>

        <div class="container">
            <div class="col-md-7">
                <%String msg = request.getParameter("msgser");
                    if (msg != null) {
                %>

                <font color="red"><%=msg%></font>
                <%}%>
            </div>


        </div>

        <div class="contact-form">
            <div class="container">
                <div class="col-md-8 col-md-offset-2">
                    <!--                    <div id="sendmessage">Your message has been sent. Thank you!</div>-->
                    <div id="errormessage"></div>
                    <form action="ChangePasswordServlet" method="post" role="form" class="contactForm">

                        <div class="form-group">
                            <input type="password" required="true" name="oldpassword" class="form-control" id="oldpassword" placeholder="Enter old password" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="password" required="true" name="cnewpassword" class="form-control" id="newpassword" placeholder="Enter new Password" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>
                        <div class="text-center"><button type="submit" class="btn btn-primary btn-lg btnbg" style="height:46px; width: 200px">Submit Details</button></div>
                    </form>
                </div>
            </div>
            <br><br> <br><br><br>
        </div>

        <%@include file="footer.jsp"%>

    </body>
</html>