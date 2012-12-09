/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java.daos;

/**
 *
 * @author dell
 */
public class VoimalikAlluvus {

    private int ylem_id;
    private int alam_id;
    private String avaja;
    private String avatud;
    private String muutja;
    private String muudetud;
    private String sulgeja;
    private String suletud;

    public VoimalikAlluvus() {
    }

    public VoimalikAlluvus(int yl_id, int al_id) {
        this.ylem_id = yl_id;
        this.alam_id = al_id;
    }

    public VoimalikAlluvus(int yl_id, int al_id, String avaja, String avatud, String muutja, String muudetud, String sulgeja, String suletud) {
        this.ylem_id = yl_id;
        this.alam_id = al_id;
        this.avaja = avaja;
        this.avatud = avatud;
        this.muutja = muutja;
        this.muudetud = muudetud;
        this.sulgeja = sulgeja;
        this.suletud = suletud;
    }

    /**
     * @return the ylem_id
     */
    public int getYlem_id() {
        return ylem_id;
    }

    /**
     * @param ylem_id the ylem_id to set
     */
    public void setYlem_id(int ylem_id) {
        this.ylem_id = ylem_id;
    }

    /**
     * @return the alam_id
     */
    public int getAlam_id() {
        return alam_id;
    }

    /**
     * @param alam_id the alam_id to set
     */
    public void setAlam_id(int alam_id) {
        this.alam_id = alam_id;
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

    @Override
    public String toString() {

        String both = String.valueOf(ylem_id) + "," + String.valueOf(alam_id);

        return both;
    }
}
