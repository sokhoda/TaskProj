package bean;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the countries database table.
 *
 */
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cntrId;
	private String cntrName;
	private String cntrTag;

	// bi-directional many-to-one association to Region
	private List<Region> regions;

	public Country() {
	}

	public Country(Long cntrId, String cntrName, String cntrTag) {
		this.cntrId = cntrId;
		this.cntrName = cntrName;
		this.cntrTag = cntrTag;
	}

	public Long getCntrId() {
		return this.cntrId;
	}

	public void setCntrId(Long cntrId) {
		this.cntrId = cntrId;
	}

	public String getCntrName() {
		return this.cntrName;
	}

	public void setCntrName(String cntrName) {
		this.cntrName = cntrName;
	}

	public String getCntrTag() {
		return this.cntrTag;
	}

	public void setCntrTag(String cntrTag) {
		this.cntrTag = cntrTag;
	}

	public List<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public Region addRegion(Region region) {
		getRegions().add(region);
		region.setCntrId(this.getCntrId());

		return region;
	}

	public Region removeRegion(Region region) {
		getRegions().remove(region);
		region.setCntrId(null);

		return region;
	}

	@Override
	public String toString() {
		return "\nCountry [cntrId=" + cntrId + ", cntrName=" + cntrName
				+ ", cntrTag=" + cntrTag + "]";
	}

}