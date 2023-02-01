package projekt.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.persistence.*;


import projekt.dao.CarDAO;
import projekt.entities.Auto;


@Named
@ViewScoped
public class CarEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_AUTO_LIST = "carList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Auto auto = new Auto();
	private Auto loaded = null;



	@EJB
	CarDAO carDAO;
	
	@Inject
	FacesContext context;

	@Inject
	ExternalContext extctx;

	@Inject
	Flash flash;

	public Auto getAuto() {
		return auto;
	}
	
	


	public void onLoad() throws IOException {
		// 1. load person passed through session
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		loaded = (Auto) session.getAttribute("vin");

		// 2. load person passed through flash
		loaded = (Auto) flash.get("vin");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			auto = loaded;
			session.removeAttribute("vin");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			if (!context.isPostback()) { // possible redirect
				context.getExternalContext().redirect("carList.xhtml");
				context.responseComplete();
			}
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (auto.getVin() == null) {
				// new record
				carDAO.create(auto);
			} else {
				// existing record
				carDAO.merge(auto);

			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_AUTO_LIST;
	}

	public List<Auto> getList() {
		List<Auto> list = null;

		// 1. Prepare search params
		Map<String, Object> searchParams = new HashMap<String, Object>();

		// 2. Get list
		list = carDAO.getList(searchParams);

		return list;
	}

}
