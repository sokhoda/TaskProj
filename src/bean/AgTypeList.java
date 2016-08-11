package bean;

import java.util.ArrayList;
import java.util.List;

public class AgTypeList {
	List<AgType> list = new ArrayList<>();
	private int inx;

	public AgTypeList(List<AgType> list) {
		this.list = list;
		this.inx = 0;
	}

	public int getSize() {
		int res = 0;
		if (list != null) {
			res = list.size();
		}
		return res;
	}

	public String getElement() {
		String res = "";
		if (list != null) {
			Long id = list.get(inx).getAgtId();
			res = id + " " + list.get(inx++).getAgtName();
		}
		return res;
	}
}
