<%@ page pageEncoding="UTF-8" %>

<%--
<%
   String un=(String)session.getAttribute("adminemail");
   if(un==null)
   {
       response.sendRedirect(application.getContextPath()+"/login.jsp");
   }

%>
--%>
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
                        <a href="<%=application.getContextPath()%>/adminPannel.jsp"><h1><span style="color:#8B4513">Sahjanand</span> Saw Mill</h1></a>                        
                    </div>
                </div>

                <div class="navbar-collapse collapse">							
                    <div class="menu">
                        <ul class="nav nav-tabs" role="tablist">                                  
                            <li role="presentation"><a href="<%=application.getContextPath()%>/woodInsert.jsp">Add Wood </a></li>	
                            <li role="presentation"><a href="<%=application.getContextPath()%>/WoodViewServlet">View Wood</a></li>
                            <li role="presentation"><a href="<%=application.getContextPath()%>/customerInsert.jsp">Add Customer</a></li>
                            <li role="presentation"><a href="<%=application.getContextPath()%>/CustomerViewServlet">View Customer</a></li>
                            <li role="presentation"><a href="<%=application.getContextPath()%>/MeasureMainViewServlet">Measure main</a></li>                 

                        </ul>
                    </div>
                </div>						
            </div>
        </div>	
    </nav>		
</header>