
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
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
public class ConsultasUsuarios {
    Connection cn;
    ConsultasUsuarios(){
        cn=(new ConexionDB()).conectar();
    }
    
    void insertar(String id_empleado,String usr, String ctr,String tipo, String ctrC){
        if(usr.equals("")||ctr.equals("")||ctrC.equals("")){
            showMessageDialog(null,"Llenar todos los campos");
            return;
        }
        //asd ASD
        if(!ctr.equals(ctrC)){
            showMessageDialog(null,"No coincide contraseña");
            return;
        }
        
        try{
            Statement stmt = cn.createStatement();
            String sql = "insert into Usuarios values ('"+id_empleado+"','"+usr+"','"+ctr+"','"+tipo+"')";
            stmt.executeUpdate(sql);
            showMessageDialog(null,"Se insertó correctamente");
            }catch(Exception e){
                showMessageDialog(null, "Error: "+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        
        
    }
    
  public void MostrarTabla(JTable table){
            if(cn!=null){
                try{
                    Statement stmt = cn.createStatement();
                    String sql = "select U.ID_USUARIOS,U.USUARIO,U.TIPO,E.NOMBRE,E.Apellidos\n" +
                                 "FROM USUARIOS U\n" +
                                 "INNER JOIN EMPLEADOS E ON(U.ID_EMP=E.ID_EMP)";
                            
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
}
