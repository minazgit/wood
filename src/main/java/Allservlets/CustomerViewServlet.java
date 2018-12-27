
        
        package Allservlets;

import Models.CustomerPojo;
import Operations.CustomerOperations;

import Operations.WoodOperations;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerViewServlet extends HttpServlet {

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
        CustomerOperations co=new CustomerOperations(scx);
        ArrayList<CustomerPojo> cdetails;
        cdetails = co.getAllCustomerDetails();
        HttpSession hs = request.getSession(true);
        hs.setAttribute("record1", cdetails);
        response.sendRedirect(scx.getContextPath()+"/customerView.jsp");
    }
}
