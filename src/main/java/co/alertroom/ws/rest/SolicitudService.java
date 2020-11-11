package co.alertroom.ws.rest;

import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	@Path("/listarSolicitudes")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SolicitudVo> listarAmbientes() {
		List<SolicitudVo> lista = solicitudDao.obtenerListaSolicitud();
		return lista;
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
}
