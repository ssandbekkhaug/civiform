package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Optional;
import services.openApi.v3.DefinitionType;
import services.openApi.v3.Format;
import services.openApi.v3.Parameter;

public final class ParameterSerializer extends OpenApiSchemaSerializer<Parameter> {

  public ParameterSerializer() {
    this(null);
  }

  public ParameterSerializer(Class<Parameter> t) {
    super(t);
  }

  @Override
  public void serialize(Parameter value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    // open root
    gen.writeStartObject();

    gen.writeStringField("name", value.getName());
    gen.writeStringField("in", value.getIn().toString());
    writeStringFieldIfPresent(gen, "type", value.getDefinitionType().toString());

    if (canWriteFormat(value.getDefinitionType(), value.getFormat())) {
      writeEnumFieldIfPresent(gen, "format", value.getFormat());
    }

    gen.writeBooleanField("required", value.getRequired());
    writeStringFieldIfPresent(gen, "description", value.getDescription());

    // close root
    gen.writeEndObject();
  }

  /** Determines if the format field is allowed to be written */
  private Boolean canWriteFormat(DefinitionType definitionType, Optional<Format> format) {
    if (format.isEmpty()) {
      return false;
    }

    return definitionType != DefinitionType.OBJECT && definitionType != DefinitionType.ARRAY;
  }
}
