package com.yb.user.center.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Niuniu {
    public static void main(String[] args) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader dr = new BufferedReader(input);
        String num = dr.readLine();
        String str = dr.readLine();
        String[] actions = str.split("");
        String location = "N";
        for (String action : actions) {
            if (action.equals("L")){
                switch (location){
                    case "N" :
                        location = "W";
                        break;
                    case "S" :
                        location = "E";
                        break;
                    case "E" :
                        location = "N";
                        break;
                    case "W" :
                        location = "S";
                        break;
                }
            }else{
                switch (location){
                    case "N" :
                        location = "E";
                        break;
                    case "S" :
                        location = "W";
                        break;
                    case "E" :
                        location = "S";
                        break;
                    case "W" :
                        location = "N";
                        break;
                }
            }
        }
        System.out.print(location);
    }
}
