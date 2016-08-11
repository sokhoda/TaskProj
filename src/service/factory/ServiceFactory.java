package service.factory;

import interfaces.service.IAgTypeService;
import interfaces.service.IAgentService;
import interfaces.service.ICargoService;
import interfaces.service.ICargoTreeService;
import interfaces.service.ICountryService;
import interfaces.service.IDocumentService;
import interfaces.service.IJournalService;
import interfaces.service.IMiscService;
import interfaces.service.IRegionService;
import interfaces.service.IUserService;
import interfaces.service.IUserTypeService;
import service.AgTypeService;
import service.AgentService;
import service.CargoService;
import service.CargoTreeService;
import service.CountryService;
import service.DocumentService;
import service.JournalService;
import service.MiscService;
import service.RegionService;
import service.UserService;
import service.UserTypeService;

public class ServiceFactory {

	public static IAgentService getAgentService() {
		return new AgentService();
	}

	public static IAgTypeService getAgTypeService() {
		return new AgTypeService();
	}

	public static ICountryService getCountryService() {
		return new CountryService();
	}

	public static IDocumentService getDocumentService() {
		return new DocumentService();
	}

	public static IJournalService getJournalService() {
		return new JournalService();
	}

	public static IMiscService getMiscService() {
		return new MiscService();
	}

	public static IRegionService getRegionService() {
		return new RegionService();
	}

	public static IUserService getUserService() {
		return new UserService();
	}

	public static IUserTypeService getUserTypeService() {
		return new UserTypeService();
	}

	public static ICargoService getCargoService() {
		return new CargoService();
	}

	public static ICargoTreeService getCargoTreeService() {
		return new CargoTreeService();
	}
}
