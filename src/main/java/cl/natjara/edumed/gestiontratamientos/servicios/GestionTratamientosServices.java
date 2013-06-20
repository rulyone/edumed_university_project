/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestiontratamientos.servicios;

import cl.natjara.edumed.modelo.SolicitudLaboratorio;
import cl.natjara.edumed.modelo.SolicitudTratamiento;
import cl.natjara.edumed.modelo.Tratamiento;
import cl.natjara.edumed.modelo.daos.TratamientoFacade;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Natjara
 */
@Stateless
public class GestionTratamientosServices {

    private @Inject
    TratamientoFacade tratamientoFacade;

    public void agregarTratamiento(String nombre, String descripcion) throws ExcepcionLogicaDeNegocio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ExcepcionLogicaDeNegocio("El nombre no puede estar vacio.");
        }
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre(nombre);
        tratamiento.setDescripcion(descripcion);
        List<SolicitudLaboratorio> solicitudes = new ArrayList<SolicitudLaboratorio>();
        tratamiento.setSolicitudes(solicitudes);
        tratamiento.setSolicitudesTratamiento(new ArrayList<SolicitudTratamiento>());

        tratamientoFacade.create(tratamiento);

    }

    public void modificarTratamiento(Long idTratamiento, String nuevoNombre, String descripcionNueva) throws ExcepcionLogicaDeNegocio {
        Tratamiento tratamiento = tratamientoFacade.find(idTratamiento);
        if (tratamiento == null) {
            //no existe. 
            throw new ExcepcionLogicaDeNegocio("No existe un tratamiento con esa ID.");
        }

        tratamiento.setNombre(nuevoNombre);
        tratamiento.setDescripcion(descripcionNueva);

        tratamientoFacade.edit(tratamiento);


    }
    
    public List<Tratamiento> obtenerTratamientos() {
        return tratamientoFacade.findAll();
    }
    
}
