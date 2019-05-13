package sample.LibraryService;

import org.apache.http.client.utils.URLEncodedUtils;
import sample.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class LibraryCollection {
    private Collection collection;

    public LibraryCollection() {
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    /**
     * Separa Los enlaces en JSON por comas
     *
     * @param JSONResult
     * @return
     */
    private static String[] splitLinks(String JSONResult) {
        return JSONResult.split("\",");
    }

    private static String removeJSONCharacters(String secuence) {
        if (secuence.startsWith(" "))
            secuence = secuence.substring(1);

        String s = secuence.replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\"", "");

        return s;
    }

    /**
     * Clases internas para el tratamiento de los medios
     **/

    public static class Image {
        private String JSONResult;

        public Image(String href) throws IOException {
            JSONResult = Service.getRequest(href);
        }

        public String getJSONResult() {
            return JSONResult;
        }

        /**
         * De la lista de links, regresa el que lleva a la imagen small
         *
         * @return
         */
        public String getLinkImage() {
            String[] links = splitLinks(JSONResult);

            for (String l : links)
                if (l.contains("small")) {
                    l = removeJSONCharacters(l);
                    return Service.removeCommonsCharacter(l);
                    //System.out.println(removeJSONCharacters(l));
                }

            return null;
        }
    }

    public static class Video {
        private String JSONResult;

        public Video(String href) throws IOException {
            JSONResult = Service.getRequest(href);
        }

        public String getJSONResult() {
            return JSONResult;
        }

        public String getLinkVideo() {
            String links[] = splitLinks(JSONResult);

            for (String l : links) {
                if (l.contains("medium.mp4")) {
                    l = removeJSONCharacters(l);
                    return Service.removeCommonsCharacter(l);
                    //System.out.println(removeJSONCharacters(l));
                }
            }

            return null;
        }
    }

    public static class Audio {
        private String JSONResult;

        public Audio(String href) throws IOException {
            JSONResult = Service.getRequest(href);
        }

        public String getJSONResult() {
            return JSONResult;
        }

        public String getLinkAudio() {
            String links[] = splitLinks(JSONResult);

            for (String l : links) {
                if (l.contains("128k.mp3")) {
                    l = removeJSONCharacters(l);
                    return Service.removeCommonsCharacter(l);
                    //System.out.println(removeJSONCharacters(l));
                }
            }

            return null;
        }
    }
}

class Collection {
    private String version;
    private Metadata metadata; //metadata of search. Only total of results. But JSON send it like a class.
    private String href;       //link of the search
    private ArrayList<LinkCollection> links; //links to go to next Page and Previous Page
    private ArrayList<Item> items;   //All media items

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getHref() {
        return Service.removeCommonsCharacter(href);
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<LinkCollection> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkCollection> links) {
        this.links = links;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    //</editor-fold>
}

class Metadata {
    private int total_hits; //total of items

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getTotal_hits() {
        return total_hits;
    }

    public void setTotal_hits(int total_hits) {
        this.total_hits = total_hits;
    }
    //</editor-fold

}

class LinkCollection {
    private String rel; //the abbreviation of the promt --> next or prev
    private String prompt; //word Next or Previous depending of link is for the previous or Next page
    private String href; //the link of the previous or next page

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getHref() {
        return Service.removeCommonsCharacter(href);
    }

    public void setHref(String href) {
        this.href = href;
    }

    //</editor-fold>
}

/**
 * --------------------------------Clase que almacena cada Item de la busqueda-------------------
 */
class Item {
    private ArrayList<Data> data; //all data of this item
    private String href; //the link of JSON of this specific item. NOT of the media but JSON information. This is the most important link to go to the media
    private ArrayList<LinkItem> links; //the links of the preview. Generally it is only one for images and two for videos

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public String getHref() {
        return Service.removeCommonsCharacter(href);
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<LinkItem> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkItem> links) {
        this.links = links;
    }

    // </editor-fold>

}

class Data {
    private String center;
    private String description;
    private String nasa_id;
    private String media_type;
    private String title;
    private String date_created;
    private String secondary_creator;
    private String photographer;
    private String location;
    private ArrayList<String> keywords;

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNasa_id() {
        return nasa_id;
    }

    public void setNasa_id(String nasa_id) {
        this.nasa_id = nasa_id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_created() {
        return date_created.substring(0, 10);
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getSecondary_creator() {
        return secondary_creator;
    }

    public void setSecondary_creator(String secondary_creator) {
        this.secondary_creator = secondary_creator;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    // </editor-fold>
}

class LinkItem {
    private String render; //how render this preview.
    private String href; //the link of the preview of the item.
    private String rel;

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getHref() {
        return Service.removeCommonsCharacter(href);
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
    // </editor-fold>
}
