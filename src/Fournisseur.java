import java.util.List;

/**
 * Classe représentant un fournisseur
 * 
 * @author othmane
 * @version 1.0
 */

public class Fournisseur {

	/**
	 * identifiant
	 * */
	private int id;
	
	/**
	 * Coût d'ouverture
	 * */
	private int coutOuverture;
	
	/**
	 * Liste de coûts de chaque client
	 * */
	private List<Integer> coutsClients;
	
	/**
	 * Constructeur
	 * */
	public Fournisseur(int id, int coutOuv, List<Integer> coutsClis) {
		this.id = id;
		this.coutOuverture = coutOuv;
		this.coutsClients = coutsClis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoutOuverture() {
		return coutOuverture;
	}

	public void setCoutOuverture(int coutOuverture) {
		this.coutOuverture = coutOuverture;
	}

	public List<Integer> getCoutsClients() {
		return coutsClients;
	}

	public void setCoutsClients(List<Integer> coutsClients) {
		this.coutsClients = coutsClients;
	}
	
}
