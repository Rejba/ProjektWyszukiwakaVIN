package projekt.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import projekt.dao.CarDAO;

@Named
@RequestScoped
//@SessionScoped
public class CarListBB {
	


	private String vin;
		
//	@Inject
//	ExternalContext extcontext;
//	
//	@Inject
//	Flash flash;
	
	@EJB
	CarDAO carDAO;

		
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public List<InformacjeOSamochodzie> getFullList(){
		return carDAO.getFullList();
	}

	public List<InformacjeOSamochodzie> getList(){
		List<InformacjeOSamochodzie> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (vin != null && vin.length() > 0){
			searchParams.put("vin", vin);
		}
		
		//2. Get list
		list = carDAO.getList(searchParams);
		
		return list;
	}
}