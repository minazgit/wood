<%@page import="Models.MeasureMainView"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/adminHeader.jsp"%>	

        <!-- table  -->        
        <link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet" />
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->	
        <script type="text/javascript">
            $(document).ready(function() {
                $('#mviewgrid').DataTable(
                 {
        "order": [[ 0, "desc" ]]
    }         
                );
            });
        </script>
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
                        <h2>Wood Measure Details</h2>					
                    </div>
                </div>
            </div>
        </div>
       <div class="container">
            <div class="col-md-6 col-md-offset-1"  >
<h4 style="color:green">Click Sr. No. to view more details</h4>
            </div>           
        </div>
        
        
        <div class="container" >
            <div class="col-md-1" >

            </div> 
            <div class="col-md-2" style="background-color: white;color:black;">
                Number of Rows
            </div> 
            <div class="col-md-6" >
            </div> 
            <div class="col-md-2" style="background-color: white;color:black;">
                Search
            </div> 
        </div>


        <div class="container">
            <div class="col-md-10 col-md-offset-1">
                <table id="mviewgrid" class=" display table table-striped table-bordered" cellspacing="0" width="100%"  style="color:black ; ">  
                    <thead>	
                        <tr>
                            <th>Sr.No. </th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Date</th>
                            <th>Wood Type</th>
                            <th>Total</th>
                            <th>Report</th>
                        </tr>
                    </thead>

                    <tfoot>
                        <!--                        <tr>
                                                    <th>Wood Id </th>
                                                    <th>Wood Type</th>                               
                                                </tr>-->
                    </tfoot>
                    <tbody style="color:#680000 ; ">
                        <%
                            ArrayList al = (ArrayList) session.getAttribute("record1");
                            if (al != null) {
                                for (int i = 0; i < al.size(); i++) {
                                    MeasureMainView m = (MeasureMainView)al.get(i);
                                    int srno=m.getSrno();
                                    String fn=m.getFn();
                                    String ln=m.getLn();
                                    String dt=m.getDt();
                                    double total=m.getTotal();
                                    String wt=m.getWt();
                        %>

                        <tr>
                            <td><a href="<%= application.getContextPath()%>/MeasureDetailViewServlet?selid=<%= srno%>"><%=srno%></a></td>
                            <td><%=fn%></td>
                            <td><%=ln%></td>
                            <td><%=dt%></td>
                            <td><%=wt%></td>
                            <td><%=total%></td>
                            
                            <td><a href="<%= application.getContextPath()%>/Report1?selid=<%= srno%>">Report1</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%= application.getContextPath()%>/Report2?selid=<%= srno%>">Report2 </a></td>               
                        </tr>

                        <% }
                            }
                            session.removeAttribute("record1");
                        %>
                    </tbody>
                </table>

            </div>
                   
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
           
        </div>
<!--        <footer>
            <div class="sub-footer " >
                <div class="container">
                    <div class="row" >
                        <div class="col-md-6">
                            &copy; 2018 - Wood Measure   <a href="<%=application.getContextPath()%>/version1.jsp" >(Version 1.0)</a>
                            <br>   Devloped By   <a href="http://www.archivessoftdesign.in/">Archives Soft Design (opc) Pvt. Ltd.</a><br>
                             By   <a href="http://www.hitsolution.co.in/">Hirva IT Solution</a><br>
                        </div>
                    </div>
                </div>
            </div>
        </footer>-->
<%@include file="footer.jsp"%>
    </body>
</html>