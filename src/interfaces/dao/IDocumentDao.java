package interfaces.dao;

import java.util.Date;
import java.util.List;

import bean.Document;

public interface IDocumentDao extends IDao {
	long create(Document document);

	Document read(long id);

	boolean update(Document document);

	boolean delete(Document document);

	List<Document> findAll();

	List<Document> findByDocStatus(int docStatus);

	List<Document> findByDocSumInRange(int sum1, int sum2);

	List<Document> findByDocNo(String docNo);

	List<Document> findByDocNoPattern(String pattern);

	List<Document> findByName(String docName);

	List<Document> findByNamePattern(String pattern);

	List<Document> findByDate(Date docDate);

	List<Document> findByDateInPeriod(Date docDate1, Date docDate2);

	List<Document> findByManager(Long mgrId);

	List<Document> findByMc(Long mcId);

	List<Document> findByCust(Long custId);

	List<Document> findByDocStatAndDocNoPattAndCargoPatt(String documentfilter,
			String docnopattern, String cargopattern);

	Long findLastDocNo();
}
