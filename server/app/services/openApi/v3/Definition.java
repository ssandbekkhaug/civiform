package services.openApi.v3;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

import java.util.Optional;

/**
 * https://swagger.io/specification/v2/#schema-object
 *
 * <p>The Schema Object allows the definition of input and output data types. These types can be
 * objects, but also primitives and arrays. This object is based on the JSON Schema Specification
 * Draft 4 and uses a predefined subset of it. On top of this subset, there are extensions provided
 * by this specification to allow for more complete documentation.
 */
@AutoValue
public abstract class Definition {
  public abstract String getName();

  public abstract DefinitionType getDefinitionType();

  /**
   * The extending format for the previously mentioned type. See Data Type Formats for further
   * details.
   */
  public abstract Optional<Format> getFormat();

  /** Get the child definitions */
  public abstract ImmutableList<Definition> getDefinitions();

  public static Builder builder(String name, DefinitionType definitionType) {
    return new AutoValue_Definition.Builder().setName(name).setDefinitionType(definitionType);
  }

  public static Builder builder(
          String name, DefinitionType definitionType, ImmutableList<Definition> definitions) {
    Builder result =
        new AutoValue_Definition.Builder().setName(name).setDefinitionType(definitionType);

    for (Definition definition : definitions) {
      result.addDefinition(definition);
    }

    return result;
  }

  @AutoValue.Builder
  public abstract static class Builder {
    protected abstract Builder setName(String name);

    protected abstract Builder setDefinitionType(DefinitionType definitionType);

    public abstract Builder setFormat(Format format);

    protected abstract ImmutableList.Builder<Definition> definitionsBuilder();

    public Builder addDefinition(Definition definition) {
      definitionsBuilder().add(definition);
      return this;
    }

    public abstract Definition build();
  }
}
