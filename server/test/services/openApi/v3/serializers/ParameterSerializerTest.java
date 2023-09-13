package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.Format;
import services.openApi.v3.In;
import services.openApi.v3.Parameter;

public class ParameterSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    Parameter model =
        Parameter.builder("name1", In.QUERY, DefinitionType.STRING)
            .markAsRequired()
            .setFormat(Format.INT32)
            .setDescription("lorem ipsum")
            .build();
    ;
    String expected =
        new YamlFormatter()
            .appendLine("in: query")
            .appendLine("name: name1")
            .appendLine("type: string")
            .appendLine("format: int32")
            .appendLine("required: true")
            .appendLine("description: lorem ipsum")
            .toString();

    assertSerialization(model, expected);
  }

  @Test
  public void canSerializeEmptyObject() throws JsonProcessingException {
    Parameter model = Parameter.builder("name1", In.QUERY, DefinitionType.STRING).build();
    ;
    String expected =
        new YamlFormatter()
            .appendLine("in: query")
            .appendLine("name: name1")
            .appendLine("type: string")
            .appendLine("required: false")
            .toString();

    assertSerialization(model, expected);
  }
}