;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LIZBETHMB
 */
public class ConsultasEmpleados {
      Connection cn;
      
    public ConsultasEmpleados(){
        
       cn =(new ConexionDB()).conectar();
    }
    
    public void insertar(String Nombre, String Apellidos, String Calle, String Colonia, String Numero, String Telefono, String Movil, String Salario, String Cargo){
        try{
            Statement stmt = cn.createStatement();
            
            String sql = "insert into Empleados values ('"+Nombre+"','"+Apellidos+"','"+
                            Calle+"','"+Colonia+"','"+Numero+"','"+Telefono+"','"+
                            Movil+"','"+Salario+"','"+Cargo+"')";
            
           
                    stmt.executeUpdate(sql);
                    //con.close();
            }catch(Exception e){
                
                JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public void MostrarTablaEmpleados(JTable table){
            if(cn!=null){
                try{
                    Statement stmt = cn.createStatement();
                    String sql = "select ID_EMP , Nombre ,Apellidos,Calle,Colonia,Numero,Telefono,Movil,Salario,Cargo from Empleados";
                            
                    ResultSet rs=stmt.executeQuery(sql);
                    
                    ResultSetMetaData rsmetadata= rs.getMetaData();
                    
                    int columns=rsmetadata.getColumnCount();
                    
                    DefaultTableModel dtm=new DefaultTableModel();
                    Vector columns_name=new Vector();
                    Vector data_rows=new Vector();
                    
                    for(int i=1;i<=columns;i++){
                        columns_name.addElement(rsmetadata.getColumnName(i));
                    }
                    dtm.setColumnIdentifiers(columns_name);
                    
                    while(rs.next()){
                        data_rows=new Vector();
                        for(int j=1;j<=columns;j++){
                            data_rows.addElement(rs.getString(j));
                        }
                        dtm.addRow(data_rows);
                    }
                    table.setModel(dtm);
       
                //con.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
     
     public int eliminarempleados(String id_emp){
     int filas = 0;
        try {
            //Connection con = cn.conectar();
            Statement sql = cn.createStatement();
            filas = sql.executeUpdate("delete from Empleados where ID_EMP = "+id_emp+"");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error! El empleado es un usuario del sistema.\n¡No se puede eliminar!");
        }

        return filas;
    
    }


      
}
