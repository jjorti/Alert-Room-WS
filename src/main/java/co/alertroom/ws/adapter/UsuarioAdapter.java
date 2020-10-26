package co.alertroom.ws.adapter;

import co.alertroom.ws.vo.UsuarioVo;


public class UsuarioAdapter {

	public UsuarioVo asignarUsuario(co.jjortiz.entidades.Usuario usuario) {
		UsuarioVo miUsuario = null;
		if (usuario != null) {
			miUsuario = new UsuarioVo();
			miUsuario.setId(usuario.getId());
			miUsuario.setNombres(usuario.getNombres());
			miUsuario.setApellidos(usuario.getApellidos());
			miUsuario.setEmail(usuario.getEmail());
			miUsuario.setContrasena(usuario.getContrasena());
			miUsuario.setEstado(usuario.getEstado());
			miUsuario.setTelefono(usuario.getTelefono());
			miUsuario.setTipoUsuario(usuario.getTipoUsuario());
			miUsuario.setFoto(usuario.getFoto());
		}
		return miUsuario;
	}
}
