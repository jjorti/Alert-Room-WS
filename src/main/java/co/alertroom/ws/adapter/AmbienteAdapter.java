package co.alertroom.ws.adapter;

import java.util.ArrayList;
import java.util.List;

import co.alertroom.ws.vo.AmbienteVo;
import co.jjortiz.entidades.Ambiente;

public class AmbienteAdapter {
	
	public AmbienteVo asignarAmbiente(co.jjortiz.entidades.Ambiente ambiente) {
		AmbienteVo miAmbiente = null;
		
		
		if (ambiente!=null) {
			miAmbiente = new AmbienteVo();
			miAmbiente.setId(ambiente.getId());
			miAmbiente.setNombre(ambiente.getNombre());
			miAmbiente.setNumero(ambiente.getNumero());
			miAmbiente.setEstado(ambiente.getEstado());
			miAmbiente.setOcupado(ambiente.getOcupado());
			
			InventarioAmbienteAdapter miInventarioAmbienteAdapter =  new InventarioAmbienteAdapter();
			
			miAmbiente.setListaInventarioAmbiente(miInventarioAmbienteAdapter.asignarListaInventarioAmbiente(ambiente.getListaInventarioAmbiente()));
			
			
			
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
}
