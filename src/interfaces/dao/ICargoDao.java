package interfaces.dao;

import java.util.List;

import bean.Cargo;

public interface ICargoDao {
	long create(final Cargo cargo);

	Cargo read(final long id);

	boolean update(final Cargo cargo);

	boolean delete(final Cargo cargo);

	List<Cargo> findAll();

	List<Cargo> findByType(final int cargType);

	List<Cargo> findByName(final String cargName);

	List<Cargo> findByNameAndType(final String cargName, final int cargType);

	List<Cargo> findByNamePattern(final String pattern);

	List<Cargo> findByNamePatternAndType(final String pattern,
			final int cargType);

	List<Cargo> findByTag(final String cntrTag);
}
