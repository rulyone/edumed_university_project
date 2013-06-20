/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestionespecialistas.controlador;

import cl.natjara.edumed.gestionespecialidades.servicios.GestionEspecialidadesServices;
import cl.natjara.edumed.gestionespecialistas.servicios.GestionEspecialistasServices;
import cl.natjara.edumed.modelo.Especialidad;
import cl.natjara.edumed.utils.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Natjara
 */
@FacesConverter("especialidadConverter")
public class EspecialidadConverter implements Converter {

    private GestionEspecialidadesServices services = EJB.lookup(GestionEspecialidadesServices.class) ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //Convertimos de String  a Especialidad
        String nombreEspecialidad = value;
        Especialidad esp = services.buscarEspecialidadPorNombre(nombreEspecialidad);
        return esp;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //Convertimos de Especialidad a String
        Especialidad esp = (Especialidad) value;
        if (esp == null) {
            return null;
        }
        return esp.getNombre();
        
    }
    
}
