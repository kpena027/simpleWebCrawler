package simplewebcrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseLinks {
    // Rewrite with while buffer.readline != null so as to parse every page

    public static void parseFile() throws IOException {

        BufferedReader is = new BufferedReader(new FileReader("D:\\Craigslist\\jobresults.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("D:\\Craigslist\\specificjobs.txt", true));
        if ("http://miami.craigslist.org/search/sof?query=+".equals(is.readLine())) {
            is.readLine();
        }
        while (is.readLine() != null) {
            // is.skip(94);
            is.readLine();
            Document doc = Jsoup.connect(is.readLine()).get();
            Elements body = doc.select("section[id]").append("\n\n\n\n\n\n");
            for (Element p : body) {
                out.write("New Posting: ");
                out.write(p.text() + "\n");
                // This is very hack-ish. Revise later
                out.newLine();
                out.newLine();
                out.newLine();
                out.newLine();
                
            }
        }
    }
}
