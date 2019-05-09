package com.imagetotext.com;
import java.io.PrintWriter;
public class Main {
public static void main(String[] args) {
 String input_file=""; 
 String output_file=""; //No need to specify .txt
 String tesseract_install_path="C:\\Program Files (x86)\\Tesseract-OCR\\tesseract"; //Tesseract path folder
 String[] command =
    {
        "cmd",
    };
    Process p;
 try {
        p = Runtime.getRuntime().exec(command);
        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
        new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
        PrintWriter stdin = new PrintWriter(p.getOutputStream());
        stdin.println("\""+tesseract_install_path+"\" \""+input_file+"\" \""+output_file+"\" -l eng");
        stdin.close();
        p.waitFor();
        System.out.println("\n"+Read_File.read_a_file(output_file+".txt")); //Result
    } catch (Exception e) {
 e.printStackTrace();
    }
  }
}