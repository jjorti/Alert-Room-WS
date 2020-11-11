package co.alertroom.ws.rest;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.jjortiz.dao.NovedadesDAO;
import co.jjortiz.entidades.Novedad;

@Path("/novedades")
public class NovedadService {
	
	NovedadesDAO misNovedades =  new NovedadesDAO();
	
	//http://localhost:8080/AlertRoomWebServices/api/novedades/listarNovedades/{fecha_hora}
		@GET
		@Path("/consultar/{fechaHora}")
		@Produces({MediaType.APPLICATION_JSON})
		public List<Novedad> ListarNovedades(@PathParam("fechaHora") Date fechaHora){
			System.out.println("+++++++++++++++++++++++++++++El servicio recibe esta fecha:"+fechaHora);
			List<Novedad> lista;
			lista = misNovedades.listarNovedades(fechaHora);
			return lista;
		}
	

}
