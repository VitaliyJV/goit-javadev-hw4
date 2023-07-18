package Database;

import DTO.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final Connection connection = Database.INSTANCE.getConnection();
    private static final String NAME_LENGTH_EXCEPTION_MESSAGE = "Name length should be in range between 2 and 100 chars";
    private static final String CREATE_CLIENT_SQL_COMMAND = "INSERT INTO client (name) VALUES (?)";
    private static final String SELECT_CLIENT_BY_ID = "SELECT name FROM client WHERE id = ?";
    private static final String ID_EXCEPTION_MESSAGE = "ID should be bigger than 0";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String INVALID_NAME_OR_ID_EXCEPTION_MESSAGE = "Please enter valid name and id";
    private static final String UPDATE_CLIENT_NAME = "UPDATE client SET name = ? WHERE id = ?";
    private static final String SQL_COMMAND_DELETE_CLIENT = "DELETE FROM client WHERE id = ?";
    private static final String SQL_COMMAND_SELECT_ALL_CLIENTS = "SELECT * FROM client";
    private static final String COLUMN_LABEL_ID = "id";


    public long create(String name) {
        if (name.length() < 2 || name.length() > 100) {
            throw new IllegalArgumentException(NAME_LENGTH_EXCEPTION_MESSAGE);
        }
        long id = 0;
        try (PreparedStatement createSt = connection.prepareStatement(CREATE_CLIENT_SQL_COMMAND, Statement.RETURN_GENERATED_KEYS)) {
            createSt.setString(1, name);
            createSt.executeUpdate();
            ResultSet generatedKeys = createSt.getGeneratedKeys();
            generatedKeys.next();
            id = generatedKeys.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getById(long id_client) {
        if (id_client <= 0) {
            throw new IllegalArgumentException(ID_EXCEPTION_MESSAGE);
        }
        String name = null;
        try (PreparedStatement getByIdSt = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            getByIdSt.setLong(1, id_client);
            ResultSet resultSet = getByIdSt.executeQuery();
            resultSet.next();
            name = resultSet.getString(COLUMN_LABEL_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void setName(long id_client, String name) {
        if (name.length() < 2 || name.length() > 100 || id_client <= 0) {
            throw new IllegalArgumentException(INVALID_NAME_OR_ID_EXCEPTION_MESSAGE);
        }
        try (PreparedStatement setNameSt = connection.prepareStatement(UPDATE_CLIENT_NAME)) {
            setNameSt.setString(1, name);
            setNameSt.setLong(2, id_client);
            setNameSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id_client) {
        if (id_client <= 0) {
            throw new IllegalArgumentException(ID_EXCEPTION_MESSAGE);
        }
        try (PreparedStatement deleteByIdSt = connection.prepareStatement(SQL_COMMAND_DELETE_CLIENT)) {
            deleteByIdSt.setLong(1, id_client);
            deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> allClientsList() {
        List<Client> clientList = new ArrayList<>();
        try (PreparedStatement listAllSt = connection.prepareStatement(SQL_COMMAND_SELECT_ALL_CLIENTS)) {
            ResultSet resultSet = listAllSt.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId_client(resultSet.getLong(COLUMN_LABEL_ID));
                client.setName(resultSet.getString(COLUMN_LABEL_NAME));
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }



}
