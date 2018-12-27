package Allservlets;

import Models.MeasureDetails;
import Models.MeasureMainView;

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

public class MeasureMainViewServlet extends HttpServlet {

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
        MeasureOperations mo = new MeasureOperations(scx);
        ArrayList<MeasureMainView> mainview;
        mainview = mo.getAllMeasureMain();
        HttpSession hs = request.getSession(true);
        hs.setAttribute("record1", mainview);
        response.sendRedirect(scx.getContextPath() + "/measureMainView.jsp");
    }
}
