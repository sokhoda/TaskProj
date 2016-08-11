package bean;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the misc database table.
 *
 */
public class Misc implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mscId;
	// bi-directional many-to-one association to Region
	private Long regId;
	private int mscNo;
	private String mscName;
	private String mscTag;

	// bi-directional many-to-one association to Journal
	private List<Journal> journals;

	public Misc() {
	}

	public Misc(Long mscId, Long regId, int mscNo, String mscName, String mscTag) {
		this.mscId = mscId;
		this.regId = regId;
		this.mscNo = mscNo;
		this.mscName = mscName;
		this.mscTag = mscTag;
	}

	public Long getMscId() {
		return this.mscId;
	}

	public void setMscId(Long mscId) {
		this.mscId = mscId;
	}

	public String getMscName() {
		return this.mscName;
	}

	public void setMscName(String mscName) {
		this.mscName = mscName;
	}

	public int getMscNo() {
		return this.mscNo;
	}

	public void setMscNo(int mscNo) {
		this.mscNo = mscNo;
	}

	public String getMscTag() {
		return this.mscTag;
	}

	public void setMscTag(String mscTag) {
		this.mscTag = mscTag;
	}

	public List<Journal> getJournals() {
		return this.journals;
	}

	public void setJournals(List<Journal> journals) {
		this.journals = journals;
	}

	public Journal addJournal(Journal journal) {
		getJournals().add(journal);
		journal.setMscId(this.getMscId());

		return journal;
	}

	public Journal removeJournal(Journal journal) {
		getJournals().remove(journal);
		journal.setMscId(null);

		return journal;
	}

	public Long getRegId() {
		return this.regId;
	}

	public void setRegId(Long region) {
		this.regId = region;
	}

	@Override
	public String toString() {
		return "\nMisc [mscId=" + mscId + ", regId=" + regId + ", mscNo="
				+ mscNo + ", mscName=" + mscName + ", mscTag=" + mscTag + "]";
	}

}