package co.alertroom.ws.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.jjortiz.dao.ArticulosDAO;
import co.jjortiz.dao.InventarioAmbienteDAO;
import co.jjortiz.vo.InformacionInventario;
@Path("/inventario-ambiente")
public class InventarioAmbienteService {
	
	InventarioAmbienteDAO inventarioAmbienteDAO = new InventarioAmbienteDAO();
	ArticulosDAO articulosDAO  = new ArticulosDAO();
 	
	@POST
	@Path("/{idambiente}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response crearInventarioAmbiente(@PathParam("idambiente") String idAmbiente, List<InformacionInventario> listaArticulos) {
		try {
//			String res =  articulosDAO.crearArticulo(listaArticulos);
			String res = inventarioAmbienteDAO.registrarIventarioAmbiente(idAmbiente, listaArticulos);
			if (res.equals("ok")) {
				return Response.status(Response.Status.OK).build();
				
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			
		}
		
		
		
	}

}
