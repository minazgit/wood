package Dbconnecti0n;

import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JdbcConnection implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {

        ServletContext ctx;
        Connection con;
        try {
            ctx = arg0.getServletContext();
            con = (Connection) ctx.getAttribute("con");
            con.close();

        } catch (Exception cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }

        //Run this before web application is started
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext ctx;
        String url, driver, un, up;
        Connection con;
        System.out.println("ServletContextListener destroyed");
        ctx = arg0.getServletContext();
        driver = ctx.getInitParameter("driver");
        url = ctx.getInitParameter("url");
        un = ctx.getInitParameter("username");
        up = ctx.getInitParameter("password");

        try {
            Class.forName(driver);
            System.out.println("Driver is loaded");

            con = DriverManager.getConnection(url, un, up);
            ctx.setAttribute("con", con);
            System.out.println("Connected");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}
