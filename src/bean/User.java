package bean;

import java.io.Serializable;

/**
 * The persistent class for the users database table.
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;
	// bi-directional many-to-one association to UserType
	private Long uTypeId;
	private String userLogin;
	private String userPass;

	public User() {
		this.userId = 0L;
		this.uTypeId = 0L;
		this.userLogin = "";
		this.userPass = "";
	}

	public User(Long userId, Long uTypeId, String userLogin, String userPass) {
		this.userId = userId;
		this.uTypeId = uTypeId;
		this.userLogin = userLogin;
		this.userPass = userPass;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Long getuTypeId() {
		return this.uTypeId;
	}

	public void setuTypeId(Long uTypeId) {
		this.uTypeId = uTypeId;
	}

	@Override
	public String toString() {
		return "\nUser [userId=" + userId + ", userType=" + uTypeId
				+ ", userLogin=" + userLogin + ", userPass=" + userPass + "]";
	}

}