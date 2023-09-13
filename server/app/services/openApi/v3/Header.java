package services.openApi.v3;

import com.google.auto.value.AutoValue;

import java.util.Optional;

/**
 * https://spec.openapis.org/oas/v3.1.0#header-object
 *
 * <p>Settings for a header
 */
@AutoValue
public abstract class Header {
  /**
   * REQUIRED.
   *
   * The name of the parameter. Parameter names are case sensitive.
   */
  public abstract String getName();

  /**
   * Required.
   *
   * <p>The type of the object. The value MUST be one of "string", "number", "integer", "boolean",
   * or "array".
   */
  public abstract DefinitionType getType();

  /**
   * A description for the tag.
   */
  public abstract Optional<String> getDescription();

  /**
   * The extending format for the previously mentioned type. See Data Type Formats for further
   * details.
   */
  public abstract Optional<Format> getFormat();

  public static Header.Builder builder(DefinitionType definitionType, String name) {
    return new AutoValue_Header.Builder().setType(definitionType).setName(name);
  }

  @AutoValue.Builder
  public abstract static class Builder {
    protected abstract Header.Builder setName(String name);

    protected abstract Header.Builder setType(DefinitionType definitionType);

    public abstract Header.Builder setDescription(String description);

    public abstract Header.Builder setFormat(Format format);

    public abstract Header build();
  }
}
