/*
 * Esta classe servirá para efetuar a conexão do JDBC com o Banco
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Wesley Morais Santos
 */
public class Connect {
    public static Connection connect;
    static String url = "jdbc:postgresql://localhost:5434/banco_dados";
    static String user = "postgres";
    static String password = "";
    public ResultSet result;
    
/**Comando utilizado para efetuar a connexão com o SGBD**/    
    private static Connection toConect(){
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso!");
        } 
        catch (SQLException ex) {
            System.out.println("Falha na conexão! "+ex);
        }
        catch(ClassNotFoundException ex){
            System.out.println("Driver JDBC Não encontrado: " +ex);
        }
        return connect;     
    }
    
/**Fechar conexão SQL**/
    public static void toClose(){
        try{
            connect.close();
            System.out.println("Conexão Finalizada!");
        }
        catch (SQLException ex){
            System.out.println("Falha na desconexão!");
        }
    }

/**Comando de execução SQL**/
    public void executeSQL(String sql){
        try{
            Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(sql);
        }
        catch(SQLException excecao){
            System.out.println("Falha no comado SQL \nErro"+excecao+""+"\nComando SQL passado: "+sql);
        }
    }
}