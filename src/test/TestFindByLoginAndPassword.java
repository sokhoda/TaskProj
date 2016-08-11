package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import bean.User;
import dao.factory.MySqlDaoFactory;
import interfaces.dao.IUserDao;

@RunWith(Parameterized.class)
public class TestFindByLoginAndPassword {
	private String userLogin;
	private String userPass;
	private Long expected;
	private List<User> list;
	private IUserDao userDao = MySqlDaoFactory.getUserDao();

	public TestFindByLoginAndPassword(String userLogin, String userPass,
			Long expected) {
		this.userLogin = userLogin;
		this.userPass = userPass;
		this.expected = expected;
	}

	@Before
	public void init() {
		list = userDao.findByLoginAndPassword(userLogin, userPass);
	}

	@Parameterized.Parameters
	public static Collection someMethod() {
		return Arrays.asList(new Object[][] { { "root", "root", 2L },
				{ "xtof ", "1d", null } });
	}

	@Test
	public void testStringSize() {
		System.out.println("login:" + userLogin + ", pass:" + userPass);
		assertEquals(expected, list == null ? null : list.get(0).getUserId());
	}
}
