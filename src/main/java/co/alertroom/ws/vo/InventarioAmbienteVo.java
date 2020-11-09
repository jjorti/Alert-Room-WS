package co.alertroom.ws.vo;


public class InventarioAmbienteVo {
	
	private Integer idambienteinventario;

	private AmbienteVo idAmbiente;
	
	private ArticuloVo idArticulo;

	private Integer cantidad;

	public InventarioAmbienteVo() {
		
	}

	public Integer getIdambienteinventario() {
		return idambienteinventario;
	}

	public void setIdambienteinventario(Integer idambienteinventario) {
		this.idambienteinventario = idambienteinventario;
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

	public AmbienteVo getIdAmbiente() {
		return idAmbiente;
	}

	public void setIdAmbiente(AmbienteVo ambiente) {
		this.idAmbiente = ambiente;
	}
	
}