package services.openApi.v3;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import services.openApi.OpenApiVersion;

import java.util.Optional;

/**
 * This is the root object of the OpenAPI document.
 *
 * <p>https://spec.openapis.org/oas/v3.1.0#openapi-object
 */
@AutoValue
public abstract class OpenApi {
  /**
   * Required.
   *
   * <p>Specifies the Swagger Specification version being used. It can be used by the Swagger UI and
   * other clients to interpret the API listing. The value MUST be "2.0".
   */
  public abstract String getOpenApiVersionNumber();

  /**
   * Required.
   *
   * <p>Provides metadata about the API. The metadata can be used by the clients if needed
   */
  public abstract Info getInfo();

  /**
   * An array of Server Objects, which provide connectivity information to a target server. If the servers property is not provided, or is an empty array, the default value would be a Server Object with a url value of /.
   */
  public abstract ImmutableList<Server> getServers();

//  /**
//   * The transfer protocol of the API. Values MUST be from the list: "http", "https", "ws", "wss".
//   * If the schemes is not included, the default scheme to be used is the one used to access the
//   * Swagger definition itself.
//   */
  public abstract ImmutableList<Scheme> getSchemes();

//  /**
//   * A declaration of which security schemes are applied for the API as a whole. The list of values
//   * describes alternative security schemes that can be used (that is, there is a logical OR between
//   * the security requirements). Individual operations can override this definition.
//   */
  public abstract ImmutableList<SecurityRequirement> getSecurityRequirements();

//  /** Security scheme definitions that can be used across the specification. */
  public abstract ImmutableList<SecurityDefinition> getSecurityDefinitions();

  /**
   * A list of tags used by the specification with additional metadata. The order of the tags can be
   * used to reflect on their order by the parsing tools. Not all tags that are used by the
   * Operation Object must be declared. The tags that are not declared may be organized randomly or
   * based on the tools' logic. Each tag name in the list MUST be unique.
   */
  public abstract ImmutableList<Tag> getTags();

//  /**
//   * Required.
//   *
//   * <p>The available paths and operations for the API.
//   */
  public abstract Optional<Paths> getPaths();

//  /** An object to hold data types produced and consumed by operations. */
  public abstract ImmutableList<Component> getComponents();

  public static Builder builder(OpenApiVersion openApiVersion) {
    return new AutoValue_OpenApi.Builder()
      .setOpenApiVersionNumber(openApiVersion.getVersionNumber());
  }

  @AutoValue.Builder
  public abstract static class Builder {
    protected abstract Builder setOpenApiVersionNumber(String openApiVersionNumber);

    public abstract Builder setInfo(Info info);

    protected abstract ImmutableList.Builder<Server> serversBuilder();

    public Builder addServer(Server server) {
      serversBuilder().add(server);
      return this;
    }


    public abstract Builder setSchemes(ImmutableList<Scheme> schemes);

    public abstract Builder setPaths(Paths paths);

    protected abstract ImmutableList.Builder<SecurityRequirement> securityRequirementsBuilder();

    protected abstract ImmutableList.Builder<SecurityDefinition> securityDefinitionsBuilder();

    protected abstract ImmutableList.Builder<Tag> tagsBuilder();

    protected abstract ImmutableList.Builder<Scheme> schemesBuilder();

    protected abstract ImmutableList.Builder<Component> componentsBuilder();

    public Builder addSecurityRequirement(SecurityRequirement securityRequirement) {
      securityRequirementsBuilder().add(securityRequirement);
      return this;
    }

    public Builder addSecurityDefinition(SecurityDefinition securityDefinition) {
      securityDefinitionsBuilder().add(securityDefinition);
      return this;
    }

    public Builder addTag(Tag tag) {
      tagsBuilder().add(tag);
      return this;
    }

    public Builder addScheme(Scheme scheme) {
      schemesBuilder().add(scheme);
      return this;
    }

    public Builder addComponent(Component component) {
      componentsBuilder().add(component);
      return this;
    }

    public abstract OpenApi build();
  }
}
