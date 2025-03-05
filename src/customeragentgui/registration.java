/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customeragentgui;
import config.dbConnect;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author User
 */
public class registration extends javax.swing.JFrame {

    /**
     * Creates new form registration
     */
    public registration() {
        initComponents();
       }
    public static String email1,username;
   public boolean duplicateCheck(){
       dbConnect dbc = new dbConnect();
       try{
           String query = "SELECT * FROM users  WHERE u_usname = '" + usname.getText()+ "'OR u_email ='" + email.getText()+ "'";
            ResultSet resultSet = dbc.getData(query);
            if(resultSet.next()){
                email1 =resultSet.getString("u_email");
                if(email1.equals(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email is already used!");
                    email.setText("");
                }
                username =resultSet.getString("u_usname");
                if(username.equals(usname.getText())){
                    JOptionPane.showMessageDialog(null, "Username is already used!!");
                    usname.setText("");
                }
                 return true;
            }else{  
                return false;
            }
       }catch(SQLException ex){
           System.out.println(""+ex);
           return false;
           
   }
   }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        fname = new javax.swing.JTextField();
        lastname2 = new javax.swing.JLabel();
        lastname = new javax.swing.JLabel();
        lastname3 = new javax.swing.JLabel();
        lastname1 = new javax.swing.JLabel();
        lastname4 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        usname = new javax.swing.JTextField();
        register = new javax.swing.JButton();
        cancel2 = new javax.swing.JButton();
        lastname5 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        lastname6 = new javax.swing.JLabel();
        ty = new javax.swing.JComboBox<>();
        contact1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRATION FORM ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 400, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 70));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel3.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 160, 30));

        lastname2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname2.setForeground(new java.awt.Color(255, 255, 255));
        lastname2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname2.setText("First Name");
        jPanel3.add(lastname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 50));

        lastname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname.setForeground(new java.awt.Color(255, 255, 255));
        lastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname.setText("Email");
        jPanel3.add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 90, 40));

        lastname3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname3.setForeground(new java.awt.Color(255, 255, 255));
        lastname3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname3.setText("Contact N");
        jPanel3.add(lastname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, 40));

        lastname1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname1.setForeground(new java.awt.Color(255, 255, 255));
        lastname1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname1.setText("Username");
        jPanel3.add(lastname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 110, 40));

        lastname4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname4.setForeground(new java.awt.Color(255, 255, 255));
        lastname4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname4.setText("Last Name");
        jPanel3.add(lastname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 90, 40));

        lname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 160, 30));

        email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 160, 30));

        usname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        usname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usnameActionPerformed(evt);
            }
        });
        jPanel3.add(usname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 160, 30));

        register.setBackground(new java.awt.Color(0, 153, 51));
        register.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        register.setText("Register ");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        jPanel3.add(register, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 90, 30));

        cancel2.setBackground(new java.awt.Color(204, 51, 0));
        cancel2.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cancel2.setText("Cancel");
        cancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel2ActionPerformed(evt);
            }
        });
        jPanel3.add(cancel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 90, 30));

        lastname5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname5.setForeground(new java.awt.Color(255, 255, 255));
        lastname5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname5.setText("Account Type");
        jPanel3.add(lastname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 90, 40));

        password1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 160, 30));

        lastname6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname6.setForeground(new java.awt.Color(255, 255, 255));
        lastname6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname6.setText("Password");
        jPanel3.add(lastname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 90, 40));

        ty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", "Customer", "Nestea" }));
        ty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tyActionPerformed(evt);
            }
        });
        jPanel3.add(ty, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 160, 30));

        contact1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        contact1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact1ActionPerformed(evt);
            }
        });
        jPanel3.add(contact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 160, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 410, 480));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Capture-removebg-preview (2).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 380, 90));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cccccc-removebg-preview.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 280, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void usnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usnameActionPerformed

    private void cancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel2ActionPerformed
       login ln = new login();
       ln.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_cancel2ActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
       if (fname.getText().isEmpty() 
                || lname.getText().isEmpty() 
                || email.getText().isEmpty() 
                || contact1.getText().isEmpty()
                || usname.getText().isEmpty() 
               || password1.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "All field are required");
            return;
       }
       String contactPattern = "^[0-9]{11}$"; 
    if (!contact1.getText().matches(contactPattern)) {
        JOptionPane.showMessageDialog(null, "Invalid contact number. It must be exactly 11 digits and contain numbers only.");
        contact1.setText(""); 
        return;
    }
       
       String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    if (!email.getText().matches(emailPattern)) {
        JOptionPane.showMessageDialog(null, "Invalid email format. Please enter a valid email.");
        email.setText(""); 
        return;
    
        
        }else if(password1.getText().length()< 8){
        JOptionPane.showMessageDialog(null, "Password should have at least 8 characters");
        password1.setText("");
        }else if(duplicateCheck()){
           System.out.println("Duplicate Exist");
     
         }else{
            
        
        dbConnect dbc = new dbConnect();
if (dbc.insertData("INSERT INTO users (u_fname, u_lname, u_email, u_contact1, u_ty, u_usname, u_password1, u_status) "
    + "VALUES ('" + fname.getText() + "','" + lname.getText() + "','" + email.getText() + "',"
    + "'" + contact1.getText() + "','" + ty.getSelectedItem() + "','" + usname.getText() + "','" + password1.getText() + "','Active')")) {

    JOptionPane.showMessageDialog(null, "Registration Successful!");
    login lfr = new login();
    lfr.setVisible(true);
    this.dispose();
} else {
    JOptionPane.showMessageDialog(null, "Connection Error");

}
           
} 
        
    }//GEN-LAST:event_registerActionPerformed

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
   
      
        
    }//GEN-LAST:event_fnameActionPerformed

    private void tyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tyActionPerformed

    private void contact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel2;
    private javax.swing.JTextField contact1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lastname;
    private javax.swing.JLabel lastname1;
    private javax.swing.JLabel lastname2;
    private javax.swing.JLabel lastname3;
    private javax.swing.JLabel lastname4;
    private javax.swing.JLabel lastname5;
    private javax.swing.JLabel lastname6;
    private javax.swing.JTextField lname;
    private javax.swing.JPasswordField password1;
    private javax.swing.JButton register;
    private javax.swing.JComboBox<String> ty;
    private javax.swing.JTextField usname;
    // End of variables declaration//GEN-END:variables
}
