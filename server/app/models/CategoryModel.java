package models;

import io.ebean.annotation.DbJsonB;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import play.data.validation.Constraints;
import services.LocalizedStrings;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryModel extends BaseModel {

  @WhenCreated private Instant createTime;

  @WhenModified private Instant lastModifiedTime;

  @DbJsonB
  @Constraints.Required
  private LocalizedStrings localizedName;

  @ManyToMany(mappedBy = "categories")
  @JoinTable(
    name = "programs_categories",
    joinColumns = @JoinColumn(name = "categories_id"),
    inverseJoinColumns = @JoinColumn(name = "programs_id"))
  private List<ProgramModel> programs;

  public CategoryModel(String defaultName) {
    this.localizedName = LocalizedStrings.withDefaultValue(defaultName);
  }

  public String getDefaultName() {
    return localizedName.getDefault();
  }

  public LocalizedStrings getLocalizedName() {
    return localizedName;
  }
}