import java.util.List;
import java.util.stream.Collectors;

public class MainTest {

	public static void main(String[] args) {
		
		Lecteur lecteur = new Lecteur("/home/hotman/Documents/Workspace_Eclipse/APG_projet_fournisseurs/src/input/jeu_1/BildeKrarup/Eq/1/E1.7",
				"/home/hotman/Documents/Workspace_Eclipse/APG_projet_fournisseurs/src/input/jeu_1/BildeKrarup/B/B1.7.opt");
		
		List<Fournisseur> fournisseurs = lecteur.mappeur();
		lecteur.mappeurDonneesOpt();
		
		List<Integer> fournisseursOpt = lecteur.getDonneesOpt().stream().distinct().collect(Collectors.toList());
		for (int i = 0; i < fournisseursOpt.size()-1; i++) {
			System.out.println(fournisseursOpt.get(i));
		}
		
		Algorithmes algo = new Algorithmes(fournisseurs);
		/**
		algo.getFournisseursRetenus().add(fournisseurs.get(13));
		algo.getFournisseursRetenus().add(fournisseurs.get(43));
		algo.getFournisseursRetenus().add(fournisseurs.get(32));
		algo.getFournisseursRetenus().add(fournisseurs.get(14));
		algo.getFournisseursRetenus().add(fournisseurs.get(10));
		*/
		algo.glouton1();
		
		System.out.println(algo.eval(algo.getFournisseursRetenus()));
		
		for (int i = 0; i < algo.getFournisseursRetenus().size(); i++) {
			System.out.println(algo.getFournisseursRetenus().get(i).getId());
		}
		
	}
	
}
