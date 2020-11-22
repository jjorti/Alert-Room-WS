package co.alertroom.ws.rest;

import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import co.alertroom.ws.dao.SolicitudDao;
import co.alertroom.ws.vo.SolicitudVo;
import co.jjortiz.entidades.Solicitud;

@Path("/solicitudes")
public class SolicitudService {

	SolicitudDao solicitudDao = new SolicitudDao();
	
	@GET
	@Path("/listarSolicitudesAmbiente")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SolicitudVo> listarAmbientes() {
		List<SolicitudVo> lista = solicitudDao.obtenerListaSolicitud();
		return lista;
	}
	
	@GET
	@Path("/listarSolicitudes")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SolicitudVo> listarSolicitudes(){
		return solicitudDao.listarSolicitudes();
	}
	
	@GET
	@Path("/entregaLlavesInstructor/{idSolicitud}")
	public Response entregarLlavesInstructor(@PathParam("idSolicitud") Integer idSolicitud) {
		try {
			String res =  solicitudDao.entregaLlavesInstructor(idSolicitud);
			
			if (res.equals("ok")) {
				return Response.status(Response.Status.OK).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/devolverLlavesGuarda/{idSolicitud}")
	public Response devolverLlavesGuarda(@PathParam("idSolicitud") Integer idSolicitud) {
		try {
			String res =  solicitudDao.devolverLlavesGuarda(idSolicitud);
			
			if (res.equals("ok")) {
				return Response.status(Response.Status.OK).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GET
	@Path("/consultarAmbiente/{idambiente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response consultarSolicitudesAmbiente(@PathParam("idambiente") String idAmbiente) {
		try {
			List<SolicitudVo> miSolicitud =  solicitudDao.consultarSolicitudAmbiente(idAmbiente);
			if (miSolicitud!= null ) {
				return Response.ok().entity(miSolicitud).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			
		} catch (Exception e) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/solicitarllaves")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response solicitudLlave(Solicitud solicitud) {
		solicitud.setFechaHora( new Date());
		try {
			String res = solicitudDao.solicitudLLaves(solicitud);
			if (res.equals("ok")) {
				return Response.ok().build();				
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();	
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//http://localhost:8080/AlertRoomWebServices/api/solicitudes/actualizarSolicitud/123
	@PUT
	@Path("/atenderSolicitud/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response actualizarSolicitud(@PathParam("id") int idSolicitud) {

		try {
			String res = solicitudDao.actualizarSolicitud(idSolicitud);
			if (res.equals("ok")) {
				return Response.ok().build();				
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();	
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//http://localhost:8080/AlertRoomWebServices/api/solicitudes/listarNovedades
	@GET
	@Path("/listarNovedades")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SolicitudVo> listarNovedades() {
		List<SolicitudVo> lista = solicitudDao.obtenerListaNovedades();
		return lista;
	}
	
	//http://localhost:8080/AlertRoomWebServices/api/solicitudes/solicitarNovedad
	@POST
	@Path("/solicitarNovedad")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response solicitudNovedad(Solicitud solicitud) {
		solicitud.setFechaHora( new Date());
		try {
			String res = solicitudDao.solicitudNovedad(solicitud);
			if (res.equals("ok")) {
				return Response.ok().build();				
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();	
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
