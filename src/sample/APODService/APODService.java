package sample.APODService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Service;

/**
 *
 * @author jlbugarin
 */
public class APODService {

    public String getRequest() throws IOException {
        String res;

        String endpoint = "https://api.nasa.gov/planetary/apod?api_key=";
        String apiKey = "MlYLzw4KhLpIhVZEL3ly4ZItTFfefzYvDpnFcIlb";

        String url = endpoint + apiKey;

        res = Service.getRequest(url);

        return res;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        APODService service = new APODService();

        String result = service.getRequest();

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