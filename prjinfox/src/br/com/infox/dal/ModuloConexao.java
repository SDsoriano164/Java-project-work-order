/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;
// importando biblioteca 
import br.com.infox.telas.TelaLogin;
import java.sql.*;

/**
 *
 * @author phstr
 */
public class ModuloConexao {
    // metodo responsavel por estebelecer conexão com o banco
    public static Connection conector(){
                 
        java.sql.Connection conexao= null;
        // a linha abaixo "chama" o driver;
        String driver = "com.mysql.cj.jdbc.Driver";
        // Armazenando informações referente ao 
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "ccsm2609";
        //Estabelecendo a conexão com o banco de dados
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
          
        } catch (Exception e) {
            // a linha abaixo mostra o erro
            e.printStackTrace();
            return null ;   
        }
        
    }
    
}
