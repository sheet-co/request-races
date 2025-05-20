package dev.sheet_co.request_races.config.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {
  @Bean
  Jackson2ObjectMapperBuilder objectMapperBuilder() {
    var builder = new Jackson2ObjectMapperBuilder();
    builder
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .modulesToInstall(new JsonNullableModule());
    var dataTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    builder.serializers(new LocalDateTimeSerializer(dataTimeFormatter));
    return builder;
  }
}
