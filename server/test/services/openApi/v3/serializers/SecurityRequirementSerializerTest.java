package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import services.openApi.v3.SecurityRequirement;
import services.openApi.v3.SecurityType;

public class SecurityRequirementSerializerTest extends OpenApiSerializationAsserter {
  @Test
  public void canSerializeFullObject() throws JsonProcessingException {
    SecurityRequirement model = SecurityRequirement.builder(SecurityType.BASIC).build();

    String expected = new YamlFormatter().appendLine("basicAuth: []").toString();

    assertSerialization(model, expected);
  }
}