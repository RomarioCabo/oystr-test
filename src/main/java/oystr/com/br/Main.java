package oystr.com.br;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import oystr.com.br.domain.image.ImageService;
import oystr.com.br.domain.machinery.Machinery;
import oystr.com.br.domain.machinery.MachineryService;

@Slf4j
@Component
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(SpringConfig.class);

    MachineryService machineryService = context.getBean(MachineryService.class);
    ImageService imageService = context.getBean(ImageService.class);

    List<String> sites =
        List.of("https://www.agrofy.com.br/trator-mahindra-5050-novo-49cv-4x4-4cilindros.html");
    List<Machinery> machinerys = machineryService.getInformationOfMachinery(sites);

    machinerys.stream()
        .peek(machinery -> imageService.saveImage(machinery.getPicture()))
        .forEach(machinery -> log.info("maquinário --> {}", machinery));

    context.close();
  }
}
