
package Operations;

import Dbconnecti0n.JdbcConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;

public class AdminOperations {

    ServletContext ctx;
    Statement stmt;
    Connection con;
    String sql;
    ResultSet rs;

    public AdminOperations(ServletContext ctx) {
        this.ctx = ctx;
    }

    public String checkLoginDetails(String un, String up) {

        String msg = "";

        try {
            con = (Connection) ctx.getAttribute("con");

            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from admininfo");

            rs.next();
            {
                String uname = rs.getString(1);
                String upass = rs.getString(2);

                if (un.equals(uname) && up.equals(upass)) {
                    msg = "success";
                    return msg;
                }
                if (!un.equals(uname)) {
                    msg = "wrongemail";
                }

                if (!up.equals(upass)) {
                    msg = "wrongpassword";
                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return msg;
    }

    public String changePassword(String op, String np) {
        con = (Connection) ctx.getAttribute("con");
        String msg = null;

        try {

            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from admininfo");

            rs.next();
            {
                String oldp = rs.getString(2);

                if (op.equals(oldp)) {

                    stmt.executeUpdate("update admininfo set adminpassword='" + np + "'");
                    msg = "success";
                } else {
                    msg = "error";
                }
            }
            stmt.close();
            rs.close();
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return msg;
    }

    public String forgotPassword(String aemail) {
        String msg = null;
        con = (Connection) ctx.getAttribute("con");

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select adminpassword from admininfo where adminemail='" + aemail + "'");
            rs.next();
            {
                String oldp = rs.getString(1);
                System.out.println("oldp");
                msg = oldp;
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            msg = "error";
            System.out.println(e.getMessage());
        }
        return msg;
    }
}
