package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.Format;
import services.openApi.v3.Header;

public class HeaderSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    Header model =
        Header.builder(DefinitionType.NUMBER, "name1")
            .setFormat(Format.INT32)
            .setDescription("lorem ipsum")
            .build();

    String expected =
        new YamlFormatter()
            .appendLine("name1:")
            .appendLine("  description: lorem ipsum")
            .appendLine("  schema:")
            .appendLine("    type: number")
            .appendLine("    format: int32")
            .toString();

    assertSerialization(new TestObjectContainer(model), expected);
  }

  @Test
  public void canSerializeEmptyObject() throws JsonProcessingException {
    Header model = Header.builder(DefinitionType.STRING, "name1").build();

    String expected =
      new YamlFormatter()
        .appendLine("name1:")
        .appendLine("  schema:")
        .appendLine("    type: string")
        .toString();

    assertSerialization(new TestObjectContainer(model), expected);
  }
}
