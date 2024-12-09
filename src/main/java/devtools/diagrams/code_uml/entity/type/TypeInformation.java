package devtools.diagrams.code_uml.entity.type;

public record TypeInformation (String fullyQualifiedTypeName, Types type) {

	private static final String DELIMETER = ":";

	public String toString() {
		return type.name() + DELIMETER + fullyQualifiedTypeName ;
	}
};
