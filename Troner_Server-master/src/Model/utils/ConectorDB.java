package Model.utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe d'eina per connectar el servidor amb la base de dades
 * Created by Grup 6 on 05/04/2017.
 */
public class ConectorDB {
	static String userName;
	static String password;
	static String db;
	static int port;
	static String url;
	static Connection conn = null;
	static Statement s;

    /**
     * Constructor
     * @param usr
     * @param pass
     * @param db
     * @param port
     */
	public ConectorDB(String usr, String pass, String db, int port) {
		ConectorDB.userName = usr;
		ConectorDB.password = pass;
		ConectorDB.db = db;
		ConectorDB.port = port;
		ConectorDB.url = "jdbc:mysql://localhost:"+port+"/";
		ConectorDB.url += db;
	}

    /**
     * Mètode per connectar-se a la BD
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            if (conn != null) {
            }
        }
        catch(SQLException ex) {

        }
        catch(ClassNotFoundException ex) {
        }

    }

    /**
     * Mètode per inserir dades
     * @param query
     */
    public void insertQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
        }
    }

    /** Mètode per actualitzar dades
     * @param query
     */
    public void updateQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);

         } catch (SQLException ex) {
         }
    }


    /**
     * Mètode per eliminar tuples
     * @param query
     */
    public void deleteQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);
             
         } catch (SQLException ex) {
         }
    	
    }

    /**Mètode per obtenir objectes de la BD
     * @param query
     * @return
     */
    public ResultSet selectQuery(String query){
    	ResultSet rs = null;
    	 try {
             s =(Statement) conn.createStatement();
             rs = s.executeQuery (query);
             
         } catch (SQLException ex) {
         }
		return rs;
    }

    /**
     * Mètode per desconnectar-se de la BD
     */
    public void disconnect(){
    	try {
			conn.close();
		} catch (SQLException e) {
		}
    }

}
