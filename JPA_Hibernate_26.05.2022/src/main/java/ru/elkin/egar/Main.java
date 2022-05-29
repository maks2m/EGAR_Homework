package ru.elkin.egar;

import ru.elkin.egar.entity.Assignment;
import ru.elkin.egar.entity.ProductionOrder;
import ru.elkin.egar.entity.Role;
import ru.elkin.egar.entity.User;
import ru.elkin.egar.repository.AssignmentRepository;
import ru.elkin.egar.repository.ProductionOrderRepository;
import ru.elkin.egar.repository.RoleRepository;
import ru.elkin.egar.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        RoleRepository roleRepository = new RoleRepository();
        ProductionOrderRepository productionOrderRepository = new ProductionOrderRepository();
        AssignmentRepository assignmentRepository = new AssignmentRepository();


        //Criteria API
//        productionOrderRepository.findProductionOrderForOneUserBetweenDates(
//                        4L,
//                        LocalDate.of(2019, 1, 1),
//                        LocalDate.of(2019, 12, 31))
//                .stream().map(ProductionOrder::toString).forEach(System.out::println);


        //fetchgraph runtime
//        System.out.println(userRepository.findByIdWithAllEntity(4L));
        //fetchgraph in entity
//        System.out.println(roleRepository.findRoleWithUsers());


//        userRepository.findAll().stream().map(User::toString).forEach(System.out::println);
//        User user = userRepository.findById(4L);
//        System.out.println(user);
//        System.out.println(user.getRoles());
//        System.out.println(user.getProductionOrders());

//        User user = new User();
//        user.setId(12L);
//        user.setUsername("222");
//        user.setPassword("222");
//        userRepository.save(user);
//        userRepository.deleteById(11L);


//        roleRepository.findAll().stream().map(Role::toString).forEach(System.out::println);
//        Role role = roleRepository.findById(1L);
//        System.out.println(role);
//        System.out.println(role.getUsers());



//        productionOrderRepository.findAll().stream().map(ProductionOrder::toString).forEach(System.out::println);
//        ProductionOrder productionOrder = productionOrderRepository.findById(4L);
//        System.out.println(productionOrder);
//        System.out.println(productionOrder.getResponsibleUser());
//        System.out.println(productionOrder.getAssignment());



//        assignmentRepository.findAll().stream().map(Assignment::toString).forEach(System.out::println);
//        Assignment assignment = assignmentRepository.findById(4L);
//        System.out.println(assignment);
//        System.out.println(assignment.getProductionOrders());


    }
}
