package com.iu.kmi;

import java.util.Date;

public class Angebot{

    private int    kunde;
    private Date   angebotsdatum;
    private Date   gueltigBis;
    private String waehrung;
    private String status;
    private int    kondition;
    private int    angebotsId;

    //Konstruktor
    public Angebot(int kunde, Date angebotsdatum,Date gueltigBis, String waehrung, String status, int kondition, int angebotsId){
        this.kunde = kunde;
        this.angebotsdatum = angebotsdatum;
        this.gueltigBis = gueltigBis;
        this.waehrung = waehrung;
        this.status = status;
        this.kondition = kondition;
        this.angebotsId = angebotsId;
    }

    //Getter & Setter

    /**
     * @return kunde
     */
    public int getKunde(){
        return this.kunde;
    }

    /**
     * Set kunde
     *
     * @param kunde new kunde
     */
    public void setKunde(final int kunde){
        this.kunde = kunde;
    }

    /**
     * @return angebotsdatum
     */
    public Date getAngebotsdatum(){
        return this.angebotsdatum;
    }

    /**
     * Set angebotsdatum
     *
     * @param angebotsdatum new angebotsdatum
     */
    public void setAngebotsdatum(final Date angebotsdatum){
        this.angebotsdatum = angebotsdatum;
    }

    /**
     * @return gueltigBis
     */
    public Date getGueltigBis(){
        return this.gueltigBis;
    }

    /**
     * Set gueltigBis
     *
     * @param gueltigBis new gueltigBis
     */
    public void setGueltigBis(final Date gueltigBis){
        this.gueltigBis = gueltigBis;
    }

    /**
     * @return waehrung
     */
    public String getWaehrung(){
        return this.waehrung;
    }

    /**
     * Set waehrung
     *
     * @param waehrung new waehrung
     */
    public void setWaehrung(final String waehrung){
        this.waehrung = waehrung;
    }

    /**
     * @return status
     */
    public String getStatus(){
        return this.status;
    }

    /**
     * Set status
     *
     * @param status new status
     */
    public void setStatus(final String status){
        this.status = status;
    }

    /**
     * @return kondition
     */
    public int getKondition(){
        return this.kondition;
    }

    /**
     * Set kondition
     *
     * @param kondition new kondition
     */
    public void setKondition(final int kondition){
        this.kondition = kondition;
    }

    /**
     * @return angebotsId
     */
    public int getAngebotsId(){
        return this.angebotsId;
    }

    /**
     * Set angebotsId
     *
     * @param angebotsId new angebotsId
     */
    public void setAngebotsId(final int angebotsId){
        this.angebotsId = angebotsId;
    }
}
