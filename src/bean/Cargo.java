package bean;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the cargos database table.
 *
 */
public class Cargo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cargId;
	private int cargType;
	private String cargName;
	private String cargTag;
	// bi-directional many-to-one association to CargoTree
	private List<CargoTree> cargoTrees;
	// bi-directional many-to-one association to Document
	private List<Document> documents;

	public Cargo() {
	}

	public Cargo(Long cargId, int cargType, String cargName, String cargTag) {
		this.cargId = cargId;
		this.cargType = cargType;
		this.cargName = cargName;
		this.cargTag = cargTag;
	}

	public Long getCargId() {
		return this.cargId;
	}

	public void setCargId(Long cargId) {
		this.cargId = cargId;
	}

	public String getCargName() {
		return this.cargName;
	}

	public void setCargName(String cargName) {
		this.cargName = cargName;
	}

	public String getCargTag() {
		return this.cargTag;
	}

	public void setCargTag(String cargTag) {
		this.cargTag = cargTag;
	}

	public int getCargType() {
		return this.cargType;
	}

	public void setCargType(int cargType) {
		this.cargType = cargType;
	}

	public List<CargoTree> getCargoTrees() {
		return this.cargoTrees;
	}

	public void setCargoTrees(List<CargoTree> cargoTrees) {
		this.cargoTrees = cargoTrees;
	}

	public CargoTree addCargoTree(CargoTree cargoTree) {
		getCargoTrees().add(cargoTree);
		cargoTree.setCargId(this.getCargId());

		return cargoTree;
	}

	public CargoTree removeCargoTree(CargoTree cargoTree) {
		getCargoTrees().remove(cargoTree);
		cargoTree.setCargId(null);

		return cargoTree;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setCargo(this.getCargId());

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setCargo(null);

		return document;
	}

	@Override
	public String toString() {
		return "\nCargo [cargId=" + cargId + ", cargType=" + cargType
				+ ", cargName=" + cargName + ", cargTag=" + cargTag + "]";
	}

}