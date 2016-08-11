package interfaces.service;

import java.util.List;

import bean.Region;

public interface IRegionService extends IService {
	public List<Region> findByAgTypeAndNamePattern(String cntrid, String pattern);

	Long create(Region region);
}
