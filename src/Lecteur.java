/**
 * Classe permettant de lire un fichier d'entrée
 * 
 * @author othmane
 * @version 1.0
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lecteur {

	/**
	 * Nom du fichier
	 * */
	private String nomFichier;
	
	/**
	 * Nom du fichier de la solution optimale
	 * */
	private String nomFichierOpt;
	
	/**
	 * Nombre de fournisseurs
	 * */
	private int nbFournisseurs;
	
	/**
	 * Nombre de clients 
	 * */
	private int nbClients;
	
	/**
	 * Liste valeurs données optimale
	 * */
	private List<Integer> donneesOpt;

	/**
	 * Constructeur input base
	 * */
	public Lecteur(String nomFichier, String nomFOpt) {
		this.nomFichier = nomFichier;
		this.nomFichierOpt = nomFOpt;
		this.donneesOpt = new ArrayList<>();
	}

	/**
	 * Fonction qui intègre les données du fichier dans une sructure
	 * */
	public ArrayList<Fournisseur> mappeur() {
		ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();;
		try{
			FileInputStream fstream = new FileInputStream(nomFichier);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int i=1;
			String[] tokens = null;
			while (((strLine = br.readLine()) != null))   {
				//nom du fichier
				if(i == 1) {
				}else if (i == 2) {
					tokens = strLine.split(" ");
					setNbFournisseurs(Integer.parseInt(tokens[0]));
					setNbClients(Integer.parseInt(tokens[1]));
				}else {
					tokens = strLine.split(" ");
					Fournisseur fTemp;
					List<Integer> coutsClis = new ArrayList<>();
					for (int j = 2; j < tokens.length; j++) {
						coutsClis.add(Integer.parseInt(tokens[j]));
					}
					fTemp = new Fournisseur(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), coutsClis);					
					listeFournisseurs.add(fTemp);
				}
				
				//System.out.println(i);
				i++;
			}
			
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		
		return listeFournisseurs;
	}
	/**
	 * Fonction qui récupère les données de la valeur optimale
	 * */
	public void mappeurDonneesOpt() {
		try{
			FileInputStream fstream = new FileInputStream(nomFichierOpt);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] tokens = null;
			while (((strLine = br.readLine()) != null))   {
				tokens = strLine.split(" ");
			}
			for (int i = 0; i < tokens.length; i++) {
				donneesOpt.add(Integer.parseInt(tokens[i]));
			}
			
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	/**
	 * Calcule la valeur optimale de l'instance grâce au fichier optimal
	 * */
	public int calculeFichierOpt() {
		List<Fournisseur> four = mappeur();
		mappeurDonneesOpt();
		int sommeCoutsOuvs = 0;
		int sommeCoutsConnex = 0;
		List<Integer> fournisseursOpt = getDonneesOpt().stream().distinct().collect(Collectors.toList());
		for (int i = 0; i < fournisseursOpt.size()-1; i++) {
			sommeCoutsOuvs+=four.get(fournisseursOpt.get(i)).getCoutOuverture();
			//System.out.println((i+1)+" "+lecteur.getDonneesOpt().get(i));
		}
		
		for (int i = 0; i < getNbClients(); i++) {
			sommeCoutsConnex += four.get(getDonneesOpt().get(i)).getCoutsClients().get(i);
		}
		
		return sommeCoutsOuvs+sommeCoutsConnex;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public int getNbFournisseurs() {
		return nbFournisseurs;
	}

	public void setNbFournisseurs(int nbFournisseurs) {
		this.nbFournisseurs = nbFournisseurs;
	}

	public int getNbClients() {
		return nbClients;
	}

	public void setNbClients(int nbClients) {
		this.nbClients = nbClients;
	}

	public String getNomFichierOpt() {
		return nomFichierOpt;
	}

	public void setNomFichierOpt(String nomFichierOpt) {
		this.nomFichierOpt = nomFichierOpt;
	}

	public List<Integer> getDonneesOpt() {
		return donneesOpt;
	}

	public void setDonneesOpt(List<Integer> donneesOpt) {
		this.donneesOpt = donneesOpt;
	}
	
	
}
