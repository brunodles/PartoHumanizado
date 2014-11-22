package bruno.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dev on 06/11/2014.
 */
public final class Youtube {

    private static final String THUMBNAIL_URL_TO_FORMAT = "http://img.youtube.com/vi/%s/%s.jpg";
    //    private static final String youtubeRegex = "https?.*youtu\\.?be.*/(?:.*v=([\\w-]+)|([\\w-]+))";
    private static final String youtubeRegex = "https?.*youtu\\.?be.*/(?:.*v=)?([\\w-]+)";

    private Youtube() {
    }

    private static Matcher matcher(String string) {
        return Pattern.compile(youtubeRegex).matcher(string);
    }

    public static String findId(String url) {
        Matcher matcher = matcher(url);
//        String id = null;
        if (matcher.find())
            return matcher.group(1);
//            if (id == null)
//                id = matcher.group(2);

        return null;
    }

    public static String findYoutubeUrl(String string) {
        Matcher matcher = matcher(string);
        if (matcher.find())
            return matcher.group();
        return null;
    }

    public static String[] splitUrlAndTitle(String string) {
        String[] split = string.split(":", 2);
        for (int i = 0; i < split.length; i++)
            split[i] = split[i].trim();
        return split;
    }

    public static String thumbnail(String url) {
        return thumbnail(url, 0);
    }

    public static String thumbnail(String url, int index) {
        return String.format(THUMBNAIL_URL_TO_FORMAT, findId(url), index);
    }

    public static void main(String[] args) {
        String s = "http://youtu.be/fV2NFw1SzeA\n" +
                "https://www.youtube.com/watch?v=kdj3tyPfkIg\n" +
                "http://www.youtube.com/watch?v=u8nQa1cJyX8&a=GxdCwVVULXctT2lYDEPllDR0LRTutYfW\n" +
                "http://youtube.com/watch?v=94opF-9PJ2I \n" +
                "http://www.youtube.com/watch?v=94opF-9PJ2I\n" +
                "http://www.youtube.com/watch?v=Iu4gxId0okA&feature=channel \n" +
                "http://www.youtube.com/watch?v=1D2HEM1cyuw&feature=related \n" +
                "http://es.youtube.com/watch?v=1D2HEM1cyuw&feature=related\n" +
                "http://www.youtube.com/watch?v=94PJ2I \n" +
                "http://www.youtube.com/wh?v=Iu4gxId0okA&feature=channel \n" +
                "http://espanol.youtube.com/watch?v=1D2HEM1cyuw&feature=related";

        Matcher matcher = matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.printf("     %s - %s\n", i, matcher.group(i));
            }
        }
    }


}
