package co.alertroom.ws.adapter;

import co.alertroom.ws.vo.ConceptoVo;
import co.jjortiz.entidades.Concepto;

public class ConceptoAdapter {
	
	public ConceptoVo asignar(Concepto conceptoJPA) {
		ConceptoVo miConceptoVo = new ConceptoVo();
		miConceptoVo.setIdConcepto(conceptoJPA.getIdConcepto());
		miConceptoVo.setNombre(conceptoJPA.getNombre());
		miConceptoVo.setAplica(conceptoJPA.getAplica());
		return  miConceptoVo;
	}
	
	public Concepto asignarIdConcepto() {
		Concepto miConcepto = new Concepto();
		miConcepto.setIdConcepto(5);
		return  miConcepto;
	}
}
