/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestiontratamientos.controlador;

import cl.natjara.edumed.gestiontratamientos.servicios.GestionTratamientosServices;
import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.Tratamiento;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Natjara
 */
@Named(value = "gestionTratamientosMB")
@ViewScoped
public class GestionTratamientosMB implements Serializable{

    private @Inject
    GestionTratamientosServices servicios;
    private String nombre;
    private String descripcion;
    
    private Cita cita;
    
    private List<Tratamiento> tratamientosActuales;
    
    private Tratamiento tratamientoSeleccionado;

    public GestionTratamientosMB() {
    }
    
    @PostConstruct
    private void init() {
        //INICIALIZAMOS LA LISTA DE TRATAMIENTOS.
        System.out.println("PASE POR ACÁ...");
        tratamientosActuales = servicios.obtenerTratamientos();
    }

    public String agregarTratamiento() {
        try {
            servicios.agregarTratamiento(nombre, descripcion);
            tratamientosActuales = servicios.obtenerTratamientos();
            this.nombre=  null;
            this.descripcion = null;
            Messages.addGlobalInfo("Tratamiento agregado satisfactoriamente.");
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return null; //LO MANTIENE EN LA MISMA PÁGINA
        }
        return null;
    }
    
    public void seleccionarTratamiento(Tratamiento tratamiento) {
        System.out.println("seleccionarTratamiento");
        this.tratamientoSeleccionado = tratamiento;
    }
    
    public void modificarTratamiento() {
        try {
            servicios.modificarTratamiento(tratamientoSeleccionado.getId(), tratamientoSeleccionado.getNombre(), tratamientoSeleccionado.getDescripcion());
            Messages.addGlobalInfo("Tratamiento modificado satisfactoriamente.");
            tratamientosActuales = servicios.obtenerTratamientos();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public List<Tratamiento> getTratamientosActuales() {
        return tratamientosActuales;
    }

    public void setTratamientosActuales(List<Tratamiento> tratamientosActuales) {
        this.tratamientosActuales = tratamientosActuales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tratamiento getTratamientoSeleccionado() {
        return tratamientoSeleccionado;
    }

    public void setTratamientoSeleccionado(Tratamiento tratamientoSeleccionado) {
        this.tratamientoSeleccionado = tratamientoSeleccionado;
    }
    
}