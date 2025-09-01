package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private static String URL;
    private static String USER;
    private static String PASS;

    static {
        try (InputStream input = ConexionBD.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            URL  = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASS = prop.getProperty("db.pass");

        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer config.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}