package krypton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
public class Primitives {
  public static byte[] IV;
    public static byte[] Key;
    public static byte[] plaintext;
    public static byte[] ciphertext;
    public static byte[] chunk1;
    public static byte[] chunk2;
    public static byte[] key1;
    public static byte[] key2;
    public static String path;
    public static int t;
    private static PrivateKey privkey;
    private static PublicKey pubkey;
    private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;
    //Generate public and private key
   public void GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
	}
   public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}
public static String hashme(String input,String t) throws NoSuchAlgorithmException{
     MessageDigest digest= MessageDigest.getInstance(t); 
       byte[] hash= digest.digest(input.getBytes());
       BigInteger number =new BigInteger(1,hash);
       return (number.toString(16));
    }
	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

   
      public static void writefile(byte[] data,String filename) throws FileNotFoundException, IOException{
        File outfile= new File(filename);
        FileOutputStream outdata=new FileOutputStream(outfile);
        outdata.write(data);
        outdata.close();
    }      
    
    public static void main(String[] args){
        JFileChooser chooser=new JFileChooser();
              chooser.setDialogTitle("Where do you wish to save your keys"); 
              chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showSaveDialog(null);
           
            PrivateKey privkey;PublicKey pubkey; 
            try {            
            KeyPairGenerator keygen=KeyPairGenerator.getInstance("RSA");
            keygen.initialize(1024);
            KeyPair pair=keygen.generateKeyPair();
            privkey =pair.getPrivate();
            pubkey=pair.getPublic();
            String keypath=chooser.getSelectedFile().getAbsolutePath();
            writefile(privkey.getEncoded(),keypath+"/sender.private.key");
            writefile(pubkey.getEncoded(),keypath+"/sender.public.key");
           
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
