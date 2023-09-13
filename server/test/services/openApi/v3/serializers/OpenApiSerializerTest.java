package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.OpenApiVersion;
import services.openApi.v3.Info;
import services.openApi.v3.OpenApi;
import services.openApi.v3.Operation;
import services.openApi.v3.OperationType;
import services.openApi.v3.PathItem;
import services.openApi.v3.Paths;
import services.openApi.v3.SecurityRequirement;
import services.openApi.v3.SecurityType;
import services.openApi.v3.Server;
import services.openApi.v3.Tag;


public class OpenApiSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    OpenApi model =
        OpenApi.builder(OpenApiVersion.OPENAPI_V3_1_0)
          .setInfo(Info.builder("title1", "version1").build())
          .addServer(Server.builder("http://hostname/root/path").build())
          .addSecurityRequirement(SecurityRequirement.builder(SecurityType.BASIC).build())
          .addTag(Tag.builder("tag1").setDescription("description1").build())
          .setPaths(
            Paths.builder().addPathItem(
              PathItem
                .builder("/ref1")
                .addOperation(
                  Operation.builder(OperationType.GET)

                    .build()
                )
                .build()
            ).build()
          )
          .build();

    String expected =
        new YamlFormatter()
          .appendLine("openapi: 3.1.0")
          .appendLine("info:")
          .appendLine("  title: title1")
          .appendLine("  version: version1")
          .appendLine("servers:")
          .appendLine("  - url: http://hostname/root/path")
          .appendLine("security:")
          .appendLine("  - basicAuth: []")
          .appendLine("tags:")
          .appendLine("  - name: tag1")
          .appendLine("    description: description1")
          .appendLine("paths:")
          .appendLine("  /ref1:")
          .appendLine("    get: {}")
          //.appendLine("      ")
          //.appendLine("components: {}")
          .toString();

    assertSerialization(model, expected);
  }

  @Test
  public void canSerializeEmptyObject() throws JsonProcessingException {
    OpenApi model = OpenApi.builder(OpenApiVersion.OPENAPI_V3_1_0).setInfo(Info.builder("title1", "version1").build()).build();

    String expected =
        new YamlFormatter()
            .appendLine("openapi: 3.1.0")
            .appendLine("info:")
            .appendLine("  title: title1")
            .appendLine("  version: version1")
            .toString();

    assertSerialization(model, expected);
  }
}
