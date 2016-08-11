package interfaces.dao;

import java.util.List;

import bean.Journal;

public interface IJournalDao {
	long create(final Journal journal);

	Journal read(final long id);

	boolean update(final Journal journal);

	boolean delete(final Journal journal);

	boolean deleteByDocId(final Long docId);

	List<Journal> findAll();

	List<Journal> findByDocId(final Long docId);

	List<Journal> findByMscId(final Long mscId);

}
