/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author dell
 */
public class AdmAlluvusDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public AdmAlluvusDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnFactory.getInstance().getConnection();
        return conn;
    }

    public void add(AdmAlluvus admAlluvus) {
        try {
            String queryString = "INSERT INTO ADMIN_ALLUVUS VALUES (?,?,'anynyymne programmikasutaja', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, admAlluvus.getYlem_id());
            ptmt.setInt(2, admAlluvus.getAlam_id());

            ptmt.executeUpdate();
            System.out.println("Alluvussuhe lisatud");
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

    public void update(AdmAlluvus admAlluvus) {
        try {
            String queryString = "UPDATE ADMIN_ALLUVUS SET ALLUV_YKSUS_ID=? WHERE YLEMUS_YKSUS_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, admAlluvus.getAlam_id());
            ptmt.setInt(2, admAlluvus.getYlem_id());
            ptmt.executeUpdate();
            System.out.println("Alluvussuhe muudetud");
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
    //kustutatakse konkreetne paar!
    public void delete(int id_ylem, int id_alam) {
        try {
            String queryString = "DELETE FROM ADMIN_ALLUVUS WHERE YLEMUS_YKSUS_ID=? AND ALLUV_YKSUS_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id_ylem);
            ptmt.setInt(2, id_alam);
            ptmt.executeUpdate();
            System.out.println("Alluvussuhe kustutatud");
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
            String queryString = "SELECT * FROM ADMIN_ALLUVUS";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.executeUpdate();
            while (resultSet.next()) {
                System.out.println("Id ülem" + resultSet.getInt("YLEMUS_YKSUS_ID")
                        + ", alluvüksuse id" + resultSet.getString("ALLUV_YKSUS_ID"));
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
