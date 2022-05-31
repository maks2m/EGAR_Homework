package ru.elkin.egar.dao;

import ru.elkin.egar.dao.ext.UserRoleRelation;
import ru.elkin.egar.Role;
import ru.elkin.egar.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDao implements DaoInterface<User> {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JDBC_homework_egar";
    private static final String USR_NAME = "postgres";
    private static final String PASS = "postgres";

    private static final RoleDao roleDao = RoleDao.getInstance();

    private static UserDao instance;

    public static UserDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserDao();
        }
        return instance;
    }

    private UserDao() {
    }

    @Override
    public List<User> findAll() {

        List<Role> roles = roleDao.findAll();
        List<UserRoleRelation> userRoleRelations = getAllRelations();
        List<User> users = findAllSimple();
        mergeUsersInfo(users, roles, Objects.requireNonNull(userRoleRelations));

        return users;
    }


    @Override
    public User findById(Long id) {

        List<Role> roles = roleDao.findAll();
        List<UserRoleRelation> userRoleRelations = getAllRelations();
        List<User> users = new ArrayList<>();
        users.add(findByIdSimple(id));
        mergeUsersInfo(users, roles, Objects.requireNonNull(userRoleRelations));

        return users.get(0);
    }


    @Override
    public void create(User model) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO Usr (active, " +
                    "first_name, full_name, last_name, password, username) VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            setPreparedStatement(model, preparedStatement1);
            preparedStatement1.executeUpdate();
            ResultSet resultSet = preparedStatement1.getGeneratedKeys();
            resultSet.next();
            model.setId(resultSet.getLong("id"));
            for (Role role : model.getRoles()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO user_role (id_user, " +
                        "id_role) VALUES (?,?)");
                preparedStatement2.setLong(1, model.getId());
                preparedStatement2.setLong(2, role.getId());
                preparedStatement2.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Long id, User model) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Usr SET active = ?, " +
                    "first_name = ?, full_name = ?, last_name = ?, password = ?, username = ? WHERE id = ?");
            setPreparedStatement(model, preparedStatement);
            preparedStatement.setLong(7, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1;
            try {
                preparedStatement1 = connection.prepareStatement("DELETE FROM user_role WHERE id_user = ?");
                preparedStatement1.setLong(1, id);
                preparedStatement1.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM usr WHERE id = ?");
            preparedStatement2.setLong(1, id);
            preparedStatement2.execute();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAllSimple() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Usr");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(getUserModel(resultSet));
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findByIdSimple(Long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Usr WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getUserModel(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUserModel(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setActive(resultSet.getBoolean("active"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setFullName(resultSet.getString("full_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPassword(resultSet.getString("password"));
        user.setUsername(resultSet.getString("username"));
        return user;
    }

    private void setPreparedStatement(User model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setBoolean(1, model.isActive());
        preparedStatement.setString(2, model.getFirstName());
        preparedStatement.setString(3, model.getFullName());
        preparedStatement.setString(4, model.getLastName());
        preparedStatement.setString(5, model.getPassword());
        preparedStatement.setString(6, model.getUsername());
    }

    private List<UserRoleRelation> getAllRelations() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_role");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserRoleRelation> userRoleRelations = new ArrayList<>();
            while (resultSet.next()) {
                UserRoleRelation userRoleRelation = new UserRoleRelation();
                userRoleRelation.setUserId(resultSet.getLong("id_user"));
                userRoleRelation.setRoleId(resultSet.getLong("id_role"));
                userRoleRelations.add(userRoleRelation);
            }
            return userRoleRelations;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void mergeUsersInfo(List<User> users, List<Role> roles, List<UserRoleRelation> userRoleRelations) {

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, c -> c));
        Map<Long, Role> roleMap = roles.stream().collect(Collectors.toMap(Role::getId, c -> c));
        userRoleRelations.forEach(r -> {
            if (userMap.containsKey(r.getUserId()) && roleMap.containsKey(r.getRoleId())) {
                userMap.get(r.getUserId()).getRoles().add(roleMap.get(r.getRoleId()));
            }
        });
    }
}
