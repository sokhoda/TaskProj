package interfaces.service;

import java.util.List;

import bean.User;

public interface IUserService extends IService {
	List<User> findByUTypeAndNamePattern(String utypeid, final String pattern);

	Long create(User user);
}
