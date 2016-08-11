package bean;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the user_types database table.
 *
 */
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long uTypeId;

	private int uTypeCode;

	private String uTypeName;

	// bi-directional many-to-one association to User
	private List<User> users;

	public UserType() {
	}

	public UserType(Long uTypeId, int uTypeCode, String uTypeName) {
		this.uTypeId = uTypeId;
		this.uTypeCode = uTypeCode;
		this.uTypeName = uTypeName;
	}

	public Long getuTypeId() {
		return this.uTypeId;
	}

	public void setuTypeId(Long uTypeId) {
		this.uTypeId = uTypeId;
	}

	public int getuTypeCode() {
		return this.uTypeCode;
	}

	public void setuTypeCode(int uTypeCode) {
		this.uTypeCode = uTypeCode;
	}

	public String getuTypeName() {
		return this.uTypeName;
	}

	public void setuTypeName(String uTypeName) {
		this.uTypeName = uTypeName;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setuTypeId(this.getuTypeId());

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setuTypeId(null);

		return user;
	}

	@Override
	public String toString() {
		return "\nUserType [uTypeId=" + uTypeId + ", uTypeCode=" + uTypeCode
				+ ", uTypeName=" + uTypeName + "]";
	}

}