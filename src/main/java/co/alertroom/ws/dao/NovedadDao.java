package co.alertroom.ws.dao;

import java.util.Date;
import java.util.List;

import co.alertroom.ws.adapter.AmbienteAdapter;
import co.alertroom.ws.adapter.NovedadAdapter;
import co.alertroom.ws.vo.AmbienteVo;
import co.alertroom.ws.vo.NovedadVo;
import co.jjortiz.dao.AmbientesDAO;
import co.jjortiz.dao.NovedadesDAO;
import co.jjortiz.entidades.Ambiente;
import co.jjortiz.entidades.Novedad;
import co.jjortiz.entidades.Solicitud;



public class NovedadDao {

	public String registroNovedad(Novedad novedad) {
		NovedadesDAO novedadesDaoJpa= new NovedadesDAO();
		return novedadesDaoJpa.registrarNovedad(novedad);
	}

	public List<NovedadVo> listarNovedades() {
		NovedadesDAO novedadesDaoJpa = new NovedadesDAO();
		NovedadAdapter miNovedadAdapter = new NovedadAdapter();
		List<Novedad> listaNovedadesJPA =  novedadesDaoJpa.listarNovedades();
		List<NovedadVo> listaNovedades = miNovedadAdapter.asignarListaNovedades(listaNovedadesJPA);
		novedadesDaoJpa.close();
		return listaNovedades;
	}

	public List<NovedadVo> listarNovedadesPorAmbiente(String idAmbiente) {

		NovedadesDAO novedadesDaoJpa = new NovedadesDAO();
		NovedadAdapter miNovedadAdapter = new NovedadAdapter();
		AmbienteAdapter miAmbienteAdapter = new AmbienteAdapter();
		Ambiente numAmb=miAmbienteAdapter.asignarId(idAmbiente);
		List<Novedad> listaNovedadesJPA =  novedadesDaoJpa.consultarListaNovedadesAmbiente(numAmb);
		List<NovedadVo> listaNovedades = miNovedadAdapter.asignarListaNovedades(listaNovedadesJPA);
		novedadesDaoJpa.close();
		return listaNovedades;
	}

	public List<NovedadVo> listarNovedadesporfecha(Date fechaHorai,Date fechaHoraf) {
		NovedadesDAO novedadesDaoJpa = new NovedadesDAO();
		List<Novedad> listaNovedadesJpa =  novedadesDaoJpa.listarNovedadesporfecha(fechaHorai,fechaHoraf);
		NovedadAdapter miNovedadAdapter = new NovedadAdapter();
		List<NovedadVo> listaNovedadesVo = miNovedadAdapter.asignarListaNovedades(listaNovedadesJpa);
		novedadesDaoJpa.close();
		return listaNovedadesVo;
	}

	public List<NovedadVo> listarNovedadesporfechaAmbiente(Date fechaHorai, Date fechaHoraf, String idAmbiente) {
		NovedadesDAO novedadesDaoJpa = new NovedadesDAO();
		List<Novedad> listaNovedadesJpa =  novedadesDaoJpa.listarNovedadesporfechaAmbiente(fechaHorai,fechaHoraf,idAmbiente);
		NovedadAdapter miNovedadAdapter = new NovedadAdapter();
		List<NovedadVo> listaNovedadesVo = miNovedadAdapter.asignarListaNovedades(listaNovedadesJpa);
		novedadesDaoJpa.close();
		return listaNovedadesVo;
	}

	public List<AmbienteVo> obtenerListaAmbientes() {
		AmbientesDAO ambienteDaoJpa = new AmbientesDAO ();
		AmbienteAdapter miAmbienteAdapter =  new AmbienteAdapter();
		List<Ambiente> listadoAmbientesJpa = ambienteDaoJpa.obtenerListaAmbientes();
		List<AmbienteVo> listadoAmbientesVo = miAmbienteAdapter.asignarListaAmbiente(listadoAmbientesJpa);
		ambienteDaoJpa.close();
		return listadoAmbientesVo;
	}



	public String notificarNovedad(Solicitud solicitud) {
		String res = "error";
		NovedadesDAO novedadesDaoJpa = new NovedadesDAO();
		AmbientesDAO ambientesDaoJpa = new AmbientesDAO();
		Ambiente miAmbiente = ambientesDaoJpa.consultarAmbiente(solicitud.getIdAmbiente().getId());
		if (miAmbiente != null && !miAmbiente.getEstado().equals("I")) {			
			miAmbiente.setOcupado("S");
			solicitud.setIdAmbiente(miAmbiente);
			res =novedadesDaoJpa.notificarNovedad(solicitud);
			novedadesDaoJpa.close();
			return res;
		}else {
			novedadesDaoJpa.close();
			return res;
		}
	}

}
