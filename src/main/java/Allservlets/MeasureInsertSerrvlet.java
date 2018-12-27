

package Allservlets;


import Models.WoodPojo;
import Operations.WoodOperations;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MeasureInsertSerrvlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out=response.getWriter();
        
        boolean check;
        String wtype=request.getParameter("wtype");
        
        String savemsg=null;
        
        WoodOperations wo=new WoodOperations(scx);
        WoodPojo w=new WoodPojo();
        
        w.setWtype(wtype);
               
        savemsg=wo.insertWood(w);
        out.println(savemsg);       
    }
}
        