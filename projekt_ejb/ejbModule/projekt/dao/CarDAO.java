package projekt.dao;

import javax.persistence.EntityManager;


import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import projekt.entities.InformacjeOSamochodzie;

@Stateless
public class CarDAO {

//	private final static String UNIT_NAME = "projekt";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = "projekt")
	protected EntityManager em;

	public void create(InformacjeOSamochodzie vin) {
		em.persist(vin);
	}

	public InformacjeOSamochodzie merge(InformacjeOSamochodzie vin) {
		return em.merge(vin);
	}

	public void remove(InformacjeOSamochodzie vin) {
		em.remove(em.merge(vin));
	}

	public InformacjeOSamochodzie find(Object id) {
		return em.find(InformacjeOSamochodzie.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<InformacjeOSamochodzie> getFullList() {
		List<InformacjeOSamochodzie> list = null;

		Query query = em.createQuery("select i from InformacjeOSamochodzie i");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<InformacjeOSamochodzie> getList(Map<String, Object> searchParams) {
		List<InformacjeOSamochodzie> list = null;

		// 1. Build query string with parameters
		String select = "select i ";
		String from = "from InformacjeOSamochodzie i ";
		String where = "";
		String orderby = "order by i.vin asc, i.model";

		// search for login
		String vin = (String) searchParams.get("vin");
		if (vin != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "i.vin like :vin ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (vin != null) {
			query.setParameter("vin", vin+"%");
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
	
}
