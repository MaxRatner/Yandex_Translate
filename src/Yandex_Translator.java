import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Yandex_Translator {

    private static String Convert_Input_ (StringBuffer input_) {
        Integer index_ = null;
        while ((index_ = input_.indexOf(" ")) != -1) {
            input_.replace(index_, index_ + 1, "+");
        }
        return input_.toString();
    }

    private static String Convert_Otput_ (String answer_) {
        int start_of_text_ = answer_.indexOf("text") + 8;
        return answer_.substring(start_of_text_, answer_.length() - 3);
    }

    private static String Yandex_Translate_ (String text_) throws IOException {
        /*
           Insert your Yandex API key here:
         */
        String request_= "https://translate.yandex.net/api/v1.5/tr.json/translate" +
               "?key=" +
               "&text=" + text_ + "&lang=en-ru";

        URL url_ = new URL(request_);
        HttpURLConnection connection = (HttpURLConnection) url_.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        InputStream stream_ = connection.getInputStream();

        return new BufferedReader(new InputStreamReader(stream_)).lines().collect(Collectors.joining("\n"));

    }

    public static class Main {
        public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);
            StringBuffer input_ = new StringBuffer(scanner.nextLine());

            String response_ = Yandex_Translate_(Convert_Input_(input_));

            System.out.println(Convert_Otput_(response_));
        }
    }
}
