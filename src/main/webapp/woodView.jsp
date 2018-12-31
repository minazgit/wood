<%@page import="Models.WoodPojo"%>
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

        <script src="<%=application.getContextPath()%>/example/js/jquery-ui-1.10.4.custom.min.js"></script>        
        <script src="<%=application.getContextPath()%>/newjs/jquery.dataTables.editable.js"></script>
        <script src="<%=application.getContextPath()%>/newjs/jquery.jeditable.js"></script>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->	
                <script type="text/javascript">
                    $(document).ready(function() {
                       // $('#woodgrid').DataTable();
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
                        <h2>View Wood Details</h2>					
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

                jqTds[2].innerHTML = '<a class="edit" href="">Save</a>';
            }


            function saveRow(oTable, nRow)
            {
                var jqInputs = $('input', nRow);

//                alert(jqInputs.length)
                var wid = jqInputs[0].value;
                var wtype = jqInputs[1].value;

                location.href = "WoodEditServlet?w_id=" + wid + "&w_type=" + wtype;

                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 1, false);
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
                oTable = $("#woodgrid").dataTable({
                    "bScrollCollapse": true,
                    "bPaginate": true,
                    "sPaginationType": "full_numbers",
                 
                    "aLengthMenu": [[3, 5, 10, -1], [3, 5, 10, "All"]],
                    "iDisplayLength": 10
                });





                var nEditing = null;

                $(document).on('click', '#woodgrid a.edit', function(e) {
//                    alert("inedit");
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
//                        alert(nRow);
                        /* Editing this row and want to save it */
//                        alert("inelseif");
                        saveRow(oTable, nEditing);
                        nEditing = null;
                    }
                    else {
                        /* No edit in progress - let's start one */
//                        alert("inelse");
                        editRow(oTable, nRow);
                        nEditing = nRow;
                    }
                });

            });

        </script>


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
                <table id="woodgrid" class=" display table table-striped table-bordered" cellspacing="0" width="100%"  style="color:black ; ">  
                    <thead>	
                        <tr>
                            <th>Wood Id </th>
                            <th>Wood Type</th>                          
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>


                    <tbody style="color:#680000 ; ">
                        <%
                            ArrayList al = (ArrayList) session.getAttribute("record1");
                            if (al != null) {
                                for (int i = 0; i < al.size(); i++) {
                                    WoodPojo w = (WoodPojo) al.get(i);
                                    int wid = w.getWid();
                                    String wtype = w.getWtype();
                        %>

                        <tr>
                            <td><%=wid%></td>
                            <td><%=wtype%></td>
                            <td><a class="edit" href="">Edit</a></td>
                            <td><a href="<%= application.getContextPath()%>/WoodDeleteServlet?selid=<%= wid%>"><i class="fa fa-times" aria-hidden="true"></i></a></td>               

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
        <footer>
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
        </footer>
    </body>
</html>