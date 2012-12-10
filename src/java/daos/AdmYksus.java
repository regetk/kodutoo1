/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

/**
 *
 * @author dell
 */
public class AdmYksus {

    private int id;
    private String kood;
    private String nimetus;
    private String kommentaar;
    private String avaja;
    private String avatud;
    private String muutja;
    private String muudetud;
    private String sulgeja;
    private String suletud;
    private int fk;

    public AdmYksus() {
    }

    public AdmYksus(String kood, String nimetus, String kommentaar) {
        this.kood = kood;
        this.nimetus = nimetus;
        this.kommentaar = kommentaar;
    }

    public AdmYksus(int id, String kood, String nimetus, String kommentaar, int fk) {
        this.id = id;
        this.kood = kood;
        this.nimetus = nimetus;
        this.kommentaar = kommentaar;
        this.fk = fk;
    }

    public AdmYksus(int id, String kood, String nimetus, String kommentaar, String avaja, String avatud, String muutja, String muudetud, String sulgeja, String suletud, int fk) {
        this.id = id;
        this.kood = kood;
        this.nimetus = nimetus;
        this.kommentaar = kommentaar;
        this.avaja = avaja;
        this.avatud = avatud;
        this.muutja = muutja;
        this.muudetud = muudetud;
        this.sulgeja = sulgeja;
        this.suletud = suletud;
        this.fk = fk;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the kood
     */
    public String getKood() {
        return kood;
    }

    /**
     * @param kood the kood to set
     */
    public void setKood(String kood) {
        this.kood = kood;
    }

    /**
     * @return the nimetus
     */
    public String getNimetus() {
        return nimetus;
    }

    /**
     * @param nimetus the nimetus to set
     */
    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    /**
     * @return the kommentaar
     */
    public String getKommentaar() {
        return kommentaar;
    }

    /**
     * @param kommentaar the kommentaar to set
     */
    public void setKommentaar(String kommentaar) {
        this.kommentaar = kommentaar;
    }

    /**
     * @return the avaja
     */
    public String getAvaja() {
        return avaja;
    }

    /**
     * @param avaja the avaja to set
     */
    public void setAvaja(String avaja) {
        this.avaja = avaja;
    }

    /**
     * @return the avatud
     */
    public String getAvatud() {
        return avatud;
    }

    /**
     * @param avatud the avatud to set
     */
    public void setAvatud(String avatud) {
        this.avatud = avatud;
    }

    /**
     * @return the muutja
     */
    public String getMuutja() {
        return muutja;
    }

    /**
     * @param muutja the muutja to set
     */
    public void setMuutja(String muutja) {
        this.muutja = muutja;
    }

    /**
     * @return the muudetud
     */
    public String getMuudetud() {
        return muudetud;
    }

    /**
     * @param muudetud the muudetud to set
     */
    public void setMuudetud(String muudetud) {
        this.muudetud = muudetud;
    }

    /**
     * @return the sulgeja
     */
    public String getSulgeja() {
        return sulgeja;
    }

    /**
     * @param sulgeja the sulgeja to set
     */
    public void setSulgeja(String sulgeja) {
        this.sulgeja = sulgeja;
    }

    /**
     * @return the suletud
     */
    public String getSuletud() {
        return suletud;
    }

    /**
     * @param suletud the suletud to set
     */
    public void setSuletud(String suletud) {
        this.suletud = suletud;
    }

    /**
     * @return the fk
     */
    public int getFk() {
        return fk;
    }

    /**
     * @param fk the fk to set
     */
    public void setFk(int fk) {
        this.fk = fk;
    }
    
      @Override
	public String toString() {
	return  nimetus ;
	}  
}
