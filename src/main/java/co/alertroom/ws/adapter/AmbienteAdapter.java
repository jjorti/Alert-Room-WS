package co.alertroom.ws.adapter;

import java.util.ArrayList;
import java.util.List;

import co.alertroom.ws.vo.AmbienteVo;
import co.jjortiz.entidades.Ambiente;

public class AmbienteAdapter {
	
	public AmbienteVo asignarAmbiente(co.jjortiz.entidades.Ambiente ambiente) {
		AmbienteVo miAmbiente = null;
		if (ambiente!=null) {
			miAmbiente = asignar(ambiente);
			InventarioAmbienteAdapter miInventarioAmbienteAdapter =  new InventarioAmbienteAdapter();
			miAmbiente.setListaInventarioAmbiente(miInventarioAmbienteAdapter.asignarListaInventarioAmbiente(ambiente.getListaInventarioAmbiente(), miAmbiente.getId()));
		}
		return miAmbiente;
	}

	public List<AmbienteVo> asignarListaAmbiente(List<Ambiente> listadoAmbientesJpa) {
		List<AmbienteVo> lista =  new ArrayList<AmbienteVo>();
		for (Ambiente ambiente : listadoAmbientesJpa) {
			lista.add(asignarAmbiente(ambiente));
		}
		return lista;
	}

	public AmbienteVo asignarAmbienteInstructor(Ambiente miAmbienteJPA) {
		AmbienteVo miAmbiente = null;
		if (miAmbienteJPA!=null) {
			miAmbiente = asignar(miAmbienteJPA);
			InventarioAmbienteAdapter miInventarioAmbienteAdapter =  new InventarioAmbienteAdapter();
			miAmbiente.setListaInventarioAmbiente(miInventarioAmbienteAdapter.asignarListaInventarioAmbiente(miAmbienteJPA.getListaInventarioAmbiente(), miAmbiente.getId()));
			SolicitudAdapter miSolicitudAdapter =  new SolicitudAdapter();
			miAmbiente.setListaSolicitudes(miSolicitudAdapter.asignarListaSolicitudes(miAmbienteJPA.getListaSolicitudes()));
		}
		return miAmbiente;
	}

	public AmbienteVo asignar(Ambiente miAmbienteJPA) {
		AmbienteVo miAmbiente = new AmbienteVo();
		miAmbiente.setId(miAmbienteJPA.getId());
		miAmbiente.setNombre(miAmbienteJPA.getNombre());
		miAmbiente.setEstado(miAmbienteJPA.getEstado());
		miAmbiente.setOcupado(miAmbienteJPA.getOcupado());
		return miAmbiente;
	}
	
	public Ambiente asignar2(AmbienteVo miAmbienteJPA) {
		Ambiente miAmbiente = new Ambiente();
		miAmbiente.setId(miAmbienteJPA.getId());
		return miAmbiente;
	}

	public Ambiente asignarId(String id) {
		Ambiente miAmbiente = new Ambiente();
		miAmbiente.setId(id);
		return miAmbiente;
	}
}
