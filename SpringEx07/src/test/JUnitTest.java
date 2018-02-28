package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koitt.book.dao.BookDao;
import com.koitt.book.dao.UsersDao;
import com.koitt.book.model.Authority;
import com.koitt.book.model.AuthorityId;
import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test/config/applicationContext.xml")
public class JUnitTest {

	@Autowired
	private ApplicationContext context;

	// 픽스처
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoding;

	private Users users1;
	private Users users2;
	private Users users3;
	private Authority admin;
	private Authority user;
	Set<Authority> adminSet = new HashSet<>();
	Set<Authority> userSet = new HashSet<>();
	Set<Authority> bothSet = new HashSet<>();
	
	private Book book1;

	@Before
	public void setUp() {
		String password = passwordEncoding.encode("1234");
		
		users1 = new Users(null, "users1@koitt.com", password, "유저1", null);
		users2 = new Users(null, "users2@koitt.com", password, "유저2", null);
		users3 = new Users(null, "users3@koitt.com", password, "유저3", null);

		admin = new Authority(AuthorityId.ADMIN.getAuthorityId(), AuthorityId.ADMIN.name());
		user = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());

		adminSet.add(admin);

		userSet.add(user);

		bothSet.add(admin);
		bothSet.add(user);

		this.users1.setAuthorities(adminSet);
		this.users2.setAuthorities(userSet);
		this.users3.setAuthorities(bothSet);

		// 게시글
		this.book1 = new Book(null, "제목-1", "작가-1", "출판사-1", 100, null, "소개-1", null);
	}

	@Test
	public void addAndGetUser() throws BookException, UsersException {

		bookDao.deleteAll();
		assertThat(bookDao.getCount(), is(0));
		
		usersDao.deleteAllUsersAuthority();
		assertThat(usersDao.getCountUsersAuthority(), is(0));

		usersDao.deleteAll();
		assertThat(usersDao.getCount(), is(0));

		usersDao.insert(users1);
		users1.setNo(usersDao.selectLastInsertId());
		usersDao.insert(users2);
		users2.setNo(usersDao.selectLastInsertId());
		usersDao.insert(users3);
		users3.setNo(usersDao.selectLastInsertId());
		assertThat(usersDao.getCount(), is(3));
		
		usersDao.insertAuthority(users1);
		usersDao.insertAuthority(users2);
		usersDao.insertAuthority(users3);
		assertThat(usersDao.getCountUsersAuthority(), is(4));

		Users usersget1 = usersDao.selectByEmail(users1.getEmail());
		assertThat(usersget1.getName(), is(users1.getName()));
		assertTrue(passwordEncoding.matches("1234", usersget1.getPassword()));

		Users usersget2 = usersDao.selectByEmail(users2.getEmail());
		assertThat(usersget2.getName(), is(users2.getName()));
		assertTrue(passwordEncoding.matches("1234", usersget2.getPassword()));

		Users usersget3 = usersDao.selectByEmail(users3.getEmail());
		assertThat(usersget3.getName(), is(users3.getName()));
		assertTrue(passwordEncoding.matches("1234", usersget3.getPassword()));

	}
	
	public void addAndGetBook () throws BookException, UsersException {
		
		bookDao.deleteAll();
		assertThat(bookDao.getCount(), is(0));
		
		usersDao.deleteAllUsersAuthority();
		assertThat(usersDao.getCountUsersAuthority(), is(0));
		
		usersDao.deleteAll();
		assertThat(usersDao.getCount(), is(0));
		
		usersDao.insert(users1);
		Integer userNo = usersDao.selectLastInsertId();		// 추가한 사용자의 번호 가져오기
		assertThat(usersDao.getCount(), is(1));
		
		book1.setUserNo(userNo);
		bookDao.insert(book1);
		Integer bookNo = bookDao.selectLastInsertId();
		assertThat(bookDao.getCount(), is(1));
		
		Book bookget1 = bookDao.select(bookNo.toString());
		assertThat(bookget1.getTitle(), is(book1.getTitle()));
		assertThat(bookget1.getAuthor(), is(book1.getAuthor()));
		
	}

}
