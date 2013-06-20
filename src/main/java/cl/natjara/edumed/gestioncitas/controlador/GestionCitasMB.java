/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestioncitas.controlador;

import cl.natjara.edumed.gestioncitas.servicios.GestionCitasServices;
import cl.natjara.edumed.gestionpacientes.servicios.GestionPacientesServices;
import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.Paciente;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Named(value = "gestionCitasMB")
@ViewScoped
public class GestionCitasMB implements Serializable{

    private @Inject
    GestionCitasServices servicios;
    private Long id;
    private Date fecha;
    private Integer monto;

    private List<Cita> citasActuales;
    private Cita citaSeleccionado;

    public GestionCitasMB() {
    }

    @PostConstruct
    private void init() {
        //INICIALIZAMOS LA LISTA DE Laboratorios.
        System.out.println("PASE POR ACÁ...");
        citasActuales = servicios.obtenerCitas();
    }

    public String agregarCita() {
        try {
            servicios.agregarCita(id, fecha, monto);
            citasActuales = servicios.obtenerCitas();
            this.fecha = null;
            this.monto = null;

            Messages.addGlobalInfo("Cita agregada satisfactoriamente.");
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return null; //LO MANTIENE EN LA MISMA PÁGINA
        }
        return null;
    }

    public void seleccionarCita(Cita cita) {
        System.out.println("seleccionarCita");
        this.citaSeleccionado = cita;
    }

    public void modificarCita() {
        try {
            servicios.modificarCita(citaSeleccionado.getId(), citaSeleccionado.getFecha(),citaSeleccionado.getNumeroDeModulos());
            Messages.addGlobalInfo("Cita modificada satisfactoriamente.");
            citasActuales = servicios.obtenerCitas();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public List<Cita> getCitasActuales() {
        return citasActuales;
    }

    public void setCitasActuales(List<Cita> citasActuales) {
        this.citasActuales = citasActuales;
    }

    public Cita getCitaSeleccionado() {
        return citaSeleccionado;
    }

    public void setCitaSeleccionado(Cita citaSeleccionado) {
        this.citaSeleccionado = citaSeleccionado;
    }


 
}
