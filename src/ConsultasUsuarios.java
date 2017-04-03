
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

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
    
    public void insertar(String usr, String ctr, String ctrC, String tipo){
        if(usr.equals("")||ctr.equals("")||ctrC.equals("")||tipo.equals("")){
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
            String sql = "insert into Usuario values ('"+usr+"','"+ctr+"','"+ctrC+"','"+tipo+"')";
            stmt.executeUpdate(sql);
            showMessageDialog(null,"Se insertó correctamente");
            }catch(Exception e){
                showMessageDialog(null, "Error: "+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        
        
    }
    
   // public void mostrarTabla(JTabla tabla){
        
    //}
}
