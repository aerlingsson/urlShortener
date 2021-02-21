package aerlingsson.codeprojects.urlShortener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


public class UrlShortenerTest {
    private UrlShortener shortener = new UrlShortener();

    @Before
    public void init(){
        try {
            shortener.readFromFile();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void calculateUrlValueSuccess(){
        String url = "hello";
        int expected = 22;
        int result = shortener.calculateUrlValue(url);

        assertEquals(expected, result);
    }

    @Test
    public void calculateUrlValueFails(){
        String url = "hello";
        int expected = 23;
        int result = shortener.calculateUrlValue(url);

        assertFalse(expected == result);
    }

    @Test
    public void getUniqueKeySuccess(){
        shortener.putInStoreMap("22", "hello");
        shortener.putInStoreMap("23", "hello");
        shortener.putInStoreMap("24", "hello");
        shortener.putInStoreMap("25", "hello");
        shortener.putInStoreMap("26", "hello");

        String url = "hello";
        String expected = "27";

        int urlValue = shortener.calculateUrlValue(url);
        String result = shortener.getUniqueKey(urlValue);

        System.out.println(expected + " " + result);

        assertEquals(expected, result);
    }

    @Test
    public void getUniqueKeyFails(){
        shortener.putInStoreMap("22", "hello");
        shortener.putInStoreMap("23", "hello");
        shortener.putInStoreMap("24", "hello");
        shortener.putInStoreMap("25", "hello");
        shortener.putInStoreMap("26", "hello");

        String url = "hello";
        String expected = "25";

        int urlValue = shortener.calculateUrlValue(url);
        String result = shortener.getUniqueKey(urlValue);

        System.out.println(expected + " " + result);

        assertFalse(expected.equals(result));
    }
}
