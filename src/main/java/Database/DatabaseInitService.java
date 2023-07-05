package Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    private static final String INIT_DB_FILE = "sql/init_db.sql";

    public void initDatabase() throws SQLException {
        Connection connection = Database.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement()){
            String sql = Files.readString(Path.of(INIT_DB_FILE));
            statement.executeUpdate(sql);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Завдання №2 - створити клас для ініціалізації структури БД
Створи клас з назвою DatabaseInitService. У цьому класі має бути метод public static void main(String[] args), який
зчитуватиме файл sql/init_db.sql і виконуватиме запити з цього класу у БД.

Для роботи з БД використовуй написаний раніше тобою клас Database.

Результат запуску цього класу - проініцалізована база даних з коректно створеними таблицями та зв'язками між цими таблицями.
 */
