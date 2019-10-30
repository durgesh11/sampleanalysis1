package co.aarav.mvc.enums;
public enum EmployeeType {
	UNKNOWN(-1), PERFUMER_OWNER(1), FLAVOURS_OWNER(2),QUALITY_ANALYST(3);

	private final int value;

	EmployeeType(int value) {
		this.value = value;
	}

	public int toInt() {
		return value;
	}

	public static EmployeeType fromInt(int value) {
		switch (value) {
		case 1:
			return PERFUMER_OWNER;
		case 2:
			return FLAVOURS_OWNER;
		case 3:
			return QUALITY_ANALYST;
		default:
			return UNKNOWN;
		}
	}
}