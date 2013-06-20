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
import javax.faces.bean.ViewScoped;
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
    private boolean sexo;
    
    public GestionPacientesMB() {
    }

    @PostConstruct
    private void init() {
    }
    
    public String crearPaciente() {
        
        try {
            Paciente p = servicios.agregarPacienteNuevo(rut, nombre, direccion, telefono, celular, sexo, email, fecha_nacimiento);
            return "/gestion_pacientes/mostrar_opciones_paciente.xhtml?faces-redirect=true&id_ficha=" + p.getFicha().getId();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return "/index.xhtml";
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

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
}
