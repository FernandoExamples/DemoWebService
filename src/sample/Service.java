package sample;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Service {

    public static String getRequest(String url) throws IOException {
        String res = null;

        HttpClientBuilder hcBuilder = HttpClients.custom();
        HttpClient client = hcBuilder.build();

        HttpGet request = new HttpGet(url);

        //Executing the call
        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() == 200) {

//            System.out.println("\nSending 'Get' to " + url);
//            System.out.println("HTTP Response: " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            //Reading the response
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null)
                result.append(line);

            res = result.toString();

        } else {
            System.out.println("HTTP Response: " + response.getStatusLine().getStatusCode());
            System.out.println("Error By " + response.getStatusLine().getReasonPhrase());
        }

        return res;
    }


    /**
     * Elimina las secuencias raras del URL que no se pueden eliminar por replaceAll()
     * @param secuence
     * @return
     */
    public static String removeSpecialCharacter(String secuence, String remove, String replace){

        while (secuence.contains(remove)){

            int n = secuence.indexOf(remove);
            secuence = secuence.substring(0, n) + replace + secuence.substring(n + remove.length());

        }

        return secuence;
    }

    /**
     * Quita las cadenas raras mas comunes del URL
     * @param secuence
     * @return
     */
    public static String removeCommonsCharacter(String secuence){
        secuence = secuence.replaceAll(" ", "%20");
        secuence = secuence.replaceAll("–", "%E2%80%93");
        secuence = secuence.replaceAll("’", "%E2%80%99");
        secuence = removeSpecialCharacter(secuence, "\\u2013", "%E2%80%93");
        secuence = removeSpecialCharacter(secuence, "\\u2019", "%E2%80%99");

        return secuence;
    }
}
