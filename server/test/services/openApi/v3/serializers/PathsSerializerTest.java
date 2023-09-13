package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.In;
import services.openApi.v3.Operation;
import services.openApi.v3.OperationType;
import services.openApi.v3.Parameter;
import services.openApi.v3.PathItem;
import services.openApi.v3.Paths;

public class PathsSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    Paths model =
        Paths.builder()
            .addPathItem(
                PathItem.builder("/ref1")
                    .addOperation(
                        Operation.builder(OperationType.GET).setSummary("summary1").build())
                    .addParameter(
                        Parameter.builder("parameterName1", In.HEADER, DefinitionType.STRING)
                            .build())
                    .build())
            .build();

    String expected =
        new YamlFormatter()
            .appendLine("/ref1:")
            .appendLine("  get:")
            .appendLine("    summary: summary1")
            .appendLine("  parameters:")
            .appendLine("    - in: header")
            .appendLine("      name: parameterName1")
            .appendLine("      type: string")
            .appendLine("      required: false")
            .toString();

    assertSerialization(model, expected);
  }

  @Test
  public void canSerializeEmptyObject() throws JsonProcessingException {
    Paths model = Paths.builder().build();

    String expected = new YamlFormatter().appendLine("{}").toString();

    assertSerialization(model, expected);
  }
}