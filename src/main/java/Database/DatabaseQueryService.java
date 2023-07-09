package Database;

import DTO.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private static final String FIND_MAX_SALARY_WORKER_FILE = "sql/find_max_salary_worker.sql";
    private static final String FIND_MAX_PROJECTS_CLIENT_FILE = "sql/find_max_projects_client.sql";
    private static final String FIND_LONGEST_PROJECT_FILE = "sql/find_longest_project.sql";
    private static final String FIND_YOUNGEST_ELDEST_WORKERS_FILE = "sql/find_youngest_eldest_workers.sql";
    private static final String PRINT_PROJECT_PRICES_FILE = "sql/print_project_prices.sql";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_SALARY = "salary";
    private static final String COLUMN_LABEL_PROJECT_COUNT = "project_count";
    private static final String COLUMN_LABEL_MONTH_COUNT = "month_count";
    private static final String COLUMN_LABEL_TYPE = "type";
    private static final String COLUMN_LABEL_BIRTHDAY = "birthday";
    private static final String COLUMN_LABEL_PRICE = "price";
    private final Connection connection = Database.INSTANCE.getConnection();

    public DatabaseQueryService() throws SQLException {
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();          // лист с типом MaxSalaryWorker
        try (Statement statement = connection.createStatement()) {              // создаем Statement
            String sql = Files.readString(Path.of(FIND_MAX_SALARY_WORKER_FILE)); // создаем стринг команды, читая стринг из файла
            ResultSet resultSet = statement.executeQuery(sql);                  // в поле resultSet выполняем через executeQuery стринговую команду sql,
            while (resultSet.next()) {                                          // пока есть следующее
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();        // создаем поле типа MaxSalaryWorker
                maxSalaryWorker.setName(resultSet.getString(COLUMN_LABEL_NAME)); // вносим данные и задаем название колонки
                maxSalaryWorker.setSalary(resultSet.getInt(COLUMN_LABEL_SALARY)); // вносим данные и задаем название колонки
                maxSalaryWorkerList.add(maxSalaryWorker);                           // добавляем в лист найденные данные
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxSalaryWorkerList;                                             // возвращаем лист
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> maxProjectsClientList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT_FILE));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                MaxProjectCountClient maxProjectsClient = new MaxProjectCountClient();
                maxProjectsClient.setName(resultSet.getString(COLUMN_LABEL_NAME));
                maxProjectsClient.setProjectCount(resultSet.getInt(COLUMN_LABEL_PROJECT_COUNT));
                maxProjectsClientList.add(maxProjectsClient);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxProjectsClientList;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> longestProjectList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(FIND_LONGEST_PROJECT_FILE));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                LongestProject longestProject = new LongestProject();
                longestProject.setName(resultSet.getString(COLUMN_LABEL_NAME));
                longestProject.setMonthCount(resultSet.getInt(COLUMN_LABEL_MONTH_COUNT));
                longestProjectList.add(longestProject);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return longestProjectList;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        List<YoungestEldestWorkers> youngestEldestWorkerList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(FIND_YOUNGEST_ELDEST_WORKERS_FILE));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                YoungestEldestWorkers youngestEldestWorker = new YoungestEldestWorkers();
                youngestEldestWorker.setType(resultSet.getString(COLUMN_LABEL_TYPE));
                youngestEldestWorker.setName(resultSet.getString(COLUMN_LABEL_NAME));
                youngestEldestWorker.setBirthday(resultSet.getString(COLUMN_LABEL_BIRTHDAY));
                youngestEldestWorkerList.add(youngestEldestWorker);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkerList;
    }

    public List<MaxProjectPrice> findProjectPrices() {
        List<MaxProjectPrice> projectPriceList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(PRINT_PROJECT_PRICES_FILE));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                MaxProjectPrice projectPrice = new MaxProjectPrice();
                projectPrice.setName(resultSet.getString(COLUMN_LABEL_NAME));
                projectPrice.setPrice(resultSet.getInt(COLUMN_LABEL_PRICE));
                projectPriceList.add(projectPrice);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return projectPriceList;
    }

}

/*
Завдання №4 - створити клас для вибірки даних з БД
Створи клас з назвою DatabaseQueryService. У цьому класі мають бути методи для кожного файлу з SELECT виразом з попереднього завдання. Кожний метод має:

зчитувати відповідний .sql файл
повертати потрібний результат
Кожний метод називай згідно Java Code Conventions. Зверни увагу на коректний тип значення, що повертатиме метод. Наприклад,
для файлу find_max_projects_client сигнатура методу виглядатиме List<MaxProjectCountClient> findMaxProjectsClient().
При цьому клас MaxProjectCountClient необхідно описати, наприклад:

public class MaxProjectCountClient {
    private String name;
    private int projectCount;
}

Для роботи з БД використовуй написаний раніше тобою клас Database.

Результат виконання завдання - методи для кожного SELECT запиту, які можна викликати, наприклад, наступним чином:

List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClient();

Запусти так кожний метод, і переконайсь, що він повертає коректну інформацію і під час запуску ніде не виникають Exceptions.
 */

