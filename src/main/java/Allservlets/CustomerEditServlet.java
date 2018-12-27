
        
        
package Allservlets;

import Models.CustomerPojo;
import Operations.CustomerOperations;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerEditServlet extends HttpServlet {

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
        
        
    int cid =Integer.parseInt(request.getParameter("c_id"));
    String fname=request.getParameter("c_fname");
        System.out.println("======fname"+fname);
    String lname=request.getParameter("c_lname");
    long contactno=Long.parseLong(request.getParameter("c_cno"));
    String email=request.getParameter("c_email");
    String addline1=request.getParameter("c_add1");
    String addline2=request.getParameter("c_add2");
    String area=request.getParameter("c_area");
    int pincode=Integer.parseInt(request.getParameter("c_pin"));
    String city=request.getParameter("c_city");
        
        CustomerOperations co= new CustomerOperations(scx);
        CustomerPojo cu=new CustomerPojo();
        
        cu.setCid(cid);
        cu.setFname(fname);
        cu.setLname(lname);
        cu.setContactno(contactno);
        cu.setEmail(email);
        cu.setAddline1(addline1);
        cu.setAddline2(addline2);
        cu.setArea(area);
        cu.setPincode(pincode);
        cu.setCity(city);
        
        String msg="";
         String check=co.changeCustomer(cu);

        if(check.equals("success")){
            
           msg="update successfully";
        }
        response.sendRedirect(scx.getContextPath() + "/CustomerViewServlet"); 
      
    }
}
