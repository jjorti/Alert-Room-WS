package co.alertroom.ws.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import co.alertroom.ws.vo.UsuarioVo;
import co.jjortiz.entidades.Usuario;


public class UsuarioAdapter {

	public UsuarioVo asignarUsuario(Usuario usuario) {
		
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
		}
		return miUsuario;
	}

	public Usuario obtenerUsuario(UsuarioVo u) {
		Usuario miUsuario = null;
		
		if (u!=null) {			
			miUsuario=new Usuario();
			miUsuario.setId(u.getId());
			miUsuario.setNombres(u.getNombres());
			miUsuario.setApellidos(u.getApellidos());
			miUsuario.setEmail(u.getEmail());
			miUsuario.setContrasena(DigestUtils.md5Hex(u.getId()));
			miUsuario.setEstado(u.getEstado());
			miUsuario.setTelefono(u.getTelefono());
			miUsuario.setTipoUsuario(u.getTipoUsuario());
		}
		return miUsuario;
	}
	
	
	public Usuario actualizarUsuario(Usuario usuario) {
		Usuario miUsuario = null;
		
		if (usuario!=null) {			
			miUsuario=new Usuario();
			miUsuario.setId(usuario.getId());
			miUsuario.setNombres(usuario.getNombres());
			miUsuario.setApellidos(usuario.getApellidos());
			miUsuario.setEmail(usuario.getEmail());
			miUsuario.setContrasena(DigestUtils.md5Hex(usuario.getId()));
			miUsuario.setEstado(usuario.getEstado());
			miUsuario.setTelefono(usuario.getTelefono());
			miUsuario.setTipoUsuario(usuario.getTipoUsuario());
		}
		return miUsuario;
	}
	
	
	public UsuarioVo actualizarEstado(Usuario usuario) {
		
		UsuarioVo miUsuario = null;

		
		System.out.println("ADAPTER ESTADO = "+usuario.getEstado()+"    USUARIO= "+usuario);
		
		if (usuario != null && usuario.getEstado().equals("A")) {
			System.out.println("IF ADAPTER ESTADO = A");
			miUsuario = new UsuarioVo();
			miUsuario.setId(usuario.getId());
			miUsuario.setNombres(usuario.getNombres());
			miUsuario.setApellidos(usuario.getApellidos());
			miUsuario.setEmail(usuario.getEmail());
			miUsuario.setContrasena(usuario.getContrasena());
			miUsuario.setEstado("I");
			miUsuario.setTelefono(usuario.getTelefono());
			miUsuario.setTipoUsuario(usuario.getTipoUsuario());
		}else {
			
			System.out.println("ELSE ADAPTER ESTADO = A");
			miUsuario = new UsuarioVo();
			miUsuario = asignarUsuario(usuario);
			miUsuario.setId(usuario.getId());
			miUsuario.setNombres(usuario.getNombres());
			miUsuario.setApellidos(usuario.getApellidos());
			miUsuario.setEmail(usuario.getEmail());
			miUsuario.setContrasena(usuario.getContrasena());
			miUsuario.setEstado("A");
			miUsuario.setTelefono(usuario.getTelefono());
			miUsuario.setTipoUsuario(usuario.getTipoUsuario());
	
			
		}
		return miUsuario;
	}
	
	public Usuario obtenerUsuario2(UsuarioVo u) {
		Usuario miUsuario = null;
		
		if (u!=null) {			
			miUsuario=new Usuario();
			miUsuario.setId(u.getId());
		}
		return miUsuario;
	}

	public List<UsuarioVo> asignarListaUsuario(List<Usuario> listaUsuario) {
		List<UsuarioVo> listaUsuarios = new ArrayList<UsuarioVo>();
		for (Usuario usuario : listaUsuario) {
			listaUsuarios.add(asignarUsuario(usuario));
		}
		return listaUsuarios;
	}
	
}
