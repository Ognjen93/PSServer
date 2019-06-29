package model.dto;

public class Umjetnik extends Radnik {

    private String biografija="biografija";
    private String tipRadnika = "Umjetnik";

    public Umjetnik(String ime, String prezime/*, String opisPosla */, String jmb, boolean statusRadnika, String kontak, String biografija) {
        super(ime, prezime/*, opisPosla */, jmb, statusRadnika, kontak);
        this.biografija = biografija;
        this.tipRadnika = "Umjetnik";
    }

    public Umjetnik() {

    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public String getTipRadnika() {
        return tipRadnika;
    }

    public void setTipRadnika(String tipKorisnika) {
        this.tipRadnika = tipKorisnika;
    }


    public String getBiografija() {
        return biografija;
    }


}