/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import admin.*;
import java.sql.ResultSet;           
import java.sql.SQLException;         
import java.util.ArrayList;          
import java.util.List;              
import javax.swing.table.DefaultTableModel;  
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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import user.userdashboard;
public class OrderCar extends javax.swing.JFrame {

     private Color H;
    Color h = new Color(51,51,255);
    private Color D;
    Color d = new Color(240,240,240);
    
    public OrderCar() {
        initComponents();
        NotShowDeletedUsers();
//    displayData();

Qnty.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTotal();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTotal();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTotal();
            }

            private void updateTotal() {
                try {
                    String qtyText = Qnty.getText().trim();


                    if (qtyText.isEmpty()) {
                        Price.setText("0");

                        return;
                    }

                    int q = Integer.parseInt(qtyText);
                    String pid = PID.getText().trim();

                    if (pid.isEmpty()) {
                        Price.setText("0");
                       System.out.println("[DEBUG] No product selected. Setting total to 0.");
                        return;
                    }

                    dbConnect dbc = new dbConnect();
                    ResultSet rs = dbc.getData("SELECT * FROM product WHERE p_id = '" + pid + "'");
                    if (rs.next()) {
                        int price = rs.getInt("p_price");
                       int total = q * price;
                      

                        Price.setText("" + total); 
                        

                       
                    } else {
                        Price.setText("0");
                       
                    }
                } catch (NumberFormatException e) {
                    Price.setText("0");
                    System.out.println(""+e);
                    
                } catch (SQLException e) {
                    System.out.println(""+e);

                }
            }
        });

    }
    
    
    
    
    
    
    
    


    public static String pname;

    public boolean updateCheck() {
        dbConnect dbc = new dbConnect();
        String u = PID.getText().trim();
        String mn = Mname.getText().trim();

        try {
            String query = "SELECT * FROM product WHERE (p_name='" + mn + "') AND p_id != '" + u + "'";
            ResultSet resultSet = dbc.getData(query);
            if (resultSet.next()) 
            {
                pname = resultSet.getString("p_name");
                if (pname.equals(mn)) 
                {
                    JOptionPane.showMessageDialog(null, "Car Already Exists");
                    Mname.setText("");
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
        String mn = Mname.getText().trim();

        try {
            String query = "SELECT * FROM product WHERE p_name='" + mn + "'";
            ResultSet resultSet = dbc.getData(query);
            if (resultSet.next()) {
                pname = resultSet.getString("p_name");
                if (pname.equals(mn)) {
                    JOptionPane.showMessageDialog(null, "Car sales Already Exists");
                    Mname.setText("");
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
                String qnty = rs.getString("p_quantity");
                

                // Check if the user status is not "Deleted"
                if (!status.equals("Deleted")) {
                    
                    // Add the row to the list
                    rowData.add(new Object[]{
                        u,
                        pn,
                        pp, 
                        status,
                        qnty
                    });
                   
                }
            }

            // After processing all rows, update the table on the Swing event dispatch thread
            SwingUtilities.invokeLater(() -> {
                DefaultTableModel model = new DefaultTableModel(
                        new String[]{"ID", "Car Name", "Price", "Status", "Stocks"}, 0
                );
                for (Object[] row : rowData) {
                    model.addRow(row);
                }
                account_table.setModel(model);
                account_table.repaint(); // Force visual refresh
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
            account_table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException ex)
        {
            System.out.println("Errors: "+ex.getMessage());
        }
    }
    
    
    private void loadUsersData() 
    {
        DefaultTableModel model = (DefaultTableModel) account_table.getModel();
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
                        rs.getString("p_quantity"),
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
        dbConnect dbc = new dbConnect();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;
        Timestamp time = new Timestamp(new Date().getTime());

        try {
            String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) "
                    + "VALUES ('" + userId + "', '" + username + "', '" + time + "', '" + action + "')";
            pstmt = con.prepareStatement(sql);

            /*            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.setString(4, userType);*/
            pstmt.executeUpdate();
            System.out.println("Login log recorded successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
    }
    
    
    
    
    
    
    private void deleteUser() {
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        dbConnect connector = new dbConnect();
//        int userId = 0;
        String uname3 = null;
        String uname2 = null;
        String uname = null;
        int userId = 0;

        int selectedRow = account_table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }

        int productId = Integer.parseInt(account_table.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            try {
                String query2 = "SELECT * FROM product WHERE p_id = '" + productId + "'";
                PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String pid = rs.getString("p_id");
                    String pn = rs.getString("p_name");
                    String pr = rs.getString("p_price");
                    String s = "Deleted";

                    dbc.updateData("UPDATE product SET p_name = '" + pn + "', p_price = '" + pr + "', p_status = '" + s + "' WHERE p_id = '" + pid + "'");

                    try {
                        System.out.println("1");
                        String query = "SELECT * FROM users WHERE u_id = '" + sess.getUid() + "'";
                        PreparedStatement pstmt2 = connector.getConnection().prepareStatement(query);

                        ResultSet rs2 = pstmt2.executeQuery();

                        if (rs2.next()) {
                            System.out.println("2");
                            userId = rs2.getInt("u_id");
                            uname2 = rs2.getString("u_usname");
                            loadUsersData();
                        }
                        logEvent(userId, uname2, "Admin Deleted Account: " + uname2);

                    } catch (SQLException ex) {
                        System.out.println("" + ex);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex);
            }
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        account_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel6 = new javax.swing.JLabel();
        Price = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Mname = new javax.swing.JTextField();
        PID = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        add = new javax.swing.JPanel();
        con = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Qnty = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Payment = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main.setBackground(new java.awt.Color(39, 39, 39));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(0, 0, 0));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Orders Form");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1310, 40));

        Main.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 100));

        account_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        account_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                account_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(account_table);

        Main.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 850, 540));

        jScrollPane2.setViewportView(jEditorPane1);

        Main.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Price to Pay:");
        Main.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 100, 30));

        Price.setEditable(false);
        Price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceActionPerformed(evt);
            }
        });
        Main.add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 330, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Movie Name:");
        Main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 110, 30));

        Mname.setEditable(false);
        Mname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Mname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnameActionPerformed(evt);
            }
        });
        Main.add(Mname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 330, 30));

        PID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PID.setEnabled(false);
        PID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PIDActionPerformed(evt);
            }
        });
        Main.add(PID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 330, 30));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Product ID:");
        Main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 90, 30));

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

        con.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        con.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        con.setText("CONFIRM");
        add.add(con);
        con.setBounds(10, 10, 130, 22);

        Main.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 600, 150, 40));

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

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Back");
        logout.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 130, -1));

        Main.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 40));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Quanity:");
        Main.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 80, 30));

        Qnty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Qnty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QntyMouseClicked(evt);
            }
        });
        Qnty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QntyActionPerformed(evt);
            }
        });
        Main.add(Qnty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 330, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Enter Payment:");
        Main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 120, 30));

        Payment.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentActionPerformed(evt);
            }
        });
        Main.add(Payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 330, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
       userdashboard ad = new  userdashboard();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(h);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(d);
    }//GEN-LAST:event_logoutMouseExited

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
       
          dbConnect dbc = new dbConnect();
Session sess = Session.getInstance();
int userId = 0;
int d_qnty, sold_qnty, minusQnty, plusQnty;
String uname2 = null;

// Get input values
String mn = Mname.getText().trim();
String pr = Price.getText().trim();
String pid = PID.getText().trim();
String qntyText = Qnty.getText().trim();
String py = Payment.getText().trim();

Timestamp time = new Timestamp(new java.util.Date().getTime());
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String date = sdf.format(new java.util.Date());

// Validate inputs
if (mn.isEmpty() || pr.isEmpty() || qntyText.isEmpty() || py.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please Fill All Boxes");
    return;
}

if (!qntyText.matches("\\d+")) {
    JOptionPane.showMessageDialog(null, "Quantity Must Only Contain Numbers");
    return;
}

if (!py.matches("\\d+")) {
    JOptionPane.showMessageDialog(null, "Payment Must Only Contain Numbers");
    return;
}

int quantity = Integer.parseInt(qntyText);
int price = Integer.parseInt(pr);
int payment = Integer.parseInt(py);

if (price > payment) {
    JOptionPane.showMessageDialog(null, "Insufficient Cash");
    return;
}

// Get user info once
try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
        "SELECT u_id, u_usname FROM users WHERE u_id = ?")) {
    pstmt.setInt(1, sess.getUid());
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
        userId = rs.getInt("u_id");
        uname2 = rs.getString("u_usname");
    } else {
        JOptionPane.showMessageDialog(null, "User not found.");
        return;
    }
} catch (SQLException ex) {
    System.out.println("SQL Exception (fetch user): " + ex.getMessage());
    return;
}

// Insert order
String insertOrderQuery = "INSERT INTO `order` (u_id, p_id, quantity, date, status, o_total) VALUES (?, ?, ?, ?, ?, ?)";

try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(insertOrderQuery)) {
    pstmt.setInt(1, userId);
    pstmt.setString(2, pid);
    pstmt.setInt(3, quantity);
    pstmt.setString(4, date);
    pstmt.setString(5, "Successful");
    pstmt.setInt(6, payment);

    int result = pstmt.executeUpdate();

    if (result > 0) {
        // Update product quantities
        String productQuery = "SELECT p_quantity, p_sold FROM product WHERE p_id = ?";
        try (PreparedStatement prodStmt = dbc.getConnection().prepareStatement(productQuery)) {
            prodStmt.setString(1, pid);
            ResultSet rs = prodStmt.executeQuery();

            if (rs.next()) {
                d_qnty = rs.getInt("p_quantity");
                sold_qnty = rs.getInt("p_sold");
                minusQnty = d_qnty - quantity;
                plusQnty = sold_qnty + quantity;

                String updateProduct = "UPDATE product SET p_quantity = ?, p_sold = ? WHERE p_id = ?";
                try (PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateProduct)) {
                    updateStmt.setInt(1, minusQnty);
                    updateStmt.setInt(2, plusQnty);
                    updateStmt.setString(3, pid);
                    updateStmt.executeUpdate();
                }
            }
        }

        // Log event
        logEvent(userId, uname2, "User made transaction ID: " + mn);

        JOptionPane.showMessageDialog(null, "Added successfully!");
        userdashboard ud = new userdashboard();
        ud.setVisible(true);
        this.dispose();

    } else {
        JOptionPane.showMessageDialog(null, "An error occurred while inserting order.");
    }
} catch (SQLException ex) {
    System.out.println("SQL Exception (insert order): " + ex.getMessage());
}

        
    }//GEN-LAST:event_addMouseClicked

    private void addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseEntered
        add.setBackground(h);
    }//GEN-LAST:event_addMouseEntered

    private void addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseExited
        add.setBackground(d);
    }//GEN-LAST:event_addMouseExited

    private void PriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriceActionPerformed

    private void MnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnameActionPerformed

    private void PIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PIDActionPerformed

    private void account_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_account_tableMouseClicked
        int rowIndex = account_table.getSelectedRow();
        System.out.println("[DEBUG] Selected rowIndex: " + rowIndex);

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select an Item");
            System.out.println("[DEBUG] No row selected. Exiting.");
        } else {
            try {
                dbConnect dbc = new dbConnect();
                TableModel tbl = account_table.getModel();
                Object selectedID = tbl.getValueAt(rowIndex, 0);
                System.out.println("[DEBUG] Selected Product ID: " + selectedID);

                ResultSet rs = dbc.getData("SELECT * FROM tproduct WHERE p_id = '" + selectedID + "'");
                if (rs.next()) {
                    PID.setText(rs.getString("p_id"));
                    Mname.setText(rs.getString("p_name"));


                    System.out.println("[DEBUG] Product details loaded into fields.");
                } else {
                    System.out.println("[DEBUG] No product found for selected ID.");
                }
            } catch (SQLException e) {
                System.out.println("[ERROR] SQL Exception: " + e);
            }
        }
    }//GEN-LAST:event_account_tableMouseClicked

    private void QntyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QntyActionPerformed
        

    }//GEN-LAST:event_QntyActionPerformed

    private void PaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentActionPerformed

    private void QntyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QntyMouseClicked
        String qtyText = Qnty.getText().trim();
        int q = Integer.parseInt(qtyText);
        String pid = PID.getText();
        
        
        try
        {
            

            dbConnect dbc = new dbConnect();
            TableModel tbl = account_table.getModel();
            ResultSet rs = dbc.getData("SELECT * FROM product WHERE p_id = '" + pid + "'");
            if (rs.next()) 
            {
                int price = rs.getInt("p_price");
                int total = q * price;
                System.out.println("total: " + total);
                Price.setText("" + total);
//                addClickable = false;
//                con.setForeground(red);
            }
    
        }catch (SQLException | NumberFormatException e) 
    {
        System.out.println("" + e);
    }
    }//GEN-LAST:event_QntyMouseClicked

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
            java.util.logging.Logger.getLogger(OrderCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderCar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Main;
    public javax.swing.JTextField Mname;
    public javax.swing.JTextField PID;
    public javax.swing.JTextField Payment;
    public javax.swing.JTextField Price;
    public javax.swing.JTextField Qnty;
    private javax.swing.JTable account_table;
    private javax.swing.JPanel add;
    private javax.swing.JLabel con;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel logout;
    // End of variables declaration//GEN-END:variables
}
