package commands;

import interfaces.service.IDocumentService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import manager.Config;
import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.DocumentService;
import bean.AgType;
import bean.Agent;
import bean.Country;
import bean.Document;
import bean.Misc;
import bean.Region;
import bean.User;
import bean.UserType;
import enums.DocStatusEnum;

public class CommTool {
	private static final Logger log = LogManager.getLogger(CommTool.class
			.getName());

	public static Agent getAgent(HttpServletRequest req, HttpServletResponse res) {
		Long agId = 0L;
		Long agenttype = 0L;
		try {
			String str = req.getParameter("agId");
			agId = Long.parseLong(str.length() == 0 ? "0" : str);
			agenttype = Long.parseLong(req.getParameter("agenttype"));
		} catch (NumberFormatException e) {
			log.error(e);
		}
		String agentname = req.getParameter("agentname").trim();
		String agentaddress = req.getParameter("agentaddress").trim();
		String agentphone = req.getParameter("agentphone").trim();
		String agentemail = req.getParameter("agentemail").trim();
		String agentpassport = req.getParameter("agentpassport").trim();
		String agentwww = req.getParameter("agentwww").trim();
		String agentcountry = req.getParameter("agentcountry").trim();
		String agenttag = req.getParameter("agenttag").trim();
		return new Agent(agId, agenttype, agentname, agentaddress, agentphone,
				agentemail, agentpassport, agentwww, agentcountry, null,
				agenttag);
	}

	public static AgType getAgType(HttpServletRequest req,
			HttpServletResponse res) {
		Long agtId = 0L;
		try {
			String str = req.getParameter("agtId");
			agtId = Long.parseLong(str.length() == 0 ? "0" : str);
		} catch (NumberFormatException e) {
			log.error(e);
		}
		String agtName = req.getParameter("agtypename").trim();
		String agtTag = req.getParameter("agtypetag").trim();

		return new AgType(agtId, agtName, agtTag);
	}

	public static Country getCountry(HttpServletRequest req,
			HttpServletResponse res) {
		Long cntrId = 0L;
		try {
			String str = req.getParameter("cntrId");
			cntrId = Long.parseLong(str.length() == 0 ? "0" : str);
		} catch (NumberFormatException e) {
			log.error(e);
		}
		String cntrName = req.getParameter("countryname").trim();
		String cntrTag = req.getParameter("countrytag").trim();

		return new Country(cntrId, cntrName, cntrTag);
	}

	public static Region getRegion(HttpServletRequest req,
			HttpServletResponse res) {
		Long regId = 0L;
		Long cntrId = 0L;
		try {
			String str = req.getParameter("regId");
			regId = Long.parseLong(str.length() == 0 ? "0" : str);
			cntrId = Long.parseLong(req.getParameter("country"));
		} catch (NumberFormatException e) {
			log.error(e);
		}
		String regName = req.getParameter("regionname").trim();
		String regTag = req.getParameter("regiontag").trim();

		return new Region(regId, cntrId, regName, regTag);
	}

	public static Misc getMisc(HttpServletRequest req, HttpServletResponse res) {
		Long mscId = 0L;
		Long regId = 0L;
		try {
			String str = req.getParameter("mscId");
			mscId = Long.parseLong(str.length() == 0 ? "0" : str);
			regId = Long.parseLong(req.getParameter("regId"));
		} catch (NumberFormatException e) {
			log.error(e);
		}
		String mscName = req.getParameter("miscname").trim();
		String mscTag = req.getParameter("misctag").trim();

		return new Misc(mscId, regId, Config.MISCCITYNO, mscName, mscTag);
	}

	public static UserType getUserType(HttpServletRequest req,
			HttpServletResponse res) {
		Long uTypeId = 0L;
		try {
			String str = req.getParameter("utypeId");
			uTypeId = Long.parseLong(str.length() == 0 ? "0" : str);
		} catch (NumberFormatException e) {
			log.error(e);
		}
		String uTypeName = req.getParameter("utypename").trim();

		return new UserType(uTypeId, 0, uTypeName);
	}

	public static User getUser(HttpServletRequest req, HttpServletResponse res) {
		Long userId = 0L;
		Long uTypeId = 0L;
		try {
			String str = req.getParameter("userId");
			userId = Long.parseLong(str.length() == 0 ? "0" : str);
			uTypeId = Long.parseLong(req.getParameter("utype"));
		} catch (NumberFormatException e) {
			log.error(e);
		}
		String userLogin = req.getParameter("userlogin").trim();
		String userPass = req.getParameter("userpass").trim();

		return new User(userId, uTypeId, userLogin, userPass);
	}

	public static void setDefaultDocumentToSession(HttpServletRequest req) {
		IDocumentService docService = new DocumentService();
		Document document = new Document();

		document.setDocDate(new Date());
		document.setDocNo(docService.getAutoDocNo());
		document.setDocStatus(DocStatusEnum.DEFAULT.getStatus());
		setSessionAttr(req, "document", document);
	}

	public static Document getDocument(HttpServletRequest req,
			HttpServletResponse res) {
		Integer stat = getParamInteger(req, "status");
		Integer docStatus = (stat != null ? stat : DocStatusEnum.DEFAULT
				.getStatus());
		Document document = (Document) CommTool.getSessionAttr(req, "document");

		document.setMcId(getParamLong(req, "mc"));
		document.setMgrId(getParamLong(req, "mgr"));
		document.setCustId(getParamLong(req, "cust"));
		document.setDocNo(getParamString(req, "docno"));
		document.setDocDate(getParamDate(req, "docdate"));
		document.setDocName(getParamString(req, "docname"));
		document.setDocSum(getParamDouble(req, "docsum"));
		document.setDocStatus(docStatus);
		document.setDocTag(getParamString(req, "cargname")); // cargo name
		document.setDocCargWeight(getParamDouble(req, "cargweight"));
		document.setDocCargVolume(getParamDouble(req, "cargvolume"));

		document.setDocLoadDate(getParamDate(req, "loaddate"));
		document.setDocUnloadDate(getParamDate(req, "unloaddate"));
		document.setDocLoadAddress(getParamString(req, "loadaddress"));
		document.setDocUnloadAddress(getParamString(req, "unloadaddress"));
		document.setDocCustContactPersPhone(getParamString(req,
				"custcontactpersphone"));
		document.setDocCustContactPersName(getParamString(req,
				"custcontactpersname"));
		return document;
	}

	public static Long getLongNoNull(Long var) {
		return var != null ? var : 0L;
	}

	public static Date getParamDate(HttpServletRequest req, String parName) {
		DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		Date res = null;
		String param = req.getParameter(parName);
		try {
			res = format1.parse(param);
		} catch (ParseException e) {
			log.error(parName + "::" + e);
		}
		return res;
	}

	public static String getAndPutParamString(HttpServletRequest req,
			String parName) {
		String param = req.getParameter(parName);
		if (param != null) {
			param = param.trim();
		} else {
			param = "";
		}
		req.setAttribute(parName, param);
		return param;
	}

	public static String getParamString(HttpServletRequest req, String parName) {
		String param = req.getParameter(parName);
		if (param != null) {
			param = param.trim();
		} else {
			param = "";
		}
		return param;
	}

	public static String getParamPut2SessionString(HttpServletRequest req,
			String parName) {
		String param = req.getParameter(parName);

		if (param != null) {
			param = param.trim();
		} else {
			param = "";
		}
		req.getSession().setAttribute(parName, param);
		return param;
	}

	public static Long getAndPutParamLongNull2Zero(HttpServletRequest req,
			String parName) {
		Long param = null;
		try {
			String str = req.getParameter(parName);
			param = Long.parseLong(str == null ? "0" : str);
		} catch (NumberFormatException e) {
			log.error(parName + " :: " + e);
		}
		req.setAttribute(parName, param);
		return param;
	}

	public static Long getAndPutParamLong(HttpServletRequest req, String parName) {
		Long param = null;
		try {
			param = Long.parseLong(req.getParameter(parName));
		} catch (NumberFormatException e) {
			log.error(parName + "::" + e);
		}
		req.setAttribute(parName, param);
		return param;
	}

	public static Long getParamLong(HttpServletRequest req, String parName) {
		Long param = null;
		try {
			param = Long.parseLong(req.getParameter(parName));
		} catch (NumberFormatException e) {
			log.error(parName + "::" + e);
		}
		return param;
	}

	public static Integer getParamInteger(HttpServletRequest req, String parName) {
		Integer param = null;
		try {
			param = Integer.parseInt(req.getParameter(parName));
		} catch (NumberFormatException e) {
			log.error(parName + "::" + e);
		}
		return param;
	}

	public static Double getParamDouble(HttpServletRequest req, String parName) {
		Double param = null;
		try {
			param = Double.parseDouble(req.getParameter(parName));
		} catch (NumberFormatException | NullPointerException e) {
			log.error(parName + "::" + e);
		}
		return param;
	}

	public static Object getSessionAttr(HttpServletRequest req, String attrName) {
		return req.getSession().getAttribute(attrName);
	}

	public static Integer getSessionAttrInteger(HttpServletRequest req,
			String attrName) {
		Integer attr = null;
		try {
			attr = (Integer) req.getSession().getAttribute(attrName);
		} catch (Exception e) {
			log.error(attrName + "::" + e);
		}
		return attr;
	}

	public static String getSessionAttrString(HttpServletRequest req,
			String attrName) {
		String attr = null;
		try {
			attr = (String) req.getSession().getAttribute(attrName);
		} catch (Exception e) {
			log.error(attrName + "::" + e);
		}
		return attr;
	}

	public static void removeSessionAttr(HttpServletRequest req, String attrName) {
		req.getSession().removeAttribute(attrName);
	}

	public static void setSessionAttr(HttpServletRequest req, String attrName,
			Object object) {
		req.getSession().setAttribute(attrName, object);
	}

	public static String setSessionAttrString(HttpServletRequest req,
			String attrName, String str) {
		req.getSession().setAttribute(attrName, str);
		return str;
	}

	public static void setRouteToSession(HttpServletRequest req) {

		setSessionAttr(req, "alpharoute", getParamString(req, "alpharoute"));
		setSessionAttr(req, "numroute", getParamString(req, "numroute"));
	}

	public static void setBundles(HttpServletRequest req, Locale locale) {

		CommTool.setSessionAttr(req, "bndmmenu", new LocalizationContext(
				ResourceBundle.getBundle(Config.MAINMENUBUNDLENAME, locale),
				locale));
		CommTool.setSessionAttr(req, "bndmessages", new LocalizationContext(
				ResourceBundle.getBundle(Message.BUNDLENAME, locale), locale));
	}

}