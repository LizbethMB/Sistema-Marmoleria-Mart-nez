
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LIZBETHMB
 */
public class ConexionDB {
    public Connection conectar(){
        Connection cn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=lizbethmb;databaseName=BASEMARMOLERIA");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error" + ex.getMessage());
        }
        return cn;
    }

    
}
