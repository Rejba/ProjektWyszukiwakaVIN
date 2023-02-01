package projekt.entities;

import projekt.entities.User;
import projekt.entities.Rola;
import projekt.dao.RolaDAO;
import projekt.dao.UserDAO;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@ViewScoped
@Named
public class RegistrationBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_USER_LOGIN = "userLogin?faces-redirect=true";

	private String haslo2;

	private User user = new User();
	private Rola rola = new Rola();

	@EJB
	UserDAO userDAO;

	@EJB
	RolaDAO rolaDAO;

	@Inject
	FacesContext context;

	public String getHaslo2() {
		return haslo2;
	}

	public void setHaslo2(String haslo2) {
		this.haslo2 = haslo2;
	}

	public User getUser() {
		return user;
	}

	public Rola getRola() {
		return rola;
	}

	public String register() {

		if (!user.getHaslo().equals(haslo2)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hasła nie są takie same", null));
			return null;
		} else if (user.getLogin().equals("admin") && user.getHaslo().equals("admin")
				|| user.getLogin().equals("moderator") && user.getHaslo().equals("moderator")) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie możesz użyć tego loginu i hasła", null));
			return null;
		} else if (user.getLogin().equals(userDAO.getLoginFromDB(user.getLogin()))) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Taki login już istnieje", null));
			return null;
		}

		try {
			if (user.getIdUser() == null) {
				userDAO.create(user);

			} else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Taki użytkownik już istnieje", null));
				return null;
			}

			

		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return null;
		}

		return PAGE_USER_LOGIN;
	}

}