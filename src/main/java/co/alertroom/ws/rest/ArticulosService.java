package co.alertroom.ws.rest;

import javax.ws.rs.Path;

import co.alertroom.ws.dao.ArticuloDao;

@Path("/articulos")
public class ArticulosService {
	ArticuloDao articuloDao = new ArticuloDao();


}
