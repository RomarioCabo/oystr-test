package oystr.com.br.domain.image;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    private ImageService imageService;

    @Test
    @SneakyThrows
    @DisplayName("should save image")
    void shouldSaveImage()  {
        String url = "https://example.com/image.jpg";
        doNothing().when(imageService).saveImage(url);

        imageService.saveImage(url);

        verify(imageService, times(1)).saveImage(url);
    }

    @Test
    @DisplayName("should return throw exception when url is invalid")
    void shouldReturnThrowExceptionWhenUrlIsInvalid() {
        String invalidUrl = "invalidUrl";
        doThrow(new RuntimeException()).when(imageService).saveImage(invalidUrl);

        assertThrows(RuntimeException.class, () -> imageService.saveImage(invalidUrl));
    }
}
