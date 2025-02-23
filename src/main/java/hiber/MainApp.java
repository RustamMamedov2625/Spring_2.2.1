package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Car1", 2020);
      Car car2 = new Car("Car2", 2021);
      Car car3 = new Car("Car3", 2022);
      Car car4 = new Car("Car4", 2023);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car Model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }


      String modelToSearch = "Car4";
      int seriesToSearch = 2023;
      User foundUser = userService.getUserByCarModelAndSeries(modelToSearch, seriesToSearch);
      if (foundUser != null) {
         System.out.println("Found user: ");
         System.out.println("Id = "+foundUser.getId());
         System.out.println("First Name = " + foundUser.getFirstName());
         System.out.println( "Last Name = " + foundUser.getLastName());
         System.out.println("Email = " + foundUser.getEmail());
      } else {
         System.out.println("No user found");
      }
      context.close();
   }
}
