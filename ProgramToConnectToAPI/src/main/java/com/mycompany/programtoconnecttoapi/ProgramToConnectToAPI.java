package com.mycompany.programtoconnecttoapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Assingment 2
 * CSC 340
 * This program will connect to an API and search up a word the user put in
 * @author Bunrong
 */
public class ProgramToConnectToAPI {

    private static HttpURLConnection connection;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Search up a word with no caplization: ");
        String word = input.nextLine();

        BufferedReader reader;
        String line;
        StringBuffer respone = new StringBuffer();

        try {
            //url to dictornary API
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //setup request
            connection.setRequestMethod("GET");
            connection.connect();

            //check if connection is 200
            int status = connection.getResponseCode();
            if (status < 200) {
                System.out.println("The connection is unsucessful");
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    respone.append(line);
                }
                connection.disconnect();

            }

            System.out.println(respone.toString());
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
