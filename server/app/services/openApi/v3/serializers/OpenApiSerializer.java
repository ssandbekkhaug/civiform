package services.openApi.v3.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import services.openApi.v3.OpenApi;
import services.openApi.v3.Component;
import services.openApi.v3.SecurityRequirement;
import services.openApi.v3.Server;
import services.openApi.v3.Tag;

public final class OpenApiSerializer extends OpenApiSchemaSerializer<OpenApi> {

  public OpenApiSerializer() {
    this(null);
  }

  public OpenApiSerializer(Class<OpenApi> t) {
    super(t);
  }

  @Override
  public void serialize(OpenApi value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    // open root
    gen.writeStartObject();

    // fields
    gen.writeStringField("openapi", value.getOpenApiVersionNumber());

    // info
    gen.writeObjectField("info", value.getInfo());

    // servers
    if (shouldWriteList(value.getServers())) {
      gen.writeArrayFieldStart("servers");
      for (Server server : value.getServers()) {
        gen.writeObject(server);
      }
      gen.writeEndArray();
    }

//    // schemes
//    if (shouldWriteList(value.getSchemes())) {
//      gen.writeArrayFieldStart("schemes");
//      for (Scheme scheme : value.getSchemes()) {
//        gen.writeString(scheme.toString());
//      }
//      gen.writeEndArray();
//    }

    // security
    if (shouldWriteList(value.getSecurityRequirements())) {
      gen.writeArrayFieldStart("security");
      for (SecurityRequirement securityRequirement : value.getSecurityRequirements()) {
        gen.writeObject(securityRequirement);
      }
      gen.writeEndArray();
    }

//    // securityDefinitions
//    if (shouldWriteList(value.getSecurityDefinitions())) {
//      gen.writeObjectFieldStart("securityDefinitions");
//      for (SecurityDefinition securityDefinition : value.getSecurityDefinitions()) {
//        gen.writeObjectField(securityDefinition.getLabel(), securityDefinition);
//      }
//      gen.writeEndObject();
//    }

    // tags
    if (shouldWriteList(value.getTags())) {
      gen.writeArrayFieldStart("tags");
      for (Tag tag : value.getTags()) {
        gen.writeObject(tag);
      }
      gen.writeEndArray();
    }

    // paths
    if (value.getPaths().isPresent() && shouldWriteList(value.getPaths().get().getPathItems())) {
      gen.writeObjectField("paths", value.getPaths().get());
    }
//
//    // components
//    if (shouldWriteList(value.getComponents())) {
//      gen.writeObjectFieldStart("components");
//      for (Component component : value.getComponents()) {
//        gen.writeObject(component);
//      }
//      gen.writeEndObject();
//    }

    // close root
    gen.writeEndObject();
  }
}
