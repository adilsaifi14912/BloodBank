package com.blodbank.Controller;

import com.blodbank.Model.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.*;

@Controller
public class BloodBankController {


    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public String signUp(){


        try {
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            while (resultSet.next()) {
                if (resultSet.getString(1).equalsIgnoreCase("users")) {
                    // Table exists
                    return "signup";
                }
            }


            String query =  "CREATE TABLE IF NOT EXISTS users (\n" +
                    "  id bigint AUTO_INCREMENT,\n" +
                    "  name varchar(40) ,\n" +
                    "  username varchar(15), \n" +
                    "  DOB date, \n" +
                    "  email varchar(255),\n" +
                    "  address varchar(255),\n" +
                    "  password varchar(100),\n" +
                    "  create_date_time datetime ,\n" +
                    "  created_by varchar(255) ,\n" +
                    "  modify_by varchar(255) ,\n" +
                    "  role varchar(255),\n" +
                    "  update_date_time datetime ,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ");";

            statement.execute(query);

            statement.execute("INSERT INTO `users` (`name`, `username`,`DOB`, `email`, `address`, `password`, `create_date_time`, `created_by`, `modify_by`, `role`, `update_date_time`)\n" +
                    "\n" +
                    " VALUES ('Kashif', 'kash', '2000-04-13', 'kashifnezam123@gmail.com', 'Bihar', 'admin123', now(), 'na', 'na', 'admin', now());");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "signup";
    }

    @ResponseBody
    @PostMapping("/success")
    public String signUpSuccess(UserRegistration reg){

        System.out.println(reg);


        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

        // Insert a sample user record using prepared statement

        String insertQuery = "INSERT INTO users (name, username, DOB, email, address, password, create_date_time, created_by, modify_by, role, update_date_time) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, reg.getName()); // name
        preparedStatement.setString(2, reg.getUsername()); // username
        preparedStatement.setDate(3, reg.getDob());// DOB
        preparedStatement.setString(4, reg.getEmail()); // email
        preparedStatement.setString(5, reg.getAddress()); // address
        preparedStatement.setString(6, reg.getPassword()); // password
        preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // create_date_time
        preparedStatement.setString(8, "none"); // created_by
        preparedStatement.setString(9, "none"); // modify_by
        preparedStatement.setString(10, reg.getRole()); // role
        preparedStatement.setTimestamp(11, new Timestamp(System.currentTimeMillis())); // update date time
        preparedStatement.executeUpdate();

        connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "Signup Successfully";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @ResponseBody
    @RequestMapping("/login/{name}")
    public String check(@PathVariable String name){
        return "Login Hogye Bache: "+name;
    }
}
