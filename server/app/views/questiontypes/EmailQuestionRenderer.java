package views.questiontypes;

import static j2html.TagCreator.div;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import j2html.tags.specialized.DivTag;
import org.apache.commons.lang3.StringUtils;
import services.Path;
import services.applicant.ValidationErrorMessage;
import services.applicant.question.ApplicantQuestion;
import services.applicant.question.EmailQuestion;
import views.components.FieldWithLabel;
import views.style.ReferenceClasses;

/** Renders an email question. */
public class EmailQuestionRenderer extends ApplicantSingleQuestionRenderer {

  public EmailQuestionRenderer(ApplicantQuestion question) {
    super(question);
  }

  @Override
  public String getReferenceClass() {
    return ReferenceClasses.EMAIL_QUESTION;
  }

  @Override
  protected DivTag renderInputTag(
      ApplicantQuestionRendererParams params,
      ImmutableMap<Path, ImmutableSet<ValidationErrorMessage>> validationErrors,
      ImmutableList<String> ariaDescribedByIds,
      boolean isOptional) {
    EmailQuestion emailQuestion = applicantQuestion.createEmailQuestion();
    FieldWithLabel emailField =
        FieldWithLabel.email()
            .setScreenReaderText(applicantQuestion.getQuestionTextForScreenReader());

    DivTag thymeleafContent =
        div()
            .attr("hx-swap", "outerHTML")
            .attr(
                "hx-get",
                controllers.applicant.routes.NorthStarQuestionController.emailQuestion(
                    emailField.getId(),
                    emailQuestion.getEmailPath().toString(),
                    StringUtils.join(ariaDescribedByIds, " "),
                    emailQuestion.getEmailValue().orElse(""),
                    !isOptional,
                    params.autofocusSingleField()))
            .attr("hx-trigger", "load");
    return thymeleafContent;

    // FieldWithLabel emailField =
    //     FieldWithLabel.email()
    //         .setFieldErrors(
    //             params.messages(),
    //             validationErrors.getOrDefault(emailQuestion.getEmailPath(), ImmutableSet.of()))

    // if (!validationErrors.isEmpty()) {
    //   emailField.forceAriaInvalid();
    // }

    // return emailField.getEmailTag();
  }
}
