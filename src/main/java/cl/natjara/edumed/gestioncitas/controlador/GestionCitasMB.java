/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestioncitas.controlador;

import cl.natjara.edumed.gestioncitas.servicios.GestionCitasServices;
import cl.natjara.edumed.modelo.Especialista;
import cl.natjara.edumed.modelo.Ficha;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.Date;
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
@Named(value = "gestionCitasMB")
@ViewScoped
public class GestionCitasMB implements Serializable{

    private @Inject
    GestionCitasServices servicios;
    private Date fechaCita;
    private Integer numeroDeModulos;
    private Ficha fichaPaciente;
    private Especialista especialistaSeleccionado;
    
    private List<Especialista> especialistasActuales;

    public GestionCitasMB() {
    }

    @PostConstruct
    private void init() {
       this.especialistasActuales = servicios.obtenerEspecialistas();
    }

    public String agregarCita() {
        try {
            Long idCita = servicios.agregarCita(fechaCita, numeroDeModulos, fichaPaciente, especialistaSeleccionado);            
            Messages.addGlobalInfo("Cita agregada satisfactoriamente.");
            return "/gestion_tratamientos/agregar_tratamiento.xhtml?faces-redirect=true&id_cita=" + idCita;
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return "/index.xhtml"; 
        }        
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Integer getNumeroDeModulos() {
        return numeroDeModulos;
    }

    public void setNumeroDeModulos(Integer numeroDeModulos) {
        this.numeroDeModulos = numeroDeModulos;
    }

    public Ficha getFichaPaciente() {
        return fichaPaciente;
    }

    public void setFichaPaciente(Ficha fichaPaciente) {
        this.fichaPaciente = fichaPaciente;
    }

    public Especialista getEspecialistaSeleccionado() {
        return especialistaSeleccionado;
    }

    public void setEspecialistaSeleccionado(Especialista especialista) {
        this.especialistaSeleccionado = especialista;
    }

    public List<Especialista> getEspecialistasActuales() {
        return especialistasActuales;
    }

    public void setEspecialistasActuales(List<Especialista> especialistasActuales) {
        this.especialistasActuales = especialistasActuales;
    }
 
}
