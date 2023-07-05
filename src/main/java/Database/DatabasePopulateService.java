package Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    private static final String POPULATE_DB_FILE = "sql/populate_db.sql";

    public void populateDatabase() throws SQLException {
        Connection connection = Database.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement()){
            String sql = Files.readString(Path.of(POPULATE_DB_FILE));
            statement.executeUpdate(sql);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Завдання №3 - створити клас для наповнення таблиць БД
Створи клас з назвою DatabasePopulateService. У цьому класі має бути метод public static void main(String[] args), який
зчитуватиме файл sql/populate_db.sql і виконуватиме запити з цього класу у БД.

Для роботи з БД використовуй написаний раніше тобою клас Database.

Результат запуску цього класу - наповнені таблиці бази даних.
 */
