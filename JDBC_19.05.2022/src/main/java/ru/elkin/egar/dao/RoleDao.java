package ru.elkin.egar.dao;

import ru.elkin.egar.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoleDao implements DaoInterface<Role> {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JDBC_homework_egar";
    private static final String USR_NAME = "postgres";
    private static final String PASS = "postgres";

    private static RoleDao instance;

    public static RoleDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new RoleDao();
        }
        return instance;
    }

    private RoleDao() {
    }


    @Override
    public List<Role> findAll() {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Role");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(getRoleModel(resultSet));
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role findById(Long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Role WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getRoleModel(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Role model) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Role (role_text) VALUES (?)");
            setPreparedStatement(model, preparedStatement);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Role model) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Role SET role_text = ? WHERE id = ?");
            setPreparedStatement(model, preparedStatement);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM role WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Role getRoleModel(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong("id"));
        role.setRole(resultSet.getString("role_text"));
        return role;
    }

    private void setPreparedStatement(Role model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, model.getRole());
    }
}
