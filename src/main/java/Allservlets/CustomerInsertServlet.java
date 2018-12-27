
        
        

package Allservlets;


import Models.CustomerPojo;
import Models.WoodPojo;
import Operations.CustomerOperations;
import Operations.WoodOperations;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomerInsertServlet extends HttpServlet {

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
        
    String fname=request.getParameter("fname");
    String lname=request.getParameter("lname");
    long contactno=Long.parseLong(request.getParameter("cno"));
    String email=request.getParameter("email");
    String addline1=request.getParameter("add1");
    String addline2=request.getParameter("add2");
    String area=request.getParameter("area");
    int pincode;
            try
            {
            pincode=Integer.parseInt(request.getParameter("pcode"));
            }
            catch(Exception e)
            {
                pincode=000000;
            }
            String city=request.getParameter("city");
    if(lname.isEmpty())
    {
        lname="";
    }
    if(email.isEmpty())
    {
        email="";
    }
    if(addline1.isEmpty())
    {
        addline1="";
    }
    if(addline2.isEmpty())
    {
        addline2="";
    }
    if(area.isEmpty())
    {
        area="";
    }
        String savemsg=null;
        
        CustomerOperations co=new CustomerOperations(scx);
        CustomerPojo p=new CustomerPojo();
        
        p.setFname(fname);
        p.setLname(lname);
        p.setContactno(contactno);
        p.setEmail(email);
        p.setAddline1(addline1);
        p.setAddline2(addline2);
        p.setArea(area);
        p.setPincode(pincode);
        p.setCity(city);
               
        savemsg=co.insertCustomer(p);
        out.println(savemsg);
        response.sendRedirect("customerInsert.jsp?msg="+savemsg);
    }
}
        