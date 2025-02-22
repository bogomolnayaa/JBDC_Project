package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Katya", "Bogomolnaya", (byte) 23);
        userService.saveUser("Danya", "Gorelik", (byte) 23);
        userService.saveUser("Ivan", "Ivanov", (byte) 52);
        userService.saveUser("Maria", "Sergeeva", (byte) 18);
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
