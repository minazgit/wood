
package Operations;

import Models.CustomerPojo;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletContext;

public class CustomerOperations {

    ServletContext ctx;
    Statement stmt;
    Connection con;
    String sql;
    ResultSet rs;

    public CustomerOperations(ServletContext ctx) {
        this.ctx = ctx;
    }

    public String insertCustomer(CustomerPojo p) {

        String msg = "";
        try {
            con = (Connection) ctx.getAttribute("con");
            if (con != null) {
                stmt = con.createStatement();

                if (p != null) {
                    stmt.executeUpdate("insert into customer(fname, lname, contactno, email, addline1, addline2, area, pincode,city) values('" + p.getFname() + "','" + p.getLname() + "'," + p.getContactno() + ",'" + p.getEmail() + "','" + p.getAddline1() + "','" + p.getAddline2() + "','" + p.getArea() + "'," + p.getPincode() + ",'" + p.getCity() + "')");

                    msg = "success";

                } else {
                    msg = "error";
                }
            } else {
                msg = "error";
            }
            stmt.close();

        } catch (Exception e) {
            msg = "error";
            System.out.println(e.getMessage());
        }

        return msg;
    }

    public ArrayList<CustomerPojo> getAllCustomerDetails() {
        String sid;

        ArrayList<CustomerPojo> cdetails = new ArrayList<CustomerPojo>();
        try {
            con = (Connection) ctx.getAttribute("con");
            stmt = con.createStatement();

            rs = stmt.executeQuery("select * from customer");

            while (rs.next()) {
                CustomerPojo cobj = new CustomerPojo();

                int cid = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                long contactno = rs.getLong(4);
                String email = rs.getString(5);
                String addline1 = rs.getString(6);
                String addline2 = rs.getString(7);
                String area = rs.getString(8);
                int pincode = rs.getInt(9);
                String city = rs.getString(10);

                cobj.setCid(cid);
                cobj.setFname(fname);
                cobj.setLname(lname);
                cobj.setContactno(contactno);
                cobj.setEmail(email);
                cobj.setAddline1(addline1);
                cobj.setAddline2(addline2);
                cobj.setArea(area);
                cobj.setPincode(pincode);
                cobj.setCity(city);
                cdetails.add(cobj);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cdetails;
    }
    
    
    public String deleteCustomer(int cd) {
        String msg = "";
        try {
            con = (Connection)ctx.getAttribute("con");
            stmt = con.createStatement();
            stmt.executeUpdate("delete from  customer where cid="+cd +"");
            msg = "success";
            stmt.close();
            rs.close();
        } catch (Exception e) {
            msg="error";
            System.out.println(e.getMessage());
        }
        return msg;
    }
    
    
    public String changeCustomer(CustomerPojo c){
   
     try{
         System.out.println("116----"+c.getFname());
         con = (Connection)ctx.getAttribute("con");
            stmt = con.createStatement();
        //cid, fname, lname, contactno, email, addline1, addline2, area, pincode, city;
       stmt.executeUpdate("update customer set fname='"+c.getFname()+"',lname='"+c.getLname()+"' ,contactno="+c.getContactno()+",email='"+c.getEmail()+"',addline1='"+c.getAddline1()+"',addline2='"+c.getAddline2()+"',area='"+c.getArea()+"',pincode="+c.getPincode()+" ,city='"+c.getCity()+"' where cid="+c.getCid()+"");
         System.out.println("118=====op");
        return "success";
        }catch(Exception e)
        {
            System.out.println("msg======"+e.getMessage());
            return "error";
        }
    }
}
