package co.alertroom.ws.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.alertroom.ws.dao.UsuarioDao;
import co.alertroom.ws.vo.UsuarioVo;



@Path("/usuarios")
public class UsuariosService {
	
	UsuarioDao miUsuarioDao = new UsuarioDao();

	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public UsuarioVo validarUsuario(UsuarioVo usuarioVo) {
		System.out.println("Estoy en validar usuario desde el servicio en el proyecto webservices");
		return miUsuarioDao.consultarLoginUsuario(usuarioVo.getId(), usuarioVo.getContrasena());	
	}
	
}
