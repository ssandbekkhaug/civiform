package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import services.openApi.v3.Operation;
import services.openApi.v3.Parameter;
import services.openApi.v3.PathItem;
import services.openApi.v3.Paths;

public final class PathsSerializer extends OpenApiSchemaSerializer<Paths> {

  public PathsSerializer() {
    this(null);
  }

  public PathsSerializer(Class<Paths> t) {
    super(t);
  }

  @Override
  public void serialize(Paths value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    // open root
    gen.writeStartObject();

    for (PathItem pathItem : value.getPathItems()) {
      gen.writeObjectFieldStart(pathItem.getRef());

      for (Operation operation : pathItem.getOperations()) {
        gen.writeObjectField(operation.getOperationType().toString(), operation);

        if (shouldWriteList(pathItem.getParameters())) {
          gen.writeArrayFieldStart("parameters");
          for (Parameter parameter : pathItem.getParameters()) {
            gen.writeObject(parameter);
          }
          gen.writeEndArray();
        }
      }

      gen.writeEndObject();
    }

    // close root
    gen.writeEndObject();
  }
}
