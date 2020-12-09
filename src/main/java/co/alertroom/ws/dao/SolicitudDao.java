package co.alertroom.ws.dao;

import java.util.List;

import co.alertroom.ws.adapter.SolicitudAdapter;
import co.alertroom.ws.vo.SolicitudVo;
import co.jjortiz.dao.AmbientesDAO;
import co.jjortiz.dao.SolicitudesDAO;
import co.jjortiz.entidades.Ambiente;
import co.jjortiz.entidades.Concepto;
import co.jjortiz.entidades.Solicitud;

public class SolicitudDao{

	public String solicitudLLaves(Solicitud solicitud) {
		String res = "error";
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		AmbientesDAO ambientesDaoJpa = new AmbientesDAO();
		Ambiente miAmbiente = ambientesDaoJpa.consultarAmbiente(solicitud.getIdAmbiente().getId());
		if (miAmbiente != null && !miAmbiente.getEstado().equals("I")) {			
			miAmbiente.setOcupado("S");
			solicitud.setIdAmbiente(miAmbiente);
			solicitud.setObservaciones("Llaves solicitadas por instructor");
			res =solicitudesDaoJpa.solicitudLlaves(solicitud);
			solicitudesDaoJpa.close();
			return res;
		}else {
			return res;
		}
	}

	public List<SolicitudVo> obtenerListaSolicitud() {
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		SolicitudAdapter miSolicitudAdapter = new SolicitudAdapter();
		List<Solicitud> listaSolicitudesJPA =  solicitudesDaoJpa.obtenerListaSolicitud();
		List<SolicitudVo> listaSolicitudes = miSolicitudAdapter.asignarListaSolicitudesGuarda(listaSolicitudesJPA);
		solicitudesDaoJpa.close();
		return listaSolicitudes;
	}

	public List<SolicitudVo> consultarSolicitudAmbiente(String idAmbiente) {
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		SolicitudAdapter miSolicitudAdapter = new SolicitudAdapter();
		List<Solicitud> solicitudJPA = solicitudesDaoJpa.obtenerSolicitudesAmbiente(idAmbiente);
		List<SolicitudVo> solicitudVo =  miSolicitudAdapter.asignarListaSolicitudesGuarda(solicitudJPA);
		solicitudesDaoJpa.close();
		return solicitudVo;
	}
	
	public String solicitudNovedad(Solicitud solicitud) {
		String res = "error";
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		AmbientesDAO ambientesDaoJpa = new AmbientesDAO();
		Ambiente miAmbiente = ambientesDaoJpa.consultarAmbiente(solicitud.getIdAmbiente().getId());

		if (miAmbiente != null && miAmbiente.getOcupado().equals("S")) {	
			solicitud.setIdAmbiente(miAmbiente);
			res =solicitudesDaoJpa.solicitudNoveda(solicitud);
			solicitudesDaoJpa.close();
			return res;
		}else {
			return res;
		}
	}
	
	public List<SolicitudVo> obtenerListaNovedades() {
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		SolicitudAdapter miSolicitudAdapter = new SolicitudAdapter();
		List<Solicitud> listaSolicitudesJPA =  solicitudesDaoJpa.obtenerListaNovedades(6,7);
		List<SolicitudVo> listaSolicitudes = miSolicitudAdapter.asignarListaSolicitudesGuarda(listaSolicitudesJPA);
		solicitudesDaoJpa.close();
		return listaSolicitudes;
	}
	
	//este es el de actualizar el concepto en la solicitud para atender y finalizar
	public String actualizarSolicitud(int idSolicitud) {
		String resp="";
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		Solicitud solicitudJpa=solicitudesDaoJpa.consultarSolicitud(idSolicitud);
		if (solicitudJpa.getConcepto().getIdConcepto()==6) {
			Concepto miConcepto= new Concepto();
			miConcepto.setIdConcepto(7);
			solicitudJpa.setConcepto(miConcepto);
			String nombreConcepto="Atender Novedad";
			solicitudJpa.setObservaciones(nombreConcepto);
			resp=solicitudesDaoJpa.actualizarSolicitud(solicitudJpa);
			solicitudesDaoJpa.close();
		} else{
			if (solicitudJpa.getConcepto().getIdConcepto()==7) {
				Concepto miConcepto= new Concepto();
				miConcepto.setIdConcepto(8);
				solicitudJpa.setConcepto(miConcepto);
				String nombreConcepto="Finalizar Novedad";
				solicitudJpa.setObservaciones(nombreConcepto);
				resp=solicitudesDaoJpa.actualizarSolicitud(solicitudJpa);
				solicitudesDaoJpa.close();
			}
			return resp;
		}	
		return resp;
		
	}

	public List<SolicitudVo> listarSolicitudes() {
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		SolicitudAdapter miSolicitudAdapter = new SolicitudAdapter();
		List<Solicitud> listaSolicitudesJPA =  solicitudesDaoJpa.obtenerListadoSolicitudes();
		List<SolicitudVo> listaSolicitudes = miSolicitudAdapter.asignarListaSolicitudesGuarda(listaSolicitudesJPA);
		solicitudesDaoJpa.close();
		return listaSolicitudes;

	}

	public String entregaLlavesInstructor(int idSolicitud) {
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		Solicitud solicitudJpa = solicitudesDaoJpa.consultarSolicitud(idSolicitud);
		solicitudJpa.setObservaciones("Llaves en ambiente entregadas por guarda");
		Concepto miConcepto = new Concepto();
		miConcepto.setIdConcepto(4);
		solicitudJpa.setConcepto(miConcepto);
		String res = solicitudesDaoJpa.actualizarSolicitud(solicitudJpa);
		solicitudesDaoJpa.close();
		return res;
	}

	public String devolverLlavesGuarda(Integer idSolicitud) {
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		Solicitud solicitudJpa = solicitudesDaoJpa.consultarSolicitud(idSolicitud);
		solicitudJpa.setObservaciones("Entregó llaves al guarda");
		
		AmbientesDAO ambientesDaoJpa = new AmbientesDAO();
		Ambiente miAmbiente = ambientesDaoJpa.consultarAmbiente(solicitudJpa.getIdAmbiente().getId());
		miAmbiente.setOcupado("N");
		solicitudJpa.setIdAmbiente(miAmbiente);
		
		Concepto miConcepto = new Concepto();
		miConcepto.setIdConcepto(2);
		solicitudJpa.setConcepto(miConcepto);
		
		String res = solicitudesDaoJpa.actualizarSolicitud(solicitudJpa);
		solicitudesDaoJpa.close();
		return res;
	}

	public String rotacionLlaves(int idSolicitud, Solicitud solicitud) {
		String res = "error";
		res=devolverLlavesGuarda(idSolicitud);
		if (res.equals("ok")) {
			res=solicitudLLaves(solicitud);
			return res;
		}
		return res;
	}

	public String rotarLlaves(String idInstructor, Solicitud solicitud) {
		String res = "error";
		SolicitudesDAO solicitudDAO = new SolicitudesDAO();
		Solicitud solicitudInstructor = solicitudDAO.consultarSolicitudInstructor(idInstructor, solicitud.getIdAmbiente().getId());
		if (solicitudInstructor != null) {
			res = devolverLlavesGuarda(solicitudInstructor.getIdSolicitud());
			if (res.equals("ok")) {
				res=entregarLlavesRotacion(solicitud);
				solicitudDAO.close();
				return res;
			}			
		}else {
			res = "error1";
		}
		return res;
	}

	private String entregarLlavesRotacion(Solicitud solicitud) {
		String res = "";
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		AmbientesDAO ambientesDaoJpa = new AmbientesDAO();
		Ambiente miAmbiente = ambientesDaoJpa.consultarAmbiente(solicitud.getIdAmbiente().getId());
		if (miAmbiente != null && !miAmbiente.getEstado().equals("I")) {			
			miAmbiente.setOcupado("S");
			solicitud.setIdAmbiente(miAmbiente);
			solicitud.setObservaciones("Llaves en ambiente entregadas por rotacion");
			Concepto miConcepto = new Concepto();
			miConcepto.setIdConcepto(4);
			solicitud.setConcepto(miConcepto);
			res =solicitudesDaoJpa.solicitudLlaves(solicitud);
			solicitudesDaoJpa.close();
			return res;
		}else {
			return "error2";
		}
	}
	


}
