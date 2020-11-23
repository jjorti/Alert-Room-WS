package co.jjortiz.entidades;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase para la tabla novedades
 * @author JUAN ORTIZ
 *
 */
@Entity
@Table(name = "novedades")
public class Novedad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idnovedad")
	private Integer idNovedad;
	
	@ManyToOne
	@JoinColumn(name = "ambiente_id", referencedColumnName = "idambiente", foreignKey = @ForeignKey(name ="Fk_novedad_ambiente"))
	private Ambiente idAmbiente;
	
	
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    
    @ManyToOne
    @JoinColumn(name = "idUsuarioI", referencedColumnName = "identificacion", foreignKey = @ForeignKey(name ="Fk_novedad_usuario_instructor"))
	private Usuario idUsuarioInstructor;
    
    @ManyToOne
    @JoinColumn(name = "idUsuarioG", referencedColumnName = "identificacion", foreignKey = @ForeignKey(name ="Fk_novedad_usuario_guarda"))
	private Usuario idUsuarioGuarda;
    
    @Column(name ="observaciones", nullable = false , length = 255)
    private String observaciones;

	/**
	 * Class constructor empty
	 */
	public Novedad() {
		
	}
	
    /**
     * Class constructor using fields
     * @param idNovedad
     * @param idAmbiente
     * @param idUsuarioInstructor
     * @param fechaHora
     * @param idUsuarioGuarda
     * @param observaciones
     */
	public Novedad(Integer idNovedad, Ambiente idAmbiente, Date fechaHora, Usuario idUsuarioInstructor,
			Usuario idUsuarioGuarda, String observaciones) {
		super();
		this.idNovedad = idNovedad;
		this.idAmbiente = idAmbiente;
		this.fechaHora = fechaHora;
		this.idUsuarioInstructor = idUsuarioInstructor;
		this.idUsuarioGuarda = idUsuarioGuarda;
		this.observaciones = observaciones;
	}

	public Ambiente getIdAmbiente() {
		return idAmbiente;
	}

	public void setIdAmbiente(Ambiente idAmbiente) {
		this.idAmbiente = idAmbiente;
	}

	public Usuario getIdUsuarioInstructor() {
		return idUsuarioInstructor;
	}

	public void setIdUsuarioInstructor(Usuario idUsuarioInstructor) {
		this.idUsuarioInstructor = idUsuarioInstructor;
	}

	public Usuario getIdUsuarioGuarda() {
		return idUsuarioGuarda;
	}

	public void setIdUsuarioGuarda(Usuario idUsuarioGuarda) {
		this.idUsuarioGuarda = idUsuarioGuarda;
	}

	public Integer getIdNovedad() {
		return idNovedad;
	}

	public void setIdNovedad(Integer idNovedad) {
		this.idNovedad = idNovedad;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
