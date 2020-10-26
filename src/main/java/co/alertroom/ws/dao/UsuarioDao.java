package co.alertroom.ws.dao;

import co.alertroom.ws.adapter.UsuarioAdapter;
import co.alertroom.ws.vo.UsuarioVo;
import co.jjortiz.entidades.Usuario;

public class UsuarioDao {

	public UsuarioVo consultarLoginUsuario(String id, String contrasena) {
		co.jjortiz.dao.UsuariosDAO usuarioDaoJpa = new co.jjortiz.dao.UsuariosDAO();
		Usuario usuarioJpa = usuarioDaoJpa.consultarLoginUsuario(id,contrasena);
		UsuarioAdapter usuarioAdapter = new UsuarioAdapter();
		UsuarioVo usuarioVo = usuarioAdapter.asignarUsuario(usuarioJpa);
		usuarioDaoJpa.close();
		return usuarioVo;
	}

}
