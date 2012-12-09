/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anneli
 * klass kiirendab andmebaasiühenduse loomist
 */
public class ConnFactory {

    String driverClassName = "org.hsqldb.jdbcDriver";
    String connectionUrl = "jdbc:hsqldb:file:${user.home}/piirivalveDb;shutdown=true";
//	String dbUser = "root";
//	String dbPwd = "root";
    private static ConnFactory ConnFactory = null;

    private ConnFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl); //cutted dbUser, dbPwd
        return conn;
    }

    public static ConnFactory getInstance() {
        if (ConnFactory == null) {
            ConnFactory = new ConnFactory();
        }
        return ConnFactory;
    }
}
