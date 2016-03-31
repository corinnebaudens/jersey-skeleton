package fr.iutinfo.skeleton.api;

public class Plat {
	
	private String nom;
	private String cuisinier;
	private int id;
	private int quantitePart;
	
	public Plat() {}
	
	public Plat(int id, String nom, String cuisinier, int quantitePart) {
		this.nom = nom;
		this.cuisinier = cuisinier;
		this.id = id;
		this.quantitePart = quantitePart;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCuisinier() {
		return cuisinier;
	}
	public void setCuisinier(String cuisinier) {
		this.cuisinier = cuisinier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantitePart() {
		return quantitePart;
	}
	public void setQuantitePart(int quantitePart) {
		this.quantitePart = quantitePart;
	}
	
	public String toString() {
		return nom + " fait par : " + this.cuisinier;
	}
	
	
	
}
