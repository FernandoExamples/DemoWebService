package sample.LibraryService;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import sample.Service;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryService {

    public String getRequest() throws IOException {
        String res;

        //image, video and audio
        String url = "https://images-api.nasa.gov/search?q=Orion&page=3&media_type=image,video,audio&year_start=1920&year_end=2019";

        //image
//        String url = "https://images-api.nasa.gov/search?q=Orion&page=2&media_type=image&year_start=1920&year_end=2019";

        //video
//        String url = "https://images-api.nasa.gov/search?q=Orion&page=2&media_type=video&year_start=1920&year_end=2019";

        //audio
//        String url = "https://images-api.nasa.gov/search?q=Orion&page=1&media_type=audio&year_start=1920&year_end=2019";

        res = Service.getRequest(url);

        return res;
    }

    public void processImage(String Href) throws IOException {
        LibraryCollection.Image image = new LibraryCollection.Image(Href);

        System.out.println("Link to go To Image " + image.getLinkImage());
    }

    public void processVideo(String Href) throws IOException {
        LibraryCollection.Video video = new LibraryCollection.Video(Href);

        System.out.println("Link to go To Video " + video.getLinkVideo());
    }

    public void processAudio(String Href) throws IOException {
        LibraryCollection.Audio audio = new LibraryCollection.Audio(Href);

        System.out.println("Link to go To Audio " + audio.getLinkAudio());
    }

    public static void main(String[] args) throws IOException {

        LibraryService libraryService = new LibraryService();

        String result = libraryService.getRequest();

        /** PARSE JSON to JAVA **/

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        LibraryCollection library = mapper.readValue(result, LibraryCollection.class);

        Collection collection = library.getCollection();

        System.out.println("---------------Data OF JSON---------------");
        System.out.println();

        System.out.println("Total Hints: " + collection.getMetadata().getTotal_hits());
        System.out.println("Href: " + collection.getHref());
        System.out.println("Version: " + collection.getVersion());

        System.out.println();
        System.out.println("----------------Links of PAGES (NEXT or PREVIOUS)--------------------");

        if (collection.getLinks() != null)
            for (LinkCollection link : collection.getLinks()) {
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

            for (Data data : item.getData()) {
                System.out.println("Center: " + data.getCenter());
                System.out.println("Date Created: " + data.getDate_created());
                System.out.println("Location: " + data.getLocation());
                System.out.println("Media Type: " + data.getMedia_type());
                System.out.println("Nasa ID: " + data.getNasa_id());
                System.out.println("Photographer: " + data.getPhotographer());
                System.out.println("Secondary Creator: " + data.getSecondary_creator());
                System.out.println("Title: " + data.getTitle());

                System.out.print("Keywords: ");
                if (data.getKeywords() != null)
                    for (String keyword : data.getKeywords())
                        System.out.print(keyword + ", ");

                System.out.println();
                System.out.println("Description: " + data.getDescription());
            }

            int j = 0;
            if (item.getLinks() != null)
                for (LinkItem link : item.getLinks()) {
                    System.out.println(" ----- link " + j + "-----");
                    System.out.println("    Href: " + link.getHref());
                    System.out.println("    Rel: " + link.getRel());
                    System.out.println("    Render: " + link.getRender());
                    j++;
                }

            System.out.println();
            System.out.println();


            //Tratar cada Item dependiendo de que tipo sea
            if (item.getData().get(0).getMedia_type().equals("image")) {
                libraryService.processImage(item.getHref());
                System.out.println();
            } else if (item.getData().get(0).getMedia_type().equals("video")) {
                libraryService.processVideo(item.getHref());
                System.out.println();
            }else{
                libraryService.processAudio(item.getHref());
                System.out.println();
            }

        }

    }
}
