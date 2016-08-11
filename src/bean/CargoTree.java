package bean;

import java.io.Serializable;

/**
 * The persistent class for the cargo_tree database table.
 *
 */
public class CargoTree implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cgtrId;
	// bi-directional many-to-one association to Cargo
	private Long cargId;
	private Long cgtrF0;
	private Long cgtrF1;
	private Long cgtrF2;

	public CargoTree() {
	}

	public CargoTree(Long cgtrId, Long cargId, Long cgtrF0, Long cgtrF1,
			Long cgtrF2) {
		this.cgtrId = cgtrId;
		this.cargId = cargId;
		this.cgtrF0 = cgtrF0;
		this.cgtrF1 = cgtrF1;
		this.cgtrF2 = cgtrF2;
	}

	public Long getCgtrId() {
		return this.cgtrId;
	}

	public void setCgtrId(Long cgtrId) {
		this.cgtrId = cgtrId;
	}

	public Long getCgtrF0() {
		return this.cgtrF0;
	}

	public void setCgtrF0(Long cgtrF0) {
		this.cgtrF0 = cgtrF0;
	}

	public Long getCgtrF1() {
		return this.cgtrF1;
	}

	public void setCgtrF1(Long cgtrF1) {
		this.cgtrF1 = cgtrF1;
	}

	public Long getCgtrF2() {
		return this.cgtrF2;
	}

	public void setCgtrF2(Long cgtrF2) {
		this.cgtrF2 = cgtrF2;
	}

	public Long getCargId() {
		return this.cargId;
	}

	public void setCargId(Long cargo) {
		this.cargId = cargo;
	}

	@Override
	public String toString() {
		return "\nCargoTree [cgtrId=" + cgtrId + ", cargId=" + cargId
				+ ", cgtrF0=" + cgtrF0 + ", cgtrF1=" + cgtrF1 + ", cgtrF2="
				+ cgtrF2 + "]";
	}

}