package forms;

import play.data.validation.Constraints;

public class BlockForm {
  private @Constraints.Required String name;
  private @Constraints.Required String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
