package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Optional;

import services.openApi.v3.Component;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.Format;

public final class ComponentSerializer extends OpenApiSchemaSerializer<Component> {

  public ComponentSerializer() {
    this(null);
  }

  public ComponentSerializer(Class<Component> t) {
    super(t);
  }

  @Override
  public void serialize(Component value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    // open root
    gen.writeObjectFieldStart(value.getName());
    gen.writeStringField("type", value.getDefinitionType().toString());

    if (canWriteFormat(value.getFormat())) {
      writeEnumFieldIfPresent(gen, "format", value.getFormat());
    }

    if (value.getNullable()) {
      gen.writeBooleanField("nullable", value.getNullable());
    }

    // process child schemas
    if (value.getDefinitionType() == DefinitionType.ARRAY) {
      gen.writeObjectFieldStart("items");
      gen.writeStringField("type", DefinitionType.OBJECT.toString());
      processChildDefinitions(value.getComponents(), gen);
      gen.writeEndObject();
    } else {
      processChildDefinitions(value.getComponents(), gen);
    }

    // close root
    gen.writeEndObject();
  }

  private void processChildDefinitions(
          ImmutableList<Component> childDefinitions, JsonGenerator gen) throws IOException {
    if (!childDefinitions.isEmpty()) {
      gen.writeObjectFieldStart("properties");
      for (Component definition : childDefinitions) {
        gen.writeObject(definition);
      }
      gen.writeEndObject();
    }
  }

  /** Determines if the format field is allowed to be written */
  private Boolean canWriteFormat(Optional<Format> format) {
    if (format.isEmpty()) {
      return false;
    }

    return format.get() != Format.STRING && format.get() != Format.ARRAY;
  }
}
