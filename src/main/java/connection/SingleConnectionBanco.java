package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	/* 
	//localhost
	private static String banco = "jdbc:postgresql://localhost:5433/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null; 
	*/
	
	
    // AWS connection
	private static String banco = "jdbc:postgresql://16.170.235.54:5432/curso-jsp?autoReconnect=true";
	private static String user = "natan-aws";
	private static String senha = "xxxx";
	private static Connection connection = null; 
	
	
	/*
    // Heroku connection
	private static String banco = "jdbc:postgresql://ec2-34-236-103-63.compute-1.amazonaws.com:5432/dbjnekanokg26n?sslmode=require&autoReconnect=true";
	private static String user = "sfnuuqgvjmyfqw";
	private static String senha = "2c625d3a34127e633d1cdd4983391d8a67e289372e313a712340ac3e16fcdcfa";
	private static Connection connection = null;
	*/ 
	
	
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	
	public SingleConnectionBanco() {/*quando tiver um instancia vai conectar*/
		conectar();
	}
	
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); /*Carrega o driver de conex�o do banco*/
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); /*para nao efetuar alteracoes no banco sem nosso comando*/
			}
			
		}catch (Exception e) {
			e.printStackTrace();/*Mostrar qualquer erro no momento de conectar*/
		}
	}

}
