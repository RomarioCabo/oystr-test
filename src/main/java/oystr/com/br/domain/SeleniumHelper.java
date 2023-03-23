package oystr.com.br.domain;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public abstract class SeleniumHelper {
  protected String getTextFromElement(WebDriver driver, String xPath) {
    if (xPath.isEmpty()) {
      return "Não informado";
    }

    WebElement element =
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    String elementText = element.getText();

    log.info("TextFromElement ---> {}", elementText);

    return elementText;
  }

  protected String getUrlFromElement(WebDriver driver, String xPath) {
    if (xPath.isEmpty()) {
      return "URL não encotrada";
    }

    WebElement element =
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    String urlFromElement = element.getAttribute("src");

    log.info("UrlFromElement ---> {}", urlFromElement);

    return urlFromElement;
  }
}
