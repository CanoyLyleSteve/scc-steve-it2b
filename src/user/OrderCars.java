/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import admin.*;
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
import java.awt.BorderLayout;
import static java.awt.Color.black;
import static java.awt.Color.red;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
public class OrderCars extends javax.swing.JFrame {

     private Color H;
    Color h = new Color(51,51,255);
    private Color D;
    Color d = new Color(240,240,240);
    
    public OrderCars() {
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
        String mn = Cname.getText().trim();

        try {
            String query = "SELECT * FROM product WHERE (p_name='" + mn + "') AND p_id != '" + u + "'";
            ResultSet resultSet = dbc.getData(query);
            if (resultSet.next()) 
            {
                pname = resultSet.getString("p_name");
                if (pname.equals(mn)) 
                {
                    JOptionPane.showMessageDialog(null, "Customer Agent Sales Car already exists");
                    Cname.setText("");
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
        String mn = Cname.getText().trim();

        try {
            String query = "SELECT * FROM product WHERE p_name='" + mn + "'"; //If output mentions something about ''', there is a missing '
            ResultSet resultSet = dbc.getData(query);
            if (resultSet.next()) {
                pname = resultSet.getString("p_name");
                if (pname.equals(mn)) {
                    JOptionPane.showMessageDialog(null, "Customer Agent Sales Car already exists");
                    Cname.setText("");
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
      
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

      
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();

   
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
     
        List<Object[]> rowData = new ArrayList<>();

        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT * FROM product");

            while (rs.next()) {
               
                String u = rs.getString("p_id");
                String pn = rs.getString("p_name");
                String pp = rs.getString("p_price");
                String status = rs.getString("p_status");
                String qnty = rs.getString("p_quantity");
                

                if (!status.equals("Deleted")) {
                    
                 
                    rowData.add(new Object[]{
                        u,
                        pn,
                        pp, 
                        status,
                        qnty
                    });
                   
                }
            }

           
            SwingUtilities.invokeLater(() -> {
                DefaultTableModel model = new DefaultTableModel(
                        new String[]{"ID", "Car Name", "Price", "Status", "Stocks","PaymentTypeBox"}, 0
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

    
    
    
    
     public void logEvent(int userId, String username, String action) {
    {
     dbConnect dbc = new dbConnect();
Connection con = dbc.getConnection();
PreparedStatement pstmt = null;
Timestamp time = new Timestamp(new Date().getTime());

try {
    String sql = "INSERT INTO tbl_logs (u_id, u_username, action_time, log_action) VALUES (?, ?, ?, ?)";
    pstmt = con.prepareStatement(sql);
    pstmt.setInt(1, userId); // Must exist in users.u_id
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
     }
    
    
    
    
    private void deleteUser() {
        dbConnect dbc = new dbConnect();
        Session sess = Session.getInstance();
        dbConnect connector = new dbConnect();
        String uname3 = null;
        String uname2 = null;
        String uname = null;
        int userId = 0;

        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }

        int productId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
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
        Logout = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        Price = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Cname = new javax.swing.JTextField();
        PID = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Qnty = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Payment = new javax.swing.JTextField();
        add = new javax.swing.JPanel();
        con = new javax.swing.JLabel();
        paymentTypeBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main.setBackground(new java.awt.Color(0, 0, 0));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                HeaderAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CARS ORDERS");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 320, 40));

        Logout.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        Logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-exit-button-30.png"))); // NOI18N
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        Header.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 70, -1, -1));

        Main.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 100));

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

        Main.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 450, 420));

        jLabel6.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Price to Pay:");
        Main.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 100, 30));

        Price.setEditable(false);
        Price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceActionPerformed(evt);
            }
        });
        Main.add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 140, 30));

        jLabel7.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Car Name:");
        Main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 110, 30));

        Cname.setEditable(false);
        Cname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Cname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CnameActionPerformed(evt);
            }
        });
        Main.add(Cname, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 140, 30));

        PID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PID.setEnabled(false);
        PID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PIDActionPerformed(evt);
            }
        });
        Main.add(PID, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 140, 30));

        jLabel20.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Product ID:");
        Main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 90, 30));

        jLabel8.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Quanity:");
        Main.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 80, 30));

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
        Main.add(Qnty, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 140, 30));

        jLabel9.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Enter Payment:");
        Main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 120, 30));

        Payment.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentActionPerformed(evt);
            }
        });
        Main.add(Payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 140, 30));

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

        con.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        con.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        con.setText("CONFIRM");
        add.add(con);
        con.setBounds(0, 0, 100, 30);

        Main.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, 100, 30));

        paymentTypeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Payment", "Cash", "Installment" }));
        Main.add(paymentTypeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 140, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
                                
  dbConnect connector = new dbConnect();
Session sess = Session.getInstance();

// Variable declarations
long d_qnty = 0L;
long sold_qnty = 0L;
long minusQnty = 0L;
long plusQnty = 0L;
int userId = 0;
String uname2 = null;

// Get input values from GUI
String mn = Cname.getText().trim();
String pr = Price.getText().trim();
String pid = PID.getText().trim();
String qtyStr = (Qnty != null && Qnty.getText() != null) ? Qnty.getText().trim() : "";
String py = (Payment != null && Payment.getText() != null) ? Payment.getText().trim() : "";
String paymentType = paymentTypeBox.getSelectedItem().toString().trim();

// Display selected input
System.out.println("Selected Product Name: " + mn);
System.out.println("Price: " + pr);
System.out.println("Product ID: " + pid);
System.out.println("Quantity: " + qtyStr);
System.out.println("Payment: " + py);
System.out.println("Payment Type: " + paymentType);

// Input validations
if (mn.isEmpty() || pr.isEmpty() || qtyStr.isEmpty() || py.isEmpty() || paymentType.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please fill all boxes");
    return;
}
if (!qtyStr.matches("\\d+")) {
    JOptionPane.showMessageDialog(null, "Quantity must only contain numbers");
    return;
}
if (!pr.matches("\\d+(\\.\\d{1,2})?")) {
    JOptionPane.showMessageDialog(null, "Price must be a valid number, e.g., 15 or 15.00");
    return;
}
if (!py.matches("\\d+(\\.\\d{1,2})?")) {
    JOptionPane.showMessageDialog(null, "Payment must be a valid number, e.g., 15 or 15.00");
    return;
}
if (!paymentType.equalsIgnoreCase("Cash") && !paymentType.equalsIgnoreCase("Installment")) {
    JOptionPane.showMessageDialog(null, "Payment type must be either 'Cash' or 'Installment'");
    return;
}

// Convert inputs
BigDecimal price = new BigDecimal(pr);
BigDecimal payment = new BigDecimal(py);
int q = Integer.parseInt(qtyStr);
BigDecimal total = price.multiply(BigDecimal.valueOf(q));

// Validate payment amount based on type
// Validate payment amount based on type
if (paymentType.equalsIgnoreCase("Cash")) {
    if (payment.compareTo(total) < 0) {
        JOptionPane.showMessageDialog(null, "Insufficient Cash Payment. Please pay the full amount.");
        return;
    }
} else if (paymentType.equalsIgnoreCase("Installment")) {
    if (payment.compareTo(total) >= 0) {
        JOptionPane.showMessageDialog(null, "Installment payment should not fully pay the total. Please enter a partial payment.");
        return;
    } else if (payment.compareTo(BigDecimal.ZERO) <= 0) {
        JOptionPane.showMessageDialog(null, "Installment payment must be greater than zero.");
        return;
    }
}


try {
    Timestamp now = new Timestamp(System.currentTimeMillis());

    try (Connection conn = connector.getConnection()) {

        // Get user details
        String queryUser = "SELECT * FROM users WHERE u_id = ?";
        try (PreparedStatement pstmtUser = conn.prepareStatement(queryUser)) {
            pstmtUser.setInt(1, sess.getUid());
            try (ResultSet resultSetUser = pstmtUser.executeQuery()) {
                if (resultSetUser.next()) {
                    userId = resultSetUser.getInt("u_id");
                    uname2 = resultSetUser.getString("u_usname");
                }
            }
        }

        // Get product details
        String productQuery = "SELECT * FROM product WHERE p_id = ?";
        try (PreparedStatement pstmtProd = conn.prepareStatement(productQuery)) {
            pstmtProd.setInt(1, Integer.parseInt(pid));
            try (ResultSet resultSetProd = pstmtProd.executeQuery()) {
                if (resultSetProd.next()) {
                    d_qnty = resultSetProd.getLong("p_quantity");
                    sold_qnty = resultSetProd.getLong("p_sold");

                    if (d_qnty == 0L) {
                        JOptionPane.showMessageDialog(null, "This product is out of stock.");
                        return;
                    } else if (q > d_qnty) {
                        JOptionPane.showMessageDialog(null, "Requested quantity exceeds available stock.");
                        return;
                    }

                    minusQnty = d_qnty - q;
                    plusQnty = sold_qnty + q;

                    // Insert order
                    String orderQuery = "INSERT INTO orders (u_id, p_id, quantity, date, status, o_total, payment_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement orderStmt = conn.prepareStatement(orderQuery)) {
                        orderStmt.setInt(1, userId);
                        orderStmt.setInt(2, Integer.parseInt(pid));
                        orderStmt.setInt(3, q);
                        orderStmt.setTimestamp(4, now);
                        orderStmt.setString(5, "Successful");
                        orderStmt.setBigDecimal(6, total);
                        orderStmt.setString(7, paymentType);

                        int rowsInserted = orderStmt.executeUpdate();

                        if (rowsInserted > 0) {
                            // Update product stock and sold count
                            String updateProduct = "UPDATE product SET p_quantity = ?, p_sold = ? WHERE p_id = ?";
                            try (PreparedStatement updateStmt = conn.prepareStatement(updateProduct)) {
                                updateStmt.setLong(1, minusQnty);
                                updateStmt.setLong(2, plusQnty);
                                updateStmt.setInt(3, Integer.parseInt(pid));
                                updateStmt.executeUpdate();
                            }

                            // Log the event
                            logEvent(userId, uname2, "User made transaction for product: " + mn);

                            JOptionPane.showMessageDialog(null, "Added successfully!");
                            userdashboard ud = new userdashboard();
                            ud.setVisible(true);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "An error occurred while inserting the order.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found.");
                }
            }
        }
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
    System.out.println("SQL Exception: " + ex);
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

    private void CnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CnameActionPerformed

    private void PIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PIDActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int rowIndex = table.getSelectedRow();
        System.out.println("Selected rowIndex: " + rowIndex);

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select an Item");
            System.out.println(" No row selected. Exiting.");
        } else {
            try {
                dbConnect dbc = new dbConnect();
                TableModel tbl = table.getModel();
                Object selectedID = tbl.getValueAt(rowIndex, 0);
                System.out.println(" Selected Product ID: " + selectedID);

                ResultSet rs = dbc.getData("SELECT * FROM product WHERE p_id = '" + selectedID + "'");
                if (rs.next()) {
                    PID.setText(rs.getString("p_id"));
                    Cname.setText(rs.getString("p_name"));



                    System.out.println(" Product details loaded into fields.");
                } else {
                    System.out.println(" No product found for selected ID.");
                }
            } catch (SQLException e) {
                System.out.println("[ERROR] SQL Exception: " + e);
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void QntyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QntyActionPerformed
        

    }//GEN-LAST:event_QntyActionPerformed

    private void PaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentActionPerformed

    private void QntyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QntyMouseClicked
    String qtyText = Qnty.getText().trim();
    String pid = PID.getText().trim();

    if (qtyText.isEmpty() || pid.isEmpty()) {
        System.out.println("Quantity or Product ID is empty.");
        return;
    }

    try {
        int quantity = Integer.parseInt(qtyText);

        dbConnect dbc = new dbConnect();
        Connection conn = dbc.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT p_price FROM product WHERE p_id = ?");
        stmt.setString(1, pid);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            BigDecimal price = rs.getBigDecimal("p_price");
            BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));
            // Suppose you have BigDecimal soldQuantity
            BigDecimal bd = new BigDecimal("1.0E10");
           long value = bd.longValueExact(); // converts 1.0E10 to 10000000000 as a long



            System.out.println("Total: " + total.toPlainString());
            Price.setText(total.toPlainString());  // This avoids '1.0E10' notation
        } else {
            System.out.println("Product not found.");
            Price.setText("");
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (NumberFormatException e) {
        System.out.println("Invalid quantity input: " + e.getMessage());
        Price.setText("");
    } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
        Price.setText("");
    }

    }//GEN-LAST:event_QntyMouseClicked
    
    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        userdashboard ad = new userdashboard();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogoutMouseClicked

    private void HeaderAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_HeaderAncestorAdded
 

    }//GEN-LAST:event_HeaderAncestorAdded

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
            java.util.logging.Logger.getLogger(OrderCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new OrderCars().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Cname;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel Logout;
    private javax.swing.JPanel Main;
    public javax.swing.JTextField PID;
    public javax.swing.JTextField Payment;
    public javax.swing.JTextField Price;
    public javax.swing.JTextField Qnty;
    private javax.swing.JPanel add;
    private javax.swing.JLabel con;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> paymentTypeBox;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
