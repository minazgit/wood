package Allservlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//jasper file
import java.util.*;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Report1 extends HttpServlet {

    ServletContext scx;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("------in get----");
            myServletMethod(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void myServletMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;

        int sn=Integer.parseInt(request.getParameter("selid"));
        
        //  String abc = scx.getContextPath()+"/images"+"/logo.jpg";
        // true
        String abc=getServletContext().getRealPath("")+"/logo.jpg";         
        // true
       
        System.out.println("=====59====" + abc);

        Map parameters = new HashMap();
//        parameters.put("parameter1","http://localhost:8084/WoodApplication/images/logo.jpg");
        parameters.put("parameter1", abc);
        parameters.put("parameter2", sn);

        String path = getServletContext().getRealPath("/");
        System.out.println("path==" + path);
        jasperDesign = JRXmlLoader.load(path + "report1.jrxml");
        jasperReport = JasperCompileManager.compileReport(jasperDesign);
        byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, (Connection) this.getServletContext().getAttribute("con"));
        System.out.println("--" + byteStream.length);
        File f = new File(path + "woodreport.pdf");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(byteStream);
        response.setContentType("application/pdf");
        OutputStream outStream = response.getOutputStream();
        String headerKey = "Content-Disposition";
        String fn = "measuredetails.pdf";
        String headerValue = String.format("attachment; filename=\"%s\"", fn);
        response.setHeader(headerKey, headerValue);

        response.setContentLength(byteStream.length);
        outStream.write(byteStream, 0, byteStream.length);
    }

//    public void myServletMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
//
//        BillOperation bo = new BillOperation(scx);
//        ArrayList<BillMain> bdetails;
//        bdetails = bo.getJasperTestDetails();
//
//        JRBeanCollectionDataSource jrcs = new JRBeanCollectionDataSource(bdetails, false);
//        try {
//            JasperPrint jp = JasperFillManager.fillReport("E:\\2-Archives-Projects\\JasperReportExample\\web\\ReportExample.jasper", new HashMap(), jrcs);           
//            JasperExportManager.exportReportToPdfFile(jp, "E:\\ReportExample.pdf");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
