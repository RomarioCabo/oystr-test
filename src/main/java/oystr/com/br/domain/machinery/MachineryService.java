package oystr.com.br.domain.machinery;

import java.util.List;

public interface MachineryService {
  List<Machinery> getInformationOfMachinery(List<String> sites);
}
