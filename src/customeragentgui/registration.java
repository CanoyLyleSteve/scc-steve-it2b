/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customeragentgui;
import config.dbConnect;
import config.passwordHasher;
import static config.passwordHasher.hashPassword;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
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
      Color hover = new Color(0,153,0);
        Color defbutton = new Color(255,255,255);
        Border empty = BorderFactory.createEmptyBorder();
        
        void buttonBorderAnimation(JButton button){
            button.setBackground(hover);
             button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              button.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        }
        void buttonDefualtColor(JButton button){
             button.setBackground(defbutton);
            button.setBorder(empty);
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
        jPanel3 = new javax.swing.JPanel();
        fname = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        usname = new javax.swing.JTextField();
        register = new javax.swing.JButton();
        Show = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        ty = new javax.swing.JComboBox<>();
        contact1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cancel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        secQ = new javax.swing.JComboBox<>();
        secA = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 380));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel3.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 210, 40));

        lname.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        jPanel3.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 210, 40));

        email.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 210, 40));

        usname.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        usname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "User Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        usname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usnameActionPerformed(evt);
            }
        });
        jPanel3.add(usname, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 210, 40));

        register.setBackground(new java.awt.Color(255, 255, 255));
        register.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        register.setText("Register ");
        register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerMouseExited(evt);
            }
        });
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        jPanel3.add(register, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 90, 30));

        Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-hide-16.png"))); // NOI18N
        Show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowMouseClicked(evt);
            }
        });
        jPanel3.add(Show, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, 30));

        password1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        jPanel3.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 210, 40));

        ty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", "Customer", "Nestea" }));
        ty.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Account Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11)))); // NOI18N
        ty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tyActionPerformed(evt);
            }
        });
        jPanel3.add(ty, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 210, 40));

        contact1.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        contact1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contact1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Contact Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        contact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact1ActionPerformed(evt);
            }
        });
        jPanel3.add(contact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 210, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-account-24.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, -1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-24.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-phone-24.png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, 20));

        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-exit-button-30.png"))); // NOI18N
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });
        jPanel3.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 30, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-email-24.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, -1, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-password-24_1.png"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-username-24.png"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-24.png"))); // NOI18N
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 20, 20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-registration-40.png"))); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 40, 40));

        jLabel4.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Registration Form");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 170, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bckregister.jpg"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 400, 530));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-password-24_1.png"))); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, -1, -1));
        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        secQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What was the name of your first school?", "What's the lastname of your Mother?", "What is the name of the city where you were born?", "What is your dream job?", "What's your birth month?" }));
        secQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secQActionPerformed(evt);
            }
        });
        jPanel3.add(secQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 220, 30));
        jPanel3.add(secA, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 220, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void usnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usnameActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
       
if (fname.getText().isEmpty() 
        || lname.getText().isEmpty() 
        || email.getText().isEmpty() 
        || contact1.getText().isEmpty()
        || usname.getText().isEmpty() 
        || password1.getText().isEmpty()
        || secQ.getSelectedItem() == null
        || secA.getText().isEmpty()) {

    JOptionPane.showMessageDialog(null, "All fields are required");
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
} else if (password1.getText().length() < 8) {
    JOptionPane.showMessageDialog(null, "Password should have at least 8 characters");
    password1.setText("");
    return;
} else if (duplicateCheck()) {
    System.out.println("Duplicate Exist");
    return;
} else {
    dbConnect dbc = new dbConnect();

    try {
        String hashedPassword = hashPassword(password1.getText());

        String sql = "INSERT INTO users (u_fname, u_lname, u_email, u_contact1, u_ty, u_usname, u_password1, u_status, security_question, security_answer) "
                + "VALUES ('" + fname.getText() + "','" + lname.getText() + "','" + email.getText() + "',"
                + "'" + contact1.getText() + "','" + ty.getSelectedItem() + "','" + usname.getText() + "','" + hashedPassword + "','Active',"
                + "'" + secQ.getSelectedItem() + "','" + secA.getText() + "')";

        if (dbc.insertData(sql)) {
            JOptionPane.showMessageDialog(null, "Registration Successful!");
            login lfr = new login();
            lfr.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Connection Error");
        }

    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(registration.class.getName()).log(Level.SEVERE, null, ex);
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

    private void registerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseEntered
         buttonBorderAnimation(register);
    }//GEN-LAST:event_registerMouseEntered

    private void registerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseExited
       buttonDefualtColor(register);
    }//GEN-LAST:event_registerMouseExited

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        login ln = new login();
       ln.setVisible(true);
       this.dispose();
                       
    }//GEN-LAST:event_cancelMouseClicked

    private void ShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowMouseClicked
         if (password1.getEchoChar() == '\u2022') { 
        password1.setEchoChar((char) 0); 
    } else {
        password1.setEchoChar('\u2022'); 
    }
    }//GEN-LAST:event_ShowMouseClicked

    private void secQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_secQActionPerformed

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
    private javax.swing.JLabel Show;
    private javax.swing.JLabel cancel;
    private javax.swing.JTextField contact1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lname;
    private javax.swing.JPasswordField password1;
    private javax.swing.JButton register;
    private javax.swing.JTextField secA;
    private javax.swing.JComboBox<String> secQ;
    private javax.swing.JComboBox<String> ty;
    private javax.swing.JTextField usname;
    // End of variables declaration//GEN-END:variables
}
