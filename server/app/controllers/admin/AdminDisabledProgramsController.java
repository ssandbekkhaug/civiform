package controllers.admin;

import auth.Authorizers;
import auth.CiviFormProfile;
import auth.ProfileUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import controllers.CiviFormController;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Provider;
import org.pac4j.play.java.Secure;
import play.mvc.Http;
import play.mvc.Result;
import repository.VersionRepository;
import services.program.ProgramDefinition;
import services.program.ProgramService;
import views.admin.programs.DisabledProgramsView;

/** Controller for displaying reporting data to the admin roles. */
public final class AdminDisabledProgramsController extends CiviFormController {

  private final Provider<DisabledProgramsView> disabledProgramsView;
  private final ProgramService programService;

  @Inject
  public AdminDisabledProgramsController(
      Provider<DisabledProgramsView> disabledProgramsView,
      ProgramService programService,
      ProfileUtils profileUtils,
      VersionRepository versionRepository) {
    super(profileUtils, versionRepository);
    this.disabledProgramsView = Preconditions.checkNotNull(disabledProgramsView);
    this.programService = Preconditions.checkNotNull(programService);
  }

  @Secure(authorizers = Authorizers.Labels.ANY_ADMIN)
  public Result index(Http.Request request) {

    Optional<CiviFormProfile> optionalProfile = profileUtils.currentUserProfile(request);
    ImmutableList<ProgramDefinition> programs = programService.getDisabledPrograms();

    return ok(disabledProgramsView.get().render(request, optionalProfile, programs));
  }
}
