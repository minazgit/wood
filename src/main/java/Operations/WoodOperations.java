
package Operations;

import Dbconnecti0n.JdbcConnection;
import Models.WoodPojo;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletContext;

public class WoodOperations {

    ServletContext ctx;
    Statement stmt;
    Connection con;
    String sql;
    ResultSet rs;

    public WoodOperations(ServletContext ctx) {
        this.ctx = ctx;
    }

    public String insertWood(WoodPojo w) {

        String msg = "";
        try {
            con = (Connection) ctx.getAttribute("con");
            if (con != null) {
                stmt = con.createStatement();

                if (w != null) {                 
                   stmt.executeUpdate("insert into woodtype(woodtype) values('"+w.getWtype()+"')");
                    
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

   
    

   

    public ArrayList<WoodPojo> getAllWoodDetails() {
        int  wid;
        String wtype;
       
        ArrayList<WoodPojo> wooddetails = new ArrayList<WoodPojo>();
        try {
            con = (Connection) ctx.getAttribute("con");
            stmt = con.createStatement();

            rs = stmt.executeQuery("select * from woodtype");

            while (rs.next()) {
                WoodPojo wobj = new WoodPojo();

                wid = rs.getInt(1);
                wtype=rs.getString(2);

                wobj.setWid(wid);
                wobj.setWtype(wtype);
                wooddetails.add(wobj);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return wooddetails;
    }
    
    
    public String deleteWood(int wd) {
        String msg = "";
        try {
            con = (Connection)ctx.getAttribute("con");
            stmt = con.createStatement();
            stmt.executeUpdate("delete from  woodtype where wid="+ wd +"");
            msg = "success";
            stmt.close();
            rs.close();
        } catch (Exception e) {
            msg="error";
            System.out.println(e.getMessage());
        }
        return msg;
    }
    
    public String changeWood(WoodPojo w){
   
     try{
         //System.out.println("116----"+c.getFname());
         con = (Connection)ctx.getAttribute("con");
            stmt = con.createStatement();
    //  wid, woodtype 
       stmt.executeUpdate("update woodtype set woodtype='"+w.getWtype()+"' where wid="+w.getWid()+"");
         System.out.println("118=====op");
        return "success";
        }catch(Exception e)
        {
            System.out.println("msg======"+e.getMessage());
            return "error";
        }
    }
    

}
