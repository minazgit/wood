
package Allservlets;

import Operations.AdminOperations;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminForgotPassword extends HttpServlet {

    ServletContext scx;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        scx = getServletContext();

        try {
            scx = sc.getServletContext();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fp;
        String aemail = request.getParameter("adminemail");
       

        AdminOperations ad = new AdminOperations(scx);

        fp = ad.forgotPassword(aemail);
        response.sendRedirect(scx.getContextPath()+"/"+"forgotpassword.jsp?msg="+fp);
      
    }
}
