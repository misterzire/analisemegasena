package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Analise;
import conexaoBanco.*;

public class AnalisesDAO {

	private static FabricaConexao banco = Conexao.getConexao();
			
			
	private static AnalisesDAO instancia = new AnalisesDAO();
	
	
	private String dataInicioSorteio;
	
	private String dataFinalSorteio;
	
	private String tipoApuracao;
	
	private int quantidadeNumerosApurar;
	
	private String tipoLabel;
	
	private String sql;

	private AnalisesDAO() {
		
			
	}

	public String getDataInicioSorteio() {
		return dataInicioSorteio;
	}


	public void setDataInicioSorteio(String dataInicioSorteio) {
		this.dataInicioSorteio = dataInicioSorteio;
	}


	public String getDataFinalSorteio() {
		return dataFinalSorteio;
	}


	public void setDataFinalSorteio(String dataFinalSorteio) {
		this.dataFinalSorteio = dataFinalSorteio;
	}

	
	public static AnalisesDAO getInstance() throws IOException,
			ClassNotFoundException {

		System.out.println("retornado instancia de AnaliseDAO");

		return instancia;
	}

	
	public void getAnaliseJogosApuradosDAO(Analise analise){
		
		System.out.println("inicio:getAnaliseJogosApuradosDAO");
						
		System.out.println("getAnaliseJogosApuradosDAO:Numeros mais sorteados");
		
		this.tipoApuracao = "Numeros mais sorteados";
		
		this.quantidadeNumerosApurar = 6;
		
		this.tipoLabel = "label label-primary";
		
		this.sql	= "select resultado,count(*) from mydb.vallresultados "+
					  "where data >= ? and "+
					  "data <= ? "+
					  "group by resultado "+
					  "order by count(*) desc";
		
		
		getExecutaAnaliseSQLDAO(analise);
		
				
		this.tipoApuracao = "Numeros mais sorteados que ainda não sairam";
		
		this.quantidadeNumerosApurar = 6;
		
		this.tipoLabel = "label label-success";
		
		this.sql	= "select resultado,count(*) from mydb.vallresultados "+
					  "where resultado not in "+
					  "(select resultado from mydb.vallresultados "+
					  "where data >= ? and "+
					  "data <= ? "+
					  ")"+
					  "group by resultado "+
					  "order by count(*) desc ";
		
		getExecutaAnaliseSQLDAO(analise);
		
		
		this.tipoApuracao = "Numeros que menos sairam em todos os concursos";
		
		this.quantidadeNumerosApurar = 10;
		
		this.tipoLabel = "label label-danger";
		
		this.sql	= "select resultado,count(*) from mydb.vallresultados "+
					  "where resultado not in "+
					  "(select resultado from mydb.vallresultados "+
					  "where data >= ? and "+
					  "data <= ? "+
					  ")"+
					  "group by resultado "+
					  "order by count(*) ";
		
		getExecutaAnaliseSQLDAO(analise);
						
		//<span class="label label-info">Info</span><br></br>
		//<span class="label label-warning">Warning</span><br></br>		
	}

			

	public void getExecutaAnaliseSQLDAO(Analise analise) {
		
		System.out.println("AnalisesDao.inicio : "+this.tipoApuracao);
		
		String selectMaisSorteados= this.sql;
	
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
										
		try {
						
			pstmt = con.prepareStatement(selectMaisSorteados);
			
			System.out.println("Parametros");
			
			System.out.println(this.getDataInicioSorteio());
			System.out.println(this.getDataFinalSorteio() );
			
			pstmt.setString(1, this.getDataInicioSorteio() );
			pstmt.setString(2, this.getDataFinalSorteio() );
			
			ResultSet rs = pstmt.executeQuery();
			
						
			int x = 1;
					
			
			System.out.println("AnalisesDao antes do while");
			
			while ( rs.next() && x++ <=this.quantidadeNumerosApurar ) {
			    
				System.out.println("AnalisesDao dentro do while");
				
			    int numSorteado = rs.getInt(1);
			    
			    if (numSorteado == 1) {
			    	analise.setAnaliseNum01(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 2) {
			    	analise.setAnaliseNum02(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 3) {
			    	analise.setAnaliseNum03(this.tipoLabel); 	
			    }
			    if (numSorteado == 4) {
			    	analise.setAnaliseNum04(this.tipoLabel); 	
			    }
			    if (numSorteado == 5) {
			    	analise.setAnaliseNum05(this.tipoLabel); 	
			    }
			    if (numSorteado == 6) {
			    	analise.setAnaliseNum06(this.tipoLabel); 	
			    }
			    if (numSorteado == 7) {
			    	analise.setAnaliseNum07(this.tipoLabel); 	
			    }
			    if (numSorteado == 8) {
			    	analise.setAnaliseNum08(this.tipoLabel); 	
			    }
			    if (numSorteado == 9) {
			    	analise.setAnaliseNum09(this.tipoLabel); 	
			    }
			    if (numSorteado == 10) {
			    	analise.setAnaliseNum10(this.tipoLabel); 	
			    }
			    if (numSorteado == 11) {
			    	analise.setAnaliseNum11(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 12) {
			    	analise.setAnaliseNum12(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 13) {
			    	analise.setAnaliseNum13(this.tipoLabel); 	
			    }
			    if (numSorteado == 14) {
			    	analise.setAnaliseNum14(this.tipoLabel); 	
			    }
			    if (numSorteado == 15) {
			    	analise.setAnaliseNum15(this.tipoLabel); 	
			    }
			    if (numSorteado == 16) {
			    	analise.setAnaliseNum16(this.tipoLabel); 	
			    }
			    if (numSorteado == 17) {
			    	analise.setAnaliseNum17(this.tipoLabel); 	
			    }
			    if (numSorteado == 18) {
			    	analise.setAnaliseNum18(this.tipoLabel); 	
			    }
			    if (numSorteado == 19) {
			    	analise.setAnaliseNum19(this.tipoLabel); 	
			    }
			    if (numSorteado == 20) {
			    	analise.setAnaliseNum20(this.tipoLabel); 	
			    }
			    if (numSorteado == 21) {
			    	analise.setAnaliseNum21(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 22) {
			    	analise.setAnaliseNum22(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 23) {
			    	analise.setAnaliseNum23(this.tipoLabel); 	
			    }
			    if (numSorteado == 24) {
			    	analise.setAnaliseNum24(this.tipoLabel); 	
			    }
			    if (numSorteado == 25) {
			    	analise.setAnaliseNum25(this.tipoLabel); 	
			    }
			    if (numSorteado == 26) {
			    	analise.setAnaliseNum26(this.tipoLabel); 	
			    }
			    if (numSorteado == 27) {
			    	analise.setAnaliseNum27(this.tipoLabel); 	
			    }
			    if (numSorteado == 28) {
			    	analise.setAnaliseNum28(this.tipoLabel); 	
			    }
			    if (numSorteado == 29) {
			    	analise.setAnaliseNum29(this.tipoLabel); 	
			    }
			    if (numSorteado == 30) {
			    	analise.setAnaliseNum30(this.tipoLabel); 	
			    }
			    if (numSorteado == 31) {
			    	analise.setAnaliseNum31(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 32) {
			    	analise.setAnaliseNum32(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 33) {
			    	analise.setAnaliseNum33(this.tipoLabel); 	
			    }
			    if (numSorteado == 34) {
			    	analise.setAnaliseNum34(this.tipoLabel); 	
			    }
			    if (numSorteado == 35) {
			    	analise.setAnaliseNum35(this.tipoLabel); 	
			    }
			    if (numSorteado == 36) {
			    	analise.setAnaliseNum36(this.tipoLabel); 	
			    }
			    if (numSorteado == 37) {
			    	analise.setAnaliseNum37(this.tipoLabel); 	
			    }
			    if (numSorteado == 38) {
			    	analise.setAnaliseNum38(this.tipoLabel); 	
			    }
			    if (numSorteado == 39) {
			    	analise.setAnaliseNum39(this.tipoLabel); 	
			    }
			    if (numSorteado == 40) {
			    	analise.setAnaliseNum40(this.tipoLabel); 	
			    }
			    if (numSorteado == 41) {
			    	analise.setAnaliseNum41(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 42) {
			    	analise.setAnaliseNum42(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 43) {
			    	analise.setAnaliseNum43(this.tipoLabel); 	
			    }
			    if (numSorteado == 44) {
			    	analise.setAnaliseNum44(this.tipoLabel); 	
			    }
			    if (numSorteado == 45) {
			    	analise.setAnaliseNum45(this.tipoLabel); 	
			    }
			    if (numSorteado == 46) {
			    	analise.setAnaliseNum46(this.tipoLabel); 	
			    }
			    if (numSorteado == 47) {
			    	analise.setAnaliseNum47(this.tipoLabel); 	
			    }
			    if (numSorteado == 48) {
			    	analise.setAnaliseNum48(this.tipoLabel); 	
			    }
			    if (numSorteado == 49) {
			    	analise.setAnaliseNum49(this.tipoLabel); 	
			    }
			    if (numSorteado == 50) {
			    	analise.setAnaliseNum50(this.tipoLabel); 	
			    }
			    if (numSorteado == 51) {
			    	analise.setAnaliseNum51(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 52) {
			    	analise.setAnaliseNum52(this.tipoLabel); 	
			    }			    
			    if (numSorteado == 53) {
			    	analise.setAnaliseNum53(this.tipoLabel); 	
			    }
			    if (numSorteado == 54) {
			    	analise.setAnaliseNum54(this.tipoLabel); 	
			    }
			    if (numSorteado == 55) {
			    	analise.setAnaliseNum55(this.tipoLabel); 	
			    }
			    if (numSorteado == 56) {
			    	analise.setAnaliseNum56(this.tipoLabel); 	
			    }
			    if (numSorteado == 57) {
			    	analise.setAnaliseNum57(this.tipoLabel); 	
			    }
			    if (numSorteado == 58) {
			    	analise.setAnaliseNum58(this.tipoLabel); 	
			    }
			    if (numSorteado == 59) {
			    	analise.setAnaliseNum59(this.tipoLabel); 	
			    }
			    if (numSorteado == 60) {
			    	analise.setAnaliseNum60(this.tipoLabel); 			    	
			    }
			    
			    
			    System.out.println(numSorteado);
			    
		    }
			
			
			
			banco.close(con);
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
					
	}

	public void getSorteadoUltimoConcursoDAO(Analise analise) {
		
		System.out.println("getSorteadoUltimoConcursoDAO.inicio");
		
		String s = new String("select r1,r2,r3,r4,r5,r6,data from mydb.resultados order by concurso desc");
	
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
										
		try {
						
			pstmt = con.prepareStatement(s);
			
					
			ResultSet rs = pstmt.executeQuery();
			
			analise.setSorteado01(rs.getString(1));
			analise.setSorteado02(rs.getString(2));
			analise.setSorteado03(rs.getString(3));
			analise.setSorteado04(rs.getString(4));
			analise.setSorteado05(rs.getString(5));
			analise.setSorteado06(rs.getString(6));
		    analise.setDataUltimoSoteio(rs.getString(7));
			
							    
		    banco.close(con);
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
		
}
