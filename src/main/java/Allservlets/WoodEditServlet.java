    
package Allservlets;


import Models.WoodPojo;
import Operations.WoodOperations;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WoodEditServlet extends HttpServlet {

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
        
        
    int wid =Integer.parseInt(request.getParameter("w_id"));
    String wtype=request.getParameter("w_type");
        
        
        WoodOperations wo= new WoodOperations(scx);
        WoodPojo wp=new WoodPojo();
        
        wp.setWid(wid);
        wp.setWtype(wtype);
                
        String msg="";
         String check=wo.changeWood(wp);

        if(check.equals("success")){
            
           msg="update successfully";
        }
        response.sendRedirect(scx.getContextPath() + "/WoodViewServlet"); 
      
    }
}
