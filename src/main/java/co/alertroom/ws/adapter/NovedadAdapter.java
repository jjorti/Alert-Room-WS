package co.alertroom.ws.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import co.alertroom.ws.vo.NovedadVo;
import co.jjortiz.entidades.Novedad;

public class NovedadAdapter {


	public List<NovedadVo> asignarListaNovedades(List<Novedad> listaNovedadesJPA) {
		List<NovedadVo> miListaNovedades = new ArrayList<NovedadVo>();
		for (Novedad novedadJPA : listaNovedadesJPA) {
			miListaNovedades.add(asignar(novedadJPA));	
		}
		return miListaNovedades;
	}

	private NovedadVo asignar(Novedad novedadJPA) {
		NovedadVo miNovedad = new NovedadVo();
		miNovedad.setIdNovedad(novedadJPA.getIdNovedad());
		miNovedad.setObservaciones(novedadJPA.getObservaciones());
		miNovedad.setFechaHora(parseFecha(novedadJPA.getFechaHora()));
		AmbienteAdapter miAmbienteAdapter = new AmbienteAdapter();
		miNovedad.setIdAmbiente(miAmbienteAdapter.asignar(novedadJPA.getIdAmbiente()));
		UsuarioAdapter miUsuarioAdapter =  new UsuarioAdapter();
		miNovedad.setIdUsuarioGuarda(miUsuarioAdapter.asignarUsuario(novedadJPA.getIdUsuarioGuarda()));
		miNovedad.setIdUsuarioInstructor(miUsuarioAdapter.asignarUsuario(novedadJPA.getIdUsuarioInstructor()));
		return miNovedad;
	}
	
	private String parseFecha(Date fechaHora) {
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm a dd/MM/yyyy");
		return hourdateFormat.format(fechaHora);
	}
	


}
