/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpacientes.controlador;

import cl.natjara.edumed.gestionpacientes.servicios.GestionPacientesServices;
import cl.natjara.edumed.modelo.Paciente;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.omnifaces.util.Messages;

/**
 *
 * @author Natjara
 */
@Named(value = "gestionPacientesMB")
@ViewScoped
public class GestionPacientesMB implements Serializable {

    private @Inject
    GestionPacientesServices servicios;
    private String rut;
    private String nombre;
    private String telefono;
    private String celular;
    private String direccion;
    private Date fecha_nacimiento;
    private String email;
    
    private List<Paciente> pacientesActuales;
    private Paciente pacienteSeleccionado;

    public GestionPacientesMB() {
    }

    @PostConstruct
    private void init() {
        //INICIALIZAMOS LA LISTA DE Laboratorios.
        System.out.println("PASE POR ACÁ...");
        pacientesActuales = servicios.obtenerPacientes();
    }

    public void seleccionarPaciente(Paciente paciente) {
        System.out.println("seleccionarPaciente");
        this.pacienteSeleccionado = paciente;
    }

    public void modificarPaciente() {
        try {
            servicios.modificarLaboratorio(rut, nombre, telefono, celular, direccion, fecha_nacimiento, email);
            Messages.addGlobalInfo("Paciente modificado satisfactoriamente.");
            pacientesActuales = servicios.obtenerPacientes();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
        }
        
    
    }

    public List<Paciente> getPacientesActuales() {
        return pacientesActuales;
    }

    public void setPacientesActuales(List<Paciente> pacientesActuales) {
        this.pacientesActuales = pacientesActuales;
    }

    public Paciente getPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
