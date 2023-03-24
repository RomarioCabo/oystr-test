package oystr.com.br.domain.machinery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MachineryServiceTest {

  @Mock private MachineryService machineryService;

  @Test
  void testGetInformationOfMachinery() {
    List<String> sites = new ArrayList<>();
    sites.add("https://www.agrofy.com.br/montana-2011-4x4-full-3-0-221cv-turbo-diesel.html");

    when(machineryService.getInformationOfMachinery(sites)).thenReturn(buildMachineryList());

    List<Machinery> machinerys = machineryService.getInformationOfMachinery(sites);

    assertEquals(1, machinerys.size());
    assertEquals("950H", machinerys.get(0).getModel());
    assertEquals("Não informado", machinerys.get(0).getContract());
    assertEquals("Venda", machinerys.get(0).getContractType());
    assertEquals("Não informado", machinerys.get(0).getMake());
    assertEquals("2012", machinerys.get(0).getYear());
    assertEquals("20.000 h", machinerys.get(0).getWorkedHours());
    assertEquals("Desterro de Entre Rios / MG", machinerys.get(0).getCity());
    assertEquals("R$ 320.000,00", machinerys.get(0).getPrice());
    assertEquals(
        "https://imagens.mercadomaquinas.com.br/anuncios/mini/img-214554-pa-carregadeira-caterpillar-950h-2012-curitiba-pr-08e0a0cb.jpeg",
        machinerys.get(0).getPicture());
  }

  private List<Machinery> buildMachineryList() {
    return List.of(
        Machinery.builder()
            .model("950H")
            .contract("Não informado")
            .contractType("Venda")
            .make("Não informado")
            .year("2012")
            .workedHours("20.000 h")
            .city("Desterro de Entre Rios / MG")
            .price("R$ 320.000,00")
            .picture(
                "https://imagens.mercadomaquinas.com.br/anuncios/mini/img-214554-pa-carregadeira-caterpillar-950h-2012-curitiba-pr-08e0a0cb.jpeg")
            .build());
  }
}
