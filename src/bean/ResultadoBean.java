package bean;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
//import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import dao.ResultadosDAO;

@ManagedBean
@SessionScoped
public class ResultadoBean {

	private int concurso;

	private String data;

	private int r1;
	
	private int r2;
	
	private int r3;
	
	private int r4;
	
	private int r5;
	
	private int r6;

	public int getConcurso() {
		return concurso;
	}

	public void setConcurso(int concurso) {
		this.concurso = concurso;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getR1() {
		return r1;
	}

	public void setR1(int r1) {
		this.r1 = r1;
	}

	public int getR2() {
		return r2;
	}

	public void setR2(int r2) {
		this.r2 = r2;
	}

	public int getR3() {
		return r3;
	}

	public void setR3(int r3) {
		this.r3 = r3;
	}

	public int getR4() {
		return r4;
	}

	public void setR4(int r4) {
		this.r4 = r4;
	}

	public int getR5() {
		return r5;
	}

	public void setR5(int r5) {
		this.r5 = r5;
	}

	public int getR6() {
		return r6;
	}

	public void setR6(int r6) {
		this.r6 = r6;
	}

		
	private FacesContext context;

	
	public String executaInclusao() throws IOException,ClassNotFoundException, SQLException {

		context = FacesContext.getCurrentInstance();

		executaValidacoesGenericas();

		if (obterConcursoValido() == true) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Concurso já cadastrado.",""));
		}

		if (context.getMessageList().size() > 0) {

			return (null); // Returning null means to redisplay the form

		} 
		
		String retorno = incluir();
		
		if (retorno == "OK"){
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Inclusão efetuada com sucesso.",""));
			
		}
		else{
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,retorno,""));
			
		}
		
		return ("page-list");
				
	}
	
	public String executaAlteracao() throws IOException,	ClassNotFoundException, SQLException {

		context = FacesContext.getCurrentInstance();

		executaValidacoesGenericas();
					
		if (context.getMessageList().size() > 0) {

			return (null); // Returning null means to redisplay the form

		}
		
		
		String retorno = alterar();

		if (retorno == "OK"){
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Alteração efetuada com sucesso.",""));
				
		}
		else{
				
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,retorno,""));
				
		}

		return ("page-list");
		
	}

	public String executaExclusao() throws IOException,	ClassNotFoundException, SQLException {

		context = FacesContext.getCurrentInstance();

		if ( Integer.valueOf(getConcurso() ) == 0 ) {
			context.addMessage(null, new FacesMessage(
			"Campo concurso deve ser preenchido"));
		}

				
		if (!obterConcursoValido() ) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Concurso não esta cadastrado.",""));
		}

		if (context.getMessageList().size() > 0) {

			return (null); // Returning null means to redisplay the form

		} 
		
		String retorno = excluir();

		if (retorno == "OK"){
			
		 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exclusão efetuada com sucesso.",""));
				
		}
		else{
				
		 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,retorno,""));
				
		}

		return ("page-list");
		
	}

	public List <ResultadoBean> getListPessoas() throws IOException, ClassNotFoundException,SQLException, ParseException {
		
		ResultadosDAO r = ResultadosDAO.getInstance();

		return r.getList();
		
		
	}
	
	public List <ResultadoBean> getSearchPessoas() throws IOException, ClassNotFoundException,SQLException {
		
		ResultadosDAO r = ResultadosDAO.getInstance();

		try {
			return r.getSearchResultados(this);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
				
	}
	
	private String incluir()  {
						
		ResultadosDAO r;
		
		try {
			
			r = ResultadosDAO.getInstance();
			
			r.insertResultadoDao(this);
						
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());			
					
		} catch (SQLException e) {
			e.printStackTrace();			
			return (e.getMessage());
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return (e.getMessage());
		}	

		return "OK";
		
	}
	
	private String alterar(){

		ResultadosDAO r;

		try {
			
			r = ResultadosDAO.getInstance();
			
			r.updateResultadoDao(this);
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		}
				
		return "OK";

	} 

	private String excluir() {

		ResultadosDAO r;

		try {
			
			r = ResultadosDAO.getInstance();
			
			r.deleteResultadoDao(this);
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		}
		
		return "OK";
		
	} 
	
	private boolean obterConcursoValido() throws IOException,ClassNotFoundException, SQLException {

		ResultadosDAO r = ResultadosDAO.getInstance();

		return r.getResultadoDao(this);

	}
	
	private void executaValidacoesGenericas(){
		
		
		if ( Integer.valueOf(getConcurso() ) == 0 ) { 
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Campo concurso deve ser preenchido",""));
		}

				
	}
	
}
