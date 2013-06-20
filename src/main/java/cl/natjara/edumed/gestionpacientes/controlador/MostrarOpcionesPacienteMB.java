/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpacientes.controlador;

import cl.natjara.edumed.modelo.Ficha;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author rulyone
 */
@Named(value = "mostrarOpcionesPacienteMB")
@RequestScoped
public class MostrarOpcionesPacienteMB {

    private Ficha ficha;
    
    /**
     * Creates a new instance of MostrarOpcionesPacienteMB
     */
    public MostrarOpcionesPacienteMB() {
    }
    
    public String irACrearCita() {
        return "/gestion_citas/crear_cita.xhtml?faces-redirect=true&id_ficha=" + ficha.getId();
    }
    
    public String irAIngresarPago() {
        return "/gestion_pagos/ingresar_pago.xhtml?faces-redirect=true&amp;id_ficha=" + ficha.getId();
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
}
