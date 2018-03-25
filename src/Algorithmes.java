import java.util.ArrayList;
import java.util.List;

/**
 * Classe contenant les algorithmes
 * 
 * @author othmane
 * @version 1.0
 */

public class Algorithmes {

	/**
	 * Liste de tous les fournisseurs
	 * */
	private List<Fournisseur> fournisseurs;
	
	/**
	 * Sous-ensemble de fournisseurs
	 * */
	private List<Fournisseur> fournisseursRetenus;
	
	/**
	 * Constructeur
	 * */
	public Algorithmes(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
		this.fournisseursRetenus = new ArrayList<>();
	}
	
	/**
	 * Fonction eval(O)
	 * */
	public int eval(List<Fournisseur> O) {
		int sommeCoutsOuvs = 0;
		int sommeCoutsconnexClis = 0;
		int minCliCourant;		
		
		//somme des coûts d'ouverture des fournisseurs retenu
		for (int i = 0; i < O.size(); i++) {
			sommeCoutsOuvs += O.get(i).getCoutOuverture();
		}
		
		for (int i = 0; i < 100; i++) {
			minCliCourant = Integer.MAX_VALUE;
			for (int j = 0; j < O.size(); j++) {
				if(O.get(j).getCoutsClients().get(i) < minCliCourant) {
					minCliCourant = O.get(j).getCoutsClients().get(i);
				}
			}
			sommeCoutsconnexClis += minCliCourant;
		}
		
		return sommeCoutsOuvs+sommeCoutsconnexClis;
	}

	/**
	 * Algorithme 1 : Glouton
	 * */
	public List<Fournisseur> glouton1() {
		//declarations
		List<Fournisseur> iFournisseurs = new ArrayList<>();
		Fournisseur fournisseurTemp = null;
		int iMin = Integer.MAX_VALUE;
		int nbFournisseurs = getFournisseurs().size();
		
		//ajout du premier (par défaut)
		getFournisseursRetenus().add(getFournisseurs().get(0));

		//choix du premier fournisseur moins cher
		for (int i = 1; i < getFournisseurs().size(); i++) {
			iFournisseurs.add(getFournisseurs().get(i));
			if(eval(iFournisseurs) < eval(getFournisseursRetenus())) {
				getFournisseursRetenus().clear();
				getFournisseursRetenus().add(iFournisseurs.get(0));
			}
			iFournisseurs.clear();
		}
		
		iFournisseurs = new ArrayList<>(getFournisseursRetenus());
		iMin = eval(getFournisseursRetenus());
		
		//choix des autres fournisseurs
		while (nbFournisseurs > 0) {
			
			for (int i = 0; i < getFournisseurs().size(); i++) {
				iFournisseurs.add(getFournisseurs().get(i));
				if (eval(iFournisseurs) < iMin) {
					iMin = eval(iFournisseurs);
					fournisseurTemp = getFournisseurs().get(i);
				}
				iFournisseurs.remove(getFournisseurs().get(i));
			}
			
			if(fournisseurTemp != null) {
				getFournisseursRetenus().add(fournisseurTemp);
				iFournisseurs = new ArrayList<>(getFournisseursRetenus());
			}
			
			fournisseurTemp = null;
			nbFournisseurs--;
		}
		
		return getFournisseursRetenus();
	}
	
	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

	public List<Fournisseur> getFournisseursRetenus() {
		return fournisseursRetenus;
	}

	public void setFournisseursRetenus(List<Fournisseur> fournisseursRetenus) {
		this.fournisseursRetenus = fournisseursRetenus;
	}
	
}
