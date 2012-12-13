/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author reget.kalamees
 */
public class MkDb extends HttpServlet {
    
    @Override
    public void init(){
    try{
    Class.forName("org.hsqldb.jdbcDriver");
   } catch (ClassNotFoundException e){
    throw new RuntimeException(e);
   }
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {String serr="";
            if(request.getParameter("mkdb")!=null){
            serr =setupDatabase();
            }
            if(request.getParameter("add")!=null){
                serr=addData();
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MkDb</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MkDb at " + request.getContextPath() + "</h1>");
            out.println("<h1>Baas " + serr + "</h1>");
             out.println("<form method=\"GET\">");
            out.println("<input type=\"hidden\"  name=\"mkdb\"><br /> ");
            out.println("<input type=\"submit\" value=\"Tee baas\">");
            out.println("</form >");
            out.println("<form method=\"GET\">");
            out.println("<input type=\"hidden\" name=\"add\"><br />");
            out.println("<input type=\"submit\" value=\"Sisesta testandmed\">");
            out.println("</form >");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    
    private static String setupDatabase()  {

  String erro="OK";
  Connection conn=null;

  Statement stmt = null;
  ResultSet rset = null; // et midagi välja printida, praegu pole vaja

  try {
   conn = DriverManager.getConnection("jdbc:hsqldb:file:${user.home}/i377/Team03d/piirivalveDb;shutdown=true");
   stmt = conn.createStatement();
   stmt.executeUpdate("CREATE TABLE RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_lik_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, kood VARCHAR(10) NOT NULL, nimetus VARCHAR(100) NOT NULL, kommentaar LONGVARCHAR, alates DATE NOT NULL, kuni DATE NOT NULL, PRIMARY KEY (riigi_admin_yksuse_lik_id))");
   stmt.executeUpdate("CREATE UNIQUE INDEX XPKRIIGI_ADMIN_YKSUSE_LIIK ON RIIGI_ADMIN_YKSUSE_LIIK ( riigi_admin_yksuse_lik_id )");
   stmt.executeUpdate("CREATE TABLE RIIGI_ADMIN_YKSUS ( riigi_admin_yksus_ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, kood VARCHAR(20), nimetus VARCHAR(100), kommentaar LONGVARCHAR, alates DATE NOT NULL, kuni DATE NOT NULL, riigi_admin_yksuse_lik_id INTEGER NOT NULL, PRIMARY KEY (riigi_admin_yksus_ID), FOREIGN KEY (riigi_admin_yksuse_lik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT )");
   stmt.executeUpdate("CREATE UNIQUE INDEX XPKRIIGI_ADMIN_YKSUS ON RIIGI_ADMIN_YKSUS ( riigi_admin_yksus_ID )");
   stmt.executeUpdate("CREATE INDEX XIF5RIIGI_ADMIN_YKSUS ON RIIGI_ADMIN_YKSUS ( riigi_admin_yksuse_lik_id )");
   stmt.executeUpdate("CREATE TABLE ADMIN_ALLUVUS ( admin_alluvus_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, alates CHAR(18), kuni CHAR(18), kommentaar CHAR(18), ylemus_yksus_ID INTEGER NOT NULL, alluv_yksus_ID INTEGER NOT NULL, PRIMARY KEY (admin_alluvus_id), FOREIGN KEY (alluv_yksus_ID) REFERENCES RIIGI_ADMIN_YKSUS ON DELETE RESTRICT, FOREIGN KEY (ylemus_yksus_ID) REFERENCES RIIGI_ADMIN_YKSUS ON DELETE RESTRICT );");
   stmt.executeUpdate("CREATE UNIQUE INDEX XPKADMIN_ALLUVUS ON ADMIN_ALLUVUS ( admin_alluvus_id )");
   stmt.executeUpdate("CREATE INDEX XIF3ADMIN_ALLUVUS ON ADMIN_ALLUVUS ( ylemus_yksus_ID )");
   stmt.executeUpdate("CREATE INDEX XIF4ADMIN_ALLUVUS ON ADMIN_ALLUVUS ( alluv_yksus_ID )");
   stmt.executeUpdate("CREATE TABLE VOIMALIK_ALLUVUS ( voimalik_alluvus_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, riigi_admin_yksuse_lik_id INTEGER NOT NULL, voimalik_alluv_liik_ID INTEGER NOT NULL, kommentaar LONGVARCHAR, PRIMARY KEY (voimalik_alluvus_id), FOREIGN KEY (voimalik_alluv_liik_ID) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT, FOREIGN KEY (riigi_admin_yksuse_lik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT )");
   stmt.executeUpdate("CREATE UNIQUE INDEX XPKVOIMALIK_ALLUVUS ON VOIMALIK_ALLUVUS ( voimalik_alluvus_id )");
   stmt.executeUpdate("CREATE INDEX XIF6VOIMALIK_ALLUVUS ON VOIMALIK_ALLUVUS ( riigi_admin_yksuse_lik_id )");
   stmt.executeUpdate("CREATE INDEX XIF7VOIMALIK_ALLUVUS ON VOIMALIK_ALLUVUS ( voimalik_alluv_liik_ID )");
  
   
   //loome tabeli RIIGI_ADMIN_YKSUSE_Liik
   /*
    * CREATE TABLE RIIGI_ADMIN_YKSUSE_LIIK ( riigi_admin_yksuse_lik_id INTEGER, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, kood VARCHAR(10) NOT NULL, nimetus VARCHAR(100) NOT NULL, kommentaar LONGVARCHAR, alates DATE NOT NULL, kuni DATE NOT NULL, PRIMARY KEY (riigi_admin_yksuse_lik_id) );
CREATE UNIQUE INDEX XPKRIIGI_ADMIN_YKSUSE_LIIK ON RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_lik_id );
CREATE TABLE RIIGI_ADMIN_YKSUS ( riigi_admin_yksus_ID INTEGER, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, kood VARCHAR(20), nimetus VARCHAR(100), kommentaar LONGVARCHAR, alates DATE NOT NULL, kuni DATE NOT NULL, riigi_admin_yksuse_lik_id INTEGER NOT NULL, PRIMARY KEY (riigi_admin_yksus_ID), FOREIGN KEY (riigi_admin_yksuse_lik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT );
CREATE UNIQUE INDEX XPKRIIGI_ADMIN_YKSUS ON RIIGI_ADMIN_YKSUS ( riigi_admin_yksus_ID );
CREATE INDEX XIF5RIIGI_ADMIN_YKSUS ON RIIGI_ADMIN_YKSUS ( riigi_admin_yksuse_lik_id );
CREATE TABLE ADMIN_ALLUVUS ( admin_alluvus_id INTEGER, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, alates CHAR(18), kuni CHAR(18), kommentaar CHAR(18), ylemus_yksus_ID INTEGER NOT NULL, alluv_yksus_ID INTEGER NOT NULL, PRIMARY KEY (admin_alluvus_id), FOREIGN KEY (alluv_yksus_ID) REFERENCES RIIGI_ADMIN_YKSUS ON DELETE RESTRICT, FOREIGN KEY (ylemus_yksus_ID) REFERENCES RIIGI_ADMIN_YKSUS ON DELETE RESTRICT );
CREATE UNIQUE INDEX XPKADMIN_ALLUVUS ON ADMIN_ALLUVUS ( admin_alluvus_id );
CREATE INDEX XIF3ADMIN_ALLUVUS ON ADMIN_ALLUVUS ( ylemus_yksus_ID );
CREATE INDEX XIF4ADMIN_ALLUVUS ON ADMIN_ALLUVUS ( alluv_yksus_ID );
CREATE TABLE VOIMALIK_ALLUVUS ( voimalik_alluvus_id INTEGER, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32), suletud DATE NOT NULL, riigi_admin_yksuse_lik_id INTEGER NOT NULL, voimalik_alluv_liik_ID INTEGER NOT NULL, kommentaar LONGVARCHAR, PRIMARY KEY (voimalik_alluvus_id), FOREIGN KEY (voimalik_alluv_liik_ID) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT, FOREIGN KEY (riigi_admin_yksuse_lik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT );
CREATE UNIQUE INDEX XPKVOIMALIK_ALLUVUS ON VOIMALIK_ALLUVUS ( voimalik_alluvus_id );
CREATE INDEX XIF6VOIMALIK_ALLUVUS ON VOIMALIK_ALLUVUS ( riigi_admin_yksuse_lik_id );
CREATE INDEX XIF7VOIMALIK_ALLUVUS ON VOIMALIK_ALLUVUS ( voimalik_alluv_liik_ID );
 

           stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, kood VARCHAR(10) NOT NULL ,nimetus VARCHAR(100) NOT NULL,kommentaar LONGVARCHAR NOT NULL,avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL, PRIMARY KEY (riigi_admin_yksuse_liik_id) ) ");
            //sisestame sinna administratiivyksused
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'mk','maakond','suurim v�imalik administratiiv�ksus Eesti vabariigis, sisaldab linnu ja valdasid','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'ln','linn','suuruselt teine admin �ksus, sisaldub maakonnas,  Tallinn sisaldab linnaosasid','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'v','vald','samuti suuruselt teine admin �ksus, sisaldub maakonnas, v�ib sisaldada ka linna, sisaldab aleveid, alevikke ja k�lasid','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'a','alev','sisaldub vallas','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");

            //loome tabeli VOIMALIK_ALLUVUS NB! Rings6ltuvused pole lubatud, aga praegu vist on
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VOIMALIK_ALLUVUS (riigi_admin_yksuse_liik_id INTEGER, voimalik_alluv_liik_id INTEGER, avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL ) ");
            //seame valisv6tmed VOIMALIK ALLUVUS tulpadele
            stmt.executeUpdate("ALTER TABLE VOIMALIK_ALLUVUS  ADD CONSTRAINT Voimalik_Alluvus_CR_FK_ylem FOREIGN KEY (riigi_admin_yksuse_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id) ON DELETE RESTRICT");
            stmt.executeUpdate("ALTER TABLE VOIMALIK_ALLUVUS  ADD CONSTRAINT Voimalik_Alluvus_CR_FK_alam FOREIGN KEY (voimalik_alluv_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id) ON DELETE RESTRICT");
        
            // proov otse mudelist SQL käske tekitada
           // stmt.executeUpdate("CREATE TABLE VOIMALIK_ALLUVUS (voimalik_alluvus_id  INTEGER, avaja VARCHAR(32) NOT NULL, avatud DATE NOT NULL, muutja VARCHAR(32) NOT NULL, muudetud DATE NOT NULL, sulgeja VARCHAR(32),suletud DATE NOT NULL, riigi_admin_yksuse_lik_id INTEGER NOT NULL, voimalik_alluv_liik_ID INTEGER NOT NULL, kommentaar LONG VARCHAR, PRIMARY KEY (voimalik_alluvus_id), FOREIGN KEY (voimalik_alluv_liik_ID) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT, FOREIGN KEY (riigi_admin_yksuse_lik_id)  REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT)");
            //indeksid - TODO
           
            //lisame alluvused
            stmt.executeUpdate(" INSERT INTO VOIMALIK_ALLUVUS  VALUES (1,2,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO VOIMALIK_ALLUVUS  VALUES (1,3,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");

            //loome tabeli RIIGI_ADMIN_YKSUS
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RIIGI_ADMIN_YKSUS (riigi_admin_yksus_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, kood VARCHAR(20) NOT NULL ,nimetus VARCHAR(100) NOT NULL,kommentaar LONGVARCHAR NOT NULL,avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL, riigi_admin_yksuse_liik_id INTEGER, PRIMARY KEY (riigi_admin_yksus_id) ) ");
            //lisame valisv6tme
            stmt.executeUpdate("ALTER TABLE RIIGI_ADMIN_YKSUS ADD CONSTRAINT Riigi_admin_yksus_CR_FK FOREIGN KEY (riigi_admin_yksuse_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id);");
            //lisame andmed
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'V145','Leisi vald','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',3 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (null, 'V146','Orissaare vald','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',3 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'Mk100','Harju maakond','suurima rahvastikutihedusega','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',1)");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'Mk123','Saare maakond','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',1 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'L234','Kuressaare linn','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',2 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (null, 'A456','Orissaare alev','asub Saare mk-s, Orissaare vallas','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',4 )");

            //loome tabeli ADMIN_ALLUVUS
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ADMIN_ALLUVUS (ylemus_yksus_id INTEGER , alluv_yksus_id INTEGER,avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL ) ");
            //seame valisv6tmed
            stmt.executeUpdate("ALTER TABLE ADMIN_ALLUVUS ADD CONSTRAINT Admin_alluvus_CR_FK_ylem FOREIGN KEY (ylemus_yksus_id) REFERENCES RIIGI_ADMIN_YKSUS (riigi_admin_yksus_id)");
            stmt.executeUpdate("ALTER TABLE ADMIN_ALLUVUS ADD CONSTRAINT Admin_alluvus_CR_FK_alam FOREIGN KEY (alluv_yksus_id) REFERENCES RIIGI_ADMIN_YKSUS (riigi_admin_yksus_id)");
            //lisame andmed
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (1,1,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' );");
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (4,5,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (2,6,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
 */
  
 
  } 
  catch(Exception err){
  System.out.println(err.getMessage());
  erro= err.getMessage();
  }
  finally {
   DbUtils.closeQuietly(rset);
   DbUtils.closeQuietly(stmt);
   DbUtils.closeQuietly(conn);
   return erro;
  }

 }
    
 private static String addData(){
 String erro="OK";
  Connection conn=null;

  Statement stmt = null;
  ResultSet rset = null; // et midagi välja printida, praegu pole vaja

  try {
      conn = DriverManager.getConnection("jdbc:hsqldb:file:${user.home}/i377/Team03d/piirivalveDb;shutdown=true");
   stmt = conn.createStatement();
             //sisestame sinna administratiivyksused

            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','mk','maakond','suurim v�imalik administratiiv�ksus Eesti vabariigis, sisaldab linnu ja valdasid',CURRENT_DATE,'9999-12-31')");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','ln','linn','suuruselt teine admin �ksus, sisaldub maakonnas,  Tallinn sisaldab linnaosasid',CURRENT_DATE,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','v','vald','samuti suuruselt teine admin �ksus, sisaldub maakonnas, v�ib sisaldada ka linna, sisaldab aleveid, alevikke ja k�lasid',CURRENT_DATE,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','a','alev','sisaldub vallas',CURRENT_DATE,'9999-12-31' )");

            /*
            stmt.executeUpdate(" INSERT INTO VOIMALIK_ALLUVUS  VALUES (1,2,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO VOIMALIK_ALLUVUS  VALUES (1,3,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            */
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','V145','Leisi vald','asub Saare mk-s','2000-01-01','9999-12-31',3 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31', 'V146','Orissaare vald','asub Saare mk-s','2000-01-01','9999-12-31',3 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','Mk100','Harju maakond','suurima rahvastikutihedusega','2000-01-01','9999-12-31',1)");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','Mk123','Saare maakond','asub Saare mk-s','2000-01-01','9999-12-31',1 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES ( null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','L234','Kuressaare linn','asub Saare mk-s','2000-01-01','9999-12-31',2 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31', 'A456','Orissaare alev','asub Saare mk-s, Orissaare vallas','2000-01-01','9999-12-31',4 )");
            

         //lisame andmed
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','eilsest','ülehomsega','komm',4,1 );");
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' ,'eilsest','ülehomsega','komm',4,2)");
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31','eilsest','ülehomsega','no komm',2,6 )");

  
 
  } 
  catch(Exception err){
  System.out.println(err.getMessage());
  erro= err.getMessage();
  }
  finally {
   DbUtils.closeQuietly(rset);
   DbUtils.closeQuietly(stmt);
   DbUtils.closeQuietly(conn);
   return erro;
  }
 
 
 } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
