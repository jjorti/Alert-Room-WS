package co.alertroom.ws.vo;

import java.util.Date;

public class SolicitudVo {

	private Integer idSolicitud;
	
	private AmbienteVo idAmbiente;

	private UsuarioVo idUsuario;
	
    private String fechaHora;
    
    private ConceptoVo concepto;
	
    private String observaciones;

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public AmbienteVo getIdAmbiente() {
		return idAmbiente;
	}

	public void setIdAmbiente(AmbienteVo idAmbiente) {
		this.idAmbiente = idAmbiente;
	}

	public UsuarioVo getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioVo idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public ConceptoVo getConcepto() {
		return concepto;
	}

	public void setConcepto(ConceptoVo concepto) {
		this.concepto = concepto;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
    
    
}
