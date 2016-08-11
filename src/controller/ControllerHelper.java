package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import commands.Back2MainMenuCmd;
import commands.ICommand;
import commands.InitCmd;
import commands.LangOnChangeCmd;
import commands.LoginCmd;
import commands.LogoutCmd;
import commands.agent.AgentCreateCmd;
import commands.agent.AgentDeleteCmd;
import commands.agent.AgentUpdateCmd;
import commands.agent.ShAgentCreateCmd;
import commands.agent.ShAgentFilterCmd;
import commands.agent.ShAgentListCmd;
import commands.agent.ShAgentUpdateCmd;
import commands.agtype.AgTypeCreateCmd;
import commands.agtype.AgTypeDeleteCmd;
import commands.agtype.AgTypeUpdateCmd;
import commands.agtype.ShAgTypeCreateCmd;
import commands.agtype.ShAgTypeFilterCmd;
import commands.agtype.ShAgTypeListCmd;
import commands.agtype.ShAgTypeUpdateCmd;
import commands.country.CountryCreateCmd;
import commands.country.CountryDeleteCmd;
import commands.country.CountryUpdateCmd;
import commands.country.ShCountryCreateCmd;
import commands.country.ShCountryFilterCmd;
import commands.country.ShCountryListCmd;
import commands.country.ShCountryUpdateCmd;
import commands.document.DocInitDocSelectorCmd;
import commands.document.DocInitNumAlphaRouteCmd;
import commands.document.DocSetAgentOnChangeCmd;
import commands.document.DocSetFilterAttrCmd;
import commands.document.DocumentCreateCmd;
import commands.document.DocumentDeleteCmd;
import commands.document.DocumentUpdateCmd;
import commands.document.ShDocAgentFilterCmd;
import commands.document.ShDocMiscFilterCmd;
import commands.document.ShDocumentCreateCmd;
import commands.document.ShDocumentFilterCmd;
import commands.document.ShDocumentListCmd;
import commands.document.ShDocumentUpdateCmd;
import commands.misc.MiscCreateCmd;
import commands.misc.MiscDeleteCmd;
import commands.misc.MiscUpdateCmd;
import commands.misc.OnCountryChangeCmd;
import commands.misc.OnMultipleCountryChangeCmd;
import commands.misc.ShMiscCreateCmd;
import commands.misc.ShMiscFilterCmd;
import commands.misc.ShMiscListCmd;
import commands.misc.ShMiscUpdateCmd;
import commands.region.RegionCreateCmd;
import commands.region.RegionDeleteCmd;
import commands.region.RegionUpdateCmd;
import commands.region.ShRegionCreateCmd;
import commands.region.ShRegionFilterCmd;
import commands.region.ShRegionListCmd;
import commands.region.ShRegionUpdateCmd;
import commands.user.ShUserCreateCmd;
import commands.user.ShUserFilterCmd;
import commands.user.ShUserListCmd;
import commands.user.ShUserUpdateCmd;
import commands.user.UserCreateCmd;
import commands.user.UserDeleteCmd;
import commands.user.UserUpdateCmd;
import commands.usertype.ShUserTypeCreateCmd;
import commands.usertype.ShUserTypeFilterCmd;
import commands.usertype.ShUserTypeListCmd;
import commands.usertype.ShUserTypeUpdateCmd;
import commands.usertype.UserTypeCreateCmd;
import commands.usertype.UserTypeDeleteCmd;
import commands.usertype.UserTypeUpdateCmd;

public class ControllerHelper {
	private HashMap<String, ICommand> commands = new HashMap<>();
	private static ControllerHelper instance = null;

	private ControllerHelper() {
		commands.put("Init", new InitCmd());
		commands.put("Back2MainMenu", new Back2MainMenuCmd());
		commands.put("LangOnChange", new LangOnChangeCmd());
		// agent
		commands.put("AgentCreate", new AgentCreateCmd());
		commands.put("AgentDelete", new AgentDeleteCmd());
		commands.put("AgentUpdate", new AgentUpdateCmd());
		commands.put("ShAgentCreate", new ShAgentCreateCmd());
		commands.put("ShAgentFilter", new ShAgentFilterCmd());
		commands.put("ShAgentList", new ShAgentListCmd());
		commands.put("ShAgentUpdate", new ShAgentUpdateCmd());
		// agType
		commands.put("AgTypeCreate", new AgTypeCreateCmd());
		commands.put("AgTypeUpdate", new AgTypeUpdateCmd());
		commands.put("AgTypeDelete", new AgTypeDeleteCmd());
		commands.put("ShAgTypeUpdate", new ShAgTypeUpdateCmd());
		commands.put("ShAgTypeCreate", new ShAgTypeCreateCmd());
		commands.put("ShAgTypeFilter", new ShAgTypeFilterCmd());
		commands.put("ShAgTypeList", new ShAgTypeListCmd());
		// country
		commands.put("CountryCreate", new CountryCreateCmd());
		commands.put("CountryUpdate", new CountryUpdateCmd());
		commands.put("CountryDelete", new CountryDeleteCmd());
		commands.put("ShCountryUpdate", new ShCountryUpdateCmd());
		commands.put("ShCountryCreate", new ShCountryCreateCmd());
		commands.put("ShCountryFilter", new ShCountryFilterCmd());
		commands.put("ShCountryList", new ShCountryListCmd());
		// region
		commands.put("RegionCreate", new RegionCreateCmd());
		commands.put("RegionUpdate", new RegionUpdateCmd());
		commands.put("RegionDelete", new RegionDeleteCmd());
		commands.put("ShRegionUpdate", new ShRegionUpdateCmd());
		commands.put("ShRegionCreate", new ShRegionCreateCmd());
		commands.put("ShRegionFilter", new ShRegionFilterCmd());
		commands.put("ShRegionList", new ShRegionListCmd());
		// misc
		commands.put("MiscDelete", new MiscDeleteCmd());
		commands.put("MiscCreate", new MiscCreateCmd());
		commands.put("MiscUpdate", new MiscUpdateCmd());
		commands.put("ShMiscUpdate", new ShMiscUpdateCmd());
		commands.put("ShMiscCreate", new ShMiscCreateCmd());
		commands.put("ShMiscFilter", new ShMiscFilterCmd());
		commands.put("ShMiscList", new ShMiscListCmd());
		commands.put("OnCountryChange", new OnCountryChangeCmd());
		commands.put("OnMultipleCountryChange",
				new OnMultipleCountryChangeCmd());
		// uType
		commands.put("UserTypeCreate", new UserTypeCreateCmd());
		commands.put("UserTypeUpdate", new UserTypeUpdateCmd());
		commands.put("UserTypeDelete", new UserTypeDeleteCmd());
		commands.put("ShUserTypeUpdate", new ShUserTypeUpdateCmd());
		commands.put("ShUserTypeCreate", new ShUserTypeCreateCmd());
		commands.put("ShUserTypeFilter", new ShUserTypeFilterCmd());
		commands.put("ShUserTypeList", new ShUserTypeListCmd());
		// user
		commands.put("UserCreate", new UserCreateCmd());
		commands.put("UserUpdate", new UserUpdateCmd());
		commands.put("UserDelete", new UserDeleteCmd());
		commands.put("ShUserUpdate", new ShUserUpdateCmd());
		commands.put("ShUserCreate", new ShUserCreateCmd());
		commands.put("ShUserFilter", new ShUserFilterCmd());
		commands.put("ShUserList", new ShUserListCmd());
		// document
		commands.put("DocumentCreate", new DocumentCreateCmd());
		commands.put("DocumentUpdate", new DocumentUpdateCmd());
		commands.put("DocumentDelete", new DocumentDeleteCmd());
		commands.put("ShDocumentUpdate", new ShDocumentUpdateCmd());
		commands.put("ShDocumentCreate", new ShDocumentCreateCmd());
		commands.put("ShDocumentFilter", new ShDocumentFilterCmd());
		commands.put("ShDocumentList", new ShDocumentListCmd());
		commands.put("ShDocMiscFilter", new ShDocMiscFilterCmd());
		commands.put("ShDocAgentFilter", new ShDocAgentFilterCmd());
		commands.put("DocSetFilterAttr", new DocSetFilterAttrCmd());
		commands.put("DocInitDocSelector", new DocInitDocSelectorCmd());
		commands.put("DocInitNumAlphaRoute", new DocInitNumAlphaRouteCmd());
		commands.put("DocSetAgentOnChange", new DocSetAgentOnChangeCmd());
		// login
		commands.put("Login", new LoginCmd());
		commands.put("Logout", new LogoutCmd());

	}

	public ICommand getCommand(HttpServletRequest req) {
		ICommand command = commands.get(req.getParameter("command"));
		if (command == null) {
			// command = new CommandMissing();
		}
		return command;
	}

	public static ControllerHelper getInstance() {
		if (instance == null) {
			instance = new ControllerHelper();
		}
		return instance;
	}
}
