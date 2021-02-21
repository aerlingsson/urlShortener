## URL Shortener
A simple URL shortener that takes a url (or any other string that does not contain "=>"), and creates a unique, short token for that string.
Key-value pairs are stored in store.txt, using "=>" as a separator.\
Project is built with Maven.

### Code structure
**src/main** contains the *main* file App.java, and the UrlShortener.java class file.\
**src/test** contains the test files. Currently, only `UrlShortener` is tested (and only partially).

#### App.java
Facilitates very minimal CLI interaction with an `UrlShortener` object. To use the CLI functionality, supply arguments for whether you want to *shorten* a URL, or get the URL for a specific token, followed by the url or token:\
`shorten <url>` or `get <token>`.

#### UrlShortener.java
Is the `UrlShortener` class. Contains functionality to convert any string to a short token, as well as read and write key-value (token-url) pairs to and from `store.txt`.
The constructor is empty and all methods are public.
During run-time, token-url pairs are stored in a `HashMap` within the `UrlShortener`. `UrlShortener` has public functions to put and remove token-url pairs from the `HashMap`, as well as get retrieve the url associated with a token.

#### UrlShortenerTest.java
Contains unit tests for the `UrlShortener`'s `calculateUrlValue()` and `getUniqueKey()` methods.

#### pom.xml
Is the project configuration file for Maven, containing project info and dependencies. Currently we only need JUnit 4 for unit tests.


#### Example launch.json for VS Code to run with arguments
This is my launch.json file for VS Code. Launch arguments are set in `"args":`. In this example we want to `shorten 1234link.dk`.
```
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "Launch App",
            "request": "launch",
            "mainClass": "aerlingsson.codeprojects.urlShortener.App",
            "projectName": "urlShortener",
            "args": ["shorten", "1234link.dk"]
        }
    ]
}
```
