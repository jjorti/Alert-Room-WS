package co.alertroom.ws.dao;


import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

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

	public String registrarUsuario(Usuario usuario) {
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa= new UsuariosDAO();
		String mensaje = "";
		usuario.setContrasena(DigestUtils.md5Hex(usuario.getContrasena()));
		mensaje = usuarioDaoJpa.registrarUsuario(usuario);
		usuarioDaoJpa.close();
		return mensaje;
	}

	
	
	public String actualizarUsuario(String documento, Usuario usuario) {
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa= new UsuariosDAO();
		String resp="";
		Usuario usuarioConsultado = usuarioDaoJpa.consultarUsuario(documento);
		if(usuarioConsultado!=null){
			
			if (usuarioConsultado.getContrasena().equals(usuario.getContrasena())) {
				resp=usuarioDaoJpa.actualizarUsuario(usuario);					
			}else {
				usuario.setContrasena(DigestUtils.md5Hex(usuario.getContrasena()));
				resp = usuarioDaoJpa.actualizarUsuario(usuario);
			}
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
	
	
	public List<UsuarioVo> listarUsuarios() {
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa = new co.jjortiz.dao.UsuariosDAO();
		UsuarioAdapter usuarioAdapter = new UsuarioAdapter();
		List<Usuario> listaUsuario = usuarioDaoJpa.listarUsuarios();
		List<UsuarioVo> listaUsuarioVo = usuarioAdapter.asignarListaUsuario(listaUsuario);
		usuarioDaoJpa.close();
		return listaUsuarioVo;
	}

}