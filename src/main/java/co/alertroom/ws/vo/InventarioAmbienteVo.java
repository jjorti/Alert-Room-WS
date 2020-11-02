package co.alertroom.ws.vo;


public class InventarioAmbienteVo {
	
	private Integer idambienteinventario;

	private String idAmbiente;
	
	private ArticuloVo idArticulo;

	private Integer cantidad;

	public InventarioAmbienteVo() {
		
	}

	public InventarioAmbienteVo(Integer idambienteinventario, String idAmbiente, ArticuloVo idArticulo, Integer cantidad) {
		super();
		this.idambienteinventario = idambienteinventario;
		this.idAmbiente = idAmbiente;
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
	}

	public Integer getIdambienteinventario() {
		return idambienteinventario;
	}

	public void setIdambienteinventario(Integer idambienteinventario) {
		this.idambienteinventario = idambienteinventario;
	}

	public String getIdAmbiente() {
		return idAmbiente;
	}

	public void setIdAmbiente(String idAmbiente) {
		this.idAmbiente = idAmbiente;
	}

	public ArticuloVo getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(ArticuloVo idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}