package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.CarUserService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);
        CarUserService carUserService = context.getBean(CarUserService.class);

        Car porshe = new Car("Porshe", 123);
        Car lada = new Car("Lada", 321);

        carService.add(porshe);
        carService.add(lada);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", lada));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", porshe));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            Car car = user.getCar();
            System.out.println("Car:" + car.getModel() + " " + car.getSeries());
            System.out.println();
        }

        System.out.println(carUserService.getUserForCarDetails(lada.getModel(), lada.getSeries()).getLastName());
        context.close();
    }

}
