package oystr.com.br.domain.machinery;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import oystr.com.br.domain.SeleniumHelper;

@Slf4j
@Service
@AllArgsConstructor
public class MachineryServiceImpl extends SeleniumHelper implements MachineryService {

  private final WebDriver driver;

  @Override
  public List<Machinery> getInformationOfMachinery(List<String> sites) {
    List<Machinery> machinerys = new ArrayList<>();

    for (String site : sites) {
      log.info("Abrindo o site ---> {}", site);
      driver.get(site);

      String model =
          getTextFromElement(
              driver,
              "//*[@id=\"pdp-body\"]/div[3]/div/div[2]/section[3]/div[1]/div/ul/li[2]/span");
      String make =
          getTextFromElement(
              driver,
              "//*[@id=\"pdp-body\"]/div[3]/div/div[2]/section[3]/div[1]/div/ul/li[1]/span/ul/li");
      String year =
          getTextFromElement(
              driver,
              "//*[@id=\"pdp-body\"]/div[3]/div/div[2]/section[3]/div[1]/div/ul/li[4]/span/ul/li");
      String city = getTextFromElement(driver, "//*[@id=\"ProductInfo\"]/div[2]/div/p");
      String picture =
          getUrlFromElement(
              driver, "//*[@id=\"ProductCarousel\"]/div/div[1]/div[1]/div/img", "src");

      machinerys.add(
          Machinery.builder()
              .model(model)
              .make(make)
              .year(year)
              .city(city)
              .picture(picture)
              .build());
    }

    driver.quit();

    return machinerys;
  }
}
