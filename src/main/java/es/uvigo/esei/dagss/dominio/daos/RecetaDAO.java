/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class RecetaDAO extends GenericoDAO<Receta>{
    
    public List<Receta> buscarRecetas(String tarjetaSanitaria, Date fecha){
        TypedQuery<Receta> q = em.createQuery("SELECT r FROM Receta AS r"
                + " WHERE r.prescripcion.paciente.numeroTarjetaSanitaria = :tSanitaria AND :fecha BETWEEN r.inicioValidez AND r.finValidez", Receta.class); 
        q.setParameter("tSanitaria", tarjetaSanitaria);
        q.setParameter("fecha", fecha);
        
         return q.getResultList();
    }
}
