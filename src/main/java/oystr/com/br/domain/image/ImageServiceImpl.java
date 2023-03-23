package oystr.com.br.domain.image;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

  @Override
  public void saveImage(String imageUrl) {
    try {
      URL url = new URL(imageUrl);
      String extension = getExtension(imageUrl.substring(imageUrl.lastIndexOf('/') + 1));
      String fileName = String.format("%s.%s", buildFileName(), extension);
      log.info("FileName ---> {}", fileName);

      Path path = Paths.get("src/main/resources/img/");
      if (!Files.exists(path)) {
        Files.createDirectory(path);
      }

      try (InputStream in = url.openStream()) {
        Files.copy(in, Paths.get(path.toString(), fileName), REPLACE_EXISTING);
      }

      log.info("Imagem salva no dirtótio {} com o nome {}", path, fileName);
    } catch (Exception e) {
      log.error("Não foi possível salvar Imagem.", e);
    }
  }

  private String getExtension(String str) {
    Pattern pattern = Pattern.compile("\\.([a-zA-Z0-9]+)(\\?|$)");
    Matcher matcher = pattern.matcher(str);
    if (matcher.find()) {
      return matcher.group(1);
    }

    return "jpg";
  }

  private String buildFileName() {
    return String.format(
            "%s%s%s%s%s%s%s",
            LocalDateTime.now().getYear(),
            formatNumber(LocalDateTime.now().getMonthValue()),
            formatNumber(LocalDateTime.now().getDayOfMonth()),
            formatNumber(LocalDateTime.now().getHour()),
            formatNumber(LocalDateTime.now().getMinute()),
            formatNumber(LocalDateTime.now().getSecond()),
            LocalDateTime.now().getNano()
    );
  }

  private String formatNumber(int number) {
    return String.format("%02d", number);
  }
}
