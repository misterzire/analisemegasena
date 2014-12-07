package bean;

import java.io.IOException;
import javax.faces.bean.*;
//import javax.annotation.PostConstruct;


import modelo.Analise;
import dao.AnalisesDAO;

@ManagedBean
@RequestScoped
public class AnaliseBean {

	public Analise analise = new Analise();	

	public Analise getAnalise() {
		return analise;
	}

	public void setAnalise(Analise analise) {
		this.analise = analise;
	}

	
	
public void sorteadosUltimoConcurso()  {
		
		System.out.println("executando metodo sorteadosUltimoConcurso");
		
		AnalisesDAO r = null;
		
		try {
			r = AnalisesDAO.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

							
		r.getSorteadoUltimoConcursoDAO(analise);
		
		System.out.println("finalizando metodo sorteadosUltimoConcurso");
		
					
	}
	
	
	public void executaAnaliseDosJogosApurados()  {
		
		System.out.println("executando metodo executaAnaliseDosJogosApurados");
		
		AnalisesDAO r = null;
		
		try {
			r = AnalisesDAO.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		r.setDataFinalSorteio(analise.getDataFimAnalise());
		r.setDataInicioSorteio(analise.getDataInicioAnalise());
		
				
		r.getAnaliseJogosApuradosDAO(analise);
	
		//r.getSorteadoUltimoConcursoDAO(analise);
		
		System.out.println("finalizando metodo executaAnaliseDosJogosApurados");
		
					
	}
		
	
		
}
