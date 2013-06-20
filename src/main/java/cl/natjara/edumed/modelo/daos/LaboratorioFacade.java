/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.modelo.daos;

import cl.natjara.edumed.modelo.Laboratorio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Natjara
 */
@Stateless
public class LaboratorioFacade extends AbstractFacade<Laboratorio> {

    @PersistenceContext(unitName = "edumedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LaboratorioFacade() {
        super(Laboratorio.class);
    }
}
