package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
		
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private class Marche {
		public Etal etals[];
		public int nbetals;
		public Marche(int nbEtals) {
			this.nbetals = nbEtals;
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
	            etals[i] = new Etal();
	            }
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			for(int i =0; i<nbetals-1;i++) {
				if (!etals[i].isEtalOccupe()) {
					return(i);
				}
			}
			return(-1);
		}
		
		public Etal[] trouverEtals(String produit) {
			int nbEtalsAvecProduit = 0;
			for(int i =0; i<nbetals;i++) {
				if (etals[i].contientProduit(produit)) {
					++nbEtalsAvecProduit;
				}
			}
		    Etal[] etalsAvecProduit = new Etal[nbEtalsAvecProduit];
			int j=0;
			for(int i =0; i<nbetals;i++) {
				if (etals[i].contientProduit(produit)) {
					etalsAvecProduit[j] = etals[i];
					++j;
				}
			}
			return(etalsAvecProduit);
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i =0; i<nbetals;i++) {
				if (etals[i].getVendeur() == gaulois) {
					return(etals[i]);
				}
			}
			return(null);
		}
		
		public String afficherMarche() {
			StringBuilder res = new StringBuilder();
			int nbEtalsVides = 0;
			for(int i =0; i<nbetals;i++) {
				if (etals[i].isEtalOccupe()) {
					res.append(etals[i].afficherEtal());
				}
				else {
					++nbEtalsVides;
				}
			}
			res.append("Il reste" + nbEtalsVides + "�tals non utilis�s dans le march�.");
			return(res.toString());
		}
	}
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int indiceEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(indiceEtal,vendeur,produit,nbProduit);
		return(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\nLe vendeur " + vendeur.getNom() + " vend des " + produit + " � l'�tal n�" + (indiceEtal+1) + ".\n");
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] etalsAvecProduit = marche.trouverEtals(produit);
		if (etalsAvecProduit == null) {
			return("Il n'y a pas de vendeur qui propose des "+produit+" au march�.");
		}
		else if (etalsAvecProduit.length == 1) {
			return("Seul le vendeur " + etalsAvecProduit[0].getVendeur() + " propose des " + produit + " au march�.");
		}
		StringBuilder res = new StringBuilder();
		res.append("Les vendeurs qui proposent des fleurs sont :");
		for (int i=0; i<etalsAvecProduit.length; i++) {
			res.append("\n- " + etalsAvecProduit[i].getVendeur());
		}
		return(res.toString());
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return(marche.trouverVendeur(vendeur));
	}
	
	public String partirVendeur(Gaulois vendeur) {
		return(rechercherEtal(vendeur).libererEtal());
	}
	
	public String afficherMarche() {
		if (marche.nbetals == 0) return("Le marché n'a aucun étal.");
		StringBuilder res = new StringBuilder();
		if (marche.nbetals == 1) res.append("Le marché du village \"" + getNom() + "\" possède cet unique étal:");
		else res.append("Le marché du village \"" + getNom() + "\" possède ces étals :\n");
		res.append(marche.afficherMarche());
		return(res.toString());
	}
}
