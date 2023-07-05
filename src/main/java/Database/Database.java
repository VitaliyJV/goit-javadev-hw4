package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static Database.DataBaseConfig.*;

public enum Database {
    INSTANCE;

    Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e1) {
            throw new RuntimeException(e1);

        }
    }

}

/*
Завдання №1 - створити утилітний клас для роботи з БД
Створи клас-сінглтон Database, який інкапсулює у собі логіку для роботи з БД. Під час створення екземпляру цього класу
має відбуватись підключення до СУБД та зберігатись екземпляр Connection. Має бути можливість отримати Connection викликом
методу getConnection(). Наприклад, ось так:
 */