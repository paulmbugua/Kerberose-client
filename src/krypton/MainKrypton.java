package krypton;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static krypton.login.isValid;

public class MainKrypton extends javax.swing.JFrame {
String email,pass,attachment;
    public MainKrypton() {
        initComponents();
    }

    MainKrypton(String emails, String passs) {
        initComponents();
        email=emails;
        pass=passs;
    }
    //sends email
    public void  sendmail(String to,String title,String msg){
        Properties props =System.getProperties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.stmp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
              
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(email, pass);
                }
                });
        
        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(title);
            message.setText(msg);
            
            Transport.send(message);
            statuslabel.setText("Message sent");
            statuslabel.setForeground(Color.GREEN);
        }
      catch (Exception e){
          statuslabel.setText(e.getMessage());
               System.out.println(e.getMessage());
           statuslabel.setForeground(Color.red);
      }
        
    }
    //sends attachment
    public void sendattachmentfile(String to,String title,String msg,String phrase){
        Properties props =System.getProperties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.stmp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
        
        Session session1 = Session.getInstance(props,
                new javax.mail.Authenticator() {
              
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(email, pass);
                }
                });
        
        try{
            Message message=new MimeMessage(session1);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(title);
           
            MimeBodyPart mb = new MimeBodyPart();
            mb.setText(msg);
            Multipart mp =new MimeMultipart();
            mp.addBodyPart(mb);
            
            mb=new MimeBodyPart();
            Algorithm mx=new Algorithm();
            mx.encryptFile(path.getText(), phrase);
            DataSource source= new FileDataSource(path.getText()+".crypt");
            mb.setDataHandler(new DataHandler(source));
            mb.setFileName(attachmenttxt.getText());
            mp.addBodyPart(mb);
            message.setContent(mp);
            
            Transport.send(message);
            statuslabel.setText("Message sent");
            statuslabel.setForeground(Color.GREEN);
        }
      catch (Exception e){
          statuslabel.setText(e.getMessage());
           statuslabel.setForeground(Color.red);
      }
    }
    public void sendattachment(){
       JFileChooser chooser= new JFileChooser();
       chooser.showOpenDialog(null);
       File f =chooser.getSelectedFile();
       attachment=f.getAbsolutePath();
       attachmenttxt.setText(f.getName());
       path.setText(attachment);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statuslabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        encryptpanel = new javax.swing.JPanel();
        mailbutton = new javax.swing.JButton();
        attachbutton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totxt = new javax.swing.JTextField();
        subjecttxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        texttxt = new javax.swing.JTextArea();
        path = new javax.swing.JLabel();
        attachmenttxt = new javax.swing.JTextField();
        sendattachmentbutton = new javax.swing.JToggleButton();
        decryptpanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        encryptedtxt = new javax.swing.JTextArea();
        decrypttxt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        plaintexttxt = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        decryptfilebutton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        typecombo = new javax.swing.JComboBox<>();
        generatehashbtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        hastextarea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        valuetxtarea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Krytpon Main Page");
        setResizable(false);

        jTabbedPane1.setBackground(java.awt.Color.white);
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        mailbutton.setText("Mail");
        mailbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailbuttonActionPerformed(evt);
            }
        });

        attachbutton.setText("Fetch attachment");
        attachbutton.setToolTipText("");
        attachbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachbuttonActionPerformed(evt);
            }
        });

        jLabel1.setText("To:");

        jLabel2.setText("Subject:");

        totxt.setForeground(new java.awt.Color(150, 72, 72));

        subjecttxt.setForeground(new java.awt.Color(84, 77, 77));

        texttxt.setColumns(20);
        texttxt.setFont(new java.awt.Font("Samanata", 0, 12)); // NOI18N
        texttxt.setLineWrap(true);
        texttxt.setRows(5);
        texttxt.setWrapStyleWord(true);
        texttxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(texttxt);

        attachmenttxt.setText("Attachment-name");

        sendattachmentbutton.setText("Send attachment");
        sendattachmentbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendattachmentbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout encryptpanelLayout = new javax.swing.GroupLayout(encryptpanel);
        encryptpanel.setLayout(encryptpanelLayout);
        encryptpanelLayout.setHorizontalGroup(
            encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encryptpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(encryptpanelLayout.createSequentialGroup()
                        .addGroup(encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sendattachmentbutton)
                            .addComponent(attachmenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(attachbutton)
                            .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, encryptpanelLayout.createSequentialGroup()
                        .addComponent(mailbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(encryptpanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subjecttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, encryptpanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        encryptpanelLayout.setVerticalGroup(
            encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encryptpanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(mailbutton)))
                .addGroup(encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(encryptpanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(encryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(subjecttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                    .addGroup(encryptpanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(attachbutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(attachmenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sendattachmentbutton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Encrypt", null, encryptpanel, "");

        encryptedtxt.setColumns(20);
        encryptedtxt.setForeground(new java.awt.Color(212, 85, 22));
        encryptedtxt.setLineWrap(true);
        encryptedtxt.setRows(5);
        encryptedtxt.setWrapStyleWord(true);
        encryptedtxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Ciphertext"));
        jScrollPane2.setViewportView(encryptedtxt);

        decrypttxt.setText("Decrypt");
        decrypttxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decrypttxtActionPerformed(evt);
            }
        });

        plaintexttxt.setColumns(20);
        plaintexttxt.setForeground(new java.awt.Color(23, 31, 149));
        plaintexttxt.setLineWrap(true);
        plaintexttxt.setRows(5);
        plaintexttxt.setWrapStyleWord(true);
        plaintexttxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Plaintext"));
        jScrollPane3.setViewportView(plaintexttxt);

        javax.swing.GroupLayout decryptpanelLayout = new javax.swing.GroupLayout(decryptpanel);
        decryptpanel.setLayout(decryptpanelLayout);
        decryptpanelLayout.setHorizontalGroup(
            decryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decryptpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(decrypttxt)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        decryptpanelLayout.setVerticalGroup(
            decryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decryptpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(decryptpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, decryptpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decrypttxt)
                .addGap(122, 122, 122))
        );

        jTabbedPane1.addTab("Decrypt Text", null, decryptpanel, "");

        decryptfilebutton.setText("Decrypt File");
        decryptfilebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptfilebuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(decryptfilebutton)
                .addContainerGap(826, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(decryptfilebutton)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Decrypt File", jPanel1);

        typecombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512" }));

        generatehashbtn.setText("Generate");
        generatehashbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatehashbtnActionPerformed(evt);
            }
        });

        hastextarea.setColumns(20);
        hastextarea.setLineWrap(true);
        hastextarea.setRows(5);
        hastextarea.setWrapStyleWord(true);
        jScrollPane4.setViewportView(hastextarea);

        valuetxtarea.setColumns(20);
        valuetxtarea.setLineWrap(true);
        valuetxtarea.setRows(5);
        valuetxtarea.setWrapStyleWord(true);
        jScrollPane5.setViewportView(valuetxtarea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(typecombo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(generatehashbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(typecombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(generatehashbtn)
                        .addGap(0, 74, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hash Value", null, jPanel2, "");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/krypton/Krypton.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setOpaque(true);

        jMenu1.setText("File");

        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statuslabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statuslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void attachbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachbuttonActionPerformed
      sendattachment();
    }//GEN-LAST:event_attachbuttonActionPerformed

    private void mailbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailbuttonActionPerformed
       String t,s,m;
       t=totxt.getText();
       s=subjecttxt.getText();
       m=texttxt.getText();
       if (t.isEmpty()||s.isEmpty()||m.isEmpty()){
           statuslabel.setText("Missing fields");
           statuslabel.setForeground(Color.red);
       }else if(!isValid(t)){
          statuslabel.setText("Email is not valid");
           statuslabel.setForeground(Color.red);        
        }else
       {
           try {
                   if(getpublickey(t)==false){
                   statuslabel.setText("Invalid email");
                   statuslabel.setForeground(Color.red);
               }else{
                  statuslabel.setText("Valid email");
                  statuslabel.setForeground(Color.green);              
                 byte[] keybyte=GetFile("/tmp/publickey.key");
                  X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(keybyte);
                  KeyFactory kf = KeyFactory.getInstance("RSA");                 
                  PublicKey pubKey = kf.generatePublic(keySpecX509);
                  statuslabel.setText("Valid email");
                  statuslabel.setForeground(Color.green);
                  String phrase=JOptionPane.showInputDialog("Enter the pass phrase");
                  
                  Algorithm ec =new Algorithm();
                 m= ec.encrypt(pubKey,m,phrase); 
                 sendmail(t,s,m);
                   }
                
           } catch (Exception e){
               System.out.println(e.getMessage());  
           }
       }
       
    }//GEN-LAST:event_mailbuttonActionPerformed
    //check if email is valid
    public static boolean isValid(String email) { 
      String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$";                               
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    private void sendattachmentbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendattachmentbuttonActionPerformed
        String t,s,m;
       t=totxt.getText();
       s=subjecttxt.getText();
       m=texttxt.getText();
       if (t.isEmpty()||s.isEmpty()||m.isEmpty()||path.getText().isEmpty()){
           statuslabel.setText("Missing fields");
           statuslabel.setForeground(Color.red);
       }else if(!isValid(t)){
          statuslabel.setText("Email is not valid");
           statuslabel.setForeground(Color.red);        
        }else
       {
          
           try {
              
               if(getpublickey(t)==false){
                   statuslabel.setText("Invalid email");
                   statuslabel.setForeground(Color.red);
               }else{
                  statuslabel.setText("Valid email");
                  statuslabel.setForeground(Color.green);
               if(getpublickey(t))
               { 
                 byte[] keybyte=GetFile("/tmp/publickey.key");
                   X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(keybyte);
                  KeyFactory kf = KeyFactory.getInstance("RSA");
                 
                  PublicKey pubKey = kf.generatePublic(keySpecX509);
                  
                  String phrase=JOptionPane.showInputDialog("Enter the pass phrase");
                  
                  Algorithm ec =new Algorithm();
                 m= ec.encrypt(pubKey,m,phrase); 
                 sendattachmentfile(t,s,m,phrase);
               }
               else{
                  statuslabel.setText("Email not valid ");
                  statuslabel.setForeground(Color.red); 
               }
                }
           } catch (Exception e){
              System.out.println(e.getMessage());
           }
       }
    }//GEN-LAST:event_sendattachmentbuttonActionPerformed
//
    private void decrypttxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decrypttxtActionPerformed
        String dec=encryptedtxt.getText();
        String t=JOptionPane.showInputDialog("Enter the parse phrase");
        if (dec.isEmpty()){
            statuslabel.setText("There is no data to decrypt");
            statuslabel.setForeground(Color.red);
        }else {
            try {
                JFileChooser chooser= new JFileChooser();
                chooser.showOpenDialog(null);
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                PrivateKey pv=getPrivate(chooser.getSelectedFile().getAbsolutePath());
                Algorithm xa=new Algorithm();                
                plaintexttxt.setText(xa.decrypt( pv,dec,t));
            } catch (Exception ex) {
               System.out.println(ex);
            }
        }
    }//GEN-LAST:event_decrypttxtActionPerformed

    private void decryptfilebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptfilebuttonActionPerformed
    try {
        String t=JOptionPane.showInputDialog("Enter the parse phrase");
        Algorithm re=new Algorithm();
        JFileChooser chooser= new JFileChooser();
        chooser.showOpenDialog(null);
        File f =chooser.getSelectedFile();
        re.decryptfile( f.getAbsolutePath(),t);
    } catch (Exception ex) {
       System.out.println(ex.getMessage());
    }
        
    }//GEN-LAST:event_decryptfilebuttonActionPerformed
  
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       login lg =new login();
       lg.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
  public byte[] GetFile(String filename) throws FileNotFoundException, IOException{
        File MyFile= new File(filename);
        FileInputStream FileData= new FileInputStream(MyFile);
        byte[] Temp=new byte[(int)MyFile.length()];
        FileData.read(Temp);
        FileData.close();
        return Temp;
        
    }
    private void generatehashbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatehashbtnActionPerformed
      try {
          String texts=valuetxtarea.getText();
        String t=typecombo.getSelectedItem().toString();
        if (texts.isEmpty()||t.isEmpty()){
           statuslabel.setText("Missing parameters");
           statuslabel.setForeground(Color.red);
        }else{
       Primitives hashf=new Primitives();
        hastextarea.setText(hashf.hashme(texts,t));}
    } catch (Exception ex) {
       System.out.println(ex.getMessage());
    }
    }//GEN-LAST:event_generatehashbtnActionPerformed
    //gets private key
    public PrivateKey getPrivate(String filename) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException{
      byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);  
    }	
    //gets public key
    public boolean getpublickey(String emails) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException{
        client cl=new client();
        cl.fetchkey(emails);
     byte[] b=GetFile("/tmp/publickey.key");
     if(b.length<100)
     {
         return false;
     }else{
   
      return true;}
       
    }
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
            java.util.logging.Logger.getLogger(MainKrypton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainKrypton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainKrypton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainKrypton.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainKrypton().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton attachbutton;
    private javax.swing.JTextField attachmenttxt;
    private javax.swing.JButton decryptfilebutton;
    private javax.swing.JPanel decryptpanel;
    private javax.swing.JButton decrypttxt;
    private javax.swing.JTextArea encryptedtxt;
    private javax.swing.JPanel encryptpanel;
    private javax.swing.JButton generatehashbtn;
    private javax.swing.JTextArea hastextarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton mailbutton;
    private javax.swing.JLabel path;
    private javax.swing.JTextArea plaintexttxt;
    private javax.swing.JToggleButton sendattachmentbutton;
    private javax.swing.JLabel statuslabel;
    private javax.swing.JTextField subjecttxt;
    private javax.swing.JTextArea texttxt;
    private javax.swing.JTextField totxt;
    private javax.swing.JComboBox<String> typecombo;
    private javax.swing.JTextArea valuetxtarea;
    // End of variables declaration//GEN-END:variables
}
