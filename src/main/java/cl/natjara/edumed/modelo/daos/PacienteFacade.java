/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.modelo.daos;

import cl.natjara.edumed.modelo.Paciente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Natjara
 */
@Stateless
public class PacienteFacade extends AbstractFacade<Paciente> {

    @PersistenceContext(unitName = "edumedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }
    
    public Paciente buscarPacientePorRut(String rutPaciente) {
        try {
            return em.createNamedQuery("Paciente.buscarPorRut", Paciente.class)
                .setParameter("rut", rutPaciente)
                .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
            
    }
    
}
