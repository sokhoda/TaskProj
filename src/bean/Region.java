package bean;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the regions database table.
 *
 */
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long regId;
	// bi-directional many-to-one association to Country
	private Long cntrId;
	private String regName;
	private String regTag;

	// bi-directional many-to-one association to Misc
	private List<Misc> miscs;

	public Region() {
	}

	public Region(Long regId, Long cntrId, String regName, String regTag) {
		this.regId = regId;
		this.cntrId = cntrId;
		this.regName = regName;
		this.regTag = regTag;
	}

	public Long getRegId() {
		return this.regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public String getRegName() {
		return this.regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRegTag() {
		return this.regTag;
	}

	public void setRegTag(String regTag) {
		this.regTag = regTag;
	}

	public List<Misc> getMiscs() {
		return this.miscs;
	}

	public void setMiscs(List<Misc> miscs) {
		this.miscs = miscs;
	}

	public Misc addMisc(Misc misc) {
		getMiscs().add(misc);
		misc.setRegId(this.getRegId());

		return misc;
	}

	public Misc removeMisc(Misc misc) {
		getMiscs().remove(misc);
		misc.setRegId(null);

		return misc;
	}

	public Long getCntrId() {
		return this.cntrId;
	}

	public void setCntrId(Long country) {
		this.cntrId = country;
	}

	@Override
	public String toString() {
		return "\nRegion [regId=" + regId + ", cntrId=" + cntrId + ", regName="
				+ regName + ", regTag=" + regTag + "]";
	}

}