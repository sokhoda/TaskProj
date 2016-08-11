package interfaces.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Agent;

public interface IAgentService extends IService {

	List<Agent> findByAgTypeAndNamePattern(String agtypeid, final String pattern);

	Long create(Agent agent);

	List<Agent> findAllByAgId(Long agId);

	void setAgentList(HttpServletRequest req, Long agId, String listtype);
}
