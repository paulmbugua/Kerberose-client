package krypton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;

public class Algorithm {
 public static byte[] Key;
    public static byte[] plaintext;
    public static byte[] ciphertext;
    public static byte[] chunk1;
    public static byte[] chunk2;
    public static byte[] key1;
    public static byte[] key2;
 //encryption of text
public String encrypt(PublicKey key,String message,String phrase) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
   
   Cipher cipher=Cipher.getInstance("RSA");
    plaintext=message.getBytes();
   Key=phrase.getBytes();
   splitbytes(plaintext);
    chunk1=reversebytes(chunk1);
    chunk2=reversebytes(chunk2);
    plaintext=concatination();
    plaintext=XORFunction(plaintext, Key);
    plaintext=transpose(2, plaintext);
    plaintext=XORFunction(plaintext, Key);
    splitkey();
    splitbytes(plaintext);
    chunk1=XORFunction(chunk1, key1);
    chunk2=XORFunction(chunk2, key2);
    plaintext=concatination();
    ciphertext=XORFunction(plaintext, Key);
    cipher.init(Cipher.ENCRYPT_MODE,key);
    byte[] cipherbytes =cipher.doFinal(ciphertext);
   // return Base64.encodeBase64String(cipher.doFinal(message.getBytes("UTF-8")));
    return Base64.encodeBase64String(cipherbytes);
    
}
//decryption of text
public String decrypt(PrivateKey key,String message,String phrase) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
   // String cipherText = Base64.getEncoder().encodeToString(message.getBytes());
   byte[] reCipherBytes = Base64.decodeBase64(message);
    Cipher cipher=Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE,key);
   // return new String(cipher.doFinal(Base64.decodeBase64(message)),"UTF-8");
    ciphertext=cipher.doFinal(reCipherBytes);
    Key=phrase.getBytes();
    plaintext=XORFunction(ciphertext, Key);
    splitbytes(plaintext);
    splitkey();
    chunk1=XORFunction(chunk1, key1);
    chunk2=XORFunction(chunk2, key2);
    plaintext=concatination();
    plaintext=XORFunction(plaintext, Key);
    plaintext=reversetranspose(2, plaintext);
    plaintext=XORFunction(plaintext, Key);
    splitbytes(plaintext);
    chunk1=reversebytes(chunk1);
    chunk2=reversebytes(chunk2);
    plaintext=concatination(); 
    
   return new String(plaintext);
            
    
}
//encryption of file
public void encryptFile(String filename,String phrase) throws IOException{
   plaintext=GetFile(filename);
   Key=phrase.getBytes();
   splitbytes(plaintext);
    chunk1=reversebytes(chunk1);
    chunk2=reversebytes(chunk2);
    plaintext=concatination();
    plaintext=XORFunction(plaintext, Key);
    plaintext=transpose(2, plaintext);
    plaintext=XORFunction(plaintext, Key);
    splitkey();
    splitbytes(plaintext);
    chunk1=XORFunction(chunk1, key1);
    chunk2=XORFunction(chunk2, key2);
    plaintext=concatination();
    ciphertext=XORFunction(plaintext, Key);
    writefile(ciphertext, filename+".crypt");
}
//decryption of file
public void decryptfile(String filename,String phrase) throws IOException{
  
   
    ciphertext=GetFile(filename);
    Key=phrase.getBytes();
    plaintext=XORFunction(ciphertext, Key);
    splitbytes(plaintext);
    splitkey();
    chunk1=XORFunction(chunk1, key1);
    chunk2=XORFunction(chunk2, key2);
    plaintext=concatination();
    plaintext=XORFunction(plaintext, Key);
    plaintext=reversetranspose(2, plaintext);
    plaintext=XORFunction(plaintext, Key);
    splitbytes(plaintext);
    chunk1=reversebytes(chunk1);
    chunk2=reversebytes(chunk2);
    plaintext=concatination(); 
    writefile(plaintext, filename+".plain");
}
//XOR function
    public static byte[] XORFunction(byte[] Data, byte[] key){
     byte []temp=new byte[(int)Data.length];
     int pos=0;   
     for (int i=0;i<Data.length;i++){
         if(pos>key.length-1)
         {
             pos=0;
         }
         temp[i]=(byte)(Data[i]^key[pos]);
             pos++;
     } 
     return temp;
    }
     //Transpose function  
    public static byte[] transpose(int t, byte[] filebyte) throws IOException{    byte [] newarray= new byte[filebyte.length];
        int j=t;
        for(int p=0;p<filebyte.length;p++){
            if(j>=filebyte.length)
            {
                j=0;
            }
            newarray[j]=filebyte[p];
            j++;
        }
        return newarray;
    }
    //reverse transpose
    public static byte[] reversetranspose(int t,byte[] filebyte){
         byte [] newarray1= new byte[filebyte.length];
       int j2=(filebyte.length-1)-t;
        for(int p=filebyte.length-1;p>=0;p--){
            if(j2<0)
            {
                j2=filebyte.length-1;
            }
            newarray1[j2]=filebyte[p];
            j2--;
        }
        return newarray1;
    }
   //Reverse the bytes
    public static byte[]reversebytes(byte [] inputbyte) throws IOException{
        byte[] temp= new byte[(inputbyte.length)];
        int position=temp.length-1;
            for(int i=0;i<inputbyte.length;i++){
                temp[position]=inputbyte[i];
                position--;
            }
       return temp;
    }
     //function to split the key
    public static void splitkey() throws IOException{
       int pos=Key.length; 
     key1=new byte[(pos+1)/2];
      key2=new byte[(pos-key1.length)];
       for (int i=0; i<pos;i++){
           if (i<key1.length)
           {
               key1[i]=Key[i];
           }
           else
           {
               key2[i-key1.length]=Key[i];
           }
           
       }
            
    }
     //combinign the key
    public void concatkey(){
        int size=(key1.length) + (key2.length);
        byte[] arraycombined=new byte [size];
        for(int i=0;i<arraycombined.length;i++){
          if (i<key1.length){
              arraycombined[i]=key1[i];
          }else
          {
              arraycombined[i]=key2[i-key1.length];
          }
        }
        
    
    }
    //function to split the bytes
    public static void splitbytes(byte [] inputbytes) throws IOException{
        plaintext=inputbytes;
       int pos=inputbytes.length; 
      chunk1=new byte[(pos+1)/2];
      chunk2=new byte[(pos-chunk1.length)];
       for (int i=0; i<pos;i++){
           if (i<chunk1.length)
           {
               chunk1[i]=inputbytes[i];
           }
           else
           {
               chunk2[i-chunk1.length]=inputbytes[i];
           }
           
       }
              
    }
    //combinign the blocks
    public static byte[] concatination(){
        int size=(chunk1.length) + (chunk2.length);
        byte[] arraycombined=new byte [size];
        for(int i=0;i<arraycombined.length;i++){
          if (i<chunk1.length){
              arraycombined[i]=chunk1[i];
          }else
          {
              arraycombined[i]=chunk2[i-chunk1.length];
          }
        }
    return arraycombined;
    } 
     //Getting the file
    public byte[] GetFile(String filename) throws FileNotFoundException, IOException{
        File MyFile= new File(filename);
        FileInputStream FileData= new FileInputStream(MyFile);
        byte[] Temp=new byte[(int)MyFile.length()];
        FileData.read(Temp);
        FileData.close();
        return Temp;
        
    }
      //write file
    public void writefile(byte[] data,String filename) throws FileNotFoundException, IOException{
        File outfile= new File(filename);
        FileOutputStream outdata=new FileOutputStream(outfile);
        outdata.write(data);
        outdata.close();
    }
}
