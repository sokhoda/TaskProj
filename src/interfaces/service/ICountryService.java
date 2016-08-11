package interfaces.service;

import bean.Country;

public interface ICountryService extends IService {
	Long create(Country country);
}
