package histoire;

import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Village village = new Village("ville",3,3);
		try {
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Fin du test");
	}

}
