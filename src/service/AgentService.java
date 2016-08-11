package service;

import interfaces.dao.IAgentDao;
import interfaces.service.IAgentService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Agent;

import commands.CommTool;

import dao.factory.MySqlDaoFactory;

public class AgentService implements IAgentService {
	private static final Logger log = LogManager.getLogger(AgentService.class
			.getName());
	private IAgentDao dao = MySqlDaoFactory.getAgentDao();

	public AgentService() {
	}

	@Override
	public List<Agent> findByAgTypeAndNamePattern(String agtypeid,
			String pattern) {
		return dao.findByAgTypeAndNamePattern(agtypeid, pattern);
	}

	@Override
	public Long create(Agent agent) {
		IAgentDao dao = MySqlDaoFactory.getAgentDao();
		String name = agent.getAgName();
		Long res = -1L;

		if (name.length() != 0) {
			if (dao.findByName(name) == null) {
				res = dao.create(agent);
			} else {
				log.error(name
						+ ':'
						+ Message.getInstance()
						.getProperty(Message.NAME_EXISTS));
			}
		} else {
			log.error(Message.getInstance().getProperty(
					Message.NAME_ZERO_LENGTH));
		}
		return res;
	}

	@Override
	public List<Agent> findAllByAgId(Long agId) {
		return dao.findAllByAgId(agId);
	}

	@Override
	public void setAgentList(HttpServletRequest req, Long agId, String listType) {
		List<Agent> list = findAllByAgId(agId);
		CommTool.setSessionAttr(req, listType, list);
	}

}
