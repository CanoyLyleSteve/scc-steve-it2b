/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;
import admin.*;
import java.awt.Color;
import config.Session;
import config.dbConnect;
import config.passwordHasher;
import customeragentgui.login;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import user.userdashboard;

/**
 *
 * @author DANIEL FAILADONA
 */
public class ForgetPass extends javax.swing.JFrame {

    private String correctAnswer;

    private Color H;
    Color h = new Color(153,204,255);
    private Color D;
    Color d = new Color(240,240,240);
    
    public ForgetPass() {
        initComponents();
    }
    
    
    public static String Phone, usname;
    
    
    
    
    public void logEvent(int userId, String username, String action) {
    dbConnect dbc = new dbConnect();
    Connection con = dbc.getConnection();
    PreparedStatement pstmt = null;
    Timestamp time = new Timestamp(new Date().getTime());

    if (con == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.");
        return;
    }

    try {
        String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) VALUES (?, ?, ?, ?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        pstmt.setString(2, username);
        pstmt.setTimestamp(3, time);
        pstmt.setString(4, action);

        pstmt.executeUpdate();
        System.out.println("Login log recorded successfully.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
        }
    }
}

    
    
    
    
    
    private void fetchSecurityQuestion() {
    String username = un.getText().trim(); // Trim whitespace

    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your username.");
        return;
    }

    dbConnect db = new dbConnect();
    Connection con = db.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
        return;
    }

    try {
        String sql = "SELECT security_question, security_answer FROM users WHERE u_usname = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, username);
        rs = stmt.executeQuery();

        if (rs.next()) {
            String question = rs.getString("security_question");
            String answer = rs.getString("security_answer");

            if (question != null && !question.trim().isEmpty()) {
                sq.removeAllItems();
                sq.addItem(question);
                sq.setEnabled(true);
                correctAnswer = answer; // Could still be null if not set
                confirm.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No security question set for this account.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


   private void resetPassword() {
   String enteredAnswer = ans.getText().trim();
String newPassword = new String(Newpass.getPassword()).trim();
String username = un.getText().trim();

// Validate initial conditions
if (correctAnswer == null || username.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please search for your username first.");
    return;
}

if (enteredAnswer.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your security answer.");
    return;
}


if (!enteredAnswer.equalsIgnoreCase(correctAnswer)) {
    JOptionPane.showMessageDialog(this, "Incorrect security answer. You cannot proceed.");
    return;  
}

if (newPassword.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Password cannot be empty.");
    return;
}

try {
   
    String hashedPassword = passwordHasher.hashPassword(newPassword);

    dbConnect db = new dbConnect();
    try (Connection con = db.getConnection()) {
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
            return;
        }

        String sql = "UPDATE users SET u_password1 = ? WHERE u_usname = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, hashedPassword);
            stmt.setString(2, username);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password successfully reset!");
                dispose(); // Close the window/form
            } else {
                JOptionPane.showMessageDialog(this, "Username not found or password update failed.");
            }
        }

    }
} catch (NoSuchAlgorithmException ex) {
    JOptionPane.showMessageDialog(this, "Error hashing password: " + ex.getMessage());
    ex.printStackTrace();
} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "An error occurred while updating the password.");
    e.printStackTrace();
}


   }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new javax.swing.JPanel();
        confirm = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        sq = new javax.swing.JComboBox<>();
        ans = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        acc_id1 = new javax.swing.JLabel();
        confirm1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Newpass = new javax.swing.JPasswordField();
        Cpass = new javax.swing.JPasswordField();
        acc_id3 = new javax.swing.JLabel();
        check = new javax.swing.JCheckBox();
        check1 = new javax.swing.JCheckBox();
        acc_id2 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Main.setBackground(new java.awt.Color(255, 255, 255));
        Main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MainMouseClicked(evt);
            }
        });
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmMouseExited(evt);
            }
        });
        confirm.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Confirm");
        confirm.add(jLabel11);
        jLabel11.setBounds(0, 10, 90, 10);

        Main.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 90, 30));

        sq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What was the name of your first school?", "What's the lastname of your Mother?", "What is the name of the city where you were born?", "What is your dream job?", "What's your birth month?" }));
        sq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqActionPerformed(evt);
            }
        });
        Main.add(sq, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 220, 30));
        Main.add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 220, 30));
        Main.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 220, 30));

        acc_id1.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        acc_id1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id1.setText("Username:");
        Main.add(acc_id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, 30));

        confirm1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirm1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirm1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirm1MouseExited(evt);
            }
        });
        confirm1.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Search");
        confirm1.add(jLabel12);
        jLabel12.setBounds(0, 10, 90, 10);

        Main.add(confirm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 90, 30));

        Newpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewpassActionPerformed(evt);
            }
        });
        Main.add(Newpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 220, 30));

        Cpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CpassActionPerformed(evt);
            }
        });
        Main.add(Cpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 220, 30));

        acc_id3.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        acc_id3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id3.setText("Confirm Password:");
        Main.add(acc_id3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 180, 30));

        check.setBackground(new java.awt.Color(51, 51, 51));
        check.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        check.setForeground(new java.awt.Color(255, 255, 255));
        check.setText("Show");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });
        Main.add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, 30));

        check1.setBackground(new java.awt.Color(51, 51, 51));
        check1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        check1.setForeground(new java.awt.Color(255, 255, 255));
        check1.setText("Show");
        check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check1ActionPerformed(evt);
            }
        });
        Main.add(check1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, -1, 30));

        acc_id2.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        acc_id2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id2.setText("Enter New Password:");
        Main.add(acc_id2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 180, 30));

        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-logout-rounded-30.png"))); // NOI18N
        jLabel10.setText("Back");
        logout.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        Main.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Bell MT", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORGOT PASSWORD");
        Main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 390, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/forg-removebg-preview.png"))); // NOI18N
        Main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 230, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmMouseClicked
        String u = un.getText().trim();
        String a = ans.getText().trim();
        String np = new String(Newpass.getPassword()).trim();
        String cp = new String(Cpass.getPassword()).trim();
        dbConnect connector = new dbConnect();
        int userId = 0;


        
        if (u.isEmpty() || a.isEmpty() || np.isEmpty() || cp.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please fill in all fields before submitting.");
            return;
        }else if(!np.equals(cp))
        {
            JOptionPane.showMessageDialog(this, "Password does not match");
        }else
        {
            // Call resetPassword method to validate and update password
            resetPassword();
            
            
            try 
            {
                String query = "SELECT u_id FROM users WHERE u_usname = '" + u + "'";
                PreparedStatement pstmt = connector.getConnection().prepareStatement(query);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) 
                {
                    System.out.println("user was recieved");
                    userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
                }
            } catch (SQLException ex) 
            {
                System.out.println("SQL Exception: " + ex);
            }
            System.out.println("username: "+u);
            System.out.println("user ID: "+userId);

            logEvent(userId, u, "Forgot, and changed their password");
            
            
            login lg = new login();
            lg.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_confirmMouseClicked

    private void confirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmMouseEntered
        confirm.setBackground(h);
    }//GEN-LAST:event_confirmMouseEntered

    private void confirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmMouseExited
        confirm.setBackground(d);
    }//GEN-LAST:event_confirmMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    
    }//GEN-LAST:event_formWindowActivated

    private void confirm1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm1MouseClicked
      dbConnect connector = new dbConnect();
        String username = un.getText();  
        String answer = null;
        
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your username.");
        return;
    }

    
    dbConnect db = new dbConnect();  
    Connection con = db.getConnection(); 
        try {
            String query = "SELECT * FROM users WHERE u_usname = '" + username + "'"; 
            PreparedStatement pstmt = connector.getConnection().prepareStatement(query);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                answer = resultSet.getString("security_answer");
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
        return;
    }else if(answer.equals(""))
    {
        JOptionPane.showMessageDialog(this, "You did not set a password recovery for your account.");
        login l = new login();
        l.setVisible(true);
        this.dispose();
    }else
    {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT security_question, security_answer FROM users WHERE u_usname = ?"
            );
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sq.removeAllItems();
                sq.addItem(rs.getString("security_question"));
                sq.setEnabled(true);
                correctAnswer = rs.getString("security_answer");
                confirm.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Username not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
        } finally {
            try {
                if (con != null) {
                    con.close(); // Close the connection after use
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
                                        
    }//GEN-LAST:event_confirm1MouseClicked

    private void confirm1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_confirm1MouseEntered

    private void confirm1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirm1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_confirm1MouseExited

    private void NewpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewpassActionPerformed

    private void CpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CpassActionPerformed

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        boolean isSelected = check.isSelected();

        if (isSelected) {
            Newpass.setEchoChar((char)0);
        } else {
            Newpass.setEchoChar('*');
        }
    }//GEN-LAST:event_checkActionPerformed

    private void check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check1ActionPerformed
        boolean isSelected = check1.isSelected();

        if (isSelected) {
            Cpass.setEchoChar((char)0);
        } else {
            Cpass.setEchoChar('*');
        }
    }//GEN-LAST:event_check1ActionPerformed

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(d);
    }//GEN-LAST:event_logoutMouseExited

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(h);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        userdashboard ed = new userdashboard();
        ed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void sqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sqActionPerformed

    private void MainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MainMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MainMouseClicked

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
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgetPass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Cpass;
    private javax.swing.JPanel Main;
    private javax.swing.JPasswordField Newpass;
    private javax.swing.JLabel acc_id1;
    private javax.swing.JLabel acc_id2;
    private javax.swing.JLabel acc_id3;
    private javax.swing.JTextField ans;
    private javax.swing.JCheckBox check;
    private javax.swing.JCheckBox check1;
    private javax.swing.JPanel confirm;
    private javax.swing.JPanel confirm1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel logout;
    private javax.swing.JComboBox<String> sq;
    private javax.swing.JTextField un;
    // End of variables declaration//GEN-END:variables
}
