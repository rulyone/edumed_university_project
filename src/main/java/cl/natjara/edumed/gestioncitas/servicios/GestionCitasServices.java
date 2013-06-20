/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestioncitas.servicios;

import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.Especialista;
import cl.natjara.edumed.modelo.Ficha;
import cl.natjara.edumed.modelo.Laboratorio;
import cl.natjara.edumed.modelo.SolicitudTratamiento;
import cl.natjara.edumed.modelo.Tratamiento;
import cl.natjara.edumed.modelo.daos.CitaFacade;
import cl.natjara.edumed.modelo.daos.EspecialistaFacade;
import cl.natjara.edumed.modelo.daos.FichaFacade;
import cl.natjara.edumed.modelo.daos.TratamientoFacade;
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
    private @Inject
    EspecialistaFacade especialistaFacade;
    private @Inject 
    FichaFacade fichaFacade;

    /**
     *
     * private Date fecha; private int numeroDeModulos; //cada modulo vale 15
     * minutos. private Ficha ficha; private Pago pago; private Especialista
     * especialista; private List<SolicitudTratamiento> solicitudesTratamiento;
     *
     */
    public Long agregarCita(Date fechaCita, Integer numeroDeModulos, Ficha ficha, Especialista especialista) throws ExcepcionLogicaDeNegocio {
        
        
        if (especialista == null) {
            throw new ExcepcionLogicaDeNegocio("El especialista es obligatorio.");
        }
        if (ficha == null) {
            throw new ExcepcionLogicaDeNegocio("Es necesaria una ficha para crear una cita.");
        }
        
        if (numeroDeModulos == null || numeroDeModulos < 1) {
            throw new ExcepcionLogicaDeNegocio("El número de módulos es obligatorio y mayor a 1.");
        }
        if (fechaCita == null) {
            throw new ExcepcionLogicaDeNegocio("La fecha de la cita es obligatoria");
        }
        especialistaFacade.edit(especialista);//attacheamos
        fichaFacade.edit(ficha); //attacheamos
        
        Cita cita = new Cita();
        cita.setEspecialista(especialista);
        cita.setFecha(fechaCita);
        cita.setFicha(ficha);
        cita.setNumeroDeModulos(numeroDeModulos);
        cita.setPago(null);
        
        citaFacade.create(cita);
        //guardamos la reversa de la relación
        especialista.getCitas().add(cita);
        ficha.getCitas().add(cita);
        
        especialistaFacade.edit(especialista);
        fichaFacade.edit(ficha);
        
        return cita.getId();
                
    }

    public List<Cita> obtenerCitas() {
        return citaFacade.findAll();
    }

    public List<Especialista> obtenerEspecialistas() {
        return especialistaFacade.findAll();
    }
}
