package aerlingsson.codeprojects.urlShortener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class UrlShortener {
    private String path = Paths.get(Paths.get("").toAbsolutePath().toString(), "store.txt").toString();
    private File file = new File(path);
    private HashMap<String, String> storeMap = new HashMap<>();

    // Constructor
    public UrlShortener(){}

    // Shortens a URL and stores it in storeMap
    public String shorten(String url){
        int urlValue = calculateUrlValue(url);
        String shortUrl = getUniqueKey(urlValue);

        storeMap.put(shortUrl, url);

        return shortUrl;
    }

    // Calculates a token value based on the value of each character in URL string
    public int calculateUrlValue(String url){
        int urlValue = 0;

        for (int i = 0; i < url.length(); i++){
            urlValue += ((int) url.charAt(i) % 10);
        }
        
        return urlValue;
    }

    // Creates a unique token based on initially generated token
    public String getUniqueKey(int key){
        while (get(Integer.toString(key)) != null){
            key += 1;
        }

        return Integer.toString(key);
    }

    // Get url of token from storeMap
    private String get(String token){
        return this.storeMap.get(token);
    }

    // Resolves a token and returns its url if token is valid, or an error message (not exception) if not
    public String resolve(String token){
        if (this.storeMap.get(token) == null){
            return "Invalid token";
        }

        return this.storeMap.get(token);
    }

    // Stores an entry in storeMap
    public void putInStoreMap(String key, String value){
        this.storeMap.put(key, value);
    }



    // Writes storeMap to store.txt, with one entry on each line, as such: "token=>url"
    public void writeToFile(){
        try {
            FileWriter writer = new FileWriter(file);
            Iterator<Map.Entry<String, String>> it = storeMap.entrySet().iterator();

            while (it.hasNext()){
                Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                writer.write(entry.getKey() + "=>" + entry.getValue());
                it.remove();

                if (it.hasNext()){
                    writer.write("\n");
                }
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Reads from store.txt and stores in storeMap
    public void readFromFile() throws FileNotFoundException {
        String line;
        String[] splitLine;

        if (!Files.exists(file.toPath())){  // File does not exist
            throw new FileNotFoundException("File: " + file.toString() + " does not exist");
        }

        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            
            while ((line = reader.readLine()) != null){   // Consumes a line on the check, so we make sure to store the result
                splitLine = line.split("=>");
                this.storeMap.put(splitLine[0], splitLine[1]);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
