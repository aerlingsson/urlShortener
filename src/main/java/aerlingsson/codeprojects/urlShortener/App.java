package aerlingsson.codeprojects.urlShortener;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;


public class App {
    public static void main(String[] args) throws Exception {
        if (args.length == 0){  // No arguments supplied
            System.out.println("Please supply command line arguments:\nshorten <url> to convert a link to a short token\nget <token> to get the link associated with the token");
            return;
        }

        String arg = args[1];   // Either url or token
        UrlShortener shortener = new UrlShortener();

        try {
            shortener.readFromFile();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        Method method = shortener.getClass().getMethod(args[0], String.class);  // Get requested method reflectively
        System.out.println("\nResult: " + method.invoke(shortener, arg));       // Print output

        shortener.writeToFile();    // Persist changes
    }
}
