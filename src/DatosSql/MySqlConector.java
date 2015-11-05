/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySqlConector {
    
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  private String query;
  
  public static MySqlConector getInstance() throws SQLException, ClassNotFoundException{
      return new MySqlConector();
  }
  private MySqlConector() throws SQLException, ClassNotFoundException{
      Class.forName("com.mysql.jdbc.Driver");
      connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "serviciotecnico", "root", "root");
  }
  
  public void closeConexion() throws SQLException{
      if(connect!=null && !connect.isClosed()){
          this.connect.close();
      }
  }
  public int insert(String tableName,String column, String values) throws SQLException{
        query="INSERT INTO "+tableName+"("+column+") VALUES ("+values+");";
        preparedStatement=this.connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        resultSet =preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
  }
  public void delete(String tableName,String Where,String extra) throws SQLException{//Extra is for GroupBy and limit and others commands
      query="DELETE FROM "+tableName+" WHERE "+Where+" "+extra ;
      this.preparedStatement=this.connect.prepareStatement(query);
      this.preparedStatement.executeUpdate();
      
  }
  public void update(String tableName, String Set, String Where, String Extra) throws SQLException{
      query="UPDATE "+tableName+" SET "+Set+" WHERE "+Where+" "+Extra;
      this.preparedStatement=this.connect.prepareStatement(query);
      this.preparedStatement.executeUpdate();
  }

  public ResultSet query(String columns,String From, String WHERE, String extra) throws SQLException{
      if(!WHERE.trim().isEmpty())
        this.query="SELECT "+columns+" FROM "+From+" WHERE "+WHERE+" "+extra;
      else
          this.query="SELECT "+columns+" FROM "+From+" "+extra;
      this.preparedStatement=this.connect.prepareStatement(this.query);
      return this.preparedStatement.executeQuery();
  }
  
}
