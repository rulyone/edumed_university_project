/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.index.controlador;

import cl.natjara.edumed.index.servicios.IndexServices;
import cl.natjara.edumed.modelo.Paciente;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.omnifaces.util.Messages;

/**
 *
 * @author rulyone
 */
@Named(value = "indexMB")
@RequestScoped
public class IndexMB {
    
    private @Inject
    IndexServices indexServices;
    
    private String rutPaciente;

    /**
     * Creates a new instance of IndexMB
     */
    public IndexMB() {
    }
    
    public String mostrarOpcionesPaciente() {
        Paciente paciente = indexServices.obtenerPacientePorRut(rutPaciente);
        if (paciente == null) {
            Messages.addGlobalInfo("No existe un paciente con ese rut en nuestra base de datos. Ingrese un paciente nuevo.");
            return "/gestion_pacientes/agregar_paciente.xhtml";
        }else{
            return "/gestion_pacientes/mostrar_opciones_paciente.xhtml?faces-redirect=true&id_ficha=" + paciente.getFicha().getId();
        }
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }
    
}
