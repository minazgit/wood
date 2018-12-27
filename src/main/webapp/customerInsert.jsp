<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/adminHeader.jsp"%>	

    </head>
    <body>
        <%@include file="/adminBodyHeader.jsp"%>


        <div class="contact">
            <div class="container">
                <div class="text-center">
                    <div class="wow bounceInDown" data-wow-offset="0" data-wow-delay="0.3s">
                        <h2>Customer</h2>					
                    </div>				
                </div>
            </div>
        </div>

        <div class="container">
            <div class="col-md-7">
                <%String msg = request.getParameter("msg");
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
                    <form action="CustomerInsertServlet" method="post" role="form" class="contactForm">
                        <div class="form-group">
                            <input type="text" required="true" name="fname" class="form-control" id="fname" placeholder="Company Name"   />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="lname" class="form-control" id="lname"    />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text" required="true"  name="cno" class="form-control" id="cno" placeholder="Contact Number"   />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text"  name="email" class="form-control" id="email" placeholder="Email"   />
                            <div class="validation"></div>
                        </div>

                        <div class="form-group">
                            <input type="text"  name="add1" class="form-control" id="add1" placeholder="Address line 1"   />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text"  name="add2" class="form-control" id="add2" placeholder="Address line 2"   />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text"  name="area" class="form-control" id="area" placeholder="Area"   />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text"  name="pcode" class="form-control" id="pcode" placeholder="pincode"   />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text" required="true" name="city" class="form-control" id="city" placeholder="city"   />
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