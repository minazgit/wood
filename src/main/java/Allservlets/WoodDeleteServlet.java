
package Allservlets;

import Operations.WoodOperations;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WoodDeleteServlet extends HttpServlet {

ServletContext scx;

@Override
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        WoodOperations wo= new WoodOperations(scx);
        
        int wid=Integer.parseInt(request.getParameter("selid"));
               String msg2=wo.deleteWood(wid);   
               if(msg2.equals("success"))
               {
                   msg2="Delete successfully";
               }              
               response.sendRedirect(scx.getContextPath() + "/WoodViewServlet");       
    }
}
