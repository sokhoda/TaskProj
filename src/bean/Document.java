package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the documents database table.
 *
 */
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;
	private static long counter;

	private Long docId;
	// bi-directional many-to-one association to Agent
	private Long mcId;

	// bi-directional many-to-one association to Agent
	private Long mgrId;

	// bi-directional many-to-one association to Agent
	private Long custId;
	// bi-directional many-to-one association to Cargo
	private Long cargID;
	private String docNo;
	private Date docDate;
	private String docName;
	private Double docSum;
	private Integer docStatus;
	private String docTag;
	private Double docCargWeight;
	private Double docCargVolume;
	private Date docLoadDate;
	private Date docUnloadDate;
	private String docLoadAddress;
	private String docUnloadAddress;
	private String docCustContactPersPhone;
	private String docCustContactPersName;
	private String docRoute;

	// bi-directional many-to-one association to Journal
	private List<Journal> journals;

	public Document() {
		this.docNo = String.valueOf(++counter);
	}

	public Document(Long docId, Long mcId, Long mgrId, Long custId,
			Long cargID, String docNo, Date docDate, String docName,
			Double docSum, Integer docStatus, String docTag,
			Double docCargWeight, Double docCargVolume, Date docLoadDate,
			Date docUnloadDate, String docLoadAddress, String docUnloadAddress,
			String docCustContactPersPhone, String docCustContactPersName,
			String docRoute) {
		this.docId = docId;
		this.mcId = mcId;
		this.mgrId = mgrId;
		this.custId = custId;
		this.cargID = cargID;
		this.docNo = docNo;
		this.docDate = docDate;
		this.docName = docName;
		this.docSum = docSum;
		this.docStatus = docStatus;
		this.docTag = docTag;
		this.docCargWeight = docCargWeight;
		this.docCargVolume = docCargVolume;
		this.docLoadDate = docLoadDate;
		this.docUnloadDate = docUnloadDate;
		this.docLoadAddress = docLoadAddress;
		this.docUnloadAddress = docUnloadAddress;
		this.docCustContactPersPhone = docCustContactPersPhone;
		this.docCustContactPersName = docCustContactPersName;
		this.docRoute = docRoute;
	}

	public Long getDocId() {
		return this.docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public Double getDocCargVolume() {
		return this.docCargVolume;
	}

	public void setDocCargVolume(Double docCargVolume) {
		this.docCargVolume = docCargVolume;
	}

	public Double getDocCargWeight() {
		return this.docCargWeight;
	}

	public void setDocCargWeight(Double docCargWeight) {
		this.docCargWeight = docCargWeight;
	}

	public String getDocCustContactPersName() {
		return this.docCustContactPersName;
	}

	public void setDocCustContactPersName(String docCustContactPersName) {
		this.docCustContactPersName = docCustContactPersName;
	}

	public String getDocCustContactPersPhone() {
		return this.docCustContactPersPhone;
	}

	public void setDocCustContactPersPhone(String docCustContactPersPhone) {
		this.docCustContactPersPhone = docCustContactPersPhone;
	}

	public Date getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocLoadAddress() {
		return this.docLoadAddress;
	}

	public void setDocLoadAddress(String docLoadAddress) {
		this.docLoadAddress = docLoadAddress;
	}

	public Date getDocLoadDate() {
		return this.docLoadDate;
	}

	public void setDocLoadDate(Date docLoadDate) {
		this.docLoadDate = docLoadDate;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Integer getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(Integer docStatus) {
		this.docStatus = docStatus;
	}

	public Double getDocSum() {
		return this.docSum;
	}

	public void setDocSum(Double docSum) {
		this.docSum = docSum;
	}

	public String getDocTag() {
		return this.docTag;
	}

	public void setDocTag(String docTag) {
		this.docTag = docTag;
	}

	public String getDocUnloadAddress() {
		return this.docUnloadAddress;
	}

	public void setDocUnloadAddress(String docUnloadAddress) {
		this.docUnloadAddress = docUnloadAddress;
	}

	public Date getDocUnloadDate() {
		return this.docUnloadDate;
	}

	public void setDocUnloadDate(Date docUnloadDate) {
		this.docUnloadDate = docUnloadDate;
	}

	public Long getCustId() {
		return this.custId;
	}

	public void setCustId(Long customer) {
		this.custId = customer;
	}

	public Long getMgrId() {
		return this.mgrId;
	}

	public void setMgrId(Long manager) {
		this.mgrId = manager;
	}

	public Long getMcId() {
		return this.mcId;
	}

	public void setMcId(Long mc) {
		this.mcId = mc;
	}

	public List<Journal> getJournals() {
		return this.journals;
	}

	public void setJournals(List<Journal> journals) {
		this.journals = journals;
	}

	public Journal addJournal(Journal journal) {
		getJournals().add(journal);
		journal.setDocId(this.getDocId());

		return journal;
	}

	public Journal removeJournal(Journal journal) {
		getJournals().remove(journal);
		journal.setDocId(null);

		return journal;
	}

	public Long getCargoId() {
		return this.cargID;
	}

	public void setCargo(Long cargo) {
		this.cargID = cargo;
	}

	public String getDocRoute() {
		return docRoute;
	}

	public void setDocRoute(String docRoute) {
		this.docRoute = docRoute;
	}

	@Override
	public String toString() {
		return "\nDocument [docId=" + docId + ", mcId=" + mcId + ", mgrId="
				+ mgrId + ", custId=" + custId + ", cargID=" + cargID
				+ ", docNo=" + docNo + ", docDate=" + docDate + ", docName="
				+ docName + ", docSum=" + docSum + ", docStatus=" + docStatus
				+ ", docTag=" + docTag + ", docCargWeight=" + docCargWeight
				+ ", docCargVolume=" + docCargVolume + ", docLoadDate="
				+ docLoadDate + ", docUnloadDate=" + docUnloadDate
				+ ", docLoadAddress=" + docLoadAddress + ", docUnloadAddress="
				+ docUnloadAddress + ", docCustContactPersPhone="
				+ docCustContactPersPhone + ", docCustContactPersName="
				+ docCustContactPersName + ", docRoute=" + docRoute + "]";
	}

}