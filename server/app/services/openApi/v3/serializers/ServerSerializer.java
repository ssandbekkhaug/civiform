package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import services.openApi.v3.Server;

import java.io.IOException;

public final class ServerSerializer extends OpenApiSchemaSerializer<Server> {

  public ServerSerializer() {
    this(null);
  }

  public ServerSerializer(Class<Server> t) {
    super(t);
  }

  @Override
  public void serialize(Server value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    // open root
    gen.writeStartObject();

    gen.writeStringField("url", value.getUrl());
    writeStringFieldIfPresent(gen, "description", value.getDescription());

    // close root
    gen.writeEndObject();
  }
}
