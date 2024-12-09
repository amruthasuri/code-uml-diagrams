package devtools.diagrams.code_uml.entity.type;


public enum Types {

    CLASS("Class"),
    INTERFACE("Interface"),
    ANNOTATION( "Annotation"),

    ENUM ( "Enum");

    private final String typeName;

    private Types(String typeName) {
        this.typeName = typeName;
    }
}
