/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.sql.ResultSet;           // For ResultSet
import java.sql.SQLException;         // For SQLException
import java.util.ArrayList;          // For ArrayList
import java.util.List;               // For List
import javax.swing.table.DefaultTableModel;  // For DefaultTableModel
import config.dbConnect;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;
import config.Session;
import static java.awt.Color.black;
import static java.awt.Color.red;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
public class productform extends javax.swing.JFrame {

     private Color H;
    Color h = new Color(204,255,255);
    private Color D;
    Color d = new Color(240,240,240);
    
    public productform() {
        initComponents();
        NotShowDeletedUsers();
//    displayData();
    }
    boolean addClickable  = true;
    
    
    public static String pname;

    public boolean updateCheck() {
        dbConnect dbc = new dbConnect();
        String u = PID.getText().trim();
        String mn = name.getText().trim();

        try {
            String query = "SELECT * FROM product WHERE (p_name='" + mn + "') AND p_id != '" + u + "'";
            ResultSet resultSet = dbc.getData(query);
            if (resultSet.next()) 
            {
                pname = resultSet.getString("p_name");
                if (pname.equals(mn)) 
                {
                    JOptionPane.showMessageDialog(null, "Car sales record already exists.");
                    name.setText("");
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }
    
    
    
    
    
    public boolean duplicateCheck() {
        dbConnect dbc = new dbConnect();
        String mn = name.getText().trim();

        try {
            String query = "SELECT * FROM product WHERE p_name='" + mn + "'"; //If output mentions something about ''', there is a missing '
            ResultSet resultSet = dbc.getData(query);
            if (resultSet.next()) {
                pname = resultSet.getString("p_name");
                if (pname.equals(mn)) {
                    JOptionPane.showMessageDialog(null, "Car sales record already exists.");
                    name.setText("");
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
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
    
    
    
    public void NotShowDeletedUsers() 
    {
        // Create a list to store filtered row data
        List<Object[]> rowData = new ArrayList<>();

        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT * FROM product");

            while (rs.next()) {
                // Store each column value in a separate variable
                String u = rs.getString("p_id");
                String pn = rs.getString("p_name");
                String pp = rs.getString("p_price");
                String status = rs.getString("p_status");
                

                // Check if the user status is not "Deleted"
                if (!status.equals("Deleted")) {
                    
                 
                    rowData.add(new Object[]{
                        u,
                        pn,
                        pp, 
                        status 
                    });
                    
                }
            }

           
            SwingUtilities.invokeLater(() -> {
                
                DefaultTableModel model = new DefaultTableModel(
                        new String[]{"ID", "Car Name", "Price", "Status"}, 0
                );
                for (Object[] row : rowData) {
                    model.addRow(row);
                }
                table.setModel(model);
                table.repaint(); // Force visual refresh
            });


            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    

    public void displayData()
    {
        try
        {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT * FROM product");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException ex)
        {
            System.out.println("Errors: "+ex.getMessage());
        }
    }
    
    
    private void loadUsersData() 
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table before reloading

        String sql = "SELECT * FROM product";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vali", "root", "");
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) 
        {

            while (rs.next()) 
            {
                // Check if the user's status is not "Deleted"
                String userStatus = rs.getString("p_status");
                if (!"Deleted".equals(userStatus)) 
                {
                    model.addRow(new Object[]
                    {
                        rs.getInt("u_id"),
                        rs.getString("p_name"),
                        rs.getString("p_price"),
                        rs.getString("p_status"),
                    });
                }
            }
        } catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this, "Error loading user data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    
    
    
    public void logEvent(int userId, String username, String action) 
    {
       if (username == null || username.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Username is missing. Cannot record log.");
        return;
    }

    dbConnect dbc = new dbConnect();
    Connection con = dbc.getConnection();
    PreparedStatement pstmt = null;
    Timestamp time = new Timestamp(new Date().getTime());

    try {
        String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) VALUES (?, ?, ?, ?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        pstmt.setString(2, username);
        pstmt.setTimestamp(3, time);
        pstmt.setString(4, action);

        pstmt.executeUpdate();
        System.out.println("Log recorded successfully.");
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
    
    
    
    
    
    
  private void deleteUser() {
    dbConnect dbc = new dbConnect();
    Session sess = Session.getInstance();
    dbConnect connector = new dbConnect();

    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        return;
    }

    try {
        int productId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this user?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;  // User cancelled deletion
        }

        // 1. Check if product exists
        String productQuery = "SELECT p_id, p_name, p_price FROM product WHERE p_id = ?";
        PreparedStatement pstmt = connector.getConnection().prepareStatement(productQuery);
        pstmt.setInt(1, productId);

        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            JOptionPane.showMessageDialog(this, "Product not found.");
            return;
        }

        String pid = rs.getString("p_id");
        String pn = rs.getString("p_name");
        String pr = rs.getString("p_price");

        // 2. Mark product as deleted (build full SQL string for updateData)
        String updateQuery = "UPDATE product SET p_status = 'Deleted' WHERE p_id = " + pid;
        dbc.updateData(updateQuery);

        // 3. Get current session user info
        int sessionUserId = sess.getUid();
        if (sessionUserId <= 0) {
            System.out.println("Invalid session user ID. Cannot log deletion.");
            return;
        }

        // Assuming the user ID column is named 'uid' (not 'u_id')
             String userQuery = "SELECT u_id, u_usname FROM users WHERE u_id = ?";
             String sql = "SELECT u_id, u_usname FROM users"; 

PreparedStatement pstmt2 = connector.getConnection().prepareStatement(userQuery);
pstmt2.setInt(1, sessionUserId);

ResultSet rs2 = pstmt2.executeQuery();
 while (rs.next()) {
            int userId = rs.getInt("u_id");        // use exact column names
            String username = rs.getString("u_usname");
             System.out.println("User ID: " + userId + ", Username: " + username);
        

       
    // 4. Reload user data on UI
    loadUsersData();

    // 5. Log the event with valid user ID
    logEvent(userId, username, "Admin Delete Car: " + pn);
}



    

        // 4. Reload user data on UI
       

    } catch (SQLException ex) {
        System.out.println("SQL Exception: " + ex);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid product ID selected.");
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
        Header = new javax.swing.JPanel();
        add = new javax.swing.JPanel();
        ad = new javax.swing.JLabel();
        update = new javax.swing.JPanel();
        ad1 = new javax.swing.JLabel();
        add4 = new javax.swing.JPanel();
        ad2 = new javax.swing.JLabel();
        add2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        Select = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        Remove = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Price = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        status = new javax.swing.JComboBox<>();
        PID = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        qnty = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main.setBackground(new java.awt.Color(51, 51, 51));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(0, 0, 0));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add.setBackground(new java.awt.Color(204, 204, 204));
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addMouseExited(evt);
            }
        });
        add.setLayout(null);

        ad.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        ad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ad.setText("ADD");
        add.add(ad);
        ad.setBounds(0, 0, 90, 30);

        Header.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 100, 30));

        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateMouseExited(evt);
            }
        });
        update.setLayout(null);

        ad1.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        ad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ad1.setText("UPDATE");
        update.add(ad1);
        ad1.setBounds(10, 0, 70, 30);

        Header.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 100, 30));

        add4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add4MouseExited(evt);
            }
        });
        add4.setLayout(null);

        ad2.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        ad2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ad2.setText("DELETE");
        add4.add(ad2);
        ad2.setBounds(10, 0, 80, 30);

        Header.add(add4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 100, 30));

        add2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add2MouseExited(evt);
            }
        });
        add2.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("CLEAR");
        add2.add(jLabel14);
        jLabel14.setBounds(0, 4, 90, 20);

        Header.add(add2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 100, 30));

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
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-log-out-30_1.png"))); // NOI18N
        jLabel10.setText("Back");
        logout.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        Header.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

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

        jLabel22.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Select");
        Select.add(jLabel22);
        jLabel22.setBounds(0, 10, 90, 10);

        Header.add(Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 90, 30));

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

        jLabel21.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Remove");
        Remove.add(jLabel21);
        jLabel21.setBounds(0, 10, 90, 10);

        Header.add(Remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, 90, 30));

        Main.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 90));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        Main.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 510, 480));

        jPanel1.setLayout(null);
        jPanel1.add(image);
        image.setBounds(10, 10, 190, 170);

        Main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 210, 190));

        jLabel3.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Status:");
        Main.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 500, 80, 30));

        jLabel6.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Price:");
        Main.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, 80, 30));

        Price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceActionPerformed(evt);
            }
        });
        Main.add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 170, 30));

        jLabel7.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Car Name:");
        Main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 100, 30));

        name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        Main.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 370, 170, 30));

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Out of Stock" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        Main.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 170, 30));

        PID.setEditable(false);
        PID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PID.setEnabled(false);
        PID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PIDActionPerformed(evt);
            }
        });
        Main.add(PID, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 170, 30));

        jLabel20.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Product ID:");
        Main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 90, 30));

        qnty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qnty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qntyActionPerformed(evt);
            }
        });
        Main.add(qnty, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 450, 170, 30));

        jLabel8.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Quantity:");
        Main.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-account-24.png"))); // NOI18N
        Main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, -1, 30));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/automotive-engineer-augmented-car-design-hologram-removebg-preview.png"))); // NOI18N
        Main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 640, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void qntyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qntyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qntyActionPerformed

    private void PIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PIDActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void PriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriceActionPerformed

    private void SelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseExited
        Select.setBackground(d);
    }//GEN-LAST:event_SelectMouseExited

    private void SelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseEntered
        Select.setBackground(h);
    }//GEN-LAST:event_SelectMouseEntered

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

    private void RemoveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseExited
        Remove.setBackground(d);
    }//GEN-LAST:event_RemoveMouseExited

    private void RemoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseEntered
        Remove.setBackground(h);
    }//GEN-LAST:event_RemoveMouseEntered

    private void RemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseClicked
        Remove.setEnabled(false);
        Select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_RemoveMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int rowIndex = table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select an Item");
        } else {
            //            CU_Admin cua = new CU_Admin();

            try {
                dbConnect dbc = new dbConnect();
                TableModel tbl = table.getModel();
                ResultSet rs = dbc.getData("SELECT * FROM product WHERE p_id = '" + tbl.getValueAt(rowIndex, 0) + "'");
                if (rs.next()) {

                    PID.setText("" + rs.getString("p_id"));
                    name.setText("" + rs.getString("p_name"));
                    Price.setText("" + rs.getString("p_price"));
                    status.setSelectedItem("" + rs.getString("p_status"));
                   
                }

            } catch (SQLException ex) {
                System.out.println("" + ex);
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void add4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add4MouseExited
        add4.setBackground(d);
    }//GEN-LAST:event_add4MouseExited

    private void add4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add4MouseEntered
        add4.setBackground(h);
    }//GEN-LAST:event_add4MouseEntered

    private void add4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add4MouseClicked
        deleteUser();
    }//GEN-LAST:event_add4MouseClicked

    private void updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseExited
        update.setBackground(d);
    }//GEN-LAST:event_updateMouseExited

    private void updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseEntered
        update.setBackground(h);
    }//GEN-LAST:event_updateMouseEntered

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
         String pid = PID.getText();
        if(pid.isEmpty())
        {            
            JOptionPane.showMessageDialog(null, "Please select an Item");
        }else
        {
            dbConnect dbc = new dbConnect();
            Session sess = Session.getInstance();
            dbConnect connector = new dbConnect();
            int userId = 0;
            String uname2 = null;

            String u = PID.getText().trim();
            String mn = name.getText().trim();
            String p = Price.getText().trim();
            String q = qnty.getText().trim();
            String s = status.getSelectedItem().toString().trim();

            if (p.isEmpty() || mn.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill All Boxes");

            }else if (!p.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(null, "Price must be a valid number (e.g., 12 or 12.99)");
            } else if (!q.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Quantity Must Only Contain Numbers");
            } else if (updateCheck()) {
                System.out.println("Duplicate Exists");
            } else {
                try {
                    String query = "SELECT * FROM product WHERE p_id='" + u + "'";
                    ResultSet rs = dbc.getData(query);
                    if (rs.next()) {

                        dbc.updateData("UPDATE product SET p_name = '" + mn + "', p_price = '" + p + "', p_quantity = '" + q + "', p_status = '" + s + "', p_image = '" + destination + "' WHERE p_id = '" + u + "'");

                        try {
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

                        logEvent(userId, uname2, "Admin Updated Car: " + mn);

                        if (destination.isEmpty()) {
                            if (oldpath != null) {
                                File existingFile = new File(oldpath);
                                if (existingFile.exists()) {
                                    existingFile.delete();
                                }
                            } else {
                            }
                        } else {
                            if (!(oldpath.equals(path))) {
                                imageUpdater(oldpath, path);
                            }
                        }

                        NotShowDeletedUsers();
//                        PID.setText("");
//                        Mname.setText("");
//                        Price.setText("");
//                        status.setSelectedItem(0);
                    }
                } catch (SQLException ex) {
                    System.out.println("" + ex);
                }
            }
        }
    }//GEN-LAST:event_updateMouseClicked

    private void add2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add2MouseExited
        add2.setBackground(d);
    }//GEN-LAST:event_add2MouseExited

    private void add2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add2MouseEntered
        add2.setBackground(h);
    }//GEN-LAST:event_add2MouseEntered

    private void add2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add2MouseClicked
        //        addClickable = true;
        //        ad.setForeground(black);
        NotShowDeletedUsers();
        PID.setText("");
        name.setText("");
        Price.setText("");
        status.setSelectedItem(0);
    }//GEN-LAST:event_add2MouseClicked

    private void addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseExited
        add.setBackground(d);
    }//GEN-LAST:event_addMouseExited

    private void addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseEntered
        add.setBackground(h);
    }//GEN-LAST:event_addMouseEntered

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        //        if (addClickable)
        //        {
         //        if (addClickable)
        //        {
            dbConnect dbc = new dbConnect();
            Session sess = Session.getInstance();
            dbConnect connector = new dbConnect();
            int userId = 0;
      
            String uname2 = null;
            String mn = name.getText().trim();
            String pr = Price.getText().trim();
            String q = qnty.getText().trim();
              int sold = 0;
            String st = status.getSelectedItem().toString().trim();

            if (mn.isEmpty() || pr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill All Boxes");

            } else if (!pr.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(null, "Price must be a valid number (e.g., 12 or 12.99)");
            } else if (!q.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Quantity Must Only Contain Numbers");
            } else if (duplicateCheck()) {
                JOptionPane.showMessageDialog(null, "Duplicate Exists");
            } else {
                System.out.println("1");
                try {
                    System.out.println("2");

                    if (dbc.insertData("INSERT INTO product (p_name, p_price, p_quantity, p_status, p_image, p_sold) "
                        + "VALUES ('" + mn + "', '" + pr + "', '" + q + "', '" + st + "', '" + destination + "', '" + sold + "')"))
                {
                    System.out.println("3");

                    try
                    {
                        System.out.println("4");
                        String query2 = "SELECT * FROM users WHERE u_id = '" + sess.getUid() + "'";
                        PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

                        ResultSet resultSet = pstmt.executeQuery();

                        if (resultSet.next())
                        {
                            userId = resultSet.getInt("u_id");   // Update the outer userId correctly
                            uname2 = resultSet.getString("u_usname");
                        }
                    } catch (SQLException ex)
                    {
                        System.out.println("SQL Exception: " + ex);
                    }

                    logEvent(userId, uname2, "Admin Added a Car Sales: " + mn);

                    if (selectedFile != null && destination != null) {
                        Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        System.out.println("selectedFile or destination is null!");
                    }

                    adminDashboard ad = new adminDashboard();
                    ad.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "An error occured");
                    //                        System.out.println("Dan, Error occured in line: 757, productForm");
                    adminDashboard ad = new adminDashboard();
                    ad.setVisible(true);
                    this.dispose();
                }
                //                }
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
        }
        //        }else if (!addClickable)
        //        {
            //            JOptionPane.showMessageDialog(null, "Clear the Fields First");
            //        }
    }//GEN-LAST:event_addMouseClicked

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(d);
    }//GEN-LAST:event_logoutMouseExited

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(h);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        adminDashboard as = new adminDashboard();
        as.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

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
            java.util.logging.Logger.getLogger(productform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(productform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(productform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(productform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new productform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Main;
    public javax.swing.JTextField PID;
    public javax.swing.JTextField Price;
    public javax.swing.JPanel Remove;
    public javax.swing.JPanel Select;
    private javax.swing.JLabel ad;
    private javax.swing.JLabel ad1;
    private javax.swing.JLabel ad2;
    private javax.swing.JPanel add;
    private javax.swing.JPanel add2;
    private javax.swing.JPanel add4;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel logout;
    public javax.swing.JTextField name;
    public javax.swing.JTextField qnty;
    public javax.swing.JComboBox<String> status;
    private javax.swing.JTable table;
    private javax.swing.JPanel update;
    // End of variables declaration//GEN-END:variables
}
