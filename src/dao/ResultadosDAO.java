package dao;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.List;

import bean.ResultadoBean;
import conexaoBanco.*;

public class ResultadosDAO {

	private static FabricaConexao banco = Conexao.getConexao();
			
			
	private static ResultadosDAO instancia = new ResultadosDAO();

	private ResultadosDAO() {
	}

	
	public static ResultadosDAO getInstance() throws IOException,
			ClassNotFoundException {

		System.out.println("retornado instancia de ResultadoDAO");

		return instancia;
	}

	
	public void insertResultadoDao(ResultadoBean Resultado) throws IOException,
			ClassNotFoundException, SQLException {

		try {
			System.out.println("ResultadoDao.insertResultadoDao : inicio ");

			Connection con = banco.getConnection();
			
			String insert = "INSERT INTO Resultados (concurso,data,R1,R2,R3,R4,R5,R6) VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(insert);

			System.out.println("Concurso " + Resultado.getConcurso());
			System.out.println("Data " + Resultado.getData());
			System.out.println("R1 " + Resultado.getR1());
			System.out.println("R2 " + Resultado.getR2());
			System.out.println("R3 " + Resultado.getR3());
			System.out.println("R4 " + Resultado.getR4());
			System.out.println("R5 " + Resultado.getR5());
			System.out.println("R6 " + Resultado.getR6());

			pstmt.setInt(1, Resultado.getConcurso());
			//pstmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format( Resultado.getData() ) );
			pstmt.setString(2,Resultado.getData()); 
			pstmt.setInt(3, Resultado.getR1());
			pstmt.setInt(4, Resultado.getR2());
			pstmt.setInt(5, Resultado.getR3());
			pstmt.setInt(6, Resultado.getR4());
			pstmt.setInt(7, Resultado.getR5());
			pstmt.setInt(8, Resultado.getR6());

			System.out.println("vai executar");

			pstmt.executeUpdate();
			
			banco.close(con);
			
			System.out.println("comitou");

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

	}
	
	public void updateResultadoDao(ResultadoBean Resultado) throws IOException,ClassNotFoundException, SQLException {

		try {
			
			System.out.println("ResultadoDao.updateResultadoDao : inicio ");

			String update = "UPDATE Resultados set concurso = ?,data = ?,r1 = ?,r2 = ?,r3 = ?,r4 = ?,r5 = ?,r6 = ?  where concurso = ?";

			Connection con = banco.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(update);

			System.out.println("Concurso " + Resultado.getConcurso());
			System.out.println("Data " + Resultado.getData());
			System.out.println("R1 " + Resultado.getR1());
			System.out.println("R2 " + Resultado.getR2());
			System.out.println("R3 " + Resultado.getR3());
			System.out.println("R4 " + Resultado.getR4());
			System.out.println("R5 " + Resultado.getR5());
			System.out.println("R6 " + Resultado.getR6());

		
			pstmt.setInt(1, Resultado.getConcurso());
			//pstmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format( Resultado.getData() ) );
			pstmt.setString(2,Resultado.getData());
			pstmt.setInt(3, Resultado.getR1());
			pstmt.setInt(4, Resultado.getR2());
			pstmt.setInt(5, Resultado.getR3());
			pstmt.setInt(6, Resultado.getR4());
			pstmt.setInt(7, Resultado.getR5());
			pstmt.setInt(8, Resultado.getR6());
			pstmt.setInt(9, Resultado.getConcurso());
			
			System.out.println("vai executar");

			pstmt.executeUpdate();

			banco.close(con);
			
			System.out.println("comitou");

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

	}

	
	public void deleteResultadoDao(ResultadoBean Resultado) throws IOException,ClassNotFoundException, SQLException {

		try {
			
			System.out.println("ResultadoDao.deleteResultadoDao : inicio ");

			String delete = "delete from Resultados where concurso = ?";

			Connection con = banco.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(delete);

			System.out.println("Concurso " + Resultado.getConcurso());
			
			pstmt.setInt(1, Resultado.getConcurso());
	
			System.out.println("vai executar");

			pstmt.executeUpdate();

			banco.close(con);
			
			System.out.println("comitou");			

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

	}
	
	public boolean getResultadoDao(ResultadoBean Resultado) throws IOException,
			ClassNotFoundException, SQLException {

		int codigo = 0;

		try {

			System.out.println("ResultadoDao.getResultadoDao");

			String selectConcursoResultado = "SELECT CONCURSO FROM Resultados WHERE CONCURSO = "+Resultado.getConcurso();
			
			Connection con = banco.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(selectConcursoResultado);
						
			ResultSet rs = pstmt.executeQuery();
						
			while (rs.next()) {
				codigo = rs.getInt("CONCURSO");
			}

			// close the resultset
			rs.close();
			
			banco.close(con);

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

		if (codigo > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<ResultadoBean> getList() throws ParseException{
		
		System.out.println("ResultadoDao.getList");
		
		String selectResultadolAll= "SELECT CONCURSO,DATA,R1,R2,R3,R4,R5,R6 FROM Resultados order by concurso";
	
		Connection con = null;
		
		try {
			con = banco.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PreparedStatement pstmt;
		
		List<ResultadoBean> items = null;
		
		try {
						
			pstmt = con.prepareStatement(selectResultadolAll);
			
			ResultSet rs = pstmt.executeQuery();
			
			items = new ArrayList<ResultadoBean>();
			
			while (rs.next()) {
			    
				ResultadoBean Resultado = new ResultadoBean();
			    
			    Resultado.setConcurso(rs.getInt(1) );
			    
			    String dateStr = rs.getString(2); 

			    //SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy"); 
			    //Date dateObj = (Date) curFormater.parse(dateStr); 
			    //Calendar calendar = Calendar.getInstance();
			    //calendar .setTime(dateObj);
			    
			    //Resultado.setData(calendar);
			    
			    Resultado.setData(dateStr);
			    
			    Resultado.setR1(rs.getInt(3));
			    Resultado.setR2(rs.getInt(4));
			    Resultado.setR3(rs.getInt(5));
			    Resultado.setR4(rs.getInt(6));
			    Resultado.setR5(rs.getInt(7));
			    Resultado.setR6(rs.getInt(8));
			    
			    		    
			    items.add(Resultado);
			}
						
			banco.close(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return items;		
	}
	
	
	public List<ResultadoBean> getSearchResultados(ResultadoBean ResultadoBean) throws ParseException {
		
		 System.out.println("ResultadoDao.getSearchResultados");
		
		 Connection con = null;
		try {
			con = banco.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		 PreparedStatement pstmt;
		 
		 boolean flagPesquisa = false; 
		 
		 String selectResultadolFind= "SELECT CONCURSO,DATA,R1,R2,R3,R4,R5,R6 FROM Resultados ";
		
		 if (ResultadoBean.getConcurso() != 0 ){
			 
			 selectResultadolFind = selectResultadolFind + " where concurso = ? order by concurso";
			 
			 flagPesquisa = true;
			 
		 } else if (!ResultadoBean.getData().equals(null) ){
			 
			 selectResultadolFind = selectResultadolFind + " where data = ? order by data";
			 
			 flagPesquisa = true;
			 
		 }    
			
		 List<ResultadoBean> items = null;
		
		 if (flagPesquisa == true){
		
		 	 
			 try {

				 pstmt = con.prepareStatement(selectResultadolFind);

				 if (ResultadoBean.getConcurso() != 0 ){

					 pstmt.setInt(1, ResultadoBean.getConcurso());


				 } else if (!ResultadoBean.getData().equals(null) ){
					 	 
					 pstmt.setString(1, new SimpleDateFormat("yyyy-MM-dd").format( ResultadoBean.getData() ) );


				 } 
				 
				 ResultSet rs = pstmt.executeQuery();

				 items = new ArrayList<ResultadoBean>();

				 while (rs.next()) {

					 ResultadoBean Resultado = new ResultadoBean();

					 Resultado.setConcurso(rs.getInt(1));
					 
					 String dateStr = rs.getString(2); 

					 //SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy"); 
					// Date dateObj = (Date) curFormater.parse(dateStr); 
					 //Calendar calendar = Calendar.getInstance();
					 //calendar .setTime(dateObj);
					    
					 //Resultado.setData(calendar);
					 
					 Resultado.setData(dateStr);					 
					 Resultado.setR1(rs.getInt(3));
					 Resultado.setR2(rs.getInt(4));
					 Resultado.setR3(rs.getInt(5));
					 Resultado.setR4(rs.getInt(6));
					 Resultado.setR5(rs.getInt(7));
					 Resultado.setR6(rs.getInt(8));

					 items.add(Resultado);
				 }


				 banco.close(con);

			 } catch (SQLException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }

		 }
		 
			return items;		
		
		}

	
		
	}
