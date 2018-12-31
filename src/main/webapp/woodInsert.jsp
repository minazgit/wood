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
                        <h2>Wood Type Insert</h2>					
                    </div>				
                </div>
            </div>
        </div>

        <div class="container">
            <div class="col-md-12">
                <%String msg = request.getParameter("msg");
                    if (msg != null) {
                %>
                <center>
                    <font size="6" color="red"><%=msg%></font>
                </center>
                <%}%>
            </div>


        </div>

        <div class="contact-form">
            <div class="container">
                <div class="col-md-8 col-md-offset-2">
                    <!--                    <div id="sendmessage">Your message has been sent. Thank you!</div>-->
                    <div id="errormessage"></div>
                    <form action="WoodInsertServlet" method="post" role="form" class="contactForm">
                        <div class="form-group">
                            <input type="text" required="true" name="wtype" class="form-control" id="wtype" placeholder="Enter wood type" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div><br><br><br><br><br><br>
                        <div class="text-center"><button type="submit" class="btn btn-primary btn-lg btnbg" style="height:46px; width: 200px">Submit Details</button></div>
                    </form>
                </div>
            </div>
            <br><br>
        </div>

        <%@include file="footer.jsp"%>

    </body>
</html>