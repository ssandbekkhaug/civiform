package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.Contact;
import services.openApi.v3.Server;

public class ServerSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    Server model = Server.builder("http://localhost:8000/api/v1/admin")
      .setDescription("lorem ipsum")
      .build();

    String expected =
        new YamlFormatter()
            .appendLine("url: http://localhost:8000/api/v1/admin")
            .appendLine("description: lorem ipsum")
            .toString();

    assertSerialization(model, expected);
  }

  @Test
  public void canSerializeEmptyObject() throws JsonProcessingException {
    Server model = Server.builder("http://localhost:8000/api/v1/admin").build();
    String expected =
      new YamlFormatter()
        .appendLine("url: http://localhost:8000/api/v1/admin")
        .toString();

    assertSerialization(model, expected);
  }
}
