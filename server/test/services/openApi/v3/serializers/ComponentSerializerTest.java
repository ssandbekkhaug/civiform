package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.Component;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.Format;

public class ComponentSerializerTest extends OpenApiSerializationAsserter {
//  @Test
//  public void canSerializeFullObject() throws JsonProcessingException {
//    Component model =
//        Component.builder("name1", DefinitionType.INTEGER)
//            .setFormat(Format.INT32)
//            .addComponent(Component.builder("name2", DefinitionType.STRING).build())
//            .build();
//
//    String expected =
//        new YamlFormatter()
//            .appendLine("name1:")
//            .appendLine("  type: integer")
//            .appendLine("  format: int32")
//            .appendLine("  properties:")
//            .appendLine("    name2:")
//            .appendLine("      type: string")
//            .toString();
//
//    assertSerialization(new TestObjectContainer(model), expected);
//  }

//  @Test
//  public void canSerializeEmptyObject() throws JsonProcessingException {
//    Component model =
//      Component
//        .builder("name1", DefinitionType.STRING)
//        .build();
//
//    String expected =
//        new YamlFormatter()
//          .appendLine("name1:")
//          .appendLine("  type: string")
//          .toString();
//
//    assertSerialization(new TestObjectContainer(model), expected);
//  }
}
