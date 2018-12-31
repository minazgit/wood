
package AndroServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class InsertAndro extends HttpServlet {
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert Andro");
        try {
            c=(Connection)scx.getAttribute("con");
            System.out.println("Servlet Called");
            String json = request.getParameter("json");
            System.out.println(json);
            JSONObject jaa;
            Statement st = c.createStatement();
            JSONParser jp = new JSONParser();
            jaa = (JSONObject) jp.parse(json);
            String srn = (String) jaa.get("srn");
                String type = (String) jaa.get("type");
                String party = (String)jaa.get("party");
                String total = (String)jaa.get("total");
                int pid=getPartyname(party);
                int wid=getWoodid(type);
                long millis=System.currentTimeMillis();  
java.sql.Date date=new java.sql.Date(millis);
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
String dt=sdf.format(date);
PreparedStatement delstmt = c.prepareStatement("delete from measure_main where srno="+srn);
PreparedStatement delestmt = c.prepareStatement("delete from measure_details where srno="+srn);
delestmt.executeUpdate();
delstmt.executeUpdate();
                PreparedStatement pstmt = c.prepareStatement("insert into measure_main(total,cid,cdate,wid) values(?,?,?,?)");
                pstmt.setString(1, total);
                pstmt.setInt(2,pid);
                pstmt.setString(3, dt);
                pstmt.setInt(4,wid);
                pstmt.executeUpdate();
                PreparedStatement ps;
            JSONArray ja = (JSONArray) jaa.get("array");
            for(int i=0;i<ja.size();i++)
            {
                JSONObject jo = (JSONObject) ja.get(i);
                ps = c.prepareStatement("insert into measure_details values(?,?,?,?,?,?)");
                String width = (String) jo.get("height");
                String thick = (String) jo.get("thick");
                String length = (String) jo.get("length");
                String cfeet = (String)jo.get("cfeet");
                String srno = (String)jo.get("srno");
                ps.setString(1, srno);
                ps.setString(2,length);
                ps.setString(3,width);
                ps.setString(4,thick);
                ps.setString(5, cfeet);
                ps.setString(6,srn);
                ps.executeUpdate();
                out.print("Inserted Succesfully");
            }
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println("JSON Error : "+ex.getLocalizedMessage());
            ex.printStackTrace();
            out.print("Error , Try Again After Sometime");
     } finally {
            out.close();
        }
    }
    public int getPartyname(String name)
    {
        int id = 0;
        StringTokenizer st = new StringTokenizer(name);
        Statement st1;
        ResultSet rs;
        try{
           st1=c.createStatement();
           rs = st1.executeQuery("select cid from customer where fname='"+st.nextToken()+"' and lname='"+st.nextToken()+"'");
           if(rs.next())
           {
               id=rs.getInt(1);
           }
           st1.close();
           rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error : 123 : "+e.getLocalizedMessage());
        }
        return id;
    }
    public int getWoodid(String type)
    {
        int id=0;
        Statement st;
        ResultSet rs;
        try
        {
            st=c.createStatement();
            rs=st.executeQuery("select wid from woodtype where woodtype='"+type+"'");
            if(rs.next())
            {
                id=rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error:155");
            e.printStackTrace();
        }
        return id;
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
