package services.openApi.v3.serializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import services.openApi.v3.Contact;
import services.openApi.v3.Component;
import services.openApi.v3.Header;
import services.openApi.v3.Info;
import services.openApi.v3.OpenApi;
import services.openApi.v3.Operation;
import services.openApi.v3.Parameter;
import services.openApi.v3.Paths;
import services.openApi.v3.Response;
import services.openApi.v3.SecurityDefinition;
import services.openApi.v3.SecurityRequirement;
import services.openApi.v3.Server;
import services.openApi.v3.Tag;


public final class OpenApi3YamlMapper {
  public static ObjectMapper getMapper() {

    ObjectMapper mapper =
        new YAMLMapper()
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR)
            .enable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS)
            .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
            .disable(YAMLGenerator.Feature.SPLIT_LINES)
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    SimpleModule module =
        new SimpleModule()
            .addSerializer(OpenApi.class, new OpenApiSerializer())
            .addSerializer(Info.class, new InfoSerializer())
            .addSerializer(Contact.class, new ContactSerializer())
            .addSerializer(Server.class, new ServerSerializer())
            .addSerializer(SecurityRequirement.class, new SecurityRequirementSerializer())
            .addSerializer(SecurityDefinition.class, new SecurityDefinitionSerializer())
            .addSerializer(Tag.class, new TagSerializer())
            .addSerializer(Paths.class, new PathsSerializer())
            .addSerializer(Operation.class, new OperationSerializer())
            .addSerializer(Parameter.class, new ParameterSerializer())
            .addSerializer(Component.class, new ComponentSerializer())
            .addSerializer(Response.class, new ResponseSerializer())
            .addSerializer(Header.class, new HeaderSerializer())
      ;

    mapper.registerModule(module);

    return mapper;
  }
}
