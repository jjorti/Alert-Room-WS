package co.alertroom.ws.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.alertroom.ws.dao.AmbienteDao;
import co.alertroom.ws.vo.AmbienteVo;
import co.jjortiz.entidades.Ambiente;

@Path("/ambientes")
public class AmbientesService {

	AmbienteDao ambienteDao = new AmbienteDao();

	@POST
	@Path("/registrar")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response crearAmbiente(Ambiente ambiente) {
		try {
			String res = ambienteDao.registrarAmbiente(ambiente);
			if (res.equals("ok")) {
				return Response.ok().entity(ambiente).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();			
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}


	@GET
	@Path("/listar")
	@Produces({MediaType.APPLICATION_JSON})
	public List<AmbienteVo> listarAmbientes() {
		List<AmbienteVo> lista = ambienteDao.obtenerListaAmbientes();
		return lista;
	}

}
