package sample;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LibraryService {

    public static void main(String[] args) throws IOException {
        String url = "https://images-api.nasa.gov/search?q=Orion&page=2&media_type=image,video,audio&year_start=1920&year_end=2019";

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


        /** PARSE JSON to JAVA **/

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        LibraryCollection library = mapper.readValue(result.toString(), LibraryCollection.class);

        Collection collection = library.getCollection();

        System.out.println("---------------Data OF JSON---------------");
        System.out.println();

        System.out.println("Total Hints: " + collection.getMetadata().getTotal_hits());
        System.out.println("Href: " + collection.getHref());
        System.out.println("Version: " + collection.getVersion());

        System.out.println();
        System.out.println("----------------Links of PAGES (NEXT or PREVIOUS)--------------------");

        for (LinkCollection link : collection.getLinks()){
            System.out.println("Prompt: " + link.getPrompt());
            System.out.println("Rel: " + link.getRel());
            System.out.println("Href Link: " + link.getHref());
            System.out.println();
        }

        System.out.println();
        System.out.println("----------------------------------Items------------------");

        ArrayList<Item> items = collection.getItems();
        Item item;

        for (int i = 0; i < items.size(); i++) {

            System.out.println("-------Item " + i + "------");
            item = items.get(i);
            System.out.println("Href item: " + item.getHref());

            for(Data data : item.getData()){
                System.out.println("Center: " + data.getCenter());
                System.out.println("Date Created: " + data.getDate_created());
                System.out.println("Location: " + data.getLocation());
                System.out.println("Media Type: " + data.getMedia_type());
                System.out.println("Nasa ID: " + data.getNasa_id());
                System.out.println("Photographer: " + data.getPhotographer());
                System.out.println("Secondary Creator: " + data.getSecondary_creator());
                System.out.println("Title: " + data.getTitle());

                System.out.print("Keywords: ");
                for (String keyword : data.getKeywords())
                    System.out.print(keyword + ", ");

                System.out.println();
                System.out.println("Description: " + data.getDescription());
            }

            int j = 0;
            for(LinkItem link : item.getLinks()){
                System.out.println(" ----- link " + j + "-----");
                System.out.println("    Href: " + link.getHref());
                System.out.println("    Rel: " + link.getRel());
                System.out.println("    Render: " + link.getRender());
                j++;
            }

            System.out.println();
            System.out.println();
        }

    }
}
