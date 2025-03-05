/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.dbConnect;
import customeragentgui.login;
import customeragentgui.registration;
import static customeragentgui.registration.email1;
import static customeragentgui.registration.username;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class createUserForm extends javax.swing.JFrame {

    /**
     * Creates new form createUserForm
     */
    public createUserForm() {
        initComponents();
    }
    public boolean duplicateCheck(){
       dbConnect dbc = new dbConnect();
       try{
           String query = "SELECT * FROM users  WHERE u_usname = '" + ussname.getText()+ "'OR u_email ='" + email.getText()+ "'";
            ResultSet resultSet = dbc.getData(query);
            if(resultSet.next()){
               email1 =resultSet.getString("u_email");
                if(email1.equals(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email is already used!");
                    email.setText("");
                }
                username =resultSet.getString("u_usname");
                if(username.equals(ussname.getText())){
                    JOptionPane.showMessageDialog(null, "Username is already used!!");
                    ussname.setText("");
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
    public boolean updateCheck(){
       dbConnect dbc = new dbConnect();
       try{
           String query = "SELECT * FROM users  WHERE (u_usname = '" + ussname.getText()+ "'OR u_email ='" + email.getText()+ "')AND u_id !='"+uid.getText()+"'";
            ResultSet resultSet = dbc.getData(query);
            if(resultSet.next()){
                email1 =resultSet.getString("u_email");
                if(email1.equals(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email is already used!");
                    email.setText("");
                }
                username =resultSet.getString("u_usname");
                if(username.equals(ussname.getText())){
                    JOptionPane.showMessageDialog(null, "Username is already used!!");
                    ussname.setText("");
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

        jPanel3 = new javax.swing.JPanel();
        fname = new javax.swing.JTextField();
        lastname2 = new javax.swing.JLabel();
        lastname = new javax.swing.JLabel();
        lastname3 = new javax.swing.JLabel();
        lastname1 = new javax.swing.JLabel();
        lastname4 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        ussname = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        lastname5 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        lastname6 = new javax.swing.JLabel();
        ty = new javax.swing.JComboBox<>();
        contact1 = new javax.swing.JTextField();
        us = new javax.swing.JComboBox<>();
        lastname7 = new javax.swing.JLabel();
        uid = new javax.swing.JTextField();
        lastname8 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        ref = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel3.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 160, 30));

        lastname2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname2.setForeground(new java.awt.Color(255, 255, 255));
        lastname2.setText("First Name:");
        jPanel3.add(lastname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 90, 50));

        lastname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname.setForeground(new java.awt.Color(255, 255, 255));
        lastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname.setText("Email:");
        jPanel3.add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 90, 40));

        lastname3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname3.setForeground(new java.awt.Color(255, 255, 255));
        lastname3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname3.setText("Contact N:");
        jPanel3.add(lastname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 90, 40));

        lastname1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname1.setForeground(new java.awt.Color(255, 255, 255));
        lastname1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname1.setText("Username:");
        jPanel3.add(lastname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 110, 40));

        lastname4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname4.setForeground(new java.awt.Color(255, 255, 255));
        lastname4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname4.setText("Last Name:");
        jPanel3.add(lastname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 90, 40));

        lname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 160, 30));

        email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 160, 30));

        ussname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ussname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ussname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ussnameActionPerformed(evt);
            }
        });
        jPanel3.add(ussname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 160, 30));

        add.setBackground(new java.awt.Color(0, 153, 51));
        add.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel3.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 80, 30));

        lastname5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname5.setForeground(new java.awt.Color(255, 255, 255));
        lastname5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname5.setText("User Status:");
        jPanel3.add(lastname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 90, 40));

        password1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 160, 30));

        lastname6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname6.setForeground(new java.awt.Color(255, 255, 255));
        lastname6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname6.setText("Password:");
        jPanel3.add(lastname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 90, 40));

        ty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", "Customer", "Nestea" }));
        ty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tyActionPerformed(evt);
            }
        });
        jPanel3.add(ty, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 160, 30));

        contact1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        contact1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact1ActionPerformed(evt);
            }
        });
        jPanel3.add(contact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 160, 30));

        us.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usActionPerformed(evt);
            }
        });
        jPanel3.add(us, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 160, 30));

        lastname7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname7.setForeground(new java.awt.Color(255, 255, 255));
        lastname7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname7.setText("Account Type");
        jPanel3.add(lastname7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 90, 40));

        uid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        uid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        uid.setEnabled(false);
        uid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidActionPerformed(evt);
            }
        });
        jPanel3.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 160, 30));

        lastname8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname8.setForeground(new java.awt.Color(255, 255, 255));
        lastname8.setText("User ID:");
        jPanel3.add(lastname8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 60, 30));

        update.setBackground(new java.awt.Color(0, 153, 51));
        update.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        update.setText("Update");
        update.setEnabled(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel3.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 80, 30));

        clear.setBackground(new java.awt.Color(0, 153, 51));
        clear.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel3.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 80, 30));

        delete.setBackground(new java.awt.Color(0, 153, 51));
        delete.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel3.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 80, 30));

        ref.setBackground(new java.awt.Color(0, 153, 51));
        ref.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        ref.setText("Refresh");
        ref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refActionPerformed(evt);
            }
        });
        jPanel3.add(ref, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 80, 30));

        cancel.setBackground(new java.awt.Color(0, 153, 51));
        cancel.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel3.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 80, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed

    }//GEN-LAST:event_fnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void ussnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ussnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ussnameActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

           if (fname.getText().isEmpty() 
                || lname.getText().isEmpty() 
                || email.getText().isEmpty() 
                || contact1.getText().isEmpty()
                || ussname.getText().isEmpty() 
               || password1.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "All field are required");
            }else if(password1.getText().length()< 8){
            JOptionPane.showMessageDialog(null, "Password should have at least 8 characters");
        password1.setText("");
        }else if(duplicateCheck()){
           System.out.println("Duplicate Exist");
     
         }else{
            
        
        dbConnect dbc = new dbConnect();
 if (dbc.insertData("INSERT INTO users (u_fname, u_lname, u_email, u_contact1, u_ty, u_usname, u_password1, u_status) "
                + "VALUES ('" + fname.getText() + "','" + lname.getText() + "','" + email.getText() + "',"
                + "'" + contact1.getText() + "','" + ty.getSelectedItem() + "','" + ussname.getText() + "','" + password1.getText()+"','"+us.getSelectedItem()+"')"))
                    {
    JOptionPane.showMessageDialog(null, "Registration Successful!");
    customers ctr = new customers();
    ctr.setVisible(true);
    this.dispose();
} else {
    JOptionPane.showMessageDialog(null, "Connection Error");

}
           
} 
    }//GEN-LAST:event_addActionPerformed

    private void tyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tyActionPerformed

    private void contact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact1ActionPerformed

    private void usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usActionPerformed

    private void uidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
          if (fname.getText().isEmpty() 
                || lname.getText().isEmpty() 
                || email.getText().isEmpty() 
                || contact1.getText().isEmpty()
                || ussname.getText().isEmpty() 
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
        }else if(updateCheck()){
           System.out.println("Duplicate Exist");
     
         }else{
        
   dbConnect dbc = new dbConnect();
   dbc.updateData("UPDATE users SET u_fname ='"+fname.getText()+"',u_lname='"+lname.getText()+"'"
           + ",u_email='"+email.getText()+"',u_contact1 ='"+contact1.getText()+"',"
          + "u_password1='"+password1.getText()+"',u_ty ='"+ty.getSelectedItem()+"',u_status = '"+us.getSelectedItem()+"',"
          + "u_usname ='"+ussname.getText()+"'WHERE u_id ='"+uid.getText()+"'");
          JOptionPane.showMessageDialog(null,"Updated Successfully!");
          customers ctr = new customers();
          ctr.setVisible(true);
          this.dispose();
   
    }//GEN-LAST:event_updateActionPerformed
    }
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void refActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
       customers ctr = new customers();
       ctr.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

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
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createUserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton add;
    private javax.swing.JButton cancel;
    private javax.swing.JButton clear;
    public javax.swing.JTextField contact1;
    private javax.swing.JButton delete;
    public javax.swing.JTextField email;
    public javax.swing.JTextField fname;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lastname;
    private javax.swing.JLabel lastname1;
    private javax.swing.JLabel lastname2;
    private javax.swing.JLabel lastname3;
    private javax.swing.JLabel lastname4;
    private javax.swing.JLabel lastname5;
    private javax.swing.JLabel lastname6;
    private javax.swing.JLabel lastname7;
    private javax.swing.JLabel lastname8;
    public javax.swing.JTextField lname;
    public javax.swing.JPasswordField password1;
    private javax.swing.JButton ref;
    public javax.swing.JComboBox<String> ty;
    public javax.swing.JTextField uid;
    public javax.swing.JButton update;
    public javax.swing.JComboBox<String> us;
    public javax.swing.JTextField ussname;
    // End of variables declaration//GEN-END:variables
}
