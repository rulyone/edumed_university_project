/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionlaboratorios.controlador;

import cl.natjara.edumed.gestionlaboratorios.servicios.GestionLaboratoriosServices;
import cl.natjara.edumed.modelo.Laboratorio;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Natjara
 */
@Named(value = "gestionLaboratoriosMB")
@ViewScoped
public class GestionLaboratoriosMB implements Serializable{

    private @Inject
    GestionLaboratoriosServices servicios;
    private String rut;
    private String nombre;
    private String telefono;
    private String direccion;
    private String representante;
    private String email;
    private List<Laboratorio> laboratoriosActuales;
    private Laboratorio laboratorioSeleccionado;

    public GestionLaboratoriosMB() {
    }

    @PostConstruct
    private void init() {
        //INICIALIZAMOS LA LISTA DE Laboratorios.
        System.out.println("PASE POR ACÁ...");
        laboratoriosActuales = servicios.obtenerLaboratorios();
    }

    public String agregarLaboratorio() {
        try {
            servicios.agregarLaboratorio(rut, nombre, telefono, direccion, email, representante);
            laboratoriosActuales = servicios.obtenerLaboratorios();
            this.nombre = null;
            this.rut = null;
            this.telefono = null;
            this.direccion = null;
            this.representante = null;
            this.email = null;
            Messages.addGlobalInfo("Laboratorio agregado satisfactoriamente.");
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return null; //LO MANTIENE EN LA MISMA PÁGINA
        }
        return null;
    }

    public void seleccionarLaboratorio(Laboratorio laboratorio) {
        System.out.println("seleccionarLaboratorio");
        this.laboratorioSeleccionado = laboratorio;
    }

    public void modificarLaboratorio() {
        try {
            servicios.modificarLaboratorio(laboratorioSeleccionado.getId(),laboratorioSeleccionado.getRutLaboratorio() , laboratorioSeleccionado.getNombre(), laboratorioSeleccionado.getTelefono(), laboratorioSeleccionado.getDireccion(), laboratorioSeleccionado.getRepresentante(), laboratorioSeleccionado.getEmail());
            Messages.addGlobalInfo("Laboratorio modificado satisfactoriamente.");
            laboratoriosActuales = servicios.obtenerLaboratorios();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Laboratorio> getLaboratoriosActuales() {
        return laboratoriosActuales;
    }

    public void setLaboratoriosActuales(List<Laboratorio> laboratoriosActuales) {
        this.laboratoriosActuales = laboratoriosActuales;
    }

    public Laboratorio getLaboratorioSeleccionado() {
        return laboratorioSeleccionado;
    }

    public void setLaboratorioSeleccionado(Laboratorio laboratorioSeleccionado) {
        this.laboratorioSeleccionado = laboratorioSeleccionado;
    }

   
}