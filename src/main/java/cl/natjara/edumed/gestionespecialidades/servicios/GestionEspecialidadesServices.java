/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionespecialidades.servicios;

import cl.natjara.edumed.modelo.Especialidad;
import cl.natjara.edumed.modelo.Especialista;
import cl.natjara.edumed.modelo.Tratamiento;
import cl.natjara.edumed.modelo.daos.EspecialidadFacade;
import cl.natjara.edumed.modelo.daos.TratamientoFacade;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author Natjara
 */
@Stateless
public class GestionEspecialidadesServices {

    private @Inject
    EspecialidadFacade especialidadFacade;

    public void agregarEspecialidad(String nombre, String descripcion) throws ExcepcionLogicaDeNegocio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ExcepcionLogicaDeNegocio("El nombre no puede estar vacio.");
        }
        Especialidad especialidad =new Especialidad();
        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);

        especialidadFacade.create(especialidad);
        
    }

       public void modificarEspecialidad(String Nombre, String descripcionNueva) throws ExcepcionLogicaDeNegocio {
        Especialidad especialidad = especialidadFacade.find(Nombre);
        if (especialidad == null) {
            //no existe. 
            throw new ExcepcionLogicaDeNegocio("No existe una especialidad con ese Nombre.");
        }

        especialidad.setNombre(Nombre);
        especialidad.setDescripcion(descripcionNueva);

        especialidadFacade.edit(especialidad);


    }
    
    public List<Especialidad> obtenerEspecialidades() {
        return especialidadFacade.findAll();
    }

    public Especialidad buscarEspecialidadPorNombre(String nombre) {
        try {
            return especialidadFacade.find(nombre);
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
