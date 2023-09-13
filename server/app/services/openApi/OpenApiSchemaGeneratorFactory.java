package services.openApi;

import javax.transaction.NotSupportedException;
import services.openApi.v3.OpenApi3SchemaGenerator;

public final class OpenApiSchemaGeneratorFactory {
  public static OpenApiSchemaGenerator createGenerator(
      OpenApiVersion openApiVersion, OpenApiSchemaSettings openApiSchemaSettings)
      throws NotSupportedException {
    switch (openApiVersion) {
      case OPENAPI_V3_1_0:
        return new OpenApi3SchemaGenerator(openApiSchemaSettings);
      default:
        throw new NotSupportedException(String.format("'%s' is not supported", openApiVersion));
    }
  }
}
