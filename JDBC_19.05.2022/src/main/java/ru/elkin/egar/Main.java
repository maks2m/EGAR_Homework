package ru.elkin.egar;

import ru.elkin.egar.dao.AssignmentDao;
import ru.elkin.egar.dao.ProductionOrderDao;
import ru.elkin.egar.dao.RoleDao;
import ru.elkin.egar.dao.UserDao;

public class Main {
    public static void main(String[] args) {

        AssignmentDao assignmentDao = AssignmentDao.getInstance();
        ProductionOrderDao productionOrderDao = ProductionOrderDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        RoleDao roleDao = RoleDao.getInstance();

        assignmentDao.findAll().stream().map(Assignment::toString).forEach(System.out::println);
        System.out.println();
        System.out.println(assignmentDao.findById(2L).toString());
        System.out.println();
        System.out.println();

        roleDao.findAll().stream().map(Role::toString).forEach(System.out::println);
        System.out.println();
        System.out.println(roleDao.findById(1L));
        System.out.println();
        System.out.println();

        productionOrderDao.findAll().stream().map(ProductionOrder::toString).forEach(System.out::println);
        System.out.println();
        System.out.println(productionOrderDao.findById(2L).toString());
        System.out.println();
        System.out.println();

        userDao.findAll().stream().map(User::toString).forEach(System.out::println);
        System.out.println();
        System.out.println(userDao.findById(4L).toString());
        System.out.println();
        System.out.println();

    }
}
