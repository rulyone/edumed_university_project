/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.natjara.edumed.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Natjara
 */
@Entity
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    private int numeroDeModulos; //cada modulo vale 15 minutos.
    @ManyToOne
    private Ficha ficha;
    @OneToOne
    private Pago pago;
    @ManyToOne
    private Especialista especialista;
    @OneToMany(mappedBy = "cita")
    private List<SolicitudTratamiento> solicitudesTratamiento;

    //*******************GETTERS Y SETTERS*******************//
    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }

    public List<SolicitudTratamiento> getSolicitudesTratamiento() {
        return solicitudesTratamiento;
    }

    public void setSolicitudesTratamiento(List<SolicitudTratamiento> solicitudesTratamiento) {
        this.solicitudesTratamiento = solicitudesTratamiento;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroDeModulos() {
        return numeroDeModulos;
    }

    public void setNumeroDeModulos(int numeroDeModulos) {
        this.numeroDeModulos = numeroDeModulos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.natjara.edumed.modelo.Cita[ id=" + id + " ]";
    }
}
