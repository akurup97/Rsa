/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.lang.Math;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Scanner;
/**
 *
 * @author adith
 */
public class Rsa {
 
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int findGCD(int x, int y) {
        int count = 0;
        if(y == 0){ 
            return x;
        } 
        
        return findGCD(y, x%y); 
    }
    
    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 'A' - 1)) : " ";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int func;
        String temp_val;
        //int dc_val;
        int val;
        int auto;
        BigInteger[] encrypt = new BigInteger[1000];
        BigInteger[] decrypt = new BigInteger[1000];
        int p;
        int q;
        int n;
        int k;
        int e = 0;
        int d = 0;
        int[] e_values = new int[20];
        int[] prime = new int[]{2,3,5,7,11,13,17,19,23};
        int[] message = new int[1000];
        int[] dc_message = new int[1000];
        String new_val = "";
        
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
     
        System.out.println("Enter 0 to encrypt or 1 to decrypt.");
        func = scan.nextInt();
        
        if (func == 0){
            System.out.println("Enter 0 to encrypt automatically or 1 for manual");
            auto = scan.nextInt();
            scan.nextLine();
            
            System.out.println("Enter message to encrypt");
            temp_val = scan.nextLine();
        }
        else{
            System.out.println("Enter 1 for manual decryption");
            auto = scan.nextInt();
            scan.nextLine();
            
            System.out.println("Enter numerical values to decrypt");
            temp_val = scan.nextLine();
        }
        if (auto == 0){
            p = prime[((int) (Math.random() * 9))];
            System.out.println("P value: " + p);
            q = prime[((int) (Math.random() * 9))];
            System.out.println("Q value: " + q);
        } 
        else{
            System.out.println("Please enter a P");
            p = scan.nextInt();
            for (int i = 2; i < p; i++){
                if (p % i == 0 || p < 0){
                    System.out.println("P is not prime");
                    System.exit(0);
                }
            }
        //System.out.println(p);
        
            System.out.println("Please enter a Q");
            q = scan.nextInt();
            for (int i = 2; i < q; i++){
                if (q % i == 0 || q < 0){
                    System.out.println("Q is not prime");
                    System.exit(0);
                }
            }
        //System.out.println(q);
        }
        
        if (func == 0){
            for(int i = 0; i < temp_val.length(); i++){
                //System.out.println(val.charAt(i) -96);
                if (temp_val.charAt(i) == ' '){
                    message[i] = 0;
                }
                else{
                    message[i] = ((int) temp_val.charAt(i) - 96);
                }
            }
        }
        
        
        //val = Integer.parseInt(new_val);
        //System.out.println(val);
        
        n = p * q;
        k = (p-1)*(q-1);
        //System.out.println(k);
        
        if (auto == 0){
            int arr_count = 0;
            int count = 0;
            for(int i = 2; i < k; i++){
                if (count <= 5){
                    int gcd = findGCD(i, k);
                    //System.out.println(val);
                    if (gcd == 1){
                        //System.out.println(i);
                        e_values[arr_count] = i;
                        arr_count++;
                    }

                }
                count++;
            }

            e = e_values[0];
        }
        else{
            if (func == 0){
                System.out.println("Please enter a value for e");
                e = scan.nextInt();
            }
            else{
                System.out.println("Please enter a value for d");
                d = scan.nextInt();
            }
        }

        if (func == 0){
            for(int i = 1; i < k; i++){

                if ((i * e)%k == 1 ){
                    d = i;
                }
            }
        }
        
        if (func == 0){
            System.out.format("Public key is: (%d, %d)", e, n);
        }
        System.out.format("\nPrivate key is: (%d, %d)", d, n);
        
        BigInteger dec_n = BigInteger.valueOf(n);
        String[] temp_msg = temp_val.split("\\s");
        String temp_storage = "";
        
        if (func == 1){
            System.out.print("\nDecryption: ");
            for(int i = 0; i < temp_msg.length; i++){
                //System.out.print(temp_msg[i]);
                dc_message[i] = Integer.parseInt(temp_msg[i]);
                decrypt[i] = (BigDecimal.valueOf(Math.pow(dc_message[i], d)).toBigInteger());
                decrypt[i] = decrypt[i].mod(dec_n);
                temp_storage = getCharForNumber((decrypt[i].intValue()));
                System.out.print(temp_storage + " "); 
            }
            System.out.print("\n");
        }
        
        
        BigInteger new_n = BigInteger.valueOf(n);
        
        
        if (func == 0){
            /*System.out.println(n);
            System.out.println(val);
            System.out.println(e);*/
            System.out.print("\nEncrypted value: ");
            for(int i = 0; i < temp_val.length(); i++){

                encrypt[i] = (BigDecimal.valueOf(Math.pow(message[i], e)).toBigInteger());
                encrypt[i] = encrypt[i].mod(new_n);
                System.out.print(encrypt[i] + " ");
            }
            System.out.print("\n");
        }
        
    }
    
            
}
        
    
    

