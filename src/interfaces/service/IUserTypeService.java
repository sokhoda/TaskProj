package interfaces.service;

import bean.UserType;

public interface IUserTypeService extends IService {
	Long create(UserType userType);
}
