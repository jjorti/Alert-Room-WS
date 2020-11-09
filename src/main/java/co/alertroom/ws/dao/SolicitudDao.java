package co.alertroom.ws.dao;

import java.util.List;

import co.jjortiz.dao.AmbientesDAO;
import co.jjortiz.dao.SolicitudesDAO;
import co.jjortiz.entidades.Ambiente;
import co.jjortiz.entidades.Solicitud;

public class SolicitudDao{
	
	
//	public String solicitarLlaves(String idAmbiente) {
//		String res = "error";
//		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
//		AmbientesDAO ambientesDaoJpa = new AmbientesDAO();
//		Ambiente miAmbiente = ambientesDaoJpa.consultarAmbiente(idAmbiente);
//		if (miAmbiente != null) {
//			miAmbiente.setOcupado("S");
//			return solicitudesDaoJpa.solicitarLlaves(miAmbiente);
//		}else {
//			return res;
//		}
//	}

	public String solicitudLLaves(Solicitud solicitud) {
		String res = "error";
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		AmbientesDAO ambientesDaoJpa = new AmbientesDAO();
		Ambiente miAmbiente = ambientesDaoJpa.consultarAmbiente(solicitud.getIdAmbiente().getId());
		if (miAmbiente != null) {			
			System.out.println(miAmbiente.getOcupado());
			miAmbiente.setOcupado("S");
			solicitud.setIdAmbiente(miAmbiente);
			System.out.println(solicitud.getIdAmbiente().getOcupado()); 
			return solicitudesDaoJpa.solicitudLlaves(solicitud);
		}else {
			return res;
		}
	}

	public List<Solicitud> obtenerListaSolicitud() {
		SolicitudesDAO solicitudesDaoJpa = new SolicitudesDAO();
		List<Solicitud> listaSolicitudes =  solicitudesDaoJpa.obtenerListaSolicitud();
		
		return listaSolicitudes;
	}

}
