package oystr.com.br.infrastructure.mapper;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.TimeZone;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = PRIVATE)
public class Mapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.disable(WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(ACCEPT_FLOAT_AS_INT);
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(FAIL_ON_MISSING_CREATOR_PROPERTIES);
        objectMapper.enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String toJson(Object data) {
        try {
            return getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T jSonToClass(String data, Class<T> clazz) {
        try {
            return getInstance().readValue(data, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
