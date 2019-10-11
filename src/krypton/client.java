package krypton;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JFileChooser;
import org.apache.commons.codec.binary.Base64;

public class client {
    private Cipher cipher;
    static int port=4444;
    public client() throws NoSuchAlgorithmException, NoSuchPaddingException{
        this.cipher=Cipher.getInstance("RSA");
    }

 
    public PrivateKey getPrivate(String filename) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException{
      byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);  
    }
    public PublicKey getPublic(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
    public String encryptText(String msg, PublicKey key) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.ENCRYPT_MODE , key);		
                return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}
    public String decryptText(String msg, PrivateKey key) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return  new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8"); 
	}
    public static boolean connection(String msg) throws UnknownHostException, IOException, Exception{
         //System.setProperty("javax.net.ssl.trustStore","/home/pc/examplestore");
         //System.setProperty("javax.net.ssl.trustStorePassword","paulmbugua");
        // SSLSocketFactory sf=(SSLSocketFactory)SSLSocketFactory.getDefault();
         //InetAddress ip=InetAddress.getLocalHost();
       
        //Socket s=new Socket(ip,port);
         Socket s=new Socket("192.168.2.237",port);
        DataInputStream dis= new DataInputStream(s.getInputStream());
        DataOutputStream dos= new DataOutputStream(s.getOutputStream());
        
        client c=new client();
        JFileChooser chooser=new JFileChooser();
                chooser.setDialogTitle("Select server public key"); 
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.showSaveDialog(null);
        PublicKey serverpubk= c.getPublic(chooser.getSelectedFile().getAbsolutePath());
        String y=msg;
        y=c.encryptText(y, serverpubk);              
        dos.writeUTF(y);
        
        String response=dis.readUTF();
        if(response.equals("false")){
          return false;  
        }else{
            JFileChooser chooser1=new JFileChooser();
                chooser1.setDialogTitle("Select Your private key"); 
                chooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser1.showSaveDialog(null);
            PrivateKey pv=c.getPrivate(chooser1.getSelectedFile().getAbsolutePath());              
       if(  "true".equals(c.decryptText(response, pv)))
       { return true;}
       
       else{
           return false;
       }
        }        
    }
    public static boolean test(String xy) throws IOException, Exception{
       boolean x=connection(xy);
       if (x==true){
           System.out.println("logged in");
           return true;
       }
       else{
           System.out.println("wrong credentials");
           return false;
       }
        
    }

    public void fetchkey(String emails) throws UnknownHostException, IOException {
        //System.setProperty("javax.net.ssl.trustStore","/home/pc/examplestore");
       // System.setProperty("javax.net.ssl.trustStorePassword","paulmbugua");
        //SSLSocketFactory sf=(SSLSocketFactory)SSLSocketFactory.getDefault();
        
        //Socket s1=sf.createSocket("192.168.2.237",4445);
        Socket s1=new Socket("192.168.2.237",4445);
        byte[] mb= new byte[1024];
        DataOutputStream dos= new DataOutputStream(s1.getOutputStream());
        dos.writeUTF(emails);
        
        try{
        InputStream is=s1.getInputStream();
        FileOutputStream fos =new FileOutputStream("/tmp/publickey.key");
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        int b =is.read(mb,0,mb.length);
        bos.write(mb,0,b);
        bos.close();
        s1.close();
                
        }
        catch (Exception e){
            
        }
    
    }
    
  
}
