package cenzi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public EmpDataService createEmpDataService() {
        return new EmpDataService();
    }

    @Bean
    public ServiceDataService createServiceDataService() {
        return new ServiceDataService();
    }

    @Bean
    public Manager createManager() {
        return new Manager();
    }

    @Bean
    public LocationIQDataService createLocationIQDataService() {
        return new LocationIQDataService();
    }
}
