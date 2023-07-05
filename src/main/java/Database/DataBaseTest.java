package Database;

import java.sql.SQLException;

public class DataBaseTest {
    public static void main(String[] args) throws SQLException {
        DatabaseInitService databaseInitService = new DatabaseInitService();
        databaseInitService.initDatabase();
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.populateDatabase();
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
        System.out.println(databaseQueryService.findMaxSalaryWorker());
        System.out.println(databaseQueryService.findMaxProjectsClient());
        System.out.println(databaseQueryService.findLongestProject());
        System.out.println(databaseQueryService.findYoungestEldestWorkers());
        System.out.println(databaseQueryService.findProjectPrices());
    }
}