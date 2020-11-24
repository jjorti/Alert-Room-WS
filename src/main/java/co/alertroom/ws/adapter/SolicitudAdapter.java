package co.alertroom.ws.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.alertroom.ws.vo.ConceptoVo;
import co.alertroom.ws.vo.SolicitudVo;
import co.jjortiz.entidades.Solicitud;

public class SolicitudAdapter {

	public List<SolicitudVo> asignarListaSolicitudes(List<Solicitud> listaSolicitudes) {
		List<SolicitudVo> miListaSolicitudes = new ArrayList<SolicitudVo>();
		for (Solicitud solicitud : listaSolicitudes) {
			if (solicitud.getConcepto().getIdConcepto() == 4 || solicitud.getConcepto().getIdConcepto() == 1) {
				miListaSolicitudes.add(asignarSolicitud(solicitud));
				return miListaSolicitudes;
			}
		}
		return null;
	}

	public SolicitudVo asignarSolicitud(Solicitud solicitud) {
		SolicitudVo miSolicitud = new SolicitudVo();
		miSolicitud = asignar(solicitud);
		UsuarioAdapter miUsuarioAdapter = new UsuarioAdapter();
		miSolicitud.setIdUsuario(miUsuarioAdapter.asignarUsuario(solicitud.getIdUsuario()));
		return miSolicitud;
	}

	
	/**
	 * Metodo que asigna los datos basicos sin relaciones de una solicitud
	 * @param solicitudJPA
	 * @return
	 */
	private SolicitudVo asignar(Solicitud solicitudJPA) {
		SolicitudVo miSolicitud = new SolicitudVo();
		miSolicitud.setIdSolicitud(solicitudJPA.getIdSolicitud());
		miSolicitud.setFechaHora(parseFecha(solicitudJPA.getFechaHora()));
		miSolicitud.setObservaciones(solicitudJPA.getObservaciones());
		return miSolicitud;
	}


	private String parseFecha(Date fechaHora) {
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm a dd/MM/yyyy");
		return hourdateFormat.format(fechaHora);
	}

	public List<SolicitudVo> asignarListaSolicitudesGuarda(List<Solicitud> listaSolicitudesJPA) {
		List<SolicitudVo> miListaSolicitudes = new ArrayList<SolicitudVo>();
		for (Solicitud solicitudJPA : listaSolicitudesJPA) {
			miListaSolicitudes.add(asignarSolicitudGuarda(solicitudJPA));	
		}
		return miListaSolicitudes;
	}

	public  SolicitudVo asignarSolicitudGuarda(Solicitud solicitudJPA) {
		SolicitudVo miSolicitudVo = new SolicitudVo();
		miSolicitudVo = asignar(solicitudJPA); 
		AmbienteAdapter miAmbienteAdapter = new AmbienteAdapter();
		miSolicitudVo.setIdAmbiente(miAmbienteAdapter.asignarAmbiente(solicitudJPA.getIdAmbiente()));
		UsuarioAdapter miUsuarioAdapter =  new UsuarioAdapter();
		miSolicitudVo.setIdUsuario(miUsuarioAdapter.asignarUsuario(solicitudJPA.getIdUsuario()));
		ConceptoAdapter miConceptoAdapter = new ConceptoAdapter();
		miSolicitudVo.setConcepto(miConceptoAdapter.asignar(solicitudJPA.getConcepto()));
		return miSolicitudVo;
	}
	
	
	//Preguntar donde usa este metodo
	public SolicitudVo actualizarSolicitud(Solicitud solicitudJpa) {
		SolicitudVo misolicitud = null;

		if (solicitudJpa != null && solicitudJpa.getConcepto().equals(6)) {
			System.out.println("IF ADAPTER ESTADO = A");
			ConceptoVo miConcepto=new ConceptoVo();
			miConcepto.setIdConcepto(7);
			misolicitud = new SolicitudVo();
			misolicitud.setConcepto(miConcepto);
		}
		return misolicitud;
	}



}
