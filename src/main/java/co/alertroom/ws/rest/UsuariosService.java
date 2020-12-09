package co.alertroom.ws.rest;

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

import org.apache.commons.codec.digest.DigestUtils;

import co.alertroom.ws.dao.UsuarioDao;
import co.alertroom.ws.vo.UsuarioVo;
import co.jjortiz.aplicacion.EnvioEmail;
import co.jjortiz.entidades.Usuario;



@Path("/usuarios")
public class UsuariosService {
	
	UsuarioDao miUsuarioDao = new UsuarioDao();

	//http://localhost:8080/AlertRoomWebServices/api/usuarios/login
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response validarUsuario(UsuarioVo usuarioVo) {
		UsuarioVo miUsuarioVo=miUsuarioDao.consultarLoginUsuario(usuarioVo.getId(), usuarioVo.getContrasena());
		try {
			if (miUsuarioVo!=null) {
				return Response.status(Response.Status.OK).entity(miUsuarioVo).build();
			}
			else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			//return miUsuarioDao.consultarLoginUsuario(usuarioVo.getId(), usuarioVo.getContrasena());
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
			
	}
	
	//http://localhost:8080/AlertRoomWebServices/api/usuarios/registrarPersona
	@POST
	@Path("/registrarPersona")
	@Consumes({MediaType.APPLICATION_JSON})
	//@Produces({MediaType.APPLICATION_JSON})
	public Response registrarPersona(Usuario usuario) {
		try {
			String res = miUsuarioDao.registrarUsuario(usuario);
			if (res.equals("ok")) {
				EnvioEmail miEnvioEmail = new EnvioEmail();
				miEnvioEmail.sendEmail(usuario);
				return Response.ok().build();
				//return Response.status(Response.Status.OK).entity(persona).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	//http://localhost:8080/AlertRoomWebServices/api/usuarios/123
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response actualizarUsuario(@PathParam("id") String documento, Usuario usuario) {

		try {
			String resp=miUsuarioDao.actualizarUsuario(documento,usuario);
			if(resp.equals("Persona Actualizada!")) {
				return Response.ok().build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	//http://localhost:8080/AlertRoomWebServices/api/usuarios/123
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public UsuarioVo getPersonaId(@PathParam("id") String documento) {
		UsuarioVo persona = miUsuarioDao.consultarUsuario(documento);
		if (persona == null) {
			persona = new UsuarioVo();
		}
		return persona;
	}
	
	
	
	//http://localhost:8080/AlertRoomWebServices/api/usuarios/actualizarEstado/123
	
	@PUT
	@Path("/actualizarEstado/{id}")
	public UsuarioVo actualizarEstado(@PathParam("id") String documento) {
		UsuarioVo persona = miUsuarioDao.actualizarEstado(documento);
		if (persona == null) {
			persona = new UsuarioVo();
		}
		return persona;
	}
	
	
	//http://localhost:8080/AlertRoomWebServices/api/usuarios/listarUsuarios
	
	@GET
	@Path("/listarUsuarios")
	@Produces({MediaType.APPLICATION_JSON})
	public List<UsuarioVo> ListarUsuarios(){
		List<UsuarioVo> lista = miUsuarioDao.listarUsuarios();
		return lista;
	}
		
}
