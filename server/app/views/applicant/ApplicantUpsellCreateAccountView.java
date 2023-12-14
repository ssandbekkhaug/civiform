package views.applicant;

import static com.google.common.base.Preconditions.checkNotNull;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.section;
import static services.MessageKey.CONTENT_OTHER_PROGRAMS_TO_APPLY_FOR;
import static views.applicant.AuthenticateUpsellCreator.createLoginButton;
import static views.applicant.AuthenticateUpsellCreator.createLoginPromptModal;
import static views.applicant.AuthenticateUpsellCreator.createNewAccountButton;

import annotations.BindingAnnotations;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import controllers.applicant.routes;
import j2html.tags.DomContent;
import j2html.tags.specialized.ATag;
import java.util.Locale;
import java.util.Optional;
import models.AccountModel;
import play.i18n.Messages;
import play.mvc.Http;
import play.twirl.api.Content;
import services.LocalizedStrings;
import services.MessageKey;
import services.applicant.ApplicantPersonalInfo;
import services.applicant.ApplicantService;
import services.settings.SettingsManifest;
import views.components.ButtonStyles;
import views.components.Modal;
import views.components.TextFormatter;
import views.components.ToastMessage;
import views.style.ReferenceClasses;

/** Renders a confirmation page after application submission. */
public final class ApplicantUpsellCreateAccountView extends ApplicantUpsellView {

  private final ApplicantLayout layout;
  private final ApplicantProgramDisplayPartial applicantProgramDisplayPartial;
  private final String authProviderName;
  private final SettingsManifest settingsManifest;

  @Inject
  public ApplicantUpsellCreateAccountView(
      ApplicantLayout layout,
      ApplicantProgramDisplayPartial applicantProgramDisplayPartial,
      SettingsManifest settingsManifest,
      @BindingAnnotations.ApplicantAuthProviderName String authProviderName) {
    this.applicantProgramDisplayPartial = checkNotNull(applicantProgramDisplayPartial);
    this.layout = checkNotNull(layout);
    this.settingsManifest = settingsManifest;
    this.authProviderName = checkNotNull(authProviderName);
  }

  /** Renders a sign-up page with a baked-in redirect. */
  public Content render(
      Http.Request request,
      ApplicantService.ApplicationPrograms applicantPrograms,
      String redirectTo,
      AccountModel account,
      Locale locale,
      String programTitle,
      LocalizedStrings customConfirmationMessage,
      ApplicantPersonalInfo personalInfo,
      Long applicantId,
      Long applicationId,
      Messages messages,
      Optional<ToastMessage> bannerMessage) {
    boolean shouldUpsell = shouldUpsell(account);
    String redirectUrl = routes.UpsellController.download(applicationId, applicantId).url();
    Modal loginPromptModal =
        createLoginPromptModal(
                messages,
                redirectTo,
                /* description= */ messages.at(MessageKey.GENERAL_LOGIN_MODAL_PROMPT.getKeyName()),
                /* bypassMessage= */ MessageKey.BUTTON_CONTINUE_WITHOUT_AN_ACCOUNT)
            .build();
    ATag downloadButton = new ATag();
    if (settingsManifest.getApplicationExportable(request)) {
      downloadButton =
          new ATag()
              .withHref(redirectUrl)
              .with(
                  button(messages.at(MessageKey.BUTTON_DOWNLOAD_PDF.getKeyName()))
                      .withClasses(ButtonStyles.OUTLINED_TRANSPARENT, "flex-grow"))
              .withClass("flex");
    }
    ImmutableList<DomContent> actionButtons =
        shouldUpsell
            ? ImmutableList.of(
                downloadButton,
                button(messages.at(MessageKey.LINK_APPLY_TO_ANOTHER_PROGRAM.getKeyName()))
                    .withId(loginPromptModal.getTriggerButtonId())
                    .withClasses(ButtonStyles.OUTLINED_TRANSPARENT),
                createLoginButton("sign-in", messages, redirectTo),
                createNewAccountButton("sign-up", messages))
            : ImmutableList.of(
                downloadButton,
                createApplyToProgramsButton(
                    "another-program",
                    messages.at(MessageKey.LINK_APPLY_TO_ANOTHER_PROGRAM.getKeyName()),
                    applicantId));

    String title = messages.at(MessageKey.TITLE_APPLICATION_CONFIRMATION.getKeyName());

    var content =
        createMainContent(
            title,
            section(
                div(messages.at(
                        MessageKey.CONTENT_CONFIRMED.getKeyName(), programTitle, applicationId))
                    .withClasses(ReferenceClasses.BT_APPLICATION_ID, "mb-4"),
                div()
                    .with(
                        TextFormatter.formatText(
                            customConfirmationMessage.getOrDefault(locale),
                            /* preserveEmptyLines= */ true,
                            /* addRequiredIndicator= */ false))
                    .withClasses("mb-4")),
            shouldUpsell,
            messages,
            authProviderName,
            actionButtons);

    var htmlBundle =
        createHtmlBundle(request, layout, title, bannerMessage, loginPromptModal, content);
    var relevantPrograms = applicantPrograms.unappliedAndPotentiallyEligible();

    var otherProgramsContent =
        div()
            .withClasses("p-4 sm:p-6 my-6 bg-blue-100")
            .with(
                h2(messages.at(CONTENT_OTHER_PROGRAMS_TO_APPLY_FOR.getKeyName()))
                    .withClasses("mb-4 font-bold"),
                applicantProgramDisplayPartial.programCardsSection(
                    request,
                    messages,
                    personalInfo,
                    /* sectionTitle= */ Optional.empty(),
                    applicantProgramDisplayPartial.programCardsContainerStyles(
                        ApplicantProgramDisplayPartial.ContainerWidth.MEDIUM,
                        relevantPrograms.size()),
                    applicantId,
                    locale,
                    relevantPrograms,
                    MessageKey.BUTTON_APPLY,
                    MessageKey.BUTTON_APPLY_SR,
                    htmlBundle));

    htmlBundle.addMainContent(otherProgramsContent);

    return layout.renderWithNav(request, personalInfo, messages, htmlBundle, applicantId);
  }
}
