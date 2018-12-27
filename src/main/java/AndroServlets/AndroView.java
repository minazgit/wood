
package AndroServlets;

import Operations.MeasureOperations;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class AndroView extends HttpServlet {

    ServletContext scx;
    Connection c;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("-----in pr------42");
        try {
            c=(Connection)scx.getAttribute("con");
            if(c!=null)
            {
                MeasureOperations mo = new MeasureOperations(c);
                int srno=mo.getSrno();
                System.out.println("Srno : "+srno);
                JSONObject jo = new JSONObject();
                PreparedStatement pstmt = c.prepareStatement("select * from customer");
                ResultSet rs = pstmt.executeQuery();
                JSONArray ja = new JSONArray();
                while(rs.next())
                {
                    ja.add(rs.getString("fname"));
                }
                jo.put("customer", ja);
                PreparedStatement pstmt1 = c.prepareStatement("select * from woodtype");
                JSONArray jaa = new JSONArray();
                ResultSet rss = pstmt1.executeQuery();
                while(rss.next())
                {
                    jaa.add(rss.getString("woodtype"));
                }
                jo.put("type", jaa);
                jo.put("srno", srno);
                out.print(jo);
                pstmt.close();
                rs.close();
                pstmt1.close();
                rss.close();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error 76 " );
            e.printStackTrace();
        }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AndroView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AndroView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
