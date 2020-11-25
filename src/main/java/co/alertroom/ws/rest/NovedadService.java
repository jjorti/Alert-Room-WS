package co.alertroom.ws.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.alertroom.ws.dao.NovedadDao;
import co.alertroom.ws.vo.AmbienteVo;
import co.alertroom.ws.vo.NovedadVo;
import co.jjortiz.entidades.Novedad;

@Path("/novedades")
public class NovedadService {

	NovedadDao misNovedades =  new NovedadDao();

	//http://localhost:8080/AlertRoomWebServices/api/novedades/registrarNovedad
	@POST
	@Path("/registrarNovedad")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response registrarNovedad(Novedad novedad){
		novedad.setFechaHora(setearFechaHora());
		try {
			String res = misNovedades.registroNovedad(novedad);
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
		Date dateCreacionNovedad = Date.from(date.atZone(ZoneId.of("America/Bogota")).toInstant());
		return dateCreacionNovedad;
	}

	//http://localhost:8080/AlertRoomWebServices/api/novedades/listarNovedades
	@GET
	@Path("/listarNovedades")
	@Produces({MediaType.APPLICATION_JSON})
	public List<NovedadVo> ListarNovedades(){
		List<NovedadVo> listaNovedades = misNovedades.listarNovedades();
		return listaNovedades;
	}

	//http://localhost:8080/AlertRoomWebServices/api/novedades/ConsultarNovedadesPorFechaAmbiente/14?fechaInicial=2020-11-17 00:00&fechaFinal=2020-11-18 23:59
	@GET
	@Path("/ConsultarNovedadesPorFechaAmbiente/{numAmbiente}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<NovedadVo> ListarNovedadesFechaAmbiente(
			@QueryParam("fechaInicial") String fechaInicial, 
			@QueryParam("fechaFinal") String fechaFinal,
			@PathParam("numAmbiente") String idAmbiente)
	{
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<NovedadVo> lista;
		try {
			Date dateInicio,dateFinal;
			dateInicio =  formatoFecha.parse(fechaInicial);
			dateFinal =  formatoFecha.parse(fechaFinal);
			lista = misNovedades.listarNovedadesporfechaAmbiente(dateInicio,dateFinal,idAmbiente);
		} catch (ParseException e) {
			lista = null;
		}
		return lista;
	}

	//http://localhost:8080/AlertRoomWebServices/api/novedades/ConsultarNovedadesPorFecha?fechaInicial=2020-11-06 07:56&fechaFinal=2020-11-14 16:35
	@GET
	@Path("/ConsultarNovedadesPorFecha")
	@Produces({MediaType.APPLICATION_JSON})
	public List<NovedadVo> ListarNovedadesFecha(
			@QueryParam("fechaInicial") String fechaInicial, 
			@QueryParam("fechaFinal") String fechaFinal){
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<NovedadVo> lista;
		try {
			Date dateInicio,dateFinal;
			dateInicio =  formatoFecha.parse(fechaInicial);
			dateFinal =  formatoFecha.parse(fechaFinal);
			lista = misNovedades.listarNovedadesporfecha(dateInicio,dateFinal);
		} catch (ParseException e) {
			lista = null;
		}
		return lista;
	}

	//http://localhost:8080/AlertRoomWebServices/api/novedades/15
	@GET
	@Path("{numAmbiente}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<NovedadVo> ListarNovedadesPorAmbiente(@PathParam("numAmbiente") String idAmbiente){
		List<NovedadVo> listaNovedades = misNovedades.listarNovedadesPorAmbiente(idAmbiente);
		return listaNovedades;
	}

	//http://localhost:8080/AlertRoomWebServices/api/novedades/listarAmbientes
	// servicio para obtener la lista de ambientes en la interfaz de novedades, si finalmente 
	//se va a realizar con la lista de seleccion 
	@GET
	@Path("/listarAmbientes")
	@Produces({MediaType.APPLICATION_JSON})
	public List<AmbienteVo> listarAmbientes() {
		List<AmbienteVo> lista = misNovedades.obtenerListaAmbientes();
		return lista;
	}

}
