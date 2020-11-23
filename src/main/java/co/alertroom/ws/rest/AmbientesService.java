package co.alertroom.ws.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Path("/consultar/{idambiente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response consultarAmbiente(@PathParam("idambiente") String idAmbiente) {
		try {
			AmbienteVo miAmbiente = ambienteDao.consultarAmbiente(idAmbiente);
			if (miAmbiente != null ) {
				
				return Response.ok().entity(miAmbiente).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/instructor-consultar/{idambiente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response consultarAmbienteInstructor(@PathParam("idambiente") String idAmbiente) {
		try {
			AmbienteVo miAmbiente = ambienteDao.consultarAmbienteInstructor(idAmbiente);
			if (miAmbiente != null ) {
				
				return Response.ok().entity(miAmbiente).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("/{idambiente}")
	public Response eliminarAmbiente(@PathParam("idambiente") String idAmbiente) {
		try {
			System.out.println(idAmbiente);
			String res = ambienteDao.eliminarAmbiente(idAmbiente);
			if (res.equals("ok")) {
				return Response.ok().build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).header("header", res).build();
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@PUT
	@Path("/actualizar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response actualizarAmbiente(Ambiente ambiente) {
		
		try {
			Ambiente miAmbiente = ambienteDao.actualizarAmbiente(ambiente);
			if (miAmbiente != null) {
				return Response.ok().entity(ambiente).build();				
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/listar-guardas")
	@Produces({MediaType.APPLICATION_JSON})
	public List<AmbienteVo> consultarAmbientesGuarda(){
		List<AmbienteVo> listaAmbienteGuarda = ambienteDao.obtenerListaAmbientes();
		return listaAmbienteGuarda;	
	}
	
	
}
