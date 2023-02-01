package projekt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import projekt.entities.Rola;

@Stateless
public class RolaDAO {

	@PersistenceContext(unitName = "projekt")
	protected EntityManager em;

	public void create(Rola rola) {
		em.persist(rola);
	}

	public Rola merge(Rola rola) {
		return em.merge(rola);
	}

	public void remove(Rola rola) {
		em.remove(rola);
	}

	public Rola find(Object id) {
		return em.find(Rola.class, id);
	}

	public List<Rola> getFullList() {
		List<Rola> list = null;

		Query query = em.createQuery("select r from Role r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Rola> getList(Map<String, Object> searchParams) {
		List<Rola> list = null;

		// 1. Build query string with parameters
		String select = "select r ";
		String from = "from Rola r ";
		String where = "";
		String orderby = "order by r.nazwaRoli asc, r.idRola";

		// search for surname
		String name = (String) searchParams.get("nazwaRoli");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.nazwaRoli like :nazwaRoli ";
		}

		// ... other parameters ...

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("nazwaRoli", name + "%");
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

//	public void insertRola(int idRola) {
//
//		Query query1 = em.createQuery("select idRola from Rola r where r.idRola='3'");
//
//		int rola = (Integer) query1.getSingleResult();
//
//		Query query = em.createNativeQuery("insert into rola (idRola) values (?1)");
//
//		query.setParameter(1, rola);
//
//		query.executeUpdate();
//
//	}

}
