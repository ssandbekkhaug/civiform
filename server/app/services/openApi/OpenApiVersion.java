package services.openApi;

import javax.transaction.NotSupportedException;
import java.util.Optional;

public enum OpenApiVersion {
  SWAGGER_V2(VersionLabels.swagger_v2, "2.0"),
  OPENAPI_V3_0_0(VersionLabels.openapi_v3_0_0, "3.0.0"),
  OPENAPI_V3_1_0(VersionLabels.openapi_v3_1_0, "3.1.0");

  // Nest class is here as a hack/workaround because you can't put the strings directly in the enum
  // due to when they get initialized
  private static final class VersionLabels {
    private static final String swagger_v2 = "swagger-v2";
    private static final String openapi_v3_0_0 = "openapi-v3.0.0";
    private static final String openapi_v3_1_0 = "openapi-v3.1.0";
  }

  public static OpenApiVersion fromStringOrDefault(Optional<String> openApiVersion) {
    switch (openApiVersion.orElse(SWAGGER_V2.toString())) {
//      case VersionLabels.openapi_v3_0_0:
//        throw new NotSupportedException("v3.0.0 not implemented");
//        return OPENAPI_V3_0_0;
      case VersionLabels.openapi_v3_1_0:
        return OPENAPI_V3_1_0;
      case VersionLabels.swagger_v2:
      default:
        return SWAGGER_V2;
    }
  }

  private final String name;
  private final String versionNumber;

  OpenApiVersion(String name, String versionNumber) {
    this.name = name;
    this.versionNumber = versionNumber;
  }

  @Override
  public String toString() {
    return name;
  }

  public String getVersionNumber() {
    return versionNumber;
  }
}
