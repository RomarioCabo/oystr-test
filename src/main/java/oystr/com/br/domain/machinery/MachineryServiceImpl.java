package oystr.com.br.domain.machinery;

import java.util.ArrayList;
import java.util.HashMap;
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

  private static final String MODEL_KEY = "model";
  private static final String MAKE_KEY = "make";
  private static final String YEAR_KEY = "year";
  private static final String WORKED_HOURS_KEY = "worked_hours";
  private static final String CITY_KEY = "city";
  private static final String PRICE_KEY = "price";
  private static final String PICTURE_KEY = "picture";

  @Override
  public List<Machinery> getInformationOfMachinery(List<String> sites) {
    try {
      List<Machinery> machinerys = new ArrayList<>();

      for (String site : sites) {
        log.info("Abrindo o site ---> {}", site);
        driver.get(site);

        HashMap<String, String> element = getElementsBySite(site);

        String model = getTextFromElement(driver, element.get(MODEL_KEY));

        String make = getTextFromElement(driver, element.get(MAKE_KEY));

        String year = getTextFromElement(driver, element.get(YEAR_KEY));

        String workedHours = getTextFromElement(driver, element.get(WORKED_HOURS_KEY));

        String city = getTextFromElement(driver, element.get(CITY_KEY));

        String price = getTextFromElement(driver, element.get(PRICE_KEY));

        String picture = getUrlFromElement(driver, element.get(PICTURE_KEY));

        machinerys.add(
            Machinery.builder()
                .model(model)
                .contract("Não informado")
                .contractType(!price.equals("Não informado") ? "Venda" : "Alugel")
                .make(make)
                .year(year)
                .workedHours(workedHours)
                .city(city)
                .price(price)
                .picture(picture)
                .build());
      }

      driver.quit();

      return machinerys;
    } catch (Exception e) {
      e.printStackTrace();
      driver.quit();

      return new ArrayList<>();
    }
  }

  private HashMap<String, String> getElementsBySite(String site) {
    HashMap<String, String> elements = new HashMap<>();
    String modelKey = "";
    String makeKey = "";
    String yearKey = "";
    String workedHours = "";
    String cityKey = "";
    String priceKey = "";
    String pictureKey = "";

    if (site.contains("agrofy")) {
      modelKey = "//*[@id=\"pdp-body\"]/div[3]/div/div[2]/section[3]/div[1]/div/ul/li[2]/span";
      makeKey = "//*[@id=\"pdp-body\"]/div[3]/div/div[2]/section[3]/div[1]/div/ul/li[1]/span/ul/li";
      yearKey = "//*[@id=\"pdp-body\"]/div[3]/div/div[2]/section[3]/div[1]/div/ul/li[4]/span/ul/li";
      cityKey = "//*[@id=\"ProductInfo\"]/div[2]/div/p";
      priceKey = "//*[@id=\"ProductInfo\"]/span[1]";
      pictureKey = "//*[@id=\"ProductCarousel\"]/div/div[1]/div[1]/div/img";
    }

    if (site.contains("tratoresecolheitadeiras")) {
      modelKey = "//*[@id=\"tab1\"]/div/div/div[1]/p[4]/strong";
      makeKey = "//*[@id=\"tab1\"]/div/div/div[1]/p[3]/strong";
      yearKey = "//*[@id=\"tab1\"]/div/div/div[1]/p[5]/strong";
      cityKey = "//*[@id=\"page-content\"]/div[2]/div/div[1]/div/div[2]/div[2]/ul/li[2]";
      priceKey = "//*[@id=\"ProductPrice-product-template\"]/span";
      pictureKey = "//*[@id=\"zoompro\"]";
    }

    if (site.contains("mercadomaquinas")) {
      modelKey = "//*[@id=\"ad-details\"]/div[1]/div[1]/div/div[2]/div/ul/li[5]/span[2]/a";
      yearKey = "//*[@id=\"ad-details\"]/div[1]/div[1]/div/div[3]/div[3]/ul[1]/li[1]/span[2]";
      workedHours = "//span[@class='name' and text()='Horas trabalhadas:']/following-sibling::span[@class='value']";
      cityKey = "//*[@id=\"ad-details\"]/div[1]/div[2]/div[1]/div/div/span[2]";
      priceKey = "//*[@id=\"ad-details\"]/div[1]/div[1]/div/div[2]/div/div/span[1]";
      pictureKey = "//*[@id=\"ad-main-photo\"]/img";
    }

    elements.put(MODEL_KEY, modelKey);
    elements.put(MAKE_KEY, makeKey);
    elements.put(YEAR_KEY, yearKey);
    elements.put(WORKED_HOURS_KEY, workedHours);
    elements.put(CITY_KEY, cityKey);
    elements.put(PRICE_KEY, priceKey);
    elements.put(PICTURE_KEY, pictureKey);

    return elements;
  }
}
