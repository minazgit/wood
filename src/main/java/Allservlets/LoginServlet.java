package Allservlets;

import Operations.AdminOperations;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

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

        String check;
        HttpSession hslogin = null;

        String un = request.getParameter("adminemail");
        String up = request.getParameter("adminpassword");

        System.out.println(un + "======" + up);

        String msg = null;

        AdminOperations ad = new AdminOperations(scx);

        check = ad.checkLoginDetails(un, up);

        if (check.equals("success")) {
            hslogin = request.getSession(true);

            hslogin.setAttribute("adminemail", un);
            hslogin.setAttribute("adminpassword", up);

            response.sendRedirect(scx.getContextPath() + "/" + "adminPannel.jsp");

        }
        if (check.equals("wrongemail")) {
            msg = "wrong email";
            response.sendRedirect(scx.getContextPath() + "/" + "login.jsp?msglogin=" + msg);
        }
        if (check.equals("wrongpassword")) {
            msg = "wrong password";
            response.sendRedirect(scx.getContextPath() + "/" + "login.jsp?msglogin=" + msg);
        }
    }

}
