package co.alertroom.ws.rest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	/**
	 * Servicio que lista las solicitudes con concepto 2 y 4 
	 * @return lista de solicitudes en el ambiente 
	 */
	@GET
	@Path("/listarSolicitudesAmbiente")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SolicitudVo> listarAmbientes() {
		List<SolicitudVo> lista = solicitudDao.obtenerListaSolicitud();
		return lista;
	}

	/**
	 * Servicio que lista las solicitudes de llaves que realiza el instructor y que deben ser atendidas
	 * @return lista de solicitudes con concepto 1
	 */
	@GET
	@Path("/listarSolicitudes")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SolicitudVo> listarSolicitudes(){
		return solicitudDao.listarSolicitudes();
	}

	/**
	 * Servicio que cambia el estado del concepto de una solicitud a llaves entregadas al instructor 
	 * @param idSolicitud
	 * @return Respuesta segun el estado de la solicitud
	 */
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

	/**
	 * Servicio que cambia el estado del concepto de una solicitud a llaves entregadas al guarda
	 * @param idSolicitud
	 * @return Respuesta segun el estado de la solicitud
	 */
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

	/**
	 * Servicios que recibe un json con la informacion de la solicitud para registrar en la bd y se guarda con concepto 1
	 * @param solicitud
	 * @return  Respuesta segun el estado de la solicitud
	 */
	@POST
	@Path("/solicitarllaves")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response solicitudLlave(Solicitud solicitud) {
		solicitud.setFechaHora( setearFechaHora() );
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

	private Date setearFechaHora() {
		LocalDateTime date = LocalDateTime.now();
		Date dateCreacionSolicitud = Date.from(date.atZone(ZoneId.of("America/Bogota")).toInstant());
		return dateCreacionSolicitud;
	}

	/**
	 * Servicio que consulta la lista de solicitudes de un ambiente en especifico
	 * @param idAmbiente
	 * @return lista de ambientes consultados
	 */
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

	//http://localhost:8080/AlertRoomWebServices/api/solicitudes/actualizarSolicitud/123
	@GET
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
		solicitud.setFechaHora( setearFechaHora());
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

	//http://localhost:8080/AlertRoomWebServices/api/solicitudes/rotacion/35
	@POST
	@Path("/rotacion/{idInstructor}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response rotarLlaves(@PathParam("idInstructor") String idInstructor, Solicitud solicitud) {
		solicitud.setFechaHora(setearFechaHora());
		try {
			String res = solicitudDao.rotarLlaves(idInstructor, solicitud);
			if (res.equals("ok")) {
				return Response.ok().build();				
			}else {
				co.jjortiz.aplicacion.Response respuesta = new co.jjortiz.aplicacion.Response();
				if (res.equals("error1")) {
					respuesta.setResponse("El ambiente ingresado no le pertenece al instructor, debé ingresar el ambiente del cual tiene las llaves");
					return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();	
				}else {
					if (res.equals("error2")) {
						respuesta.setResponse("El ambiente no esta disponble para prestamo");
						return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();	
					}else {
						return Response.status(Response.Status.NOT_ACCEPTABLE).build();
					}
				}
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
