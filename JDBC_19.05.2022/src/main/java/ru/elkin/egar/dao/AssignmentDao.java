package ru.elkin.egar.dao;

import ru.elkin.egar.Assignment;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class AssignmentDao implements DaoInterface<Assignment>{

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JDBC_homework_egar";
    private static final String USR_NAME = "postgres";
    private static final String PASS = "postgres";

    private final ProductionOrderDao productionOrderDao = ProductionOrderDao.getInstance();

    private static AssignmentDao instance;

    public static AssignmentDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AssignmentDao();
        }
        return instance;
    }

    private AssignmentDao() {
    }


    @Override
    public List<Assignment> findAll() {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Assignment " +
                    "JOIN production_order ON assignment.id = production_order.id_assignment");
            ResultSet resultSet = preparedStatement.executeQuery();
            return new ArrayList<>(extractorModels(resultSet).values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Assignment findById(Long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Assignment " +
                    "JOIN production_order ON assignment.id = production_order.id_assignment WHERE assignment.id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return extractorModels(resultSet).get(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Assignment model) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Assignment (date_assignment, " +
                    "general_contractor, name_npp, number_assignment, number_block, number_contract) VALUES (?,?,?,?,?,?)");
            setPreparedStatement(model, preparedStatement);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void update(Long id, Assignment model) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Assignment SET date_assignment = ?, " +
                    "general_contractor = ?, name_npp = ?, number_assignment = ?, number_block = ?, number_contract = ? WHERE id = ?");
            setPreparedStatement(model, preparedStatement);
            preparedStatement.setLong(7, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {

        Assignment assignment = findById(id);
        assignment.getProductionOrders().forEach(i -> productionOrderDao.delete(i.getId()));

        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Assignment WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Assignment getAssignmentModel(ResultSet resultSet) throws SQLException {
        Assignment assignment = new Assignment();
        assignment.setId(resultSet.getLong("id"));
        String date = resultSet.getString("date_assignment");
        if (date != null) assignment.setDateAssignment(LocalDate.parse(date));
        assignment.setGeneralContractor(resultSet.getString("general_contractor"));
        assignment.setNameNpp(resultSet.getString("name_npp"));
        assignment.setNumberAssignment(resultSet.getString("number_assignment"));
        assignment.setNumberBlock(resultSet.getString("number_block"));
        assignment.setNumberContract(resultSet.getString("number_contract"));
        return assignment;
    }

    private void setPreparedStatement(Assignment model, PreparedStatement preparedStatement) throws SQLException {
        LocalDate localDate = model.getDateAssignment();
        if (localDate != null) preparedStatement.setDate(1, Date.valueOf(localDate));
        preparedStatement.setString(2, model.getGeneralContractor());
        preparedStatement.setString(3, model.getNameNpp());
        preparedStatement.setString(4, model.getNumberAssignment());
        preparedStatement.setString(5, model.getNumberBlock());
        preparedStatement.setString(6, model.getNumberContract());
    }

    private static Map<Long, Assignment> extractorModels(ResultSet resultSet) throws SQLException {

        Map<Long, Assignment> assignmentMap = new HashMap<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Assignment assignment = assignmentMap.get(id);

            if (assignment == null) {
                assignment = getAssignmentModel(resultSet);
                assignment.setProductionOrders(new HashSet<>());
                assignmentMap.put(assignment.getId(), assignment);
            }
            assignment.getProductionOrders().add(ProductionOrderDao.getProductionOrderModel(resultSet));

        }
        return assignmentMap;
    }
}
