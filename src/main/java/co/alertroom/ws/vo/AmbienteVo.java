package co.alertroom.ws.vo;

import java.util.ArrayList;
import java.util.List;

public class AmbienteVo {

	private String id;
	
	private String nombre;
	
	private String numero;

	private String estado;

	private String ocupado;

	private List<InventarioAmbienteVo> listaInventarioAmbiente;
	
	public AmbienteVo() {
		this.setListaInventarioAmbiente(new ArrayList<InventarioAmbienteVo>());
	}

	public AmbienteVo(String id, String nombre, String numero, String estado, String ocupado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numero = numero;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
	
}
