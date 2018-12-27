package Operations;

import Models.Measure;
import Models.MeasureDetails;
import Models.MeasureMainView;
import java.util.ArrayList;
import java.sql.*;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;

public class MeasureOperations {

    ServletContext ctx;
    Statement stmt;
    Connection con;
    String sql;
    ResultSet rs;
    PreparedStatement pstmt1, pstmt2;
    private int srno;

    public MeasureOperations(ServletContext ctx) {
        this.ctx = ctx;
        con = (Connection) ctx.getAttribute("con");
    }

    public MeasureOperations(Connection c) {
        con = c;
    }

    public String insertMeasure(Measure mobj, ArrayList<MeasureDetails> mdetails) {
        String msg = "";
        try {

            if (con != null) {
                int n = mdetails.size();
                double sum = 0.0, s;
                for (int i = 0; i < n; i++) {
                    s = mdetails.get(i).getLength() + mdetails.get(i).getHeight() + mdetails.get(i).getThickness();
                    sum = sum + s;
                }
                pstmt1 = con.prepareStatement("insert into measure_main(total,cid,cdate,wid) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

                pstmt1.setDouble(1, sum);
                pstmt1.setInt(2, mobj.getCid());
                pstmt1.setString(3, mobj.getCdate());
                pstmt1.setInt(4, mobj.getWid());

                pstmt1.executeUpdate();
                rs = pstmt1.getGeneratedKeys();

                rs.next();
                srno = rs.getInt(1);

                pstmt2 = con.prepareStatement("insert into measure_details(length, height, thickness, cubic_feet, srno) values(?,?,?,?,?)");

                for (int i = 0; i < n; i++) {
                    pstmt2.setDouble(2, mdetails.get(i).getLength());
                    pstmt2.setDouble(3, mdetails.get(i).getHeight());
                    pstmt2.setDouble(4, mdetails.get(i).getThickness());
                    pstmt2.setDouble(5, (mdetails.get(i).getLength() + mdetails.get(i).getHeight() + mdetails.get(i).getThickness() / 144));
                    pstmt2.setInt(6, srno);
                }
                msg = "success";
            } else {
                msg = "error";
            }
        } catch (Exception e) {
            msg = "error";
            System.out.println(e.getMessage());
        }
        return msg;
    }

//    public String insertWood(WoodPojo w) {
//        String msg = "";
//        try {
//            con = (Connection) ctx.getAttribute("con");
//            if (con != null) {
//                stmt = con.createStatement();
//
//                if (w != null) {                 
//                   stmt.executeUpdate("insert into woodtype(woodtype) values('"+w.getWtype()+"')");
//                    
//                    msg = "success";
//
//                } else {
//                    msg = "error";
//                }
//            } else {
//                msg = "error";
//            }
//            stmt.close();
//
//        } catch (Exception e) {
//            msg = "error";
//            System.out.println(e.getMessage());
//        }
//
//        return msg;
//    }
    public ArrayList<MeasureDetails> getAllMeasureDetails(int sno) {
        int pid;
        double length;
        double height;
        double thickness;
        double cubic_feet;
        int sn;

        ArrayList<MeasureDetails> mesdetail = new ArrayList<MeasureDetails>();
        try {
            con = (Connection) ctx.getAttribute("con");
            stmt = con.createStatement();

            rs = stmt.executeQuery("select * from measure_details where srno=" + sno + "");
            System.out.println("======109=======");
            while (rs.next()) {
                System.out.println("====111======");
                MeasureDetails mesobj = new MeasureDetails();

                pid = rs.getInt(1);
                length = rs.getDouble(2);
                height = rs.getDouble(3);
                thickness = rs.getDouble(4);
                cubic_feet = rs.getDouble(5);
                sn = rs.getInt(6);
                System.out.println("=====118" + pid);
                mesobj.setPid(pid);
                mesobj.setLength(length);
                mesobj.setHeight(height);
                mesobj.setThickness(thickness);
                mesobj.setCubic_feet(cubic_feet);
                mesobj.setSrno(sn);
                mesdetail.add(mesobj);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mesdetail;
    }

//    public String deleteWood(int wd) {
//        String msg = "";
//        try {
//            con = (Connection)ctx.getAttribute("con");
//            stmt = con.createStatement();
//            stmt.executeUpdate("delete from  woodtype where wid="+ wd +"");
//            msg = "success";
//            stmt.close();
//            rs.close();
//        } catch (Exception e) {
//            msg="error";
//            System.out.println(e.getMessage());
//        }
//        return msg;
//    }
    public ArrayList<MeasureMainView> getAllMeasureMain() {
        int srno;
        String fn;
        String ln;
        String dt;
        double total;
        String wt;

        ArrayList<MeasureMainView> msview = new ArrayList<MeasureMainView>();
        try {
            con = (Connection) ctx.getAttribute("con");
            stmt = con.createStatement();

            rs = stmt.executeQuery("select mm.srno,cs.fname,lname,mm.cdate,mm.total,wt.woodtype from woodproject.measure_main  mm\n"
                    + "inner join  woodproject.woodtype wt on wt.wid=mm.wid\n"
                    + "inner join woodproject.customer  cs  on mm.cid=cs.cid;");
            System.out.println("======172=======");
            while (rs.next()) {
                System.out.println("====174======");
                MeasureMainView mvsobj = new MeasureMainView();

                srno = rs.getInt(1);
                fn = rs.getString(2);
                ln = rs.getString(3);
                dt = rs.getString(4);
             /*   System.out.println("183---"+dt);
                StringTokenizer st=new  StringTokenizer(dt,"-");
                String yy=st.nextToken();
                String mm=st.nextToken();
                String dd=st.nextToken();
                dt=dd+"-"+mm+"-"+yy;*/
                total = rs.getDouble(5);
                wt = rs.getString(6);

                mvsobj.setSrno(srno);
                mvsobj.setFn(fn);
                mvsobj.setLn(ln);
                mvsobj.setDt(dt);
                mvsobj.setTotal(total);
                mvsobj.setWt(wt);

                msview.add(mvsobj);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return msview;
    }

    public int getSrno() {
        PreparedStatement stmt;
        ResultSet rs;
        int srno;
        int max = 0;
        String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'woodproject' AND   TABLE_NAME   = 'measure_main'";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                srno = rs.getInt("AUTO_INCREMENT");
                
                    max = srno;
                
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error Getting Srno : " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return max;
    }

    public String changeMeasure(MeasureDetails m) {

        try {
            System.out.println("234--------");
            con = (Connection) ctx.getAttribute("con");
            stmt = con.createStatement();
            System.out.println("sql----" + "update measure_details set length=" + m.getLength() + ", height=" + m.getHeight() + ", thickness=" + m.getThickness() + ", cubic_feet=" + m.getCubic_feet() + " where pid="+m.getPid() + "");
            stmt.executeUpdate("update measure_details set length="+m.getLength()+", height=" +m.getHeight()+", thickness="+m.getThickness()+", cubic_feet="+m.getCubic_feet()+" where pid="+m.getPid()+"");
            System.out.println("118=====op");
            return "success";
        } catch (Exception e) {
            System.out.println("msg======" + e.getMessage());
            return "error";
        }
    }

}
