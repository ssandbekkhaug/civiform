package services;

/** Contains keys into the {@code messages} files used for translation. */
public enum MessageKey {
  ACCT_STATUS_LABEL("label.acctStatus"),
  ADDRESS_CORRECTION_AS_ENTERED_HEADING("content.addressEntered"),
  ADDRESS_CORRECTION_SUGGESTED_ADDRESS_HEADING("content.suggestedAddress"),
  ADDRESS_CORRECTION_SUGGESTED_ADDRESSES_HEADING("content.suggestedAddresses"),
  ADDRESS_CORRECTION_TITLE("title.confirmAddress"),
  ADDRESS_CORRECTION_LINE_1("content.confirmAddressLine1"),
  ADDRESS_CORRECTION_FOUND_SIMILAR_LINE_2("content.foundSimilarAddressLine2"),
  ADDRESS_CORRECTION_NO_VALID_LINE_2("content.noValidAddressLine2"),
  ADDRESS_CORRECTION_CONFIRM_BUTTON("button.confirmAddress"),
  ADDRESS_LABEL_CITY("label.city"),
  ADDRESS_LABEL_LINE_2("label.addressLine2"),
  ADDRESS_LABEL_STATE("label.state"),
  ADDRESS_LABEL_STATE_SELECT("label.selectState"),
  ADDRESS_LABEL_STREET("label.street"),
  ADDRESS_LABEL_ZIPCODE("label.zipcode"),
  ADDRESS_VALIDATION_CITY_REQUIRED("validation.cityRequired"),
  ADDRESS_VALIDATION_INVALID_ZIPCODE("validation.invalidZipcode"),
  ADDRESS_VALIDATION_NO_PO_BOX("validation.noPoBox"),
  ADDRESS_VALIDATION_STATE_REQUIRED("validation.stateRequired"),
  ADDRESS_VALIDATION_STREET_REQUIRED("validation.streetRequired"),
  ARIA_LABEL_EDIT("ariaLabel.edit"),
  ARIA_LABEL_ANSWER("ariaLabel.answer"),
  BANNER_ERROR_SAVING_APPLICATION("banner.errorSavingApplication"),
  BANNER_CLIENT_INFO_UPDATED("banner.clientInfoUpdated"),
  BANNER_CLIENT_ACCT_DELETED("banner.clientAcctDeleted"),
  BANNER_ACCT_DELETE_ERROR("banner.acctDeleteError"),
  BANNER_ACCT_DELETE_ERROR_REASON("banner.acctDeleteErrorReason"),
  BANNER_NEW_CLIENT_CREATED("banner.newClientCreated"),
  BANNER_GOV_WEBSITE_SECTION_HEADER("banner.govWebsiteSectionHeader"),
  BANNER_GOV_WEBSITE_SECTION_CONTENT("banner.govWebsiteSectionContent"),
  BANNER_HTTPS_SECTION_HEADER("banner.httpsSectionHeader"),
  BANNER_HTTPS_SECTION_CONTENT("banner.httpsSectionContent"),
  BANNER_LINK("banner.link"),
  BANNER_TITLE("banner.title"),
  BANNER_VIEW_APPLICATION("banner.viewApplication"),
  BLOCK_INDEX_LABEL("label.blockIndexLabel"),
  BUTTON_ADD_NEW_CLIENT("button.addNewClient"),
  BUTTON_APPLY("button.apply"),
  BUTTON_APPLY_TO_PROGRAMS("button.applyToPrograms"),
  BUTTON_APPLY_SR("button.applySr"),
  BUTTON_BACK_TO_CLIENT_LIST("button.backToClientList"),
  BUTTON_BACK_TO_EDITING("button.backToEditing"),
  BUTTON_CANCEL("button.cancel"),
  BUTTON_CHOOSE_FILE("button.chooseFile"),
  BUTTON_CLOSE("button.close"),
  BUTTON_CONTINUE("button.continue"),
  BUTTON_CONTINUE_SR("button.continueSr"),
  BUTTON_CONTINUE_COMMON_INTAKE_SR("button.continueCommonIntakeSr"),
  BUTTON_CONTINUE_EDITING("button.continueEditing"),
  BUTTON_CONTINUE_WITHOUT_AN_ACCOUNT("button.continueWithoutAnAccount"),
  BUTTON_CREATE_ACCOUNT("button.createAccount"),
  BUTTON_DELETE_ACCT("button.deleteAcct"),
  BUTTON_DOWNLOAD_PDF("button.downloadPdf"),
  BUTTON_EDIT("button.edit"),
  BUTTON_EDIT_SR("button.editSr"),
  BUTTON_EDIT_COMMON_INTAKE_SR("button.editCommonIntakeSr"),
  BUTTON_EXIT_APPLICATION("button.exitApplication"),
  BUTTON_GO_BACK_AND_EDIT("button.goBackAndEdit"),
  BUTTON_LOGIN("button.login"),
  BUTTON_CREATE_AN_ACCOUNT("button.createAnAccount"),
  BUTTON_CLEAR_SEARCH("button.clearSearch"),
  BUTTON_LOGIN_GUEST("button.guestLogin"),
  BUTTON_LOGOUT("button.logout"),
  BUTTON_NEXT("button.nextPage"),
  BUTTON_NEXT_SCREEN("button.nextScreen"),
  BUTTON_PREVIOUS_SCREEN("button.previousScreen"),
  BUTTON_REVIEW("button.review"),
  BUTTON_DELETE_FILE("button.deleteFile"),
  BUTTON_KEEP_ACCT("button.keepAcct"),
  BUTTON_KEEP_FILE("button.keepFile"),
  BUTTON_SAVE("button.save"),
  BUTTON_SEARCH("button.search"),
  BUTTON_SELECT("button.select"),
  BUTTON_SKIP_FILEUPLOAD("button.skipFileUpload"),
  BUTTON_START("button.start"),
  BUTTON_START_HERE("button.startHere"),
  BUTTON_START_APP("button.startApp"),
  BUTTON_CONTINUE_TO_APPLICATION("button.continueToApplication"),
  BUTTON_START_HERE_COMMON_INTAKE_SR("button.startHereCommonIntakeSr"),
  BUTTON_SUBMIT("button.submit"),
  BUTTON_UNTRANSLATED_SUBMIT("button.untranslatedSubmit"),
  BUTTON_VIEW_APPLICATIONS("button.viewApplications"),
  BUTTON_VIEW_AND_ADD_CLIENTS("button.viewAndAddClients"),
  CURRENCY_VALIDATION_MISFORMATTED("validation.currencyMisformatted"),
  CONTACT_INFO_LABEL("label.contactInfo"),
  CONTENT_ADMIN_LOGIN_PROMPT("content.adminLoginPrompt"),
  CONTENT_ADMIN_FOOTER_PROMPT("content.adminFooterPrompt"),
  CONTENT_BLOCK_PROGRESS("content.blockProgress"),
  CONTENT_BLOCK_PROGRESS_FULL("content.blockProgressFull"),
  CONTENT_FILE_UPLOAD_BLOCK_PROGRESS_FULL("content.fileUploadBlockProgressFull"),
  CONTENT_SAVE_TIME("content.saveTimeServices"),
  CONTENT_CHANGE_ELIGIBILITY_ANSWERS("content.changeAnswersForEligibility"),
  CONTENT_CIVIFORM_DESCRIPTION("content.findProgramsDescription"),
  CONTENT_CLIENT_CREATED("content.clientCreated"),
  CONTENT_CONFIRMED("content.confirmed"),
  CONTENT_DOES_NOT_QUALIFY("content.doesNotQualify"),
  CONTENT_COMMON_INTAKE_CONFIRMATION("content.commonIntakeConfirmation"),
  CONTENT_COMMON_INTAKE_CONFIRMATION_TI("content.commonIntakeConfirmationTi"),
  CONTENT_COMMON_INTAKE_NO_MATCHING_PROGRAMS("content.commonIntakeNoMatchingPrograms"),
  CONTENT_COMMON_INTAKE_NO_MATCHING_PROGRAMS_TI("content.commonIntakeNoMatchingProgramsTi"),
  CONTENT_COMMON_INTAKE_NO_MATCHING_PROGRAMS_NEXT_STEP(
      "content.commonIntakeNoMatchingProgramsNextStep"),
  CONTENT_OTHER_PROGRAMS_TO_APPLY_FOR("content.otherProgramsToApplyFor"),
  CONTENT_ELIGIBILITY_CRITERIA("content.eligibilityCriteria"),
  CONTENT_EMAIL_TOOLTIP("content.emailTooltip"),
  CONTENT_FIND_PROGRAMS("content.findPrograms"),
  CONTENT_GUEST_DESCRIPTION("content.guestDescription"),
  CONTENT_LAST_APPLICATION_DATE("content.lastApplicationDate"),
  CONTENT_LOGIN_PROMPT("content.loginPrompt"),
  CONTENT_LOGIN_DISABLED_PROMPT("content.loginDisabledPrompt"),
  CONTENT_LOGIN_PROMPT_ALTERNATIVE("content.alternativeLoginPrompt"),
  CONTENT_NO_CHANGES("content.noChanges"),
  CONTENT_NO_EMAIL_ADDRESS("content.noEmailAddress"),
  CONTENT_NO_APPLICATIONS("content.noApplications"),
  CONTENT_NOT_LOGGED_IN("content.notLoggedIn"),
  CONTENT_NUMBER_OF_APP_SUBMITTED("content.numberOfAppSubmitted"),
  CONTENT_ONE_APP_SUBMITTED("content.oneAppSubmitted"),
  CONTENT_OPTIONAL("content.optional"),
  CONTENT_OR("content.or"),
  CONTENT_WARNING("content.warning"),
  CONTENT_PLEASE_CREATE_ACCOUNT("content.pleaseCreateAccount"),
  CONTENT_PREVIOUSLY_ANSWERED_ON("content.previouslyAnsweredOn"),
  CONTENT_SELECT_LANGUAGE("label.selectLanguage"),
  ERROR_ANNOUNCEMENT_SR("validation.errorAnnouncementSr"),
  ERROR_FIRST_NAME("label.errorFirstName"),
  ERROR_LAST_NAME("label.errorLastName"),
  ERROR_INTERNAL_SERVER_TITLE("error.internalServerTitle"),
  ERROR_INTERNAL_SERVER_DESCRIPTION("error.internalServerDescription"),
  ERROR_NOT_FOUND_TITLE("error.notFoundTitle"),
  ERROR_NOT_FOUND_DESCRIPTION("error.notFoundDescription"),
  ERROR_NOT_FOUND_DESCRIPTION_LINK("error.notFoundDescriptionLink"),
  DATE_VALIDATION_INVALID_DATE_FORMAT("validation.invalidDateFormat"),
  DATE_VALIDATION_DOB_NOT_IN_PAST("validation.dobNotInPast"),
  DATE_VALIDATION_IMPOSSIBLE_DOB("validation.impossibleDob"),
  DAY_LABEL("label.day"),
  DIALOG_DELETE_CONFIRMATION("dialog.deleteConfirmation"),
  DOB_EXAMPLE("label.dobExample"),
  DOB_ERROR_LABEL("label.errorDOB"),
  DOB_LABEL("label.dob"),
  DROPDOWN_PLACEHOLDER("placeholder.noDropdownSelection"),
  END_SESSION("header.endSession"),
  EMAIL_APPLICATION_RECEIVED_BODY("email.applicationReceivedBody"),
  EMAIL_APPLICATION_RECEIVED_SUBJECT("email.applicationReceivedSubject"),
  EMAIL_APPLICATION_UPDATE_SUBJECT("email.applicationUpdateSubject"),
  EMAIL_LOGIN_TO_CIVIFORM("email.loginToCiviform"),
  EMAIL_TI_APPLICATION_SUBMITTED_BODY("email.tiApplicationSubmittedBody"),
  EMAIL_TI_APPLICATION_SUBMITTED_SUBJECT("email.tiApplicationSubmittedSubject"),
  EMAIL_TI_APPLICATION_UPDATE_SUBJECT("email.tiApplicationUpdateSubject"),
  EMAIL_TI_APPLICATION_UPDATE_BODY("email.tiApplicationUpdateBody"),
  EMAIL_TI_MANAGE_YOUR_CLIENTS("email.tiManageYourClients"),
  EMAIL_LABEL("label.email"),
  ENUMERATOR_BUTTON_ADD_ENTITY("button.addEntity"),
  ENUMERATOR_BUTTON_REMOVE_ENTITY("button.removeEntity"),
  ENUMERATOR_DIALOG_CONFIRM_DELETE("dialog.confirmDelete"),
  ENUMERATOR_DIALOG_CONFIRM_DELETE_ALL_BUTTONS_SAVE("dialog.confirmDeleteAllButtonsSave"),
  ENUMERATOR_PLACEHOLDER_ENTITY_NAME("placeholder.entityName"),
  ENUMERATOR_VALIDATION_DUPLICATE_ENTITY_NAME("validation.duplicateEntityName"),
  ENUMERATOR_VALIDATION_ENTITY_REQUIRED("validation.entityNameRequired"),
  ENUMERATOR_VALIDATION_TOO_MANY_ENTITIES("validation.tooManyEntities"),
  ERROR_INCOMPLETE_DATE("error.incompleteDate"),
  FILEUPLOAD_VALIDATION_FILE_REQUIRED("validation.fileRequired"),
  FILEUPLOAD_VALIDATION_FILE_TOO_LARGE("validation.fileTooLarge"),
  FOOTER_SUPPORT_LINK_DESCRIPTION("footer.supportLinkDescription"),
  GENERAL_LOGIN_MODAL_PROMPT("content.generalLoginModalPrompt"),
  GUEST("guest"),
  GUEST_INDICATOR("header.guestIndicator"),
  HEADER_ACCT_SETTING("header.acctSettings"),
  HEADER_CLIENT_LIST("header.clientList"),
  HEADER_SEARCH("header.search"),
  ID_VALIDATION_NUMBER_REQUIRED("validation.numberRequired"),
  ID_VALIDATION_TOO_LONG("validation.idTooLong"),
  ID_VALIDATION_TOO_SHORT("validation.idTooShort"),
  INITIAL_LOGIN_MODAL_PROMPT("content.initialLoginModalPrompt"),
  INPUT_FILE_ALREADY_UPLOADED("input.fileAlreadyUploaded"),
  INVALID_INPUT("validation.invalidInput"),
  LANGUAGE_LABEL_SR("label.languageSr"),
  LINK_ADMIN_LOGIN("link.adminLogin"),
  LINK_ALL_DONE("link.allDone"),
  LINK_APPLY_TO_ANOTHER_PROGRAM("link.applyToAnotherProgram"),
  LINK_CREATE_ACCOUNT_OR_SIGN_IN("link.createAccountOrSignIn"),
  LINK_EDIT("link.edit"),
  LINK_ANSWER("link.answer"),
  LINK_OPENS_NEW_TAB_SR("link.opensNewTabSr"),
  LINK_PROGRAM_DETAILS("link.programDetails"),
  LINK_PROGRAM_DETAILS_SR("link.programDetailsSr"),
  LINK_SELECT_NEW_CLIENT("link.selectNewClient"),
  MEMORABLE_DATE_PLACEHOLDER("placeholder.memorableDate"),
  MOBILE_FILE_UPLOAD_HELP("content.mobileFileUploadHelp"),
  MODAL_ERROR_SAVING_STAY_AND_FIX_BUTTON("modal.errorSaving.stayAndFixButton"),
  MODAL_ERROR_SAVING_PREVIOUS_CONTENT("modal.errorSaving.previous.content"),
  MODAL_ERROR_SAVING_PREVIOUS_NO_SAVE_BUTTON("modal.errorSaving.previous.noSaveButton"),
  MODAL_ERROR_SAVING_PREVIOUS_TITLE("modal.errorSaving.previous.title"),
  MODAL_ERROR_SAVING_REVIEW_CONTENT("modal.errorSaving.review.content"),
  MODAL_ERROR_SAVING_REVIEW_NO_SAVE_BUTTON("modal.errorSaving.review.noSaveButton"),
  MODAL_ERROR_SAVING_REVIEW_TITLE("modal.errorSaving.review.title"),
  MULTI_OPTION_VALIDATION("adminValidation.multiOptionEmpty"),
  MULTI_OPTION_ADMIN_VALIDATION("adminValidation.multiOptionAdminError"),
  MULTI_SELECT_VALIDATION_TOO_FEW("validation.tooFewSelections"),
  MULTI_SELECT_VALIDATION_TOO_MANY("validation.tooManySelections"),
  NAME_EXAMPLE("label.nameExample"),
  NAME_LABEL_FIRST("label.firstName"),
  NAME_LABEL_LAST("label.lastName"),
  NAME_LABEL_MIDDLE("label.middleName"),
  NAME_PLACEHOLDER_FIRST("placeholder.firstName"),
  NAME_PLACEHOLDER_LAST("placeholder.lastName"),
  NAME_PLACEHOLDER_MIDDLE("placeholder.middleName"),
  NAME_VALIDATION_FIRST_REQUIRED("validation.firstNameRequired"),
  NAME_VALIDATION_LAST_REQUIRED("validation.lastNameRequired"),
  OPTION_MEMORABLE_DATE_JANUARY("option.memorableDate.January"),
  OPTION_MEMORABLE_DATE_FEBRUARY("option.memorableDate.February"),
  OPTION_MEMORABLE_DATE_MARCH("option.memorableDate.March"),
  OPTION_MEMORABLE_DATE_APRIL("option.memorableDate.April"),
  OPTION_MEMORABLE_DATE_MAY("option.memorableDate.May"),
  OPTION_MEMORABLE_DATE_JUNE("option.memorableDate.June"),
  OPTION_MEMORABLE_DATE_JULY("option.memorableDate.July"),
  OPTION_MEMORABLE_DATE_AUGUST("option.memorableDate.August"),
  OPTION_MEMORABLE_DATE_SEPTEMBER("option.memorableDate.September"),
  OPTION_MEMORABLE_DATE_OCTOBER("option.memorableDate.October"),
  OPTION_MEMORABLE_DATE_NOVEMBER("option.memorableDate.November"),
  OPTION_MEMORABLE_DATE_DECEMBER("option.memorableDate.December"),
  PHONE_NUMBER_LABEL("label.phoneNum"),
  PHONE_VALIDATION_NUMBER_REQUIRED("validation.phoneNumberRequired"),
  PHONE_VALIDATION_COUNTRY_CODE_REQUIRED("validation.phoneCountryCodeRequired"),
  PHONE_VALIDATION_NON_NUMBER_VALUE("validation.phoneNumberMustContainNumbersOnly"),
  PHONE_VALIDATION_INVALID_PHONE_NUMBER("validation.invalidPhoneNumberProvided"),
  PHONE_VALIDATION_NUMBER_NOT_IN_COUNTRY("validation.phoneMustBeLocalToCountry"),
  PHONE_LABEL_COUNTRY_CODE("label.countryCode"),
  PHONE_LABEL_PHONE_NUMBER("label.phoneNumber"),
  SEARCH_BY_DOB("label.searchByDob"),
  SEARCH_BY_NAME("label.searchByName"),
  MONTH_LABEL("label.month"),
  NAME_LABEL("label.name"),
  NOTES_LABEL("label.notes"),
  NUMBER_VALIDATION_TOO_BIG("validation.numberTooBig"),
  NUMBER_VALIDATION_TOO_SMALL("validation.numberTooSmall"),
  NUMBER_VALIDATION_NON_INTEGER("validation.numberNonInteger"),
  REQUIRED_FIELDS_ANNOTATION("content.requiredFieldsAnnotation"),
  REQUIRED_FIELDS_NOTE("content.requiredFieldsNote"),
  REVIEW_PAGE_INTRO("content.reviewPageIntro"),
  SUBMITTED_DATE("content.submittedDate"),
  TAG_MAY_NOT_QUALIFY("tag.mayNotQualify"),
  TAG_MAY_NOT_QUALIFY_TI("tag.mayNotQualifyTi"),
  TAG_MAY_QUALIFY("tag.mayQualify"),
  TAG_MAY_QUALIFY_TI("tag.mayQualifyTi"),
  TEXT_VALIDATION_TOO_LONG("validation.textTooLong"),
  TEXT_VALIDATION_TOO_SHORT("validation.textTooShort"),
  TITLE_ALL_CLIENTS("title.allClients"),
  TITLE_ALL_PROGRAMS_SECTION("title.allProgramsSection"),
  TITLE_APPLICATION_CONFIRMATION("title.applicationConfirmation"),
  TITLE_COMMON_INTAKE_CONFIRMATION("title.commonIntakeConfirmation"),
  TITLE_COMMON_INTAKE_CONFIRMATION_TI("title.commonIntakeConfirmationTi"),
  TITLE_CREATE_CLIENT("title.createClient"),
  TITLE_APPLICATION_NOT_ELIGIBLE("title.applicantNotEligible"),
  TITLE_APPLICATION_NOT_ELIGIBLE_TI("title.applicantNotEligibleTi"),
  TITLE_COMMON_INTAKE_SUMMARY("title.commonIntakeSummary"),
  TITLE_CREATE_AN_ACCOUNT("title.createAnAccount"),
  TITLE_DISPLAY_ALL_CLIENTS("title.displayingAllClients"),
  TITLE_DISPLAY_MULTI_CLIENTS("title.displayingMultiClients"),
  TITLE_DISPLAY_ONE_CLIENT("title.displayingOneClient"),
  TITLE_EDIT_CLIENT("title.editClient"),
  TITLE_FIND_SERVICES_SECTION("title.getStartedSection"),
  TITLE_GET_STARTED("title.getStarted"),
  TITLE_LOGIN("title.login"),
  TITLE_NO_CHANGES_TO_SAVE("title.noChangesToSave"),
  TITLE_ORG_MEMBERS("title.orgMembers"),
  TITLE_PROGRAM_SECTION_COMPLETED("title.programSectionCompleted"),
  TITLE_PROGRAMS("title.programs"),
  TITLE_PROGRAMS_ACTIVE_UPDATED("title.activeProgramsUpdated"),
  TITLE_PROGRAMS_IN_PROGRESS_UPDATED("title.inProgressProgramsUpdated"),
  TITLE_PROGRAM_SUMMARY("title.programSummary"),
  TITLE_PROGRAMS_SUBMITTED("title.submittedPrograms"),
  TITLE_STATUS("title.status"),
  TITLE_TI_ACCOUNT_SETTINGS("title.tiAccountSettings"),
  TITLE_TI_DASHBOARD("title.tiDashboard"),
  TOAST_APPLICATION_SAVED("toast.applicationSaved"),
  TOAST_APPLICATION_OUT_OF_DATE("toast.applicationOutOfDate"),
  TOAST_ERROR_MSG_OUTLINE("toast.errorMessageOutline"),
  TOAST_LOCALE_NOT_SUPPORTED("toast.localeNotSupported"),
  TOAST_MAY_NOT_QUALIFY("toast.mayNotQualify"),
  TOAST_MAY_NOT_QUALIFY_TI("toast.mayNotQualifyTi"),
  TOAST_MAY_QUALIFY("toast.mayQualify"),
  TOAST_MAY_QUALIFY_TI("toast.mayQualifyTi"),
  TOAST_PROGRAM_COMPLETED("toast.programCompleted"),
  TOAST_SESSION_ENDED("toast.sessionEnded"),
  UNNAMED_USER("label.unnamedUser"),
  USER_NAME("header.userName"),
  VALIDATION_REQUIRED("validation.isRequired"),
  YEAR_LABEL("label.year");

  private final String keyName;

  MessageKey(String keyName) {
    this.keyName = keyName;
  }

  public String getKeyName() {
    return this.keyName;
  }
}
