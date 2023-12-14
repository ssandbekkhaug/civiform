package views.applicant;

import static j2html.TagCreator.a;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h3;
import static j2html.TagCreator.h4;
import static j2html.TagCreator.li;
import static j2html.TagCreator.ol;
import static j2html.TagCreator.p;
import static j2html.TagCreator.span;
import static j2html.TagCreator.text;
import static services.applicant.ApplicantPersonalInfo.ApplicantType.GUEST;
import static views.applicant.AuthenticateUpsellCreator.createLoginPromptModal;
import static views.components.Modal.RepeatOpenBehavior.Group.PROGRAMS_INDEX_LOGIN_PROMPT;

import auth.CiviFormProfile;
import auth.ProfileUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import j2html.TagCreator;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import j2html.tags.specialized.ATag;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.LiTag;
import j2html.tags.specialized.PTag;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.inject.Inject;
import play.i18n.Messages;
import play.mvc.Http;
import services.MessageKey;
import services.applicant.ApplicantPersonalInfo;
import services.applicant.ApplicantService;
import services.program.ProgramDefinition;
import services.program.StatusDefinitions;
import services.settings.SettingsManifest;
import views.BaseHtmlView;
import views.HtmlBundle;
import views.TranslationUtils;
import views.components.ButtonStyles;
import views.components.Icons;
import views.components.LinkElement;
import views.components.Modal;
import views.components.TextFormatter;
import views.style.ApplicantStyles;
import views.style.BaseStyles;
import views.style.ReferenceClasses;

final class ApplicantProgramDisplayPartial extends BaseHtmlView {

  private final ProfileUtils profileUtils;
  private final SettingsManifest settingsManifest;
  private final ZoneId zoneId;

  @Inject
  ApplicantProgramDisplayPartial(
      ProfileUtils profileUtils, SettingsManifest settingsManifest, ZoneId zoneId) {
    this.profileUtils = Preconditions.checkNotNull(profileUtils);
    this.settingsManifest = Preconditions.checkNotNull(settingsManifest);
    this.zoneId = Preconditions.checkNotNull(zoneId);
  }

  DivTag programCardsSection(
      Http.Request request,
      Messages messages,
      ApplicantPersonalInfo personalInfo,
      Optional<MessageKey> sectionTitle,
      String cardContainerStyles,
      long applicantId,
      Locale preferredLocale,
      ImmutableList<ApplicantService.ApplicantProgramData> cards,
      MessageKey buttonTitle,
      MessageKey buttonSrText,
      HtmlBundle bundle) {
    String sectionHeaderId = Modal.randomModalId();
    DivTag div = div().withClass(ReferenceClasses.APPLICATION_PROGRAM_SECTION);
    if (sectionTitle.isPresent()) {
      div.with(
          h3().withId(sectionHeaderId)
              .withText(messages.at(sectionTitle.get().getKeyName()))
              .withClasses(ApplicantStyles.PROGRAM_CARDS_SUBTITLE));
    }
    return div.with(
        ol().attr("aria-labelledby", sectionHeaderId)
            .withClasses(cardContainerStyles)
            .with(
                each(
                    cards,
                    (card) ->
                        programCard(
                            request,
                            messages,
                            personalInfo,
                            card,
                            applicantId,
                            preferredLocale,
                            buttonTitle,
                            buttonSrText,
                            sectionTitle.isPresent(),
                            bundle))));
  }

  private LiTag programCard(
      Http.Request request,
      Messages messages,
      ApplicantPersonalInfo personalInfo,
      ApplicantService.ApplicantProgramData cardData,
      Long applicantId,
      Locale preferredLocale,
      MessageKey buttonTitle,
      MessageKey buttonSrText,
      boolean nestedUnderSubheading,
      HtmlBundle bundle) {
    ProgramDefinition program = cardData.program();

    String baseId = ReferenceClasses.APPLICATION_CARD + "-" + program.id();

    ContainerTag title =
        nestedUnderSubheading
            ? h4().withId(baseId + "-title")
                .withClasses(ReferenceClasses.APPLICATION_CARD_TITLE, "text-lg", "font-semibold")
                .withText(program.localizedName().getOrDefault(preferredLocale))
            : h3().withId(baseId + "-title")
                .withClasses(ReferenceClasses.APPLICATION_CARD_TITLE, "text-lg", "font-semibold")
                .withText(program.localizedName().getOrDefault(preferredLocale));
    ImmutableList<DomContent> descriptionContent =
        TextFormatter.formatText(
            program.localizedDescription().getOrDefault(preferredLocale),
            /* preserveEmptyLines= */ false,
            /* addRequiredIndicator= */ false);
    DivTag description =
        div()
            .withId(baseId + "-description")
            .withClasses(
                ReferenceClasses.APPLICATION_CARD_DESCRIPTION, "text-xs", "my-2", "line-clamp-5")
            .with(descriptionContent);

    DivTag programData =
        div().withId(baseId + "-data").withClasses("w-full", "px-4", "overflow-auto");
    if (cardData.latestSubmittedApplicationStatus().isPresent()) {
      programData.with(
          programCardApplicationStatus(
              messages, preferredLocale, cardData.latestSubmittedApplicationStatus().get()));
    }
    if (shouldShowEligibilityTag(cardData)) {
      programData.with(eligibilityTag(request, messages, cardData.isProgramMaybeEligible().get()));
    }
    programData.with(title, description);
    // Use external link if it is present else use the default Program details page
    String programDetailsLink =
        program.externalLink().isEmpty()
            ? controllers.applicant.routes.ApplicantProgramsController.view(
                    applicantId, program.id())
                .url()
            : program.externalLink();
    ATag infoLink =
        new LinkElement()
            .setId(baseId + "-info-link")
            .setStyles("mb-2", "text-sm", "underline")
            .setText(messages.at(MessageKey.LINK_PROGRAM_DETAILS.getKeyName()))
            .setHref(programDetailsLink)
            .opensInNewTab()
            .setIcon(Icons.OPEN_IN_NEW, LinkElement.IconPosition.END)
            .asAnchorText()
            .attr(
                "aria-label",
                messages.at(
                    MessageKey.LINK_PROGRAM_DETAILS_SR.getKeyName(),
                    program.localizedName().getOrDefault(preferredLocale)));
    programData.with(div(infoLink));

    if (cardData.latestSubmittedApplicationTime().isPresent()) {
      programData.with(
          programCardSubmittedDate(messages, cardData.latestSubmittedApplicationTime().get()));
    }

    String actionUrl =
        controllers.applicant.routes.ApplicantProgramReviewController.review(
                applicantId, program.id())
            .url();

    Modal loginPromptModal =
        createLoginPromptModal(
                messages,
                actionUrl,
                messages.at(
                    MessageKey.INITIAL_LOGIN_MODAL_PROMPT.getKeyName(),
                    settingsManifest.getApplicantPortalName(request).get()),
                MessageKey.BUTTON_CONTINUE_TO_APPLICATION)
            .setRepeatOpenBehavior(
                Modal.RepeatOpenBehavior.showOnlyOnce(PROGRAMS_INDEX_LOGIN_PROMPT, actionUrl))
            .build();
    bundle.addModals(loginPromptModal);

    // If the user is a guest, show the login prompt modal, which has a button
    // to continue on to the application. Otherwise, show the button to go to the
    // application directly.
    ContainerTag content =
        personalInfo.getType() == GUEST
            ? TagCreator.button().withId(loginPromptModal.getTriggerButtonId())
            : a().withHref(actionUrl).withId(baseId + "-apply");

    content
        .withText(messages.at(buttonTitle.getKeyName()))
        .attr(
            "aria-label",
            messages.at(
                buttonSrText.getKeyName(), program.localizedName().getOrDefault(preferredLocale)))
        .withClasses(ReferenceClasses.APPLY_BUTTON, ButtonStyles.SOLID_BLUE_TEXT_SM, "mx-auto");

    DivTag actionDiv = div(content).withClasses("w-full", "mb-6", "flex-grow", "flex", "items-end");
    return li().withId(baseId)
        .withClasses(ReferenceClasses.APPLICATION_CARD, ApplicantStyles.PROGRAM_CARD)
        .with(
            // The visual bar at the top of each program card.
            div()
                .withClasses(
                    "block", "shrink-0", BaseStyles.BG_SEATTLE_BLUE, "rounded-t-xl", "h-3"))
        .with(programData)
        .with(actionDiv);
  }

  /**
   * If eligibility is gating, the eligibility tag should always show when present. If eligibility
   * is non-gating, the eligibility tag should only show if the user may be eligible.
   */
  private boolean shouldShowEligibilityTag(ApplicantService.ApplicantProgramData cardData) {
    if (!cardData.isProgramMaybeEligible().isPresent()) {
      return false;
    }

    return cardData.program().eligibilityIsGating() || cardData.isProgramMaybeEligible().get();
  }

  private PTag programCardApplicationStatus(
      Messages messages, Locale preferredLocale, StatusDefinitions.Status status) {
    return p().withClasses(
            "border",
            "rounded-full",
            "px-2",
            "py-1",
            "mb-4",
            "gap-x-2",
            "inline-block",
            "w-auto",
            "bg-blue-100")
        .with(
            Icons.svg(Icons.INFO)
                // 4.5 is 18px as defined in tailwind.config.js
                .withClasses("inline-block", "h-4.5", "w-4.5", BaseStyles.TEXT_SEATTLE_BLUE),
            span(String.format(
                    "%s: %s",
                    messages.at(MessageKey.TITLE_STATUS.getKeyName()),
                    status.localizedStatusText().getOrDefault(preferredLocale)))
                .withClasses("p-2", "text-xs", "font-medium", BaseStyles.TEXT_SEATTLE_BLUE));
  }

  private PTag eligibilityTag(Http.Request request, Messages messages, boolean isEligible) {
    CiviFormProfile submittingProfile = profileUtils.currentUserProfile(request).orElseThrow();
    boolean isTrustedIntermediary = submittingProfile.isTrustedIntermediary();
    MessageKey mayQualifyMessage =
        isTrustedIntermediary ? MessageKey.TAG_MAY_QUALIFY_TI : MessageKey.TAG_MAY_QUALIFY;
    MessageKey mayNotQualifyMessage =
        isTrustedIntermediary ? MessageKey.TAG_MAY_NOT_QUALIFY_TI : MessageKey.TAG_MAY_NOT_QUALIFY;
    Icons icon = isEligible ? Icons.CHECK_CIRCLE : Icons.INFO;
    String color = isEligible ? BaseStyles.BG_CIVIFORM_GREEN_LIGHT : "bg-gray-200";
    String textColor = isEligible ? BaseStyles.TEXT_CIVIFORM_GREEN : "text-black";
    String tagClass =
        isEligible ? ReferenceClasses.ELIGIBLE_TAG : ReferenceClasses.NOT_ELIGIBLE_TAG;
    String tagText =
        isEligible ? mayQualifyMessage.getKeyName() : mayNotQualifyMessage.getKeyName();
    return p().withClasses(
            tagClass,
            "border",
            "rounded-full",
            "px-2",
            "py-1",
            "mb-4",
            "gap-x-2",
            "inline-block",
            "w-auto",
            color)
        .with(
            Icons.svg(icon)
                // 4.5 is 18px as defined in tailwind.config.js
                .withClasses("inline-block", "h-4.5", "w-4.5", textColor),
            span(messages.at(tagText)).withClasses("p-2", "text-xs", "font-medium", textColor));
  }

  private DivTag programCardSubmittedDate(Messages messages, Instant submittedDate) {
    TranslationUtils.TranslatedStringSplitResult translateResult =
        TranslationUtils.splitTranslatedSingleArgString(messages, MessageKey.SUBMITTED_DATE);
    String beforeContent = translateResult.beforeInterpretedContent();
    String afterContent = translateResult.afterInterpretedContent();

    List<DomContent> submittedComponents = Lists.newArrayList();
    if (!beforeContent.isEmpty()) {
      submittedComponents.add(text(beforeContent));
    }

    ZonedDateTime dateTime = submittedDate.atZone(zoneId);
    String formattedSubmitTime =
        DateTimeFormatter.ofLocalizedDate(
                // SHORT will print dates as 1/2/2022.
                FormatStyle.SHORT)
            .format(dateTime);
    submittedComponents.add(
        span(formattedSubmitTime).withClasses(ReferenceClasses.BT_DATE, "font-semibold"));

    if (!afterContent.isEmpty()) {
      submittedComponents.add(text(afterContent));
    }

    return div().withClasses("text-xs", "text-gray-700").with(submittedComponents);
  }
}
