package fr.iutinfo.skeleton.api;

public class Commande {

	private int id_commande;
	private int id_client;
	private int id_plat;
	private int quantitecde;
	
	public Commande() {
	}
	
	public Commande(int id_commande, int id_client, int id_plat, int quantitecde){
		this.id_commande = id_commande;
		this.id_client = id_client;
		this.id_plat = id_plat;
		this.quantitecde = quantitecde;
	}

	public int getId_commande() {
		return id_commande;
	}

	public int getId_client() {
		return id_client;
	}

	public int getId_plat() {
		return id_plat;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public void setId_plat(int id_plat) {
		this.id_plat = id_plat;
	}

	public int getQuantitecde() {
		return quantitecde;
	}

	public void setQuantitecde(int quantitecde) {
		this.quantitecde = quantitecde;
	}

	@Override
	public String toString() {
		return "Commande numero" + id_commande + " par client "
				+ id_client + ", plat: " + id_plat + "]";
	}
}
