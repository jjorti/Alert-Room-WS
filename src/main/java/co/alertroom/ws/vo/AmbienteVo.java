package co.alertroom.ws.vo;

import java.util.ArrayList;
import java.util.List;

import co.jjortiz.entidades.Solicitud;

public class AmbienteVo {

	private String id;
	
	private String nombre;
	

	private String estado;

	private String ocupado;

	private List<InventarioAmbienteVo> listaInventarioAmbiente;
	
	private List<SolicitudVo> listaSolicitudes;
	
	public AmbienteVo() {
		this.setListaInventarioAmbiente(new ArrayList<InventarioAmbienteVo>());
		this.setListaSolicitudes(new ArrayList<SolicitudVo>());
	}

	public AmbienteVo(String id) {
		this.id = id;
	}
	
	public AmbienteVo(String id, String nombre, String estado, String ocupado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.ocupado = ocupado;
		this.setListaInventarioAmbiente(new ArrayList<InventarioAmbienteVo>());
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

	public List<InventarioAmbienteVo> getListaInventarioAmbiente() {
		return listaInventarioAmbiente;
	}

	public void setListaInventarioAmbiente(List<InventarioAmbienteVo> listaInventarioAmbiente) {
		this.listaInventarioAmbiente = listaInventarioAmbiente;
	}

	public List<SolicitudVo> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(List<SolicitudVo> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}
	
}
