package interfaces.service;

import bean.Cargo;

public interface ICargoService extends IService {
	Long create(Cargo cargo);
}
