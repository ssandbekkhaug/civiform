package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.Header;
import services.openApi.v3.HttpStatusCode;
import services.openApi.v3.Response;

public class ResponseSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    Response model =
        Response.builder(HttpStatusCode.OK, "lorem ipsum")
            .setSchema("#/components/schemas/result")
            .addHeader(Header.builder(DefinitionType.STRING, "name1").build())
            .build();

    String expected =
        new YamlFormatter()
            .appendLine("\"200\":")
            .appendLine("  description: lorem ipsum")
            .appendLine("  headers:")
            .appendLine("    name1:")
            .appendLine("      schema:")
            .appendLine("        type: string")
//            .appendLine("  content:")
//            .appendLine("    application/json:")
//            .appendLine("      schema:")
//            .appendLine("        $ref: '#/components/schemas/result'")
            .toString();

    assertSerialization(new TestObjectContainer(model), expected);
  }

  @Test
  public void canSerializeEmptyObject() throws JsonProcessingException {
    Response model = Response.builder(HttpStatusCode.OK, "lorem ipsum").build();

    String expected =
        new YamlFormatter()
            .appendLine("\"200\":")
            .appendLine("  description: lorem ipsum")
            .toString();

    assertSerialization(new TestObjectContainer(model), expected);
  }
}
