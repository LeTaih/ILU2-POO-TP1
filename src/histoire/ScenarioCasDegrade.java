package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Etal.EtalNullException;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois acheteur = new Gaulois(null,(Integer) null);
		etal.acheterProduit(5,acheteur);

//		try {
//			etal.libererEtal();
//		} catch (EtalNullException e) {
//			e.printStackTrace();
//		}
		System.out.println("Fin du test");
	}

}
