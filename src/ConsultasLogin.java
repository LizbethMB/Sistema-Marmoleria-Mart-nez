/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
/**
 *
 * @author Carla
 */
public class ConsultasLogin {
    Connection cn;
    ConsultasLogin(){
        cn=(new ConexionDB()).conectar();
    }
    
    public void inicio(String usuario, String contraseña,JFrame ventana ){
        
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresa un Usuario");
            return;
        }
        if (contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresa tu contraseña");
            return;
        }
        
        String tipo="";
        String empId="";
        String sql="SELECT ID_EMP , TIPO FROM Usuarios WHERE usuario='"+usuario+"' and contraseña='"+contraseña+"' and estatus='Activo'";
        System.out.println(sql);
        try {
            Statement stmt=cn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next())
            {
               tipo=rs.getString("Tipo");
               empId=rs.getString("ID_EMP");
            }
            if(tipo.equals("Administrador"))
            {     
               ventana.dispose();
               Administrador admin = new Administrador(); 
               admin.setVisible(true);
               admin.pack();

            }else if(tipo.equals("Vendedor")){
                ventana.dispose();
                Vendedor ven = new Vendedor(empId); 
                ven.setVisible(true);
                ven.pack();  
            }else{
                showMessageDialog(null,"No existen tus datos");
            }

        }catch(SQLException ex){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        
        
    
    
    
}
