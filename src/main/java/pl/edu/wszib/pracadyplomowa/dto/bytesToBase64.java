package pl.edu.wszib.pracadyplomowa.dto;


import java.util.Base64;

public class bytesToBase64 {

    public static String byteToBase64( byte[] bytes){
        String encodedString = Base64.getEncoder().encodeToString(bytes);
        return encodedString;
    }

}
