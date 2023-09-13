package services.openApi.v3;

import auth.ProgramAcls;
import com.google.common.collect.ImmutableList;
import controllers.dev.seeding.SampleQuestionDefinitions;
import models.DisplayMode;
import org.junit.Test;
import services.LocalizedStrings;
import services.applicant.question.Scalar;
import services.openApi.OpenApiSchemaSettings;
import services.openApi.v3.OpenApi3SchemaGenerator;
import services.openApi.v3.serializers.OpenApiSerializationAsserter;
import services.program.BlockDefinition;
import services.program.ProgramDefinition;
import services.program.ProgramQuestionDefinition;
import services.program.ProgramType;
import services.program.StatusDefinitions;
import services.question.types.QuestionDefinition;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static org.assertj.core.api.Assertions.assertThat;

public class OpenApi3SchemaGeneratorTest extends OpenApiSerializationAsserter {
  /** All members of this class that are of type {@link QuestionDefinition}. */
  public static final ImmutableList<QuestionDefinition> ALL_SAMPLE_QUESTION_DEFINITIONS =
    ImmutableList.of(
      SampleQuestionDefinitions.ADDRESS_QUESTION_DEFINITION,
      SampleQuestionDefinitions.CHECKBOX_QUESTION_DEFINITION,
      SampleQuestionDefinitions.CURRENCY_QUESTION_DEFINITION,
      SampleQuestionDefinitions.DATE_PREDICATE_QUESTION_DEFINITION,
      SampleQuestionDefinitions.DATE_QUESTION_DEFINITION,
      SampleQuestionDefinitions.DROPDOWN_QUESTION_DEFINITION,
      SampleQuestionDefinitions.EMAIL_QUESTION_DEFINITION,
      SampleQuestionDefinitions.ENUMERATOR_QUESTION_DEFINITION,
      SampleQuestionDefinitions.FILE_UPLOAD_QUESTION_DEFINITION,
      SampleQuestionDefinitions.ID_QUESTION_DEFINITION,
      SampleQuestionDefinitions.NAME_QUESTION_DEFINITION,
      SampleQuestionDefinitions.NUMBER_QUESTION_DEFINITION,
      SampleQuestionDefinitions.PHONE_QUESTION_DEFINITION,
      SampleQuestionDefinitions.RADIO_BUTTON_QUESTION_DEFINITION,
      SampleQuestionDefinitions.STATIC_CONTENT_QUESTION_DEFINITION,
      SampleQuestionDefinitions.TEXT_QUESTION_DEFINITION);

  private static final Stream<QuestionDefinition> ALL_SAMPLE_QUESTION_DEFINITIONS_WITH_IDS_STREAM =
    ALL_SAMPLE_QUESTION_DEFINITIONS.stream().map(QuestionDefinition::withPopulatedTestId);



  @Test
  public void canSerialize() {

    StatusDefinitions possibleProgramStatuses =
      new StatusDefinitions(
        ImmutableList.of(
          StatusDefinitions.Status.builder()
            .setStatusText("Pending Review")
            .setDefaultStatus(Optional.of(true))
            .setLocalizedStatusText(LocalizedStrings.empty())
            .setLocalizedEmailBodyText(Optional.empty())
            .build()));

    ImmutableList<BlockDefinition> blockDefinitions =
      ImmutableList.of(
        BlockDefinition.builder()
          .setId(135L)
          .setName("Test Block Definition")
          .setDescription("Test Block Description")
          .setProgramQuestionDefinitions(
            ALL_SAMPLE_QUESTION_DEFINITIONS_WITH_IDS_STREAM
              .map(
                questionDefinition ->
                  ProgramQuestionDefinition.create(
                    questionDefinition, Optional.empty()))
              .collect(toImmutableList()))
          .build());

    ProgramDefinition programDefinition =
      ProgramDefinition.builder()
        .setId(789L)
        .setAdminName("test-program-admin-name")
        .setAdminDescription("Test Admin Description")
        .setExternalLink("https://mytestlink.gov")
        .setDisplayMode(DisplayMode.PUBLIC)
        .setProgramType(ProgramType.DEFAULT)
        .setEligibilityIsGating(false)
        .setAcls(new ProgramAcls())
        .setBlockDefinitions(blockDefinitions)
        .setStatusDefinitions(possibleProgramStatuses)
        .build();

    OpenApiSchemaSettings settings =
      OpenApiSchemaSettings.builder()
        .setBaseUrl("baseUrl")
        .setItEmailAddress("email123@example.com")
        .setAllowHttpScheme(true)
        .build();

    OpenApi3SchemaGenerator generator = new OpenApi3SchemaGenerator(settings);
    String actual = generator.createSchema(programDefinition);
    String expected =
      new YamlFormatter()
      .appendLine("openapi: 3.1.0")
      .appendLine("info:")
      .appendLine("  title: test-program-admin-name")
      .appendLine("  version: \"789\"")
      .appendLine("  description: Test Admin Description")
      .appendLine("  contact:")
      .appendLine("    name: CiviForm Technical Support")
      .appendLine("    email: email123@example.com")
      .appendLine("servers:")
      .appendLine("  - url: http://localhost:8000/api/v1/programs/program-slug")
      .appendLine("security:")
      .appendLine("  - basicAuth: []")
      .appendLine("tags:")
      .appendLine("  - name: programs")
      .appendLine("paths:")
      .appendLine("  /applications:")
      .appendLine("    get:")
      .appendLine("      summary: Export applications")
      .appendLine("      parameters:")
      .appendLine("        - name: fromDate")
      .appendLine("          in: query")
      .appendLine("          required: false")
      .appendLine("          schema:")
      .appendLine("            type: string")
      .appendLine("            format: date")
      .appendLine("          description: An ISO-8601 formatted date (i.e. YYYY-MM-DD). Limits results to applications submitted on or after the provided date.")
      .appendLine("        - name: toDate")
      .appendLine("          in: query")
      .appendLine("          required: false")
      .appendLine("          schema:")
      .appendLine("            type: string")
      .appendLine("            format: date")
      .appendLine("          description: An ISO-8601 formatted date (i.e. YYYY-MM-DD). Limits results to applications submitted before the provided date.")
      .appendLine("        - name: pageSize")
      .appendLine("          in: query")
      .appendLine("          required: false")
      .appendLine("          schema:")
      .appendLine("            type: integer")
      .appendLine("          description: A positive integer. Limits the number of results per page. If pageSize is larger than CiviForm's maximum page size then the maximum will be used. The default maximum is 1,000 and is configurable.")
      .appendLine("        - name: nextPageToken")
      .appendLine("          in: query")
      .appendLine("          required: false")
      .appendLine("          schema:")
      .appendLine("            type: string")
      .appendLine("          description: An opaque, alphanumeric identifier for a specific page of results. When included CiviForm will return a page of results corresponding to the token.")
      .appendLine("      responses:")
      .appendLine("        \"200\":")
      .appendLine("          description: For valid requests.")
      .appendLine("          headers:")
      .appendLine("            x-next:")
      .appendLine("              description: A link to the next page of responses")
      .appendLine("              schema:")
      .appendLine("                type: string")
      .appendLine("          content:")
      .appendLine("            application/json:")
      .appendLine("              schema:")
      .appendLine("                $ref: '#/components/schemas/payload'")
      .appendLine("        \"400\":")
      .appendLine("          $ref: '#/components/responses/BadRequest'")
      .appendLine("        \"401\":")
      .appendLine("          $ref: '#/components/responses/UnauthorizedError'")
      .appendLine("      tags:")
      .appendLine("        - programs")
//      .appendLine("components:")
//      .appendLine("  securitySchemes:")
//      .appendLine("    basicAuth:")
//      .appendLine("      type: http")
//      .appendLine("      scheme: basic")
//      .appendLine("  responses:")
//      .appendLine("    BadRequest:")
//      .appendLine("      description: >-")
//      .appendLine("        Bad Request: Returned if any request parameters fail validation.")
//      .appendLine("    UnauthorizedError:")
//      .appendLine("      description: >-")
//      .appendLine("        Unauthorized: Returned if the API key is invalid or does not have access to the program.")
//      .appendLine("  schemas:")
//      .appendLine("    nextPageToken:")
//      .appendLine("      type: string")
//      .appendLine("      format: nullable")
//      .appendLine("    payload:")
//      .appendLine("      type: array")
//      .appendLine("      items:")
//      .appendLine("        type: object")
//      .appendLine("        properties:")
//      .appendLine("          applicant_id:")
//      .appendLine("            type: integer")
//      .appendLine("            format: int32")
//      .appendLine("          application:")
//      .appendLine("            type: object")
//      .appendLine("            properties:")
//      .appendLine("          application_id:")
//      .appendLine("            type: integer")
//      .appendLine("            format: int32")
//      .appendLine("          create_time:")
//      .appendLine("            type: string")
//      .appendLine("          language:")
//      .appendLine("            type: string")
//      .appendLine("          program_name:")
//      .appendLine("            type: string")
//      .appendLine("          program_version_id:")
//      .appendLine("            type: integer")
//      .appendLine("            format: int32")
//      .appendLine("          status:")
//      .appendLine("            type: string")
//      .appendLine("            format: nullable")
//      .appendLine("          submit_time:")
//      .appendLine("            type: string")
//      .appendLine("          submitter_email:")
//      .appendLine("            type: string")
      .toString();

    assertThat(actual).isEqualTo(expected);
  }
}
