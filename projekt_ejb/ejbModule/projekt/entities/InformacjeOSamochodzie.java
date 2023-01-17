package projekt.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the informacje_o_samochodzie database table.
 * 
 */
@Entity
@Table(name="informacje_o_samochodzie")
@NamedQuery(name="InformacjeOSamochodzie.findAll", query="SELECT i FROM InformacjeOSamochodzie i")
public class InformacjeOSamochodzie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_ostatniego_przegladu")
	private Date dataOstatniegoPrzegladu;

	@Column(name="liczba_miejsc_siedzacych")
	private int liczbaMiejscSiedzacych;

	private String marka;

	@Column(name="masa_wlasna_pojazdu")
	private int masaWlasnaPojazdu;

	@Column(name="moc_silnika")
	private int mocSilnika;

	private String model;

	@Column(name="ostatni_stan_drogomierza")
	private int ostatniStanDrogomierza;

	@Column(name="paliwo_alternatywne")
	private String paliwoAlternatywne;

	@Column(name="pojemnosc_silnika")
	private int pojemnoscSilnika;

	@Column(name="rodzaj_paliwa")
	private String rodzajPaliwa;

	@Column(name="rok_produkcji")
	private int rokProdukcji;

	@Column(name="typ_nadwozia")
	private String typNadwozia;

	private String vin;

	@Column(name="wynik_badania")
	private String wynikBadania;

	public InformacjeOSamochodzie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataOstatniegoPrzegladu() {
		return this.dataOstatniegoPrzegladu;
	}

	public void setDataOstatniegoPrzegladu(Date dataOstatniegoPrzegladu) {
		this.dataOstatniegoPrzegladu = dataOstatniegoPrzegladu;
	}

	public int getLiczbaMiejscSiedzacych() {
		return this.liczbaMiejscSiedzacych;
	}

	public void setLiczbaMiejscSiedzacych(int liczbaMiejscSiedzacych) {
		this.liczbaMiejscSiedzacych = liczbaMiejscSiedzacych;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public int getMasaWlasnaPojazdu() {
		return this.masaWlasnaPojazdu;
	}

	public void setMasaWlasnaPojazdu(int masaWlasnaPojazdu) {
		this.masaWlasnaPojazdu = masaWlasnaPojazdu;
	}

	public int getMocSilnika() {
		return this.mocSilnika;
	}

	public void setMocSilnika(int mocSilnika) {
		this.mocSilnika = mocSilnika;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getOstatniStanDrogomierza() {
		return this.ostatniStanDrogomierza;
	}

	public void setOstatniStanDrogomierza(int ostatniStanDrogomierza) {
		this.ostatniStanDrogomierza = ostatniStanDrogomierza;
	}

	public String getPaliwoAlternatywne() {
		return this.paliwoAlternatywne;
	}

	public void setPaliwoAlternatywne(String paliwoAlternatywne) {
		this.paliwoAlternatywne = paliwoAlternatywne;
	}

	public int getPojemnoscSilnika() {
		return this.pojemnoscSilnika;
	}

	public void setPojemnoscSilnika(int pojemnoscSilnika) {
		this.pojemnoscSilnika = pojemnoscSilnika;
	}

	public String getRodzajPaliwa() {
		return this.rodzajPaliwa;
	}

	public void setRodzajPaliwa(String rodzajPaliwa) {
		this.rodzajPaliwa = rodzajPaliwa;
	}

	public int getRokProdukcji() {
		return this.rokProdukcji;
	}

	public void setRokProdukcji(int rokProdukcji) {
		this.rokProdukcji = rokProdukcji;
	}

	public String getTypNadwozia() {
		return this.typNadwozia;
	}

	public void setTypNadwozia(String typNadwozia) {
		this.typNadwozia = typNadwozia;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getWynikBadania() {
		return this.wynikBadania;
	}

	public void setWynikBadania(String wynikBadania) {
		this.wynikBadania = wynikBadania;
	}

}