package co.jjortiz.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * class for ambient table mapping
 * @author JUAN ORTIZ
 *
 */
@Entity
@Table(name = "ambientes")
public class Ambiente {
	
	@Id
	@Column(name = "idambiente", columnDefinition="CHAR(11)")
	private String id;
	
	@Column(nullable = true , columnDefinition="CHAR(50)")
	private String nombre;

	@Column(nullable = false , columnDefinition="CHAR(1)")
	private String estado;

	@Column(nullable = false , columnDefinition="CHAR(1)")
	private String ocupado;
	
	@OneToMany(mappedBy = "idAmbiente", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Solicitud> listaSolicitudes;
	
	
	@OneToMany(mappedBy = "idAmbiente", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Novedad> listaNovedades;
	
	@OneToMany(mappedBy = "idAmbiente", cascade = {CascadeType.ALL})
	private List<InventarioAmbiente> listaInventarioAmbiente;

	/**
	 *Constructor empty 
	 */
	public Ambiente() {
		this.listaSolicitudes = new ArrayList<Solicitud>();
		this.listaNovedades = new ArrayList<Novedad>();
		this.listaInventarioAmbiente = new ArrayList<InventarioAmbiente>();
	}
	
	/**
	 * Class constructor using fields
	 * 
	 * @param id
	 * @param nombre
	 * @param numero
	 * @param estado
	 * @param ocupado
	 */
	public Ambiente(String id, String nombre, String estado, String ocupado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.ocupado = ocupado;
		this.listaSolicitudes = new ArrayList<Solicitud>();
		this.listaNovedades = new ArrayList<Novedad>();
		this.listaInventarioAmbiente = new ArrayList<InventarioAmbiente>();
	}
	
	

	public List<Solicitud> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(List<Solicitud> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	public List<Novedad> getListaNovedades() {
		return listaNovedades;
	}

	public void setListaNovedades(List<Novedad> listaNovedades) {
		this.listaNovedades = listaNovedades;
	}

	public List<InventarioAmbiente> getListaInventarioAmbiente() {
		return listaInventarioAmbiente;
	}

	public void setListaInventarioAmbiente(List<InventarioAmbiente> listaInventarioAmbiente) {
		this.listaInventarioAmbiente = listaInventarioAmbiente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getOcupado() {
		return ocupado;
	}

	public void setOcupado(String ocupado) {
		this.ocupado = ocupado;
	}
}
