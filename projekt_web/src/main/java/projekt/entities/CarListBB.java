package projekt.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import projekt.dao.CarDAO;

@Named
@RequestScoped
//@SessionScoped
public class CarListBB {
	
	private static final String PAGE_AUTO_EDIT = "carEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;


	private String vin;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	CarDAO carDAO;

		
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public List<Auto> getFullList(){
		return carDAO.getFullList();
	}

	public List<Auto> getList(){
		List<Auto> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (vin != null && vin.length() > 0){
			searchParams.put("vin", vin);
		}
		
		//2. Get list
		list = carDAO.getList(searchParams);
		
		return list;
	}
	
	public String editAuto(Auto auto) { // 1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("vin", auto);

		// 2. Pass object throughflash
		flash.put("vin", auto);

		return PAGE_AUTO_EDIT;
	}

	public String deleteAuto(Auto auto) {
		carDAO.remove(auto);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	public String newAuto() {
		Auto auto = new Auto();

		// 1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("vin", auto);

		// 2. Pass object through flash
		flash.put("vin", auto);

		return PAGE_AUTO_EDIT;
	}
	
	
}