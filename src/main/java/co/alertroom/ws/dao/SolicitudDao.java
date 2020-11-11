package co.alertroom.ws.dao;

import java.util.List;

import co.alertroom.ws.adapter.SolicitudAdapter;
import co.alertroom.ws.vo.SolicitudVo;
import co.jjortiz.dao.AmbientesDAO;
import co.jjortiz.dao.SolicitudesDAO;
import co.jjortiz.entidades.Ambiente;
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

}
