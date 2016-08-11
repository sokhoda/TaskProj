package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the agents database table.
 *
 */
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long agId;
	private String agName;
	private Long agtId;
	private String agAddress;
	private String agPhone;
	private String agEmail;
	private String agPassport;
	private String agWww;
	private String agCountry;
	private Date agDate;
	private String agTag;

	private List<Document> documentsCustId;

	// bi-directional many-to-one association to Document
	private List<Document> documentsMgrId;

	// bi-directional many-to-one association to Document
	private List<Document> documentsMcId;

	public Agent() {
	}

	public Agent(Long agId, Long agType, String agName, String agAddress,
			String agPhone, String agEmail, String agPassport, String agWww,
			String agCountry, Date agDate, String agTag) {
		this.agId = agId;
		this.agAddress = agAddress;
		this.agCountry = agCountry;
		this.agDate = agDate;
		this.agEmail = agEmail;
		this.agName = agName;
		this.agPassport = agPassport;
		this.agPhone = agPhone;
		this.agTag = agTag;
		this.agtId = agType;
		this.agWww = agWww;
	}

	public Agent(Long agType, String agName, String agAddress, String agPhone,
			String agEmail, String agPassport, String agWww, String agCountry,
			Date agDate, String agTag) {
		this(0L, agType, agName, agAddress, agPhone, agEmail, agPassport,
				agWww, agCountry, agDate, agTag);
	}

	public Long getAgId() {
		return this.agId;
	}

	public void setAgId(Long agId) {
		this.agId = agId;
	}

	public String getAgAddress() {
		return this.agAddress;
	}

	public void setAgAddress(String agAddress) {
		this.agAddress = agAddress;
	}

	public String getAgCountry() {
		return this.agCountry;
	}

	public void setAgCountry(String agCountry) {
		this.agCountry = agCountry;
	}

	public Date getAgDate() {
		return this.agDate;
	}

	public void setAgDate(Date agDate) {
		this.agDate = agDate;
	}

	public String getAgEmail() {
		return this.agEmail;
	}

	public void setAgEmail(String agEmail) {
		this.agEmail = agEmail;
	}

	public String getAgName() {
		return this.agName;
	}

	public void setAgName(String agName) {
		this.agName = agName;
	}

	public String getAgPassport() {
		return this.agPassport;
	}

	public void setAgPassport(String agPassport) {
		this.agPassport = agPassport;
	}

	public String getAgPhone() {
		return this.agPhone;
	}

	public void setAgPhone(String agPhone) {
		this.agPhone = agPhone;
	}

	public String getAgTag() {
		return this.agTag;
	}

	public void setAgTag(String agTag) {
		this.agTag = agTag;
	}

	public Long getAgtId() {
		return this.agtId;
	}

	public void setAgtId(Long agType) {
		this.agtId = agType;
	}

	public String getAgWww() {
		return this.agWww;
	}

	public void setAgWww(String agWww) {
		this.agWww = agWww;
	}

	public List<Document> getDocumentsCustId() {
		return this.documentsCustId;
	}

	public void setDocumentsCustId(List<Document> documents1) {
		this.documentsCustId = documents1;
	}

	public Document addDocumentsMyCustId(Document documents1) {
		getDocumentsCustId().add(documents1);
		documents1.setCustId(this.agId);

		return documents1;
	}

	public Document removeDocumentsMyCustId(Document documents1) {
		getDocumentsCustId().remove(documents1);
		documents1.setCustId(null);

		return documents1;
	}

	@Override
	public String toString() {
		return "\nAgent [agId=" + agId + ", agName=" + agName + ", agType="
				+ agtId + ", agAddress=" + agAddress + ", agPhone=" + agPhone
				+ ", agEmail=" + agEmail + ", agPassport=" + agPassport
				+ ", agWww=" + agWww + ", agCountry=" + agCountry + ", agDate="
				+ agDate + ", agTag=" + agTag + "]";
	}

	/*
	 * public List<Document> getDocuments2() { return this.documents2; }
	 *
	 * public void setDocuments2(List<Document> documents2) { this.documents2 =
	 * documents2; }
	 *
	 * public Document addDocuments2(Document documents2) {
	 * getDocuments2().add(documents2); documents2.setAgent2(this);
	 *
	 * return documents2; }
	 *
	 * public Document removeDocuments2(Document documents2) {
	 * getDocuments2().remove(documents2); documents2.setAgent2(null);
	 *
	 * return documents2; }
	 *
	 * public List<Document> getDocuments3() { return this.documents3; }
	 *
	 * public void setDocuments3(List<Document> documents3) { this.documents3 =
	 * documents3; }
	 *
	 * public Document addDocuments3(Document documents3) {
	 * getDocuments3().add(documents3); documents3.setAgent3(this);
	 *
	 * return documents3; }
	 *
	 * public Document removeDocuments3(Document documents3) {
	 * getDocuments3().remove(documents3); documents3.setAgent3(null);
	 *
	 * return documents3; }
	 */
}