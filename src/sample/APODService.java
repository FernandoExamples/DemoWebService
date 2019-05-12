package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jlbugarin
 */
public class APODService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String endpoint = "https://api.nasa.gov/planetary/apod?api_key=";
        String apiKey = "MlYLzw4KhLpIhVZEL3ly4ZItTFfefzYvDpnFcIlb";

        String url = endpoint + apiKey;

        HttpClientBuilder hcBuilder = HttpClients.custom();
        HttpClient client = hcBuilder.build();

        HttpGet request = new HttpGet(url);

        //Setting header parameters
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");

        //Executing the call
        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'Get' to " + url);
        System.out.println("HTTP Response: " +  response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        //Reading the response
        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null)
            result.append(line);


        System.out.println(result);

        //parsin the response to JSON Object
        try {
            JSONObject jsonObject = new JSONObject(result.toString());
            String copyrigth = jsonObject.getString("copyright");
            String date = jsonObject.getString("date");
            String explanation = jsonObject.getString("explanation");
            String mediaType = jsonObject.getString("media_type");
            String serviceVersion = jsonObject.getString("service_version");
            String title = jsonObject.getString("title");
            String imageUrl = jsonObject.getString("url");

            System.out.println("\n---------------Impreso son clase JOBJECT--------------------------------------------------");
            System.out.println("Copyrigth: " + copyrigth);
            System.out.println("Date: "+ date);
            System.out.println("Explanation: " + explanation);
            System.out.println("Media Type: " + mediaType);
            System.out.println("Service Version: " + serviceVersion);
            System.out.println("Title: " + title);
            System.out.println("URL: " + imageUrl);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        La forma anterior es usando un JSON-Object para sacar cada valor, pero usando una libreria se puede transformar
        directamente la respuesta JSON en un BEAN definido por nosotros:
        fasterxml-jackson-core
         */

        ObjectMapper mapper = new ObjectMapper();

        /*Si en el Bean no incluyo todas las propiedades que me regresan el JSON el mapper va a crashear, por eso
        * se le pasa este flag, para que las ignore.
        * En este caso no me interesa el hdurl.
        */
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        APODBean apodBean = mapper.readValue(result.toString(), APODBean.class);

        System.out.println("\n-------------------Impreso con Clase ObjectMapper---------------------------------------------------");
        System.out.println("Copyrigth: " + apodBean.getCopyright());
        System.out.println("Date: "+ apodBean.getDate());
        System.out.println("Explanation: " + apodBean.getExplanation());
        System.out.println("Media Type: " + apodBean.getMedia_type());
        System.out.println("Service Version: " + apodBean.getService_version());
        System.out.println("Title: " + apodBean.getTitle());
        System.out.println("URL: " + apodBean.getUrl());

    }

}