package enums;

public enum DocStatusEnum {
	DEFAULT(0), ACTIVE(1), DELIVERED(2), COMPLETED(3), PAYED(4);

	public static final int ENUM_LENGTH = DocStatusEnum.values().length;
	private int status;

	private DocStatusEnum(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static void printAll() {
		int i = 1;
		for (DocStatusEnum elem : values()) {
			System.out.println((i++) + ". " + elem);
		}
	}

}
