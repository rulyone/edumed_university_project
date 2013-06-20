/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestioncitas.controlador;

import cl.natjara.edumed.modelo.Cita;
import cl.natjara.edumed.modelo.daos.CitaFacade;
import cl.natjara.edumed.utils.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rulyone
 */
@FacesConverter("citaConverter")
public class CitaConverter implements Converter {
    
    private CitaFacade citaFacade = EJB.lookup(CitaFacade.class);
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return citaFacade.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cita cita = (Cita)value;
        if (cita == null) {
            return null;
        }
        return String.valueOf(cita.getId());
    }
}
