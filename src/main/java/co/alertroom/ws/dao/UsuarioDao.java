package co.alertroom.ws.dao;


import java.util.List;

import co.alertroom.ws.adapter.UsuarioAdapter;
import co.alertroom.ws.vo.UsuarioVo;
import co.jjortiz.dao.UsuariosDAO;
import co.jjortiz.entidades.Usuario;


public class UsuarioDao {

	public UsuarioVo consultarLoginUsuario(String id, String contrasena) {
		
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa= new UsuariosDAO();
		Usuario usuarioJpa = usuarioDaoJpa.consultarLoginUsuario(id,contrasena);
		UsuarioAdapter usuarioAdapter = new UsuarioAdapter();
		UsuarioVo usuarioVo = usuarioAdapter.asignarUsuario(usuarioJpa);
		usuarioDaoJpa.close();
		return usuarioVo;
	}

	public String registrarUsuario(UsuarioVo usuarioVo) {
		
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa= new UsuariosDAO();
		String mensaje="";
		UsuarioAdapter usuarioAdapter = new UsuarioAdapter();
		Usuario usuarioJpa = usuarioAdapter.obtenerUsuario(usuarioVo);
		mensaje=usuarioDaoJpa.registrarUsuario(usuarioJpa);
		
		usuarioDaoJpa.close();
		return mensaje;
	}

	public String actualizarUsuario(String documento, Usuario usuario) {
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa= new UsuariosDAO();
		String resp="";
		if(usuarioDaoJpa.consultarUsuario(documento)!=null){
			UsuarioAdapter usuarioAdapter = new UsuarioAdapter();
			Usuario miUsuario = usuarioAdapter.actualizarUsuario(usuario);
			resp=usuarioDaoJpa.actualizarUsuario(miUsuario);
			
		}
		usuarioDaoJpa.close();
		return resp;
	}

	public UsuarioVo consultarUsuario(String documento) {
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa= new UsuariosDAO();
		
		Usuario usuarioJpa=usuarioDaoJpa.consultarUsuario(documento);
		UsuarioAdapter usuarioAdapter = new UsuarioAdapter();
		UsuarioVo usuarioVo = usuarioAdapter.asignarUsuario(usuarioJpa);
	
		usuarioDaoJpa.close();
		return usuarioVo;
	}
	
	

	public UsuarioVo actualizarEstado(String documento) {
		
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa= new UsuariosDAO();
		
		Usuario usuarioJpa=usuarioDaoJpa.consultarUsuario(documento);
		UsuarioAdapter usuarioAdapter = new UsuarioAdapter();
		UsuarioVo usuarioVo=usuarioAdapter.actualizarEstado(usuarioJpa);
		Usuario usuario=usuarioAdapter.obtenerUsuario(usuarioVo);
		usuarioDaoJpa.actualizarUsuario(usuario);
		usuarioDaoJpa.close();
		return usuarioVo;
		
	}
	
	
	public List<Usuario> listarUsuarios() {
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa = new co.jjortiz.dao.UsuariosDAO();
		usuarioDaoJpa.close();
		return usuarioDaoJpa.listarUsuarios();
	}

}