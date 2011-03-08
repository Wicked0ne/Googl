package us.roob.googl;

public class GooglTest {
    
    public static void main(String[] args){
	String url = "gallery.roob.us:88";
	System.out.println("Original url: " + url);
	System.out.println("Shortened url: " + Googl.shortenURL(url));
	System.out.println("Expanded url: " + Googl.expandURL(Googl.shortenURL(url)));
	
	
//	System.out.println("URL: " + Googl.shortenURL("gallery.roob.us:88"));
//	System.out.println("URL: " + Googl.shortenURL("gallery.roob.us:88"));
	
    }

}
