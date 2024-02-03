package services.openApi.v3;


<<<<<<< Updated upstream
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import services.applicant.question.Scalar;
import services.openApi.OpenApiGenerationException;
||||||| constructed merge base
import org.apache.commons.lang3.NotImplementedException;
=======
import com.google.common.collect.ImmutableList;
>>>>>>> Stashed changes
import services.openApi.OpenApiSchemaGenerator;
import services.openApi.OpenApiSchemaSettings;
<<<<<<< Updated upstream
import services.openApi.OpenApiVersion;
import services.openApi.QuestionDefinitionNode;
import services.openApi.v3.serializers.OpenApi3YamlMapper;
import services.program.BlockDefinition;
||||||| constructed merge base
=======
import services.applicant.question.Scalar;
import services.program.BlockDefinition;
>>>>>>> Stashed changes
import services.program.ProgramDefinition;
<<<<<<< Updated upstream
import services.question.exceptions.InvalidQuestionTypeException;
import services.question.exceptions.UnsupportedQuestionTypeException;
import services.question.types.QuestionDefinition;
import services.question.types.QuestionType;
import services.question.types.ScalarType;
||||||| constructed merge base
=======
import services.question.exceptions.InvalidQuestionTypeException;
import services.question.exceptions.UnsupportedQuestionTypeException;
import services.question.types.QuestionDefinition;
import services.question.types.ScalarType;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
>>>>>>> Stashed changes

import static com.google.common.base.Preconditions.checkNotNull;
/** Configuration used to generate a program specific swagger 2 schema */
public class OpenApi3SchemaGenerator implements OpenApiSchemaGenerator {
  private final String baseUrl;

  public OpenApi3SchemaGenerator(OpenApiSchemaSettings openApiSchemaSettings) {
    checkNotNull(openApiSchemaSettings);
    this.baseUrl = openApiSchemaSettings.getBaseUrl();
  }

  @Override
  public String createSchema(ProgramDefinition programDefinition) {
<<<<<<< Updated upstream
    try {
      OpenApi openApiRoot = OpenApi.builder(OpenApiVersion.OPENAPI_V3_1_0)
        .setInfo(
          Info.builder(programDefinition.adminName(), Long.toString(programDefinition.id()))
            .setDescription(programDefinition.adminDescription())
            .setContact(
              Contact.builder()
                .setName("CiviForm Technical Support")
                .setEmail(openApiSchemaSettings.getItEmailAddress())
                .build())
            .build())
        .addServer(
          Server.builder(openApiSchemaSettings.getBaseUrl() + "/api/v1/admin").build()
        )
        .addSecurityRequirement(SecurityRequirement.builder(SecurityType.BASIC).build())
        .addTag(Tag.builder("programs").build())
        .setPaths(
          Paths.builder()
            .addPathItem(
              PathItem.builder("/applications")
                .addOperation(
                  Operation.builder(OperationType.GET)
                    .setOperationId("get_applications")
                    .setDescription("Get Applications")
                    .setSummary("Export applications")
                    .addProduces(MimeType.Json)
                    .addTag("programs")
                    .addResponse(
                      Response.builder(HttpStatusCode.OK, "For valid requests.")
                        .setSchema("#/definitions/result")
                        .addHeader(
                          Header.builder(DefinitionType.STRING, "x-next")
                            .setDescription(
                              "A link to the next page of responses")
                            .build())
                        .build())
                    .addResponse(
                      Response.builder(
                          HttpStatusCode.BadRequest,
                          "Returned if any request parameters fail"
                            + " validation.")
                        .build())
                    .addResponse(
                      Response.builder(
                          HttpStatusCode.Unauthorized,
                          "Returned if the API key is invalid or does not"
                            + " have access to the program.")
                        .build())
                    .addParameter(
                      Parameter.builder(
                          "fromDate", In.QUERY, DefinitionType.STRING)
                        .setDescription(
                          "An ISO-8601 formatted date (i.e. YYYY-MM-DD)."
                            + " Limits results to applications submitted"
                            + " on or after the provided date.")
                        .build())
                    .addParameter(
                      Parameter.builder(
                          "toDate", In.QUERY, DefinitionType.STRING)
                        .setDescription(
                          "An ISO-8601 formatted date (i.e. YYYY-MM-DD)."
                            + " Limits results to applications submitted"
                            + " before the provided date.")
                        .build())
                    .addParameter(
                      Parameter.builder(
                          "pageSize", In.QUERY, DefinitionType.INTEGER)
                        .setDescription(
                          "A positive integer. Limits the number of"
                            + " results per page. If pageSize is larger"
                            + " than CiviForm's maximum page size then"
                            + " the maximum will be used. The default"
                            + " maximum is 1,000 and is configurable.")
                        .build())
                    .addParameter(
                      Parameter.builder(
                          "nextPageToken", In.QUERY, DefinitionType.STRING)
                        .setDescription(
                          "An opaque, alphanumeric identifier for a"
                            + " specific page of results. When included"
                            + " CiviForm will return a page of results"
                            + " corresponding to the token.")
                        .build())
                    .build())
                .build())
            .build())
        .build();

      ObjectMapper mapper = OpenApi3YamlMapper.getMapper();
      return mapper.writeValueAsString(openApiRoot);
    } catch (RuntimeException | JsonProcessingException ex) {
      throw new OpenApiGenerationException(OpenApiVersion.OPENAPI_V3_1_0, "Unable to generate OpenAPI schema.", ex);
    }
  }

  /**
   * Returns the list of schemes to allow in the swagger schema. Special case to allow http for
   * non-prod environments for testing purposes.
   */
  private ImmutableList<Scheme> getSchemes() {
    if (openApiSchemaSettings.getAllowHttpScheme()) {
      return ImmutableList.of(Scheme.HTTP, Scheme.HTTPS);
    }

    return ImmutableList.of(Scheme.HTTPS);
  }

  /***
   * Entry point to start building the program specific definitions for questions
   */
  private ImmutableList<Component> buildApplicationDefinitions(ProgramDefinition programDefinition)
      throws InvalidQuestionTypeException, UnsupportedQuestionTypeException {

    QuestionDefinitionNode rootNode = getQuestionDefinitionRootNode(programDefinition);

    return new ArrayList<>(buildApplicationDefinitions(rootNode))
        .stream()
            .sorted(Comparator.comparing(Component::getName))
            .collect(ImmutableList.toImmutableList());
  }

  /**
   * Recursive method used to build out the full object graph from the tree of QuestionDefinitions
   */
  private ImmutableList<Component> buildApplicationDefinitions(
      QuestionDefinitionNode parentQuestionDefinitionNode)
      throws InvalidQuestionTypeException, UnsupportedQuestionTypeException {
    ArrayList<Component> definitionList = new ArrayList<>();

    for (QuestionDefinitionNode childQuestionDefinitionNode :
        parentQuestionDefinitionNode.getChildren()) {
      QuestionDefinition questionDefinition = childQuestionDefinitionNode.getQuestionDefinition();

      if (excludeFromSchemaOutput(questionDefinition)) {
        continue;
      }

      Component.Builder containerDefinition =
          Component.builder(
              questionDefinition.getQuestionNameKey().toLowerCase(Locale.ROOT),
              DefinitionType.OBJECT);

      containerDefinition.addComponent(
          Component.builder("question_type", DefinitionType.STRING).build());

      // Enumerators require special handling
      if (questionDefinition.getQuestionType() != QuestionType.ENUMERATOR) {
        for (Scalar scalar : getScalarsSortedByName(questionDefinition)) {
          String fieldName = scalar.name().toLowerCase(Locale.ROOT);
          DefinitionType definitionType = getDefinitionTypeFromSwaggerType(scalar.toScalarType());
          Format swaggerFormat = getSwaggerFormat(scalar.toScalarType());
          Boolean nullable = setAsNull(definitionType);

          containerDefinition.addComponent(
              Component.builder(fieldName, definitionType)
                  .setFormat(swaggerFormat)
                  .setNullable(nullable)
                  .build());
        }
      } else {
        Component enumeratorEntitiesDefinition =
            Component.builder("entities", DefinitionType.ARRAY)
                .addComponents(buildApplicationDefinitions(childQuestionDefinitionNode))
                .build();

        containerDefinition.addComponent(enumeratorEntitiesDefinition);
      }

      definitionList.add(containerDefinition.build());
    }

    return definitionList.stream().collect(ImmutableList.toImmutableList());
  }

  /** Build an n-ary tree from the flat of QuestionDefinition list */
  private QuestionDefinitionNode getQuestionDefinitionRootNode(
      ProgramDefinition programDefinition) {
    ArrayList<QuestionDefinition> list = new ArrayList<>();

    for (BlockDefinition blockDefinition : programDefinition.blockDefinitions()) {
      for (int i = 0; i < blockDefinition.getQuestionCount(); i++) {
        list.add(blockDefinition.getQuestionDefinition(i));
      }
    }

    // Getting a sorted list to allow placing the enumerator questions
    // into the tree before the questions that are children to the enumerator.
    // An enumerator already has to be created before a question can be added to it
    // and questions can only be assigned a parent enumerator when first created.
    var sortedList =
        list.stream()
            .sorted(Comparator.comparing(QuestionDefinition::getId))
            .collect(ImmutableList.toImmutableList());

    QuestionDefinitionNode rootNode = QuestionDefinitionNode.createRootNode();

    for (QuestionDefinition questionDefinition : sortedList) {
      rootNode.addQuestionDefinition(questionDefinition);
    }

    return rootNode;
  }

  /** Get the list of scalars for a question sorted alphabetically */
  private ImmutableList<Scalar> getScalarsSortedByName(QuestionDefinition questionDefinition)
    throws InvalidQuestionTypeException, UnsupportedQuestionTypeException {
    return Scalar.getScalars(questionDefinition.getQuestionType()).stream()
      .sorted(Comparator.comparing(Enum::name))
      .collect(ImmutableList.toImmutableList());
  }

  /** Gets the baseurl without scheme */
  private String getHostName() {
    return openApiSchemaSettings.getBaseUrl().replace("https://", "").replace("http://", "");
  }

  /** Map ScalarType to DefinitionType */
  private DefinitionType getDefinitionTypeFromSwaggerType(ScalarType scalarType) {
    switch (scalarType) {
      case LONG:
        return DefinitionType.INTEGER;
      case DOUBLE:
        return DefinitionType.NUMBER;
      case LIST_OF_STRINGS:
        return DefinitionType.ARRAY;
      case CURRENCY_CENTS:
      case DATE:
      case STRING:
      case PHONE_NUMBER:
      case SERVICE_AREA:
      default:
        return DefinitionType.STRING;
    }
  }

/*
  private String getSwaggerType(ScalarType scalarType) {
    switch (scalarType) {
      case CURRENCY_CENTS:
      case DATE:
      case STRING:
      case PHONE_NUMBER:
      case SERVICE_AREA:
        return "string";
      case LONG:
        return "integer";
      case DOUBLE:
        return "number";
      case LIST_OF_STRINGS:
        return "array";
      default:
        return "";
    }
  }
*/

  /** Map ScalarType to Format */
  private Format getSwaggerFormat(ScalarType scalarType) {
    switch (scalarType) {
      case DATE:
        return Format.DATE;
      case LONG:
        return Format.INT64;
      case CURRENCY_CENTS:
      case DOUBLE:
        return Format.DOUBLE;
      case LIST_OF_STRINGS:
        return Format.ARRAY;
      case STRING:
      case PHONE_NUMBER:
      case SERVICE_AREA:
      default:
        return Format.STRING;
    }
  }

  /** Determines if the question should be flagged as nullable */
  private Boolean setAsNull(DefinitionType definitionType) {
    return definitionType != DefinitionType.ARRAY && definitionType != DefinitionType.OBJECT;
  }

  /** Rules to determine if a question is included in the schema generation output. */
  private Boolean excludeFromSchemaOutput(QuestionDefinition questionDefinition) {
    // Static questions are not in the api results
    return questionDefinition.getQuestionType() == QuestionType.STATIC;
||||||| constructed merge base
    throw new NotImplementedException(
        "OpenApi v3 is not yet implemented." + openApiSchemaSettings.getBaseUrl());
=======
    var yamlFormatter = new YamlFormatter()
      .append("openapi: ''3.0.0''")

      .append("info:")
      .append(1, "title: ''{0}''", "")
      .append(1, "description: ''{0}''", "")
      .append(1, "version: ''{0}''", "1.0")

      .append("servers:")
      .append(1, "- url: {0}/api/v1/programs/{1}", baseUrl, programDefinition.slug())

      .append("security:")
      .append(1, "- basicAuth: []")

      .append("tags: []")

      .append("paths:")
      .append(1, "/applications:")
      .append(2, "get:")
      .append(3, "summary: ''Export applications''")
      .append(3, "tags:")
      .append(4, "- programs")

      .append(3, "parameters:")
      .append(4, "- name: fromDate")
      .append(5, "in: query")
      .append(5, "description: >-")
      .append(6, "An ISO-8601 formatted date (i.e. YYYY-MM-DD). Limits results to")
      .append(6, "applications submitted on or after the provided date.")
      .append(5, "required: false")
      .append(5, "schema:")
      .append(6, "type: string")
      .append(6, "format: date")

      .append(4, "- name: toDate")
      .append(5, "in: query")
      .append(5, "description: >-")
      .append(6, "An ISO-8601 formatted date (i.e. YYYY-MM-DD). Limits results to")
      .append(6, "applications submitted before the provided date.")
      .append(5, "required: false")
      .append(5, "schema:")
      .append(6, "type: string")
      .append(6, "format: date")

      .append(4, "- name: pageSize")
      .append(5, "in: query")
      .append(5, "description: >-")
      .append(6, "A positive integer. Limits the number of results per page. If")
      .append(6, "pageSize is larger than CiviForm''s maximum page size then the")
      .append(6, "maximum will be used. The default maximum is 1,000 and is")
      .append(6, "configurable.")
      .append(5, "required: false")
      .append(5, "schema:")
      .append(6, "type: integer")

      .append(4, "- name: nextPageToken")
      .append(5, "in: query")
      .append(5, "description: >-")
      .append(6, "An opaque, alphanumeric identifier for a specific page of results.")
      .append(6, "When included CiviForm will return a page of results corresponding")
      .append(6, "to the token.")
      .append(5, "required: false")
      .append(5, "schema:")
      .append(6, "type: string")

      .append(3, "responses:")
      .append(4, "''200'':")
      .append(5, "description: ''OK: For valid requests.''")
      .append(5, "headers:")
      .append(6, "x-next:")
      .append(7, "description: >-")
      .append(8, "A link to the next page of responses")
      .append(7, "schema:")
      .append(8, "type: string")
      .append(5, "content:")
      .append(6, "application/json:")
      .append(7, "schema:")
      .append(8, "$ref: ''#/components/schemas/payload''")
      .append(4, "''400'':")
      .append(5, "$ref: ''#/components/responses/BadRequest''")
      .append(4, "''401'':")
      .append(5, "$ref: ''#/components/responses/UnauthorizedError''")

      .append("components:")
      .append(1, "securitySchemes:")
      .append(2, "basicAuth:")
      .append(3, "type: http")
      .append(3, "scheme: basic")
      .append(1, "responses:")
      .append(2, "BadRequest:")
      .append(3, "description: >-")
      .append(4, "Bad Request: Returned if any request parameters fail validation.")
      .append(2, "UnauthorizedError:")
      .append(3, "description: >-")
      .append(4, "Unauthorized: Returned if the API key is invalid or does not have access to the program.")
      .append(1, "schemas:")
      .append(2, "nextPageToken:")
      .append(3, "type: string")
      .append(3, "format: nullable")

      .append(2, "payload:")
      .append(3, "type: array")
      .append(3, "items:")
      .append(4, "type: object")
      .append(4, "properties:")
      .append(5, "applicant_id:")
      .append(6, "type: integer")
      .append(6, "format: int32")
      .append(5, "application:")
      .append(6, "type: object")
      .append(6, "properties:");

    for (QuestionDefinition questionDefinition : getQuestionDefinitionsSortedByNameKey(programDefinition)) {
      yamlFormatter.append(7, "{0}:", questionDefinition.getQuestionNameKey());
      yamlFormatter.append(8, "type: object");
      yamlFormatter.append(8, "properties:");

      for (Scalar scalar : getScalarsSortedByName(questionDefinition)) {
        String fieldName = scalar.name().toLowerCase(Locale.ROOT);
        String swaggerType = getSwaggerType(scalar.toScalarType());
        String swaggerFormat = getSwaggerFormat(scalar.toScalarType());

        if (swaggerType.equals("array")) {
          yamlFormatter
            .append(9, "{0}:", fieldName)
            .append(10, "type: array")
            .append(10, "items:")
            .append(11, "type: string");
        } else {
          yamlFormatter
            .append(9, "{0}:", fieldName)
            .append(10, "type: {0}", swaggerType)
            .appendIf(!swaggerFormat.equals("string"), 10, "format: {0}", swaggerFormat);
        }
      }
    }

    yamlFormatter
      .append(5, "application_id:")
      .append(6, "type: integer")
      .append(6, "format: int32")
      .append(5, "create_time:")
      .append(6, "type: string")
      .append(5, "language:")
      .append(6, "type: string")
      .append(5, "program_name:")
      .append(6, "type: string")
      .append(5, "program_version_id:")
      .append(6, "type: integer")
      .append(6, "format: int32")
      .append(5, "status:")
      .append(6, "type: string")
      .append(6, "format: nullable")
      .append(5, "submit_time:")
      .append(6, "type: string")
      .append(5, "submitter_email:")
      .append(6, "type: string");

    return yamlFormatter.toString();
  }

  private ImmutableList<QuestionDefinition> getQuestionDefinitionsSortedByNameKey(ProgramDefinition programDefinition) {
    var list = new ArrayList<QuestionDefinition>();

    for (BlockDefinition blockDefinition : programDefinition.blockDefinitions()) {
      for (int i = 0; i < blockDefinition.getQuestionCount(); i++) {
        list.add(blockDefinition.getQuestionDefinition(i));
      }
    }

    return ImmutableList.copyOf(
      list.stream()
        .sorted(Comparator.comparing(QuestionDefinition::getQuestionNameKey))
        .collect(Collectors.toList()));
  }

  private ImmutableList<Scalar> getScalarsSortedByName(QuestionDefinition questionDefinition) {
    List<Scalar> scalars = new ArrayList<>();

    try {
      scalars =
        Scalar.getScalars(questionDefinition.getQuestionType()).stream()
          .sorted(Comparator.comparing(Enum::name))
          .collect(Collectors.toList());
    } catch (InvalidQuestionTypeException | UnsupportedQuestionTypeException e) {
      e.printStackTrace();
    }

    return ImmutableList.copyOf(scalars);
  }

  private DefinitionType getDefinitionTypeFromSwaggerType(String swaggerType){
    switch (swaggerType){
      case "string":
        return DefinitionType.STRING;
      case "integer":
        return DefinitionType.INTEGER;
      case "number":
        return DefinitionType.NUMBER;
      case "array":
        return DefinitionType.ARRAY;
      default:
        return DefinitionType.STRING;
    }
  }

  private String getHostName() {
    return baseUrl.replace("https://", "").replace("http://", "");
  }

  private String getSwaggerType(ScalarType scalarType) {
    switch (scalarType) {
      case CURRENCY_CENTS:
      case DATE:
      case STRING:
      case PHONE_NUMBER:
      case SERVICE_AREA:
        return "string";
      case LONG:
        return "integer";
      case DOUBLE:
        return "number";
      case LIST_OF_STRINGS:
        return "array";
      default:
        return "";
    }
  }

  private String getSwaggerFormat(ScalarType scalarType) {
    switch (scalarType) {
      case DATE:
        return "date";
      case STRING:
      case PHONE_NUMBER:
      case SERVICE_AREA:
        return "string";
      case LONG:
        return "int64";
      case CURRENCY_CENTS:
      case DOUBLE:
        return "double";
      case LIST_OF_STRINGS:
        return "array";
      default:
        return "";
    }
  }

  private static class YamlFormatter {
    private final StringBuilder sb = new StringBuilder();

    public YamlFormatter append(int indentation, String messageFormat, Object... args) {
      sb.append("  ".repeat(Math.max(0, indentation)))
        .append(MessageFormat.format(messageFormat, args))
        .append("\n");

      return this;
    }

    public YamlFormatter append(String value, Object... args) {
      return append(0, value, args);
    }

    public YamlFormatter appendIf(
      Boolean shouldAppend, int indentation, String messageFormat, Object... args) {
      if (shouldAppend) {
        return append(indentation, messageFormat, args);
      }

      return this;
    }

    public YamlFormatter appendIf(Boolean shouldAppend, String messageFormat, Object... args) {
      return appendIf(shouldAppend, 0, messageFormat, args);
    }

    @Override
    public String toString() {
      return sb.toString();
    }
>>>>>>> Stashed changes
  }
}

