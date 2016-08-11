package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	private static final Logger log = LogManager
			.getLogger(ConnectionFactory.class.getName());

	private static ConnectionFactory connectionFactory = null;

	ResourceBundle rb = ResourceBundle.getBundle("i18n.Config", new Locale(
			"en", "UK"));

	String driverClassName = rb.getString("driver");
	String connectionUrl = rb.getString("url");
	String dbUser = rb.getString("user");
	String dbPwd = rb.getString("password");
	String useUnicode = rb.getString("useUnicode");
	String characterEncoding = rb.getString("characterEncoding");

	private ConnectionFactory() {

		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			log.error(e);
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		Properties p = new Properties();
		p.setProperty("user", dbUser);
		p.setProperty("password", dbPwd);
		p.setProperty("useUnicode", useUnicode);
		p.setProperty("characterEncoding", characterEncoding);
		try {
			conn = DriverManager.getConnection(connectionUrl, p);
		} catch (SQLException e) {
			log.error(e);
		}
		return conn;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
