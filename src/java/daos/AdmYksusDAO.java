/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class AdmYksusDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public AdmYksusDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnFactory.getInstance().getConnection();
        return conn;
    }

    public void add(AdmYksus admYksus) {
        try {
            String queryString = "INSERT INTO RIIGI_ADMIN_YKSUS VALUES (null,?,?,?,'anynyymne programmikasutaja', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',? )";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, admYksus.getKood());
            ptmt.setString(2, admYksus.getNimetus());
            ptmt.setString(3, admYksus.getKommentaar());
            ptmt.setInt(4, admYksus.getFk());
            ptmt.executeUpdate();
            System.out.println("Administratiivüksus lisatud");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    public void update(AdmYksus admYksus) {
        try {
            String queryString = "UPDATE RIIGI_ADMIN_YKSUS SET KOOD=?,NIMETUS = ?,KOMMENTAAR=?,RIIGI_ADMIN_YKSUSE_LIIK_ID=? WHERE RIIGI_ADMIN_YKSUS_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, admYksus.getKood());
            ptmt.setString(2, admYksus.getNimetus());
            ptmt.setString(3, admYksus.getKommentaar());
            ptmt.setInt(5, admYksus.getFk());
            ptmt.setInt(5, admYksus.getId());
            ptmt.executeUpdate();
            System.out.println("Administratiivüksus uuendatud");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    public void delete(int id) {
        try {
            String queryString = "DELETE FROM RIIGI_ADMIN_YKSUS WHERE RIIGI_ADMIN_YKSUS_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
            System.out.println("Administratiivüksus kustutatud");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    /**
     * Meetod annab kõik tabeli kirjed, while loopi saab panna muud targemat ka
     * tegema nt. mingit kollektsiooni
     */
    public void findAll() {
        try {
            String queryString = "SELECT * FROM RIIGI_ADMIN_YKSUS";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.executeUpdate();
            while (resultSet.next()) {
                System.out.println("Id " + resultSet.getInt("RIIGI_ADMIN_YKSUS_ID")
                        + ", kood" + resultSet.getString("KOOD") + ", nimetus "
                        + resultSet.getString("NIMETUS") + ", Kommentaar "
                        + resultSet.getString("KOMMENTAAR"));
            }

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }
}
