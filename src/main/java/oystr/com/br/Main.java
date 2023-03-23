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
        List.of(
                "https://www.agrofy.com.br/trator-mahindra-5050-novo-49cv-4x4-4cilindros.html",
                "https://www.agrofy.com.br/trator-mahindra-6075-novo-80cv-4x4-4cilindros.html",
                "https://www.tratoresecolheitadeiras.com.br/veiculo/uberlandia/mg/plataforma-colheitadeira/gts/flexer-xs-45/2023/45-pes/draper/triamaq-tratores/1028839",
                "https://www.tratoresecolheitadeiras.com.br/veiculo/uberlandia/mg/plataforma-colheitadeira/gts/produttiva-1250/2022/caracol/12-linhas/triamaq-tratores/994257",
                "https://www.mercadomaquinas.com.br/anuncio/218193-escavadeira-caterpillar-320c-2006-aruja-sp",
                "https://www.mercadomaquinas.com.br/anuncio/214554-pa-carregadeira-caterpillar-950h-2012-curitiba-pr"
        );

    List<Machinery> machinerys = machineryService.getInformationOfMachinery(sites);

    machinerys.stream()
        .peek(machinery -> imageService.saveImage(machinery.getPicture()))
        .forEach(machinery -> log.info("maquinário --> {}", machinery));

    context.close();
  }
}
