package com.example.dell.tcmaterialdesign.domain;

public class TiposReclamos {
    private int codTipoReclamo;
    private String nombreTipoReclamo;
    private int diasMaximoFinalizados;
    private int diasMaximoPendientes;
    private Double topTipoReclamo;
    private Dependencias fkCodDependencia;

    public TiposReclamos(){
    }

    public TiposReclamos(int codTipoReclamo, String nombreTipoReclamo){
        this.codTipoReclamo = codTipoReclamo;
        this.nombreTipoReclamo = nombreTipoReclamo;
    }

    public int getCodTipoReclamo() {
        return codTipoReclamo;
    }

    public void setCodTipoReclamo(int codTipoReclamo) {
        this.codTipoReclamo = codTipoReclamo;
    }

    public String getNombreTipoReclamo() {
        return nombreTipoReclamo;
    }

    public void setNombreTipoReclamo(String nombreTipoReclamo) {
        this.nombreTipoReclamo = nombreTipoReclamo;
    }

    public int getDiasMaximoFinalizados() {
        return diasMaximoFinalizados;
    }

    public void setDiasMaximoFinalizados(int diasMaximoFinalizados) {
        this.diasMaximoFinalizados = diasMaximoFinalizados;
    }

    public int getDiasMaximoPendientes() {
        return diasMaximoPendientes;
    }

    public void setDiasMaximoPendientes(int diasMaximoPendientes) {
        this.diasMaximoPendientes = diasMaximoPendientes;
    }

    public Double getTopTipoReclamo() {
        return topTipoReclamo;
    }

    public void setTopTipoReclamo(Double topTipoReclamo) {
        this.topTipoReclamo = topTipoReclamo;
    }

    public Dependencias getFkCodDependencia() {
        return fkCodDependencia;
    }

    public void setFkCodDependencia(Dependencias fkCodDependencia) {
        this.fkCodDependencia = fkCodDependencia;
    }
}
