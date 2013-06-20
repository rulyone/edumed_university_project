/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionespecialistas.servicios;

import cl.natjara.edumed.modelo.Especialidad;
import cl.natjara.edumed.modelo.Especialista;
import cl.natjara.edumed.modelo.daos.EspecialidadFacade;
import cl.natjara.edumed.modelo.daos.EspecialistaFacade;
import cl.natjara.edumed.utils.ExcepcionLogicaDeNegocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Natjara
 */
@Stateless
public class GestionEspecialistasServices {

    private @Inject
    EspecialistaFacade especialistaFacade;
    private @Inject
    EspecialidadFacade especialidadFacade;
    
    public void agregarEspecialista(String rut, String nombre, String telefono, String celular, String email, Date fecha_nacimiento, List<Especialidad> especialidades) throws ExcepcionLogicaDeNegocio {
        if (rut == null || rut.isEmpty()) {
            throw new ExcepcionLogicaDeNegocio("El rut es obligatorio.");
        }
        
        for (int i = 0; i < especialidades.size(); i++) {
            especialidadFacade.edit(especialidades.get(i));
        }
        Especialista especialista =new Especialista();
        especialista.setRutPasaporte(rut);
        especialista.setNombre(nombre);
        especialista.setTelefono(telefono);
        especialista.setCelular(celular);
        especialista.setEmail(email);
        especialista.setFechaNacimiento(fecha_nacimiento);
        especialista.setEspecialidades(especialidades);
                
        especialistaFacade.create(especialista);
        
        for (int i = 0; i < especialidades.size(); i++) {
            Especialidad especialidad = especialidades.get(i);
            especialidad.getEspecialistas().add(especialista);
            especialidadFacade.edit(especialidad);
        }
        
    }
    public void modificarEspecialista(Long id, String rutNuevo, String nombreNuevo, String telefonoNuevo, String celularNuevo, String emailNuevo, Date fecha_nacimientoNuevo, List<Especialidad> nuevasEspecialidades) throws ExcepcionLogicaDeNegocio {
        Especialista especialista = especialistaFacade.find(id);
        if (especialista == null) {
            //no existe. 
            throw new ExcepcionLogicaDeNegocio("No existe un especialista con ese rut.");
        }
        especialista.setRutPasaporte(rutNuevo);
        especialista.setNombre(nombreNuevo);
        especialista.setTelefono(telefonoNuevo);
        especialista.setCelular(celularNuevo);
        especialista.setFechaNacimiento(fecha_nacimientoNuevo);
        especialista.setEmail(emailNuevo);
        
        

        especialistaFacade.edit(especialista);


    }
    
    public List<Especialista> obtenerEspecialistas() {
        return especialistaFacade.findAll();
    }
}
