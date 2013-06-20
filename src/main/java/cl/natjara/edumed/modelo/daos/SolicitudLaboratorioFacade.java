/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.modelo.daos;

import cl.natjara.edumed.modelo.SolicitudLaboratorio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Natjara
 */
@Stateless
public class SolicitudLaboratorioFacade extends AbstractFacade<SolicitudLaboratorio> {

    @PersistenceContext(unitName = "edumedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudLaboratorioFacade() {
        super(SolicitudLaboratorio.class);
    }
}
