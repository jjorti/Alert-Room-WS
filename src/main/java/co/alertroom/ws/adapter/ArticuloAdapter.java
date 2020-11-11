package co.alertroom.ws.adapter;

import co.alertroom.ws.vo.ArticuloVo;
import co.jjortiz.entidades.Articulo;

public class ArticuloAdapter {

	public ArticuloVo asignarArticulo(Articulo idArticulo) {
		ArticuloVo miArticulo = null;
		if (idArticulo != null) {
			miArticulo = new ArticuloVo();
			miArticulo.setId(idArticulo.getId());
			miArticulo.setNombre(idArticulo.getNombre());
		}
		return miArticulo;
	}

	
}
