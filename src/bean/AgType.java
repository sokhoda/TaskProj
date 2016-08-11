package bean;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the ag_type database table.
 *
 */
public class AgType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long agtId;
	private String agtName;
	private String agtTag;
	// bi-directional many-to-one association to Agent
	private List<Agent> agents;

	public AgType() {
	}

	public AgType(String agtName) {
		this.agtName = agtName;
	}

	public AgType(Long agtId, String agtName, String agtTag) {
		this(agtName);
		this.agtId = agtId;
		this.agtTag = agtTag;
	}

	public Long getAgtId() {
		return this.agtId;
	}

	public Long getId() {
		return this.agtId;
	}

	public void setAgtId(Long agtId) {
		this.agtId = agtId;
	}

	public String getAgtName() {
		return this.agtName;
	}

	public void setAgtName(String agtName) {
		this.agtName = agtName;
	}

	public String getAgtTag() {
		return this.agtTag;
	}

	public void setAgtTag(String agtTag) {
		this.agtTag = agtTag;
	}

	public List<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public Agent addAgent(Agent agent) {
		getAgents().add(agent);
		agent.setAgtId(this.getAgtId());

		return agent;
	}

	public Agent removeAgent(Agent agent) {
		getAgents().remove(agent);
		agent.setAgtId(null);

		return agent;
	}

	@Override
	public String toString() {
		return "\nAgType [agtId=" + agtId + ", agtName=" + agtName
				+ ", agtTag=" + agtTag + "]";
	}

}