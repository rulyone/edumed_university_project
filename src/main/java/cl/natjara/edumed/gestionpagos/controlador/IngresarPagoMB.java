/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionpagos.controlador;

import cl.natjara.edumed.gestionpagos.servicios.GestionPagosServices;
import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.Ficha;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.omnifaces.util.Messages;

/**
 *
 * @author rulyone
 */
@Named(value = "ingresarPagoMB")
@ViewScoped
public class IngresarPagoMB implements Serializable {

    private @Inject
    GestionPagosServices servicios;
    
    private Ficha fichaPaciente;
    
    private Integer monto;
    private String descripcionPago;
    private Cita citaSeleccionada;
    
    /**
     * Creates a new instance of IngresarPagoMB
     */
    public IngresarPagoMB() {
    }
    
    public String ingresarPago() {
        try {
            servicios.agregarPago(new Date(), monto, descripcionPago, citaSeleccionada);
            Messages.addGlobalInfo("Pago realizado satisfactoriamente.");
            return "/gestion_pacientes/mostrar_opciones_paciente.xhtml?faces-redirect=true&id_ficha=" + fichaPaciente.getId();
        } catch (ExcepcionLogicaDeNegocio ex) {
            Messages.addGlobalError(ex.getMessage());
            return null;
        }
    }
    
    public List<Cita> getCitasNoPagadas() {
        List<Cita> citas = fichaPaciente.getCitas();
        List<Cita> citasNoPagadas = new ArrayList<Cita>();
        //filtramos citas pagadas
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (cita.getPago() == null) {
                citasNoPagadas.add(cita);
            }
        }
        return citasNoPagadas;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }

    public Cita getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Cita citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }

    public Ficha getFichaPaciente() {
        return fichaPaciente;
    }

    public void setFichaPaciente(Ficha fichaPaciente) {
        this.fichaPaciente = fichaPaciente;
    }
    
    
    
}
