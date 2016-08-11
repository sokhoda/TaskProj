package interfaces.service;

public interface IJournalService extends IService {
	Long saveMisc(String numroute, Long docId);

	String getMiscIdByDocId(Long docId);
}
