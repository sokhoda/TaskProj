package interfaces.service;

import java.util.List;

import bean.Document;
import bean.Notification;

public interface IDocumentService extends IService {
	Long create(Document document);

	List<Document> findByDocStatAndDocNoPattAndCargoPatt(String documentfilter,
			String docnopattern, String cargopattern);

	Notification validate(Document document);

	String getAutoDocNo();
}
