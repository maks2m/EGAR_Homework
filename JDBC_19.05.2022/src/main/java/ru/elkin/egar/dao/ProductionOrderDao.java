package ru.elkin.egar.dao;

import ru.elkin.egar.ProductionOrder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductionOrderDao implements DaoInterface<ProductionOrder>{

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JDBC_homework_egar";
    private static final String USR_NAME = "postgres";
    private static final String PASS = "postgres";

    private static ProductionOrderDao instance;

    public static ProductionOrderDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ProductionOrderDao();
        }
        return instance;
    }

    private ProductionOrderDao() {
    }


    @Override
    public List<ProductionOrder> findAll() {

        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM production_order " +
                    "JOIN assignment on assignment.id = production_order.id_assignment " +
                    "JOIN usr on usr.id = production_order.id_responsible_user");
            ResultSet resultSet = preparedStatement.executeQuery();
            return extractorProductionOrder(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;



    }

    @Override
    public ProductionOrder findById(Long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM production_order " +
                    "JOIN assignment on assignment.id = production_order.id_assignment " +
                    "JOIN usr on usr.id = production_order.id_responsible_user" +
                    " WHERE production_order.id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return extractorProductionOrder(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(ProductionOrder model) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO production_order (date_developer_contract, " +
                    "date_production_order, identifier_production_order, manufacturer, name_equipment, notes, " +
                    "number_case, number_developer_contract, number_production_order, status, id_assignment, id_responsible_user) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            setPreparedStatement(model, preparedStatement);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, ProductionOrder model) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE production_order SET date_developer_contract = ?, " +
                    "date_production_order = ?, identifier_production_order = ?, manufacturer = ?, name_equipment = ?, notes = ?, " +
                    "number_case = ?, number_developer_contract = ?, number_production_order = ?, status = ?, id_assignment = ?, id_responsible_user = ? " +
                    "WHERE id = ?");
            setPreparedStatement(model, preparedStatement);
            preparedStatement.setLong(13, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USR_NAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM production_order WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ProductionOrder getProductionOrderModel(ResultSet resultSet) throws SQLException {
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setId(resultSet.getLong("id"));
        String date1 = resultSet.getString("date_developer_contract");
        if (date1 != null) productionOrder.setDateDeveloperContract(LocalDate.parse(date1));
        String date2 = resultSet.getString("date_production_order");
        if (date2 != null) productionOrder.setDateProductionOrder(LocalDate.parse(date2));
        productionOrder.setIdentifierProductionOrder(resultSet.getString("identifier_production_order"));
        productionOrder.setManufacturer(resultSet.getString("manufacturer"));
        productionOrder.setNameEquipment(resultSet.getString("name_equipment"));
        productionOrder.setNotes(resultSet.getString("notes"));
        productionOrder.setNumberCase(resultSet.getInt("number_case"));
        productionOrder.setNumberDeveloperContract(resultSet.getString("number_developer_contract"));
        productionOrder.setNumberProductionOrder(resultSet.getString("number_production_order"));
        productionOrder.setStatus(resultSet.getString("status"));
        return productionOrder;
    }

    private void setPreparedStatement(ProductionOrder model, PreparedStatement preparedStatement) throws SQLException {
        LocalDate localDate1 = model.getDateDeveloperContract();
        if (localDate1 != null) {
            preparedStatement.setDate(1, Date.valueOf(localDate1));
        } else {
            preparedStatement.setDate(1, null);
        }
        LocalDate localDate2 = model.getDateProductionOrder();
        if (localDate2 != null) {
            preparedStatement.setDate(2, Date.valueOf(localDate2));
        } else {
            preparedStatement.setDate(2, null);
        }
        preparedStatement.setString(3, model.getIdentifierProductionOrder());
        preparedStatement.setString(4, model.getManufacturer());
        preparedStatement.setString(5, model.getNameEquipment());
        preparedStatement.setString(6, model.getNotes());
        preparedStatement.setInt(7, model.getNumberCase());
        preparedStatement.setString(8, model.getNumberDeveloperContract());
        preparedStatement.setString(9, model.getNumberProductionOrder());
        preparedStatement.setString(10, model.getStatus());
        preparedStatement.setLong(11, model.getAssignment().getId());
        preparedStatement.setLong(12, model.getResponsibleUser().getId());

    }

    private List<ProductionOrder> extractorProductionOrder(ResultSet resultSet) throws SQLException {
        List<ProductionOrder> productionOrders = new ArrayList<>();
        while (resultSet.next()) {
            ProductionOrder productionOrder = getProductionOrderModel(resultSet);
            productionOrder.setAssignment(AssignmentDao.getAssignmentModel(resultSet));
            productionOrder.setResponsibleUser(UserDao.getUserModel(resultSet));
            productionOrders.add(productionOrder);
        }
        return productionOrders;
    }

}
