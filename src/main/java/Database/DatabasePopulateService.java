package Database;

import java.sql.Connection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import DTO.Client;
import DTO.Project;
import DTO.ProjectWorker;
import DTO.Worker;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabasePopulateService {
    private static final String INSERT_WORKER = "INSERT INTO worker (id_worker, name, birthday, level, salary) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_CLIENT = "INSERT INTO client (id_client, name) VALUES (?, ?)";
    private static final String INSERT_PROJECT = "INSERT INTO project (id_project, client_id, start_date, finish_date) VALUES (?, ?, ?, ?)";
    private static final String INSERT_PROJECT_WORKER = "INSERT INTO project_worker (project_id , worker_id) VALUES (?, ?)";
    private static final String WORKER_JSON = "src/main/resources/worker.json";
    private static final String CLIENT_JSON = "src/main/resources/client.json";
    private static final String PROJECT_JSON = "src/main/resources/project.json";
    private static final String PROJECT_WORKER_JSON = "src/main/resources/projectWorker.json";
    private final Connection connection = Database.INSTANCE.getConnection();

    public void insertWorkerData() {
        List<Worker> workerList = getEntityListFromJson(Worker.class, WORKER_JSON);
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKER)) {
            for (Worker worker : workerList) {

                preparedStatement.setLong(1, worker.getId_worker());
                preparedStatement.setString(2, worker.getName());
                preparedStatement.setString(3, worker.getBirthday());
                preparedStatement.setString(4, worker.getLevel());
                preparedStatement.setInt(5, worker.getSalary());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertClientData() {
        List<Client> clientList = getEntityListFromJson(Client.class, CLIENT_JSON);
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT)){
            for (Client client : clientList) {
                preparedStatement.setLong(1, client.getId_client());
                preparedStatement.setString(2, client.getName());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProjectData() {
        List<Project> projectList = getEntityListFromJson(Project.class, PROJECT_JSON);
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT)){
            for (Project project : projectList) {
                preparedStatement.setLong(1, project.getId_project());
                preparedStatement.setLong(2, project.getClient_Id());
                preparedStatement.setString(3, project.getStart_Date());
                preparedStatement.setString(4, project.getFinish_Date());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertProjectWorkerData() {
        List<ProjectWorker> projectWorkerList = getEntityListFromJson(ProjectWorker.class, PROJECT_WORKER_JSON);
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_WORKER)){
            for (ProjectWorker projectWorker : projectWorkerList) {
                preparedStatement.setLong(1, projectWorker.getProject_Id());
                preparedStatement.setLong(2, projectWorker.getWorker_Id());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private <T> List<T> getEntityListFromJson(Class<T> entityClass, String filePath) {
        List<T> entityList = new ArrayList<>();
        try (Reader fileReader = new FileReader(filePath)) {
            Type type = TypeToken.getParameterized(List.class, entityClass).getType();
            entityList = new Gson().fromJson(fileReader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityList;
    }

}



/*
Візьми проєкт з попереднього ДЗ. У цьому проєкті для всіх SQL запитів, що приймають параметри, використай
PreparedStatement замість Statement.


 */
