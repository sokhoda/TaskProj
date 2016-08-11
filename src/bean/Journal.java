package bean;

import java.io.Serializable;

/**
 * The persistent class for the journal database table.
 *
 */
public class Journal implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long jId;
	// bi-directional many-to-one association to Document
	private Long docId;
	// bi-directional many-to-one association to Misc
	private Long mscId;

	public Journal() {
	}

	public Journal(Long jId, Long docId, Long mscId) {
		this.jId = jId;
		this.docId = docId;
		this.mscId = mscId;
	}

	public Long getJId() {
		return this.jId;
	}

	public void setJId(Long jId) {
		this.jId = jId;
	}

	public Long getDocId() {
		return this.docId;
	}

	public void setDocId(Long document) {
		this.docId = document;
	}

	public Long getMscId() {
		return this.mscId;
	}

	public void setMscId(Long misc) {
		this.mscId = misc;
	}

	@Override
	public String toString() {
		return "\nJournal [jId=" + jId + ", docId=" + docId + ", mscId="
				+ mscId + "]";
	}

}