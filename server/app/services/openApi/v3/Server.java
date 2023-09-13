package services.openApi.v3;

import com.google.auto.value.AutoValue;

import java.util.Optional;

/**
 * An object representing a Server.
 *
 * <p>https://spec.openapis.org/oas/v3.1.0#serverObject
 */
@AutoValue
public abstract class Server {

  /**
   * REQUIRED.
   *
   * A URL to the target host. This URL supports Server Variables and MAY be relative, to indicate that the host location is relative to the location where the OpenAPI document is being served. Variable substitutions will be made when a variable is named in {brackets}.j
   */
  public abstract String getUrl();

  /**
   * An optional string describing the host designated by the URL. CommonMark syntax MAY be used for rich text representation.
   */
  public abstract Optional<String> getDescription();

  public static Builder builder(String url) {
    return new AutoValue_Server.Builder()
      .setUrl(url);
  }

  @AutoValue.Builder
  public abstract static class Builder {
    protected abstract Builder setUrl(String url);

    public abstract Builder setDescription(String description);

    public abstract Server build();
  }
}
