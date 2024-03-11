package com.blood.Shreya_BloodBank.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserRepository {
    @Autowired
    private DataSource dataSource;

    public void createTable()
    {
        try {
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();

            String query = "CREATE TABLE users (\n" +
                    "id bigint AUTO_INCREMENT,"+
                    "  create_date_time datetime ,\n" +
                    "  created_by varchar(255) ,\n" +
                    "  modify_by varchar(255) ,\n" +
                    "  name varchar(40) ,\n" +
                    "  password varchar(100) ,\n" +
                    "  phone_no bigint ,\n" +
                    "  role_alias varchar(255) ,\n" +
                    "  update_date_time datetime ,\n" +
                    "  username varchar(15), \n" +
                    "  PRIMARY KEY (id)\n" +
                    ");";
            System.out.println(query);
            statement.execute(query);

            statement.execute("INSERT INTO `users` (`create_date_time`, `created_by`, `modify_by`, `name`, `password`, `phone_no`, `role_alias`, `update_date_time`, `username`)\n" +
                    "\n" +
                    " VALUES (now(), 'none', 'none', 'Admin', 'admin123', '123', 'AS', now(), 'admin');");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String username, String password, String name, Long phone) {
        String sql = "INSERT INTO users (create_date_time, created_by, modify_by, name, password, phone_no, role_alias, update_date_time, username)" +
                " VALUES (now(), 'auto', 'none', ?, ?, ?, 'EU', now(), ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setLong(3, phone);
            statement.setString(4, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


//import com.blood.Shreya_BloodBank.Entity.UserModel;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends JpaRepository<UserModel, Long> {
//    // You can add custom query methods if needed
//}
