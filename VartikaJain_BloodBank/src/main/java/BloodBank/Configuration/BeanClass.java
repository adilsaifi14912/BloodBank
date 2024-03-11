package BloodBank.Configuration;

import BloodBank.Aspects.UserSignUpAspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
//@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "BloodBank")
public class BeanClass {
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
//    @Bean
//    public DataSource dataSource() {
//        System.out.println("DataSource");
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
//        dataSource.setUsername("Vartika");
//        dataSource.setPassword("vartu@123");
//        return dataSource;
//    }
    @Bean
    public JdbcTemplate create(DataSource dataSource){return new JdbcTemplate(dataSource);
    }



}
