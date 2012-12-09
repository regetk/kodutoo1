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
public class VoimalikAlluvusDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public VoimalikAlluvusDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnFactory.getInstance().getConnection();
        return conn;
    }

    public void add(VoimalikAlluvus voimalikAlluvus) {
        try {
            String queryString = "INSERT INTO VOIMALIK_ALLUVUS VALUES (?,?,'anynyymne programmikasutaja', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, voimalikAlluvus.getYlem_id());
            ptmt.setInt(2, voimalikAlluvus.getAlam_id());

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

    public void update(VoimalikAlluvus voimalikAlluvus) {
        try {
            String queryString = "UPDATE VOIMALIK_ALLUVUS SET VOIMALIK_ALLUV_LIIK_ID=? WHERE RIIGI_ADMIN_YKSUSE_LIIK_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, voimalikAlluvus.getAlam_id());
            ptmt.setInt(2, voimalikAlluvus.getYlem_id());
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
            String queryString = "DELETE FROM VOIMALIK_ALLUVUS WHERE RIIGI_ADMIN_YKSUSE_LIIK_ID=? AND VOIMALIK_ALLUV_LIIK_ID=?";

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
            String queryString = "SELECT * FROM VOIMALIK_ALLUVUS";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.executeUpdate();
            while (resultSet.next()) {
                System.out.println("Id ülem" + resultSet.getInt("RIIGI_ADMIN_YKSUSE_LIIK_ID")
                        + ", alluvüksuse id" + resultSet.getString("VOIMALIK_ALLUV_LIIK_ID"));
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
