package services.openApi.v3;

public enum DataType {
  INTEGER("integer"),
  NUMBER("number"),
  STRING("string"),
  BOOLEAN("boolean");

  private final String name;

  DataType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
