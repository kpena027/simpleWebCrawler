package simplewebcrawler;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SimpleWebCrawler {

    public static DB db = new DB();

    public static void processURL(String URL) throws IOException, SQLException {

        // Check if given URL is not already in DB
        String sql = "select * from Record where URL = '" + URL + "'";
        ResultSet rs = db.runSql(sql);
        if (!rs.next()) {
            // Store URL in DB
            sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
            PreparedStatement statement = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // SQL converts to longchar/varchar when sending to DB
            statement.setString(1, URL);
            // Executes preparedstatement and returns boolean
            statement.execute();
            Logger.log(URL);
            // Grab information from site & send fetched URLs to text file
            Document doc = Jsoup.connect("http://miami.craigslist.org/search/sof?query=+").get();
            if (doc.text().contains("Developer")) {
                System.out.println(URL);
                Logger.log(URL);
            }

            // Get title from posting
            //  Element linkz = doc.select("a").get(17);
            // Get links and make recursive call to processURL
            Elements questions = doc.select("a[href]");



            // Displays all links/jobs in http://miami.craigslist.org/search/sof?query=+
            for (Element link : questions) {
                if (link.attr("href").contains("/mdc/sof/")) {
                    processURL(link.attr("abs:href"));
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        db.runSql2("TRUNCATE Record;");
        processURL("http://miami.craigslist.org/search/sof?query=+");
        parseLinks.parseFile();
    }
}
