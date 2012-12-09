
import java.sql.*;
import org.apache.commons.dbutils.DbUtils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author reget.kalamees
 */
public class makedb {
    
    public static void main(String[] args) throws Exception{
       try{
    Class.forName("org.hsqldb.jdbcDriver");
   } catch (ClassNotFoundException e){
    throw new RuntimeException(e);
   }
        
        setupDatabase();
        
    
    }
    
    private static void setupDatabase() throws Exception {

  
  Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:database/piirivalveDb;shutdown=true");

  Statement stmt = null;
  ResultSet rset = null; // et midagi vÃ¤lja printida, praegu pole vaja

  try {
   stmt = conn.createStatement();
   //loome tabeli RIIGI_ADMIN_YKSUSE_Liik
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id INTEGER IDENTITY PRIMARY KEY, kood VARCHAR(10) NOT NULL ,nimetus VARCHAR(100) NOT NULL,kommentaar LONGVARCHAR NOT NULL,avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL ) ");
            //sisestame sinna administratiivüksused
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (1, 'mk','maakond','suurim võimalik administratiivüksus Eesti vabariigis, sisaldab linnu ja valdasid','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (2, 'ln','linn','suuruselt teine admin üksus, sisaldub maakonnas,  Tallinn sisaldab linnaosasid','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (3, 'v','vald','samuti suuruselt teine admin üksus, sisaldub maakonnas, võib sisaldada ka linna, sisaldab aleveid, alevikke ja külasid','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (4, 'a','alev','sisaldub vallas','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");

            //loome tabeli VOIMALIK_ALLUVUS NB! Ringsõltuvused pole lubatud, aga praegu need välistatud ei ole
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VOIMALIK_ALLUVUS (riigi_admin_yksuse_liik_id INTEGER, voimalik_alluv_liik_id INTEGER, avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL ) ");
            //seame välisvõtmed VOIMALIK ALLUVUS tulpadele
            stmt.executeUpdate("ALTER TABLE VOIMALIK_ALLUVUS  ADD CONSTRAINT Voimalik_Alluvus_CR_FK_ylem FOREIGN KEY (riigi_admin_yksuse_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id);");
            stmt.executeUpdate("ALTER TABLE VOIMALIK_ALLUVUS  ADD CONSTRAINT Voimalik_Alluvus_CR_FK_alam FOREIGN KEY (voimalik_alluv_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id);");
            //lisame alluvused
            stmt.executeUpdate(" INSERT INTO VOIMALIK_ALLUVUS  VALUES (1,2,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO VOIMALIK_ALLUVUS  VALUES (1,3,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");

            //loome tabeli RIIGI_ADMIN_YKSUS
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RIIGI_ADMIN_YKSUS (riigi_admin_yksus_id INTEGER IDENTITY PRIMARY KEY, kood VARCHAR(20) NOT NULL ,nimetus VARCHAR(100) NOT NULL,kommentaar LONGVARCHAR NOT NULL,avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL, riigi_admin_yksuse_liik_id INTEGER ) ");
            //lisame välisvõtme
            stmt.executeUpdate("ALTER TABLE RIIGI_ADMIN_YKSUS ADD CONSTRAINT Riigi_admin_yksus_CR_FK FOREIGN KEY (riigi_admin_yksuse_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id);");
            //lisame andmed
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (1, 'V145','Leisi vald','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',3 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (2, 'V146','Orissaare vald','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',3 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (3, 'Mk100','Harju maakond','suurima rahvastikutihedusega','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',1)");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (4, 'Mk123','Saare maakond','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',1 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (5, 'L234','Kuressaare linn','asub Saare mk-s','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',2 )");
            stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUS VALUES (6, 'A456','Orissaare alev','asub Saare mk-s, Orissaare vallas','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',4 )");

            //loome tabeli ADMIN_ALLUVUS
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ADMIN_ALLUVUS (ylemus_yksus_id INTEGER , alluv_yksus_id INTEGER,avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL ) ");
            //lisame välisvõtmed
            stmt.executeUpdate("ALTER TABLE ADMIN_ALLUVUS ADD CONSTRAINT Admin_alluvus_CR_FK_ylem FOREIGN KEY (ylemus_yksus_id) REFERENCES RIIGI_ADMIN_YKSUS (riigi_admin_yksus_id)");
            stmt.executeUpdate("ALTER TABLE ADMIN_ALLUVUS ADD CONSTRAINT Admin_alluvus_CR_FK_alam FOREIGN KEY (alluv_yksus_id) REFERENCES RIIGI_ADMIN_YKSUS (riigi_admin_yksus_id)");
            //lisame andmed
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (1,1,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' );");
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (4,5,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
            stmt.executeUpdate(" INSERT INTO ADMIN_ALLUVUS VALUES (2,6,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");

  
  } finally {
   DbUtils.closeQuietly(rset);
   DbUtils.closeQuietly(stmt);
   DbUtils.closeQuietly(conn);
  }

 }
    
}
