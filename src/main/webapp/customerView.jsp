<%@page import="Models.CustomerPojo"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/adminHeader.jsp"%>	

              
        <link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet" />
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>
        
        <script src="<%=application.getContextPath()%>/example/js/jquery-ui-1.10.4.custom.min.js"></script>        
        <script src="<%=application.getContextPath()%>/newjs/jquery.dataTables.editable.js"></script>
        <script src="<%=application.getContextPath()%>/newjs/jquery.jeditable.js"></script>

        	

    </head>
    <body>
        <%@include file="/adminBodyHeader.jsp"%>

        <%
        String emaill=(String)session.getAttribute("adminemail");
        if (emaill==null)
        {
           response.sendRedirect(application.getContextPath()+"/"+"login.jsp");
        }
        %>
        
        <div class="contact">
            <div class="container">
                <div class="text-center">
                    <div class="wow bounceInDown" data-wow-offset="0" data-wow-delay="0.3s">
                        <h2>View Customer Details</h2>					
                    </div>				
                </div>
            </div>
        </div>
        
        <!-- For update -->
        <script type="text/javascript">

            function editRow(oTable, nRow)
            {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
//                alert(jqTds.length + "-------");
                jqTds[0].innerHTML = '<input type="text" value="' + aData[0] + '" readOnly>';
                jqTds[1].innerHTML = '<input type="text" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<input type="text" value="' + aData[2] + '">';
                jqTds[3].innerHTML = '<input type="text" value="' + aData[3] + '">';
                jqTds[4].innerHTML = '<input type="text" value="' + aData[4] + '">';
                jqTds[5].innerHTML = '<input type="text" value="' + aData[5] + '">';
                jqTds[6].innerHTML = '<input type="text" value="' + aData[6] + '">';
                jqTds[7].innerHTML = '<input type="text" value="' + aData[7] + '">';
                jqTds[8].innerHTML = '<input type="text" value="' + aData[8] + '">';
                jqTds[9].innerHTML = '<input type="text" value="' + aData[9] + '">';
                jqTds[10].innerHTML = '<a class="edit" href="">Save</a>';
            }


            function saveRow(oTable, nRow)
            {
                var jqInputs = $('input', nRow);
              
                var cid = jqInputs[0].value;
                var fname = jqInputs[1].value;
                var lname = jqInputs[2].value;
                var cno = jqInputs[3].value;
                var email = jqInputs[4].value;
                var add1 = jqInputs[5].value;
                var add2 = jqInputs[6].value;
                var area = jqInputs[7].value;
                var pin= jqInputs[8].value;
                var city = jqInputs[9].value;


                location.href = "CustomerEditServlet?c_id="+ cid + "&c_fname=" + fname + "&c_lname=" + lname + "&c_cno=" + cno + "&c_email=" + email + "&c_add1=" + add1+ "&c_add2=" + add2+ "&c_area=" + area+ "&c_pin=" + pin+ "&c_city=" + city;

                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 10, false);
                oTable.fnDraw();
            }

            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }
                oTable.fnDraw();
            }


            $(document).ready(function() {
                oTable = $("#customergrid").dataTable({
                    "bScrollCollapse": true,
                    "bPaginate": true,
                    "sPaginationType": "full_numbers",
//                    "bJQueryUI": true,
                    "aLengthMenu": [[3, 5, 10, -1], [3, 5, 10, "All"]],
                    "iDisplayLength": 10
                });


                var nEditing = null;

                $(document).on('click', '#customergrid a.edit', function(e) {
                    // alert("inedit");
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];

                    if (nEditing !== null && nEditing != nRow) {
                        /* Currently editing - but not this row - restore the old before continuing to edit mode */
//                        alert("inif");
                        restoreRow(oTable, nEditing);
                        editRow(oTable, nRow);
                        nEditing = nRow;
                    }
                    else if (nEditing == nRow && this.innerHTML == "Save") {
                     
                        /* Editing this row and want to save it */
                       
                        saveRow(oTable, nEditing);
                        nEditing = null;
                    }
                    else {
                        /* No edit in progress - let's start one */
                       
                        editRow(oTable, nRow);
                        nEditing = nRow;
                    }
                });

            });

        </script>
        
        <div class="container" style="margin:0;width: 100%" >
            
            <div class="col-md-2" style="background-color: white;color:black;margin-left:0;">
                Number of Rows
            </div> 
            <div class="col-md-9" >
            </div> 
            <div class="col-md-1" style="background-color: white;color:black;">
                Search
            </div> 
        </div>
        
        <div class="container" style="width:100%" >
            <div class="col-md-12"  style="width:100%;overflow-x: auto;">
                 
                <table id="customergrid" class=" display table table-striped table-bordered" cellspacing="0" width="100%"  style="color:black;overflow-x: scroll ">  
                    <thead>	
                        <tr>
                            <th>Cid</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Contact No</th> 
                            <th>Email</th>
                            <th>Aline1 </th> 
                            <th>Aline2</th>
                            <th>Area</th> 
                            <th>Pincode</th>
                            <th>City</th> 
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>                        
                    </thead>


                    <tbody style="color:#680000 ; ">
                        <%
                            ArrayList al = (ArrayList) session.getAttribute("record1");
                            if (al != null) {
                                for (int i = 0; i < al.size(); i++) {
                                    CustomerPojo c = (CustomerPojo) al.get(i);
                                    int cid = c.getCid();
                                    String fname = c.getFname();
                                    String lname=c.getLname();
                                    long contactno=c.getContactno();
                                    String email=c.getEmail();
                                    String addline1=c.getAddline1();
                                    String addline2=c.getAddline2();
                                    String area=c.getArea();
                                    int pincode=c.getPincode();
                                    String city=c.getCity();
                        %>

                        <tr>
                            <td><%=cid%></td>
                            <td><%=fname%></td>
                            <td><%=lname%></td>
                            <td><%=contactno%></td>
                            <td><%=email%></td>
                            <td><%=addline1%></td>
                            <td><%=addline2%></td>
                            <td><%=area%></td>
                            <td><%=pincode%></td>
                            <td><%=city%></td>
                            <td><a class="edit" href="">Edit</a></td>
                            <td><a href="<%= application.getContextPath()%>/CustomerDeleteServlet?selid=<%= cid%>"><i class="fa fa-times" aria-hidden="true"></i></a></td>               
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
                            &copy; 2018 - Wood Measure  <a href="<%=application.getContextPath()%>/version1.jsp" >(Version 1.0)</a>
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