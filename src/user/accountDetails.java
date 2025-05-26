/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.sun.org.apache.bcel.internal.generic.Select;
import config.Session;
import config.dbConnect;
import customeragentgui.login;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class accountDetails extends javax.swing.JFrame {

    
    public accountDetails() {
        initComponents();
    }

     public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;

    public int FileExistenceChecker(String path) {
        File file = new File(path);
        String fileName = file.getName();

        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);

        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();

            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);

            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }

        return -1;
    }

    public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
        if (ImagePath != null) {
            MyImage = new ImageIcon(ImagePath);
        } else {
            MyImage = new ImageIcon(pic);
        }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void imageUpdater(String existingFilePath, String newFilePath) {
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: " + e);
            }
        } else {
            try {
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error on update!");
            }
        }
    }
    
    public boolean updateCheck() {
    dbConnect dbc = new dbConnect();
    Session sess = Session.getInstance();

    int currentUID = sess.getUid();
    String inputUsername = usname.getText().trim(); // assuming usname is a JTextField

    // Update user interface fields
    iddisplay.setText("USER ID: " + currentUID);
    fname.setText(sess.getFname());
    lname.setText(sess.getLname());
    email.setText(sess.getEmail());
    usname.setText(sess.getUsername());

    System.out.println("[1] Trimmed input - Username: " + inputUsername);
    System.out.println("[2] Current Session UID: " + currentUID);

    String query = "SELECT u_id FROM users WHERE u_usname = ?";
    boolean isDuplicate = false;

    try (PreparedStatement stmt = dbc.getConnection().prepareStatement(query)) {
        stmt.setString(1, inputUsername);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int uid = resultSet.getInt("u_id");
                System.out.println("[3] Found UID with same username: " + uid);

                if (uid != currentUID) {
                    JOptionPane.showMessageDialog(null, "Username is already used.");
                    usname.setText("");
                    isDuplicate = true;
                    break;
                } else {
                    System.out.println("[4] Username belongs to current user.");
                }
            }
        }
    } catch (SQLException ex) {
        System.out.println("[ERROR] SQLException: " + ex.getMessage());
        return true; // Assume duplicate on error to be safe
    }

    System.out.println("[5] Final isDuplicate: " + isDuplicate);
    return isDuplicate;
}

     
      public void logEvent(int userId, String username, String action) 
    {
        dbConnect dbc = new dbConnect();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;
        Timestamp time = new Timestamp(new Date().getTime());

        try 
        {
            String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) "
                    + "VALUES ('" + userId + "', '" + username + "', '" + time + "', '" + action + "')";
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("Login log recorded successfully.");
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
        } finally 
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                if (con != null) 
                {
                    con.close();
                }
            } catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Select = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        nvg1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        iddisplay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        usname = new javax.swing.JTextField();
        ty = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        Remove = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        Confirm = new javax.swing.JButton();
        nvg = new javax.swing.JPanel();
        acc_fn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        acc_ln = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SelectMouseExited(evt);
            }
        });
        Select.setLayout(null);

        jLabel23.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Select");
        Select.add(jLabel23);
        jLabel23.setBounds(10, 0, 70, 30);

        jPanel1.add(Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 90, 30));

        nvg1.setBackground(new java.awt.Color(0, 0, 0));
        nvg1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LOGOUT");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        nvg1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 180, 50));

        iddisplay.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        iddisplay.setForeground(new java.awt.Color(255, 255, 255));
        iddisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iddisplay.setText("(UID)");
        nvg1.add(iddisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 170, 50));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" ACCOUNT INFORMATION");
        nvg1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, 50));

        jPanel1.add(nvg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 70));

        fname.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 210, 40));

        lname.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 210, 40));

        email.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 210, 40));

        usname.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        usname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "User Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        usname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usnameActionPerformed(evt);
            }
        });
        jPanel1.add(usname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 210, 40));

        ty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", "Customer", "Nestea" }));
        ty.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Account Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11)))); // NOI18N
        ty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tyActionPerformed(evt);
            }
        });
        jPanel1.add(ty, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 210, 40));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 170));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 200, 190));

        Remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RemoveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RemoveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RemoveMouseExited(evt);
            }
        });
        Remove.setLayout(null);

        jLabel24.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Remove");
        Remove.add(jLabel24);
        jLabel24.setBounds(0, 10, 70, 10);

        jPanel1.add(Remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 80, 30));

        Confirm.setText("Confirm");
        Confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConfirmMouseClicked(evt);
            }
        });
        jPanel1.add(Confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 670, 520));

        nvg.setBackground(new java.awt.Color(0, 0, 0));
        nvg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acc_fn.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        acc_fn.setForeground(new java.awt.Color(255, 255, 255));
        acc_fn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_fn.setText("USER");
        nvg.add(acc_fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 180, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-user-80-removebg-preview.png"))); // NOI18N
        nvg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 80, 100));

        acc_ln.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        acc_ln.setForeground(new java.awt.Color(255, 255, 255));
        acc_ln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_ln.setText("USER");
        nvg.add(acc_ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 180, 40));

        jLabel3.setFont(new java.awt.Font("Bell MT", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Change Pass");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        nvg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 220, 50));

        getContentPane().add(nvg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       login loginScreen = new login(); 
        JOptionPane.showMessageDialog(null, "LOG-OUT!");
        loginScreen.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel5MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
     
       
       
        
    }//GEN-LAST:event_formWindowActivated

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
  
    }//GEN-LAST:event_fnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void usnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usnameActionPerformed

    private void tyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tyActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       changePass cp = new changePass();
       cp.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void SelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseClicked
        //         imageuploadjava.txt
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {

                selectedFile = fileChooser.getSelectedFile();
                destination = "src/userimages/" + selectedFile.getName();
                path = selectedFile.getAbsolutePath();

                if (FileExistenceChecker(path) == 1) {
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path = "";
                } else {
                    image.setIcon(ResizeImage(path, null, image));
                    Select.setEnabled(false);
                    Remove.setEnabled(true);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_SelectMouseClicked

    private void SelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseEntered
   
    }//GEN-LAST:event_SelectMouseEntered

    private void SelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseExited
     
    }//GEN-LAST:event_SelectMouseExited

    private void RemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseClicked
        Remove.setEnabled(false);
        Select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_RemoveMouseClicked

    private void RemoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseEntered

    }//GEN-LAST:event_RemoveMouseEntered

    private void RemoveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseExited
   
    }//GEN-LAST:event_RemoveMouseExited

    private void ConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmMouseClicked
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        String fn = fname.getText().trim();
        String ln = lname.getText().trim();
       String p = ty.getSelectedItem().toString().trim();
        String uname = usname.getText().trim();
        dbConnect connector = new dbConnect();
        int userId = 0;
        String uname2 = null;
        String sq = "";
        String sa = "";

        if(uname.isEmpty() || ln.isEmpty() || fn.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Fill All Boxes");
        }else if(updateCheck())
        {
            System.out.println("Duplicate Exists");
        }else
        {
            try {
                    String query = "SELECT * FROM users WHERE u_id='" + sess.getUid() + "'";
                    ResultSet rs = dbc.getData(query);
                    if (rs.next()) 
                    {
                        String npass = rs.getString("u_password1");
                        String at = rs.getString("u_ty");
                        String s = rs.getString("u_status");
       dbc.updateData("UPDATE users SET u_fname = '" + fn + "', u_lname = '" + ln + "', u_usname = '" + uname + "',"
        + " u_password1 = '" + npass + "', u_ty = '" + p + "', u_status = '" + s + "',"
        + " u_image = '" + destination + "', security_question = '" + sq + "', security_answer = '" + sa + "'"
        + " WHERE u_id = '" + sess.getUid() + "'");



                        try 
                        {
                            String query2 = "SELECT * FROM users WHERE u_id = '" + sess.getUid() + "'";
                            PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

                            ResultSet resultSet = pstmt.executeQuery();

                            if (resultSet.next()) {
                                userId = resultSet.getInt("u_id");   // Update the outer `userId` correctly
                                uname2 = resultSet.getString("u_usname");
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQL Exception: " + ex);
                        }

                        logEvent(userId, uname2, "User Changed Their Details");

                       userdashboard us = new userdashboard();
                        us.setVisible(true);
                        this.dispose();

//                       
                    }
                }catch (SQLException ex) {
                System.out.println("" + ex);
            }
        }
        
                   
    }//GEN-LAST:event_ConfirmMouseClicked

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
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(accountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new accountDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirm;
    public javax.swing.JPanel Remove;
    public javax.swing.JPanel Select;
    private javax.swing.JLabel acc_fn;
    private javax.swing.JLabel acc_ln;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel iddisplay;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lname;
    private javax.swing.JPanel nvg;
    private javax.swing.JPanel nvg1;
    private javax.swing.JComboBox<String> ty;
    private javax.swing.JTextField usname;
    // End of variables declaration//GEN-END:variables
}
