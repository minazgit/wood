
package Allservlets;

import Operations.AdminOperations;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePasswordServlet extends HttpServlet {

ServletContext scx;

public void init(ServletConfig sc) throws ServletException 
     {        
         super.init(sc);
         scx = getServletContext();

        try {          
            scx = sc.getServletContext();                                 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String change;
        String op=request.getParameter("oldpassword");
        String np=request.getParameter("cnewpassword");
        String msg=null;
        
        AdminOperations ad=new AdminOperations(scx);
        
                change=ad.changePassword(op,np);
             
                if(change.equals("success"))
                {
                    msg="password change successfully";
                    response.sendRedirect(scx.getContextPath()+"/"+"changepassword.jsp?msgser="+msg);
                }
                else
                {   
                    msg="please enter correct password";
                    response.sendRedirect(scx.getContextPath()+"/"+"changepassword.jsp?msgser="+msg);
                }       
    }
}
