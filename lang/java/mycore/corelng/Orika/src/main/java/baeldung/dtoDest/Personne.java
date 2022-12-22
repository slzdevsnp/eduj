package baeldung.dtoDest;

public class Personne {
    private String nom;
    private String surnom;
    private int age;

    public Personne(String nom, String surnom, int ageAnnees) {
        this.nom = nom;
        this.surnom = surnom;
        this.age = ageAnnees;
    }

    public String getNom() {
        return nom;
    }

    public String getSurnom() {
        return surnom;
    }

    public int getAge() {
        return age;
    }

}
