package projekt.dao;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import projekt.entities.Rola;
import projekt.entities.User;

@Stateless
public class UserDAO {

	private final static String UNIT_NAME = "projekt";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = "projekt")
	protected EntityManager em;

	@EJB
	RolaDAO rolaDAO;

	public void create(User user) {
		em.persist(user);

	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

		// 1. Build query string with parameters
		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "order by u.login asc, u.email";

		// search for login
		String login = (String) searchParams.get("login");
		if (login != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.login like :login ";
		}

		// ... other parameters ...

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (login != null) {
			query.setParameter("login", login + "%");
		}

		// ... other parameters ...

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public User getUserFromDB(String login, String haslo) {

		User u = null;

		Query query = em.createQuery("select u from User u where u.login like :login and u.haslo like :haslo");

		if (login != null) {
			query.setParameter("login", login);
			query.setParameter("haslo", haslo);
		}

		try {
			u = (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return u;
	}

	public List<String> getRolesFromDB(User user) {

		ArrayList<String> roles = new ArrayList<String>();

		if (user.getLogin().equals("admin") && user.getHaslo().equals("admin")) {
			Query query1 = em.createQuery("select nazwaRoli from Rola r where r.nazwaRoli='admin'");
			roles.add((String) query1.getSingleResult());
		} else if (user.getLogin().equals("moderator") && user.getHaslo().equals("moderator")) {
			Query query2 = em.createQuery("select nazwaRoli from Rola r where r.nazwaRoli='moderator'");
			roles.add((String) query2.getSingleResult());
		} else {
			Query query3 = em.createQuery("select nazwaRoli from Rola r where r.nazwaRoli='user'");
			roles.add((String) query3.getSingleResult());
		}

		return roles;
	}

	public String getLoginFromDB(String login) {

		String log = null;

		Query query = em.createQuery("select login from User u where u.login like :login");
		query.setParameter("login", login);

		try {
			log = (String) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return log;
	}

	public void insertUser(Integer idUser) {

		Query query = em.createNativeQuery("insert into User (idUser) values (?1)");

		query.setParameter(1, idUser);

		query.executeUpdate();

	}

	public void insertRola(Rola rola) {

		Query query1 = em.createQuery("select rola from User u where u.rola='3'");

		int role = (Integer) query1.getSingleResult();

		Query query = em.createNativeQuery("insert into User (rola) values (?1)");

		query.setParameter(1, role);

		query.executeUpdate();

	}

}
