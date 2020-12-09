package co.jjortiz.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;

import co.jjortiz.aplicacion.JPAUtil;
import co.jjortiz.entidades.Usuario;

public class UsuariosDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();

	/**
	 * Metodo para consultar si un usuario se encuentra en la base de datos
	 * @param id
	 * @param contrasena
	 * @return Usuario si existe , null si no existe
	 */
	public Usuario consultarLoginUsuario(String id, String contrasena) {
		try {
			Usuario miUsuario=entityManager.find(Usuario.class, id);
			boolean correcto = DigestUtils.md5Hex(contrasena).equals( miUsuario.getContrasena());
			if (miUsuario!=null && miUsuario.getEstado().equals("A") && correcto) {
				return miUsuario;
			}else {
				miUsuario=null;
				return miUsuario;
			}
		} catch (Exception e) {
			return null;
		}
		
	}
	

	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}

	public String registrarUsuario(Usuario miUsuario) {
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(miUsuario);
			entityManager.getTransaction().commit();
			resp="ok";
		} catch (Exception e) {
			resp="error";
		}
	
		return resp;
	}

	public Usuario consultarUsuario(String documento) {
		try {
			Usuario miUsuario=entityManager.find(Usuario.class,documento);
			if (miUsuario!=null) {
				return miUsuario;
			}else {
				return null;
			}
		}catch (Exception e) {
			return null;
		}
		
	}
	
	public String actualizarUsuario(Usuario miUsuario) {
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(miUsuario);
			entityManager.getTransaction().commit();
			resp="Persona Actualizada!";
		} catch (Exception e) {
			resp="No se puede eliminar la persona"
					+ " verifique que no tenga registros pendientes";
		}		
		return resp;
	}
	
	public String actualizarEstado(Usuario miUsuario) {
		System.out.println("Entro a actualizar estado DAO JPA");
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(miUsuario);
			entityManager.getTransaction().commit();
			resp="Persona Actualizada!";
		} catch (Exception e) {
			resp="No se puede actualizar la persona"
					+ " verifique que no tenga registros pendientes";
		}		
		return resp;
	}
	
	public List<Usuario> listarUsuarios(){
		try {
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			Query query = entityManager.createQuery("SELECT u FROM Usuario u");
			listaUsuarios = query.getResultList();
			return listaUsuarios;
		}catch (Exception e) {
			return null;
		}
		
	}
}
