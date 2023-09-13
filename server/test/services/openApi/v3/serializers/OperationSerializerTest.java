package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.HttpStatusCode;
import services.openApi.v3.In;
import services.openApi.v3.MimeType;
import services.openApi.v3.Operation;
import services.openApi.v3.OperationType;
import services.openApi.v3.Parameter;
import services.openApi.v3.Response;

public class OperationSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    Operation model =
        Operation.builder(OperationType.GET)
            .setSummary("summary1")
            .setOperationId("operationId1")
            .setDescription("description1")
            .addProduces(MimeType.Json)
            .addResponse(Response.builder(HttpStatusCode.OK, "lorem ipsum").build())
            .addParameter(Parameter.builder("name1", In.HEADER, DefinitionType.STRING).build())
            .addTag("tag1")
            .build();

    String expected =
        new YamlFormatter()
            .appendLine("summary: summary1")
            .appendLine("operationId: operationId1")
            .appendLine("description: description1")
            .appendLine("parameters:")
            .appendLine("  - in: header")
            .appendLine("    name: name1")
            .appendLine("    type: string")
            .appendLine("    required: false")
            .appendLine("produces:")
            .appendLine("  - application/json")
            .appendLine("responses:")
            .appendLine("  \"200\":")
            .appendLine("    description: lorem ipsum")
            .appendLine("tags:")
            .appendLine("  - tag1")
            .toString();

    assertSerialization(model, expected);
  }

  @Test
  public void canSerializeEmptyObject() throws JsonProcessingException {
    Operation model = Operation.builder(OperationType.GET).build();

    String expected = new YamlFormatter().appendLine("{}").toString();

    assertSerialization(model, expected);
  }
}
