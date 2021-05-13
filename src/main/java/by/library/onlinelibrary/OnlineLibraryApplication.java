package by.library.onlinelibrary;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class OnlineLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineLibraryApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer();
    }

}
