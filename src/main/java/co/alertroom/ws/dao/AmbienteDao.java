package co.alertroom.ws.dao;

import java.util.List;

import co.alertroom.ws.adapter.AmbienteAdapter;
import co.alertroom.ws.vo.AmbienteVo;
import co.jjortiz.entidades.Ambiente;

public class AmbienteDao {

	public String registrarAmbiente(Ambiente ambiente) {
		co.jjortiz.dao.AmbientesDAO ambienteDaoJpa = new co.jjortiz.dao.AmbientesDAO();
		String res = ambienteDaoJpa.crearAmbiente(ambiente);
		ambienteDaoJpa.close();
		return res;
	}

	public List<AmbienteVo> obtenerListaAmbientes() {
		co.jjortiz.dao.AmbientesDAO ambienteDaoJpa = new co.jjortiz.dao.AmbientesDAO ();
		AmbienteAdapter miAmbienteAdapter =  new AmbienteAdapter();
		
		List<Ambiente> listadoAmbientesJpa = ambienteDaoJpa.obtenerListaAmbientes();
		List<AmbienteVo> listadoAmbientesVo = miAmbienteAdapter.asignarListaAmbiente(listadoAmbientesJpa);
		ambienteDaoJpa.close();
		return listadoAmbientesVo;
	}

}
