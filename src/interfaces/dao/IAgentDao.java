package interfaces.dao;

import java.util.List;

import bean.Agent;

public interface IAgentDao extends IDao {

	long create(final Agent agent);

	Agent read(final Long id);

	boolean update(final Agent agent);

	boolean delete(final Agent agent);

	List<Agent> findAll();

	List<Agent> findByName(final String agName);

	List<Agent> findByNamePattern(final String pattern);

	List<Agent> findByType(final Long agType);

	List<Agent> findByAgTypeAndNamePattern(String agtypeid, final String pattern);

	List<Agent> findAllByAgId(Long agId);
}
