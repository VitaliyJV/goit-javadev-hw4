package Database;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseTest {


    private static final String JDBC_URL = "jdbc:h2:./DB1_M6";
    public static void main(String[] args) throws SQLException {
        Properties props = readProperties();
        Flyway.configure()
                .dataSource(props.getProperty("jdbcURL"), props.getProperty("user"), props.getProperty("password"))
                .load()
                .migrate();

        ClientService clientService = new ClientService();
        System.out.println(clientService.create("New_Client"));
        System.out.println(clientService.getById(6));
        clientService.setName(3, "Client_NewName");
        clientService.deleteById(1);
        System.out.println(clientService.allClientsList());
//
    }

    public static Properties readProperties() {
        try (InputStream input = DataBaseTest.class.getClassLoader().getResourceAsStream("DB_URL.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                throw new RuntimeException("File not found");
            }
            prop.load(input);
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));
        return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Some" + ex.getMessage());
        }

    }
}