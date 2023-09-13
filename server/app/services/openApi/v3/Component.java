package services.openApi.v3;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

import java.util.Optional;

/**
 * <p>The Schema Object allows the definition of input and output data types. These types can be
 * objects, but also primitives and arrays. This object is based on the JSON Schema Specification
 * Draft 4 and uses a predefined subset of it. On top of this subset, there are extensions provided
 * by this specification to allow for more complete documentation.
 *
 * <p>https://swagger.io/specification/v2/#schema-object
 */
@AutoValue
public abstract class Component {
  public abstract String getName();

  public abstract DefinitionType getDefinitionType();

  /**
   * The extending format for the previously mentioned type. See Data Type Formats for further
   * details.
   */
  public abstract Optional<Format> getFormat();

  public abstract Boolean getNullable();

  /** Get the child definitions */
  public abstract ImmutableList<Component> getComponents();

  public static Component.Builder builder(String name, DefinitionType definitionType) {
    return new AutoValue_Component.Builder()
        .setName(name)
        .setDefinitionType(definitionType)
        .setNullable(false);
  }

  public static Component.Builder builder(
      String name, DefinitionType definitionType, ImmutableList<Component> components) {
    Builder result = builder(name, definitionType);

    for (Component component : components) {
      result.addComponent(component);
    }

    return result;
  }

  @AutoValue.Builder
  public abstract static class Builder {
    protected abstract Component.Builder setName(String name);

    protected abstract Component.Builder setDefinitionType(DefinitionType definitionType);

    public abstract Component.Builder setFormat(Format format);

    public abstract Component.Builder setNullable(Boolean nullable);

    protected abstract ImmutableList.Builder<Component> componentsBuilder();

    public Component.Builder addComponent(Component component) {
      componentsBuilder().add(component);
      return this;
    }

    public Component.Builder addComponents(ImmutableList<Component> components) {
      for (Component component : components) {
        addComponent(component);
      }
      return this;
    }

    public abstract Component build();
  }
}
