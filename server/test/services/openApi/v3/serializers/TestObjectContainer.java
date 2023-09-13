package services.openApi.v3.serializers;

import services.openApi.v3.Component;
import services.openApi.v3.Header;
import services.openApi.v3.Response;

/**
 * Some serializers don't directly start as a new field and thus will throw JsonGenerationException:
 * Can not write a field name, expecting a value
 *
 * <p>This class acts as a small container to test the serialization of a specific object
 */
public class TestObjectContainer {
  private Header header;
  private Response response;
  private Component schema;

  public TestObjectContainer(Header header) {
    this.header = header;
  }

  public TestObjectContainer(Response response) {
    this.response = response;
  }

  public TestObjectContainer(Component schema) {
    this.schema = schema;
  }

  public Header getHeader() {
    return header;
  }

  public Response getResponse() {
    return response;
  }

  public Component getSchema() {
    return schema;
  }
}
