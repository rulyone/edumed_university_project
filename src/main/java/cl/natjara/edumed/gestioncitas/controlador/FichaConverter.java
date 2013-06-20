/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.gestioncitas.controlador;

import cl.natjara.edumed.modelo.Ficha;
import cl.natjara.edumed.modelo.daos.FichaFacade;
import cl.natjara.edumed.utils.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rulyone
 */
@FacesConverter(forClass=Ficha.class)
public class FichaConverter implements Converter {
    
    private FichaFacade fichaFacade = EJB.lookup(FichaFacade.class);
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return fichaFacade.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Ficha ficha = (Ficha)value;
        return String.valueOf(ficha.getId());
    }
}
