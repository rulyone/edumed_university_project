/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestioncitas.servicios;

import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.Paciente;
import cl.natjara.edumed.modelo.SolicitudTratamiento;
import cl.natjara.edumed.modelo.daos.CitaFacade;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Natjara
 */
@Stateless
public class GestionCitasServices {

   private @Inject
    CitaFacade citaFacade;
    
    public void agregarCita (Long id, Date fecha, Integer modulo) throws ExcepcionLogicaDeNegocio{
        if (fecha == null || modulo==null) {
            throw new ExcepcionLogicaDeNegocio("La fecha es obligatoria.");
    }
        Cita cita =new Cita();
        cita.setFecha(fecha);
        cita.setNumeroDeModulos(modulo);

        List<SolicitudTratamiento> solicitudes = new ArrayList<SolicitudTratamiento>();
        cita.setSolicitudesTratamiento(solicitudes);
        
        citaFacade.create(cita);
    }
    public void modificarCita(Long idCita, Date nuevaFecha, Integer nuevoMonto) throws ExcepcionLogicaDeNegocio {
        Cita cita = citaFacade.find(idCita);
        if (cita == null) {
            //no existe. 
            throw new ExcepcionLogicaDeNegocio("No existe un laboratorio con esa ID.");
        }
        
        cita.setFecha(nuevaFecha);
        cita.setNumeroDeModulos(nuevoMonto);

        

        citaFacade.edit(cita);


    }
    
    public List<Cita> obtenerCitas() {
        return citaFacade.findAll();
    }
}
