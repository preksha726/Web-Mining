import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupWordCount {
 public static DB worddb = new DB();
   public static void wordcount(String URL, String sword) throws IOException, SQLException {
        long time = System.currentTimeMillis();

       // Map<String, Word> countMap = new HashMap<String, Word>();
        
        //connect to wikipedia and get the HTML
        System.out.println("Downloading page...");
        Document doc = Jsoup.connect(URL).timeout(600000).ignoreHttpErrors(true).ignoreContentType(true).get();
        
        String searchword = sword;

        //Get the actual text from the page, excluding the HTML
        String text = doc.body().text();
        int count=0;
        System.out.println("Analyzing text...");
        //Create BufferedReader so the words can be counted
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("[^A-ZÃƒâ€¦Ãƒâ€žÃƒâ€“a-zÃƒÂ¥ÃƒÂ¤ÃƒÂ¶]+");
            for (String word : words) {
                if(searchword.equals(word)||searchword.equals(word.toLowerCase()) )
                {
                    count++;
                }
                }

               
            
        }

        reader.close();
      if(count!=0){
        String sql = "INSERT INTO  `login`.`wordcount` "+ "(URL,word,count) VALUES " + "('" +URL+"','"+searchword +"','"+count+"')";
        PreparedStatement stmt = worddb.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //stmt.setString(1, URL);
	stmt.execute();
       
      }
        System.out.println(searchword+count);

       /* SortedSet<Word> sortedWords = new TreeSet<Word>(countMap.values());
        int i = 0;
        int maxWordsToDisplay = 10;

        String[] wordsToIgnore = {"the", "and", "a"};

        for (Word word : sortedWords) {
            if (i >= maxWordsToDisplay) { //10 is the number of words you want to show frequency for
                break;
            }

            if (Arrays.asList(wordsToIgnore).contains(word.word)) {
                i++;
                maxWordsToDisplay++;
            } else {
                System.out.println(word.count + "\t" + word.word);
                i++;
            }

        }

        time = System.currentTimeMillis() - time;

        System.out.println("Finished in " + time + " ms");*/
    }

   /* public static class Word implements Comparable<Word> {
        String word;
        int count;

        @Override
        public int hashCode() { return word.hashCode(); }

        @Override
        public boolean equals(Object obj) { return word.equals(((Word)obj).word); }

        @Override
        public int compareTo(Word b) { return b.count - count; }
    }*/
}
