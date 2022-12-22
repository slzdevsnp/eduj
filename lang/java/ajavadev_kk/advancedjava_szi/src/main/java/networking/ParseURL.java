package networking;

import java.net.URL;

public class ParseURL {
    public static void main(String[] args) throws Exception {

        final String url_s = "http://search.oreilly.com:80/index.html?q=kousen&x=0&y=0#REFERENCE";
        URL aURL = new URL(url_s);

        System.out.println("protocol = " + aURL.getProtocol());
        System.out.println("authority = " + aURL.getAuthority());
        System.out.println("host = " + aURL.getHost());
        System.out.println("port = " + aURL.getPort());
        System.out.println("path = " + aURL.getPath());
        System.out.println("query = " + aURL.getQuery());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("ref = " + aURL.getRef());
    }
}