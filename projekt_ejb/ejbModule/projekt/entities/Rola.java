package projekt.entities;

import java.io.Serializable;
import javax.persistence.*;



import java.util.Date;
import java.util.List;

/**
 * The persistent class for the rola database table.
 * 
 */
@Entity
@NamedQuery(name="Rola.findAll", query="SELECT r FROM Rola r")
public class Rola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rola")
	private int idRola;

	@Temporal(TemporalType.DATE)
	@Column(name="data_nadania_roli")
	private Date dataNadaniaRoli;

	private int kto_nadał_role_id;

	@Column(name="nazwa_roli")
	private String nazwaRoli;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="rola")
	private List<User> users;

	public Rola() {
	}

	public int getIdRola() {
		return this.idRola;
	}

	public void setIdRola(int idRola) {
		this.idRola = idRola;
	}

	public Date getDataNadaniaRoli() {
		return this.dataNadaniaRoli;
	}

	public void setDataNadaniaRoli(Date dataNadaniaRoli) {
		this.dataNadaniaRoli = dataNadaniaRoli;
	}

	public int getKto_nadał_role_id() {
		return this.kto_nadał_role_id;
	}

	public void setKto_nadał_role_id(int kto_nadał_role_id) {
		this.kto_nadał_role_id = kto_nadał_role_id;
	}

	public String getNazwaRoli() {
		return this.nazwaRoli;
	}

	public void setNazwaRoli(String nazwaRoli) {
		this.nazwaRoli = nazwaRoli;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRola(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRola(null);

		return user;
	}

}