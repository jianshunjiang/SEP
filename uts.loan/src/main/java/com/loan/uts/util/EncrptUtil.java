package com.loan.uts.util;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncrptUtil {

    public void Encrypt(){
        PasswordEncoder password = new BCryptPasswordEncoder();
        for(int i = 0; i < 5; ++i){
            String encrypted = password.encode("PaSsWoRd");

            if(password.matches("PaSsWoRd", encrypted)) {

            }
            else {

            }
        }
    }

    public static String encrypt(String s){

        String password = "testpassword";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        String candidate = "testpassword";

        if(BCrypt.checkpw(candidate, hashed)){

        }
        else {

        }
        return "";
    }
}
