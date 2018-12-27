package Allservlets;

import Models.MeasureDetails;

import Operations.MeasureOperations;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MeasureDetailEditServlet extends HttpServlet {

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

        int no = Integer.parseInt(request.getParameter("j_no"));
        double ln = Double.parseDouble(request.getParameter("j_lt"));
        double ht = Double.parseDouble(request.getParameter("j_ht"));
        double th = Double.parseDouble(request.getParameter("j_tk"));

        double cf = (ln * ht * th)/144;
        int sno = Integer.parseInt(request.getParameter("j_sn"));

        MeasureOperations mo = new MeasureOperations(scx);
        MeasureDetails md = new MeasureDetails();

        md.setPid(no);
        md.setLength(ln);
        md.setHeight(ht);
        md.setThickness(th);
        md.setCubic_feet(cf);

        String msg = "";
        String check = mo.changeMeasure(md);

        if (check.equals("success")) {

            msg = "update successfully";
        }
        response.sendRedirect(scx.getContextPath() + "/MeasureDetailViewServlet?selid=" + sno);
    }
}
