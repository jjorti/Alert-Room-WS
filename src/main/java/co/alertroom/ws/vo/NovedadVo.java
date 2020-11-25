package co.alertroom.ws.vo;

import java.util.Date;

public class NovedadVo {
	
	private Integer idNovedad;
	
	private String fechaHora;
	
	private AmbienteVo idAmbiente;

	private UsuarioVo idUsuarioInstructor;
	
	private UsuarioVo idUsuarioGuarda;
	
    private String observaciones;

	public Integer getIdNovedad() {
		return idNovedad;
	}

	public void setIdNovedad(Integer idNovedad) {
		this.idNovedad = idNovedad;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String string) {
		this.fechaHora = string;
	}

	public AmbienteVo getIdAmbiente() {
		return idAmbiente;
	}

	public void setIdAmbiente(AmbienteVo idAmbiente) {
		this.idAmbiente = idAmbiente;
	}

	public UsuarioVo getIdUsuarioInstructor() {
		return idUsuarioInstructor;
	}

	public void setIdUsuarioInstructor(UsuarioVo idUsuarioInstructor) {
		this.idUsuarioInstructor = idUsuarioInstructor;
	}

	public UsuarioVo getIdUsuarioGuarda() {
		return idUsuarioGuarda;
	}

	public void setIdUsuarioGuarda(UsuarioVo idUsuarioGuarda) {
		this.idUsuarioGuarda = idUsuarioGuarda;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


    
}
