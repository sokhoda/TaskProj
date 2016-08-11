package bean;

import manager.Message;

import commands.EnumOperations;

public class Notification {
	private String text;
	private String type;

	public Notification(String text, String type) {
		this.text = text;
		this.type = type;
	}

	public Notification(Long status, EnumOperations oper) {
		String str = "";
		if (status > 0) {
			switch (oper) {
			case CREATE:
				str = Message.CREATION_SUCCESS;
				break;
			case READ:
				str = Message.CREATION_SUCCESS;
				break;
			case UPDATE:
				str = Message.UPDATE_SUCCESS;
				break;
			case DELETE:
				str = Message.DELETE_SUCCESS;
				break;
			}
			this.type = Message.SUCCESS;
		} else {
			switch (oper) {
			case CREATE:
				str = Message.CREATION_FAIL;
				break;
			case READ:
				str = Message.CREATION_FAIL;
				break;
			case UPDATE:
				str = Message.UPDATE_FAIL;
				break;
			case DELETE:
				str = Message.DELETE_FAIL;
				break;
			}
			this.type = Message.DANGER;
		}
		// this.text = Message.getInstance().getProperty(str);
		this.text = str;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Notification [text=" + text + ", type=" + type + "]";
	}

}
