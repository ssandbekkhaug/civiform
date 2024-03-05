package views.admin.programs;

import static com.google.common.base.Preconditions.checkNotNull;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.p;

import auth.CiviFormProfile;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import j2html.tags.specialized.DivTag;
import java.util.Optional;
import play.mvc.Http;
import play.twirl.api.Content;
import services.program.ProgramDefinition;
import views.BaseHtmlView;
import views.HtmlBundle;
import views.admin.AdminLayout;
import views.admin.AdminLayout.NavPage;
import views.admin.AdminLayoutFactory;
import views.components.ProgramCardFactory;

/** Renders a page so the admin can view all active programs and draft programs. */
public final class DisabledProgramsView extends BaseHtmlView {
  private final AdminLayout layout;
  private final ProgramCardFactory programCardFactory;

  @Inject
  public DisabledProgramsView(
      AdminLayoutFactory layoutFactory, ProgramCardFactory programCardFactory) {
    this.layout = checkNotNull(layoutFactory).getLayout(NavPage.DISABLED_PROGRAMS);
    this.programCardFactory = checkNotNull(programCardFactory);
  }

  public Content render(
      Http.Request request,
      Optional<CiviFormProfile> profile,
      ImmutableList<ProgramDefinition> programs) {
    if (profile.isPresent()) {
      layout.setAdminType(profile.get());
    }

    DivTag contentDiv =
        div()
            .with(p("disabled programs page"))
            .with(
                each(
                    programs.stream()
                        .map(program -> this.buildProgramCardData(program, profile))
                        .map(cardData -> programCardFactory.renderCard(request, cardData))));
    HtmlBundle htmlBundle =
        layout.getBundle(request).setTitle("Disabled Programs").addMainContent(contentDiv);
    return layout.renderCentered(htmlBundle);
  }

  private ProgramCardFactory.ProgramCardData buildProgramCardData(
      ProgramDefinition program, Optional<CiviFormProfile> profile) {
    Optional<ProgramCardFactory.ProgramCardData.ProgramRow> activeRow =
        Optional.of(
            ProgramCardFactory.ProgramCardData.ProgramRow.builder()
                .setProgram(program)
                .setRowActions(ImmutableList.of())
                .setExtraRowActions(ImmutableList.of())
                .build());

    return ProgramCardFactory.ProgramCardData.builder()
        .setActiveProgram(activeRow)
        .setProfile(profile)
        .build();
  }
}
