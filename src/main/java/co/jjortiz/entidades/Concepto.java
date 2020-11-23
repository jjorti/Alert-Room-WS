package co.jjortiz.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class for concepts table 
 * @author JUAN ORTIZ
 *
 */
@Entity
@Table(name = "conceptos")
public class Concepto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "idconcepto")
	private Integer idConcepto;
	
	@Column(name = "nombre", nullable = false , length = 45)
	private String nombre;
	
	@Column(name = "aplica", nullable = false)
	private Integer aplica;
	
	@OneToMany(mappedBy = "concepto", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Solicitud> listaSolicitudes;

	/**
	 * Class constructor empty
	 */
	public Concepto() {
	}

	/**
	 * Class constructor using fields
	 * 
	 * @param idConcepto
	 * @param nombre
	 * @param aplica
	 */
	public Concepto(Integer idConcepto, String nombre, Integer aplica) {
		super();
		this.idConcepto = idConcepto;
		this.nombre = nombre;
		this.aplica = aplica;
	}

	public Integer getIdConcepto() {
		return idConcepto;
	}

	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getAplica() {
		return aplica;
	}

	public void setAplica(Integer aplica) {
		this.aplica = aplica;
	}
	
	
	
	

}
