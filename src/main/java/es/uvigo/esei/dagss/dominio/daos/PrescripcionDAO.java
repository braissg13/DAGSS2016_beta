/*
 Proyecto Java EE, DAGSS-2016
 */
package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class PrescripcionDAO extends GenericoDAO<Prescripcion> {

    public Prescripcion buscarPorIdConRecetas(Long id) {
        TypedQuery<Prescripcion> q = em.createQuery("SELECT p FROM Prescripcion AS p JOIN FETCH p.recetas"
                                                  + "  WHERE p.id = :id", Prescripcion.class);
        q.setParameter("id", id);
        
        return q.getSingleResult();
    }
    
    // Completar aqui  
    public List<Prescripcion> buscarPorPaciente(Long idPaciente, Long idMedico) {
        TypedQuery<Prescripcion> q = em.createQuery("SELECT p FROM Prescripcion AS p "
                + "  WHERE p.paciente.id = :idPaciente AND p.medico.id = :idMedico " + "ORDER BY p.fechaInicio", Prescripcion.class);
        q.setParameter("idPaciente", idPaciente);
        q.setParameter("idMedico", idMedico);
        return q.getResultList();
    }
    
    public List<Prescripcion> buscarTratamientosPorDni(String dni) {
         TypedQuery<Prescripcion> q = em.createQuery("SELECT p FROM Prescripcion AS p"
                + " WHERE p.paciente.dni = :dni", Prescripcion.class); 
        q.setParameter("dni", dni);
        
         return q.getResultList();
    }
}
