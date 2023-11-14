import org.jsoup.Jsoup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class AppLauncher {

    public static void main(String[] args) throws IOException {
        String url = "https://docs.google.com/spreadsheets/d/1sJNt4WL90Y2UdSfhDRzxUarP3jSwNk1gVchADY59k4U/edit#gid=92461680";
        //Jsoup.connect(url).ignoreContentType(true).get().body().text();

        BufferedWriter writer = new BufferedWriter(new FileWriter("text1.txt"));
        writer.write(Jsoup.connect(url).ignoreContentType(true).get().body().text());
        writer.close();
    }
}