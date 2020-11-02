package co.alertroom.ws.adapter;

import java.util.ArrayList;
import java.util.List;

import co.alertroom.ws.vo.InventarioAmbienteVo;
import co.jjortiz.entidades.InventarioAmbiente;

public class InventarioAmbienteAdapter {

	public List<InventarioAmbienteVo> asignarListaInventarioAmbiente(List<InventarioAmbiente> list) {
		List<InventarioAmbienteVo> miListaInventarioAmbiente = new ArrayList<InventarioAmbienteVo>();
		
		for (InventarioAmbiente inventarioAmbienteVo : list) {
			miListaInventarioAmbiente.add(asignarInventario(inventarioAmbienteVo));
		}
		return miListaInventarioAmbiente;
	}

	private InventarioAmbienteVo asignarInventario(InventarioAmbiente inventarioAmbiente) {

		InventarioAmbienteVo miInventarioAmbiente = null;
		if (inventarioAmbiente != null) {
			miInventarioAmbiente   = new InventarioAmbienteVo();
			miInventarioAmbiente.setCantidad(inventarioAmbiente.getCantidad());
			miInventarioAmbiente.setIdambienteinventario(inventarioAmbiente.getIdambienteinventario());
			
			ArticuloAdapter miArticuloAdapter = new ArticuloAdapter();
			miInventarioAmbiente.setIdArticulo(miArticuloAdapter.asignarArticulo(inventarioAmbiente.getIdArticulo()));
			System.out.println();
		}
		
		return miInventarioAmbiente;
	}

}
