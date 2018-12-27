package Allservlets;

import Models.MeasureDetails;


import Operations.MeasureOperations;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MeasureDetailViewServlet extends HttpServlet {

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
        System.out.println("======servlet---in");
        
        int sn=Integer.parseInt(request.getParameter("selid"));
        System.out.println(sn +"-------------------39");
        MeasureOperations  mo = new MeasureOperations(scx);
        ArrayList<MeasureDetails> mdetails;
        mdetails = mo.getAllMeasureDetails(sn);
        HttpSession hs = request.getSession(true);
        hs.setAttribute("record1", mdetails);
        response.sendRedirect(scx.getContextPath() + "/measureDetailsView.jsp");
    }
}
