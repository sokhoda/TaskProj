package bean;

import java.util.ArrayList;
import java.util.List;

import enums.DocStatusEnum;

public class DocStatus {
	private String name;
	private int id;

	public DocStatus(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public DocStatus() {

	}

	public static List<DocStatus> getList() {
		List<DocStatus> list = new ArrayList<>();
		DocStatusEnum[] arr = DocStatusEnum.values();
		for (DocStatusEnum elem : arr) {
			list.add(new DocStatus(elem.name(), elem.getStatus()));
		}
		return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DocStatus [name=" + name + ", id=" + id + "]";
	}

}
