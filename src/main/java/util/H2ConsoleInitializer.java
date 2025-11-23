package util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.h2.tools.Server;

import java.sql.SQLException;

@WebListener
public class H2ConsoleInitializer implements ServletContextListener {

    private Server webServer;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
            webServer.start();

            System.out.println("==========================================");
            System.out.println("H2 Console started successfully!");
            System.out.println("Web Console URL: http://localhost:8082");
            System.out.println("JDBC URL: jdbc:h2:mem:db");
            System.out.println("User Name: sa");
            System.out.println("Password: [empty]");
            System.out.println("==========================================");

        } catch (SQLException e) {
            System.err.println("Failed to start H2 Console: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (webServer != null) {
            webServer.stop();
        }
        System.out.println("H2 Console stopped");
    }
}