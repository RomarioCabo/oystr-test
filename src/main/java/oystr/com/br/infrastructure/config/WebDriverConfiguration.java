package oystr.com.br.infrastructure.config;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class WebDriverConfiguration {

    @Bean("defaultWebDriver")
    @Primary
    public WebDriver defaultWebDriver() {
        String os = System.getProperty("os.name").toLowerCase();
        log.info("Sistema Operacional ---> {}", os);

        System.setProperty(
                os.contains("win") ? "webdriver.edge.driver" : "webdriver.gecko.driver",
                os.contains("win") ? buildPathToEdgeDriver() : buildPathToFirefoxDriver()
        );
        WebDriver driver = os.contains("win") ? new EdgeDriver() : new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        return driver;
    }

    private String buildPathToEdgeDriver() {
        String pathToEdgeDriver = String.format(
                "%s\\src\\main\\resources\\webdriver\\msedgedriver.exe", System.getProperty("user.dir")
                        .replace("/", "\\"));
        log.info("Caminho para o driver do Edge ---> {}", pathToEdgeDriver);
        return pathToEdgeDriver;
    }

    private String buildPathToFirefoxDriver() {
        String pathToFirefoxDriver = String.format(
                "%s/src/main/resources/webdriver/geckodriver", System.getProperty("user.dir"));
        log.info("Caminho para o driver do Firefox ---> {}", pathToFirefoxDriver);
        return pathToFirefoxDriver;
    }
}
