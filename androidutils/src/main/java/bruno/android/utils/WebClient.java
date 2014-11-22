package bruno.android.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dev
 */
public class WebClient {

    public static final int BUFFER_SIZE = 1024;
//    private String USER_AGENT = "Mozilla/5.0";

    private String method = "GET";
    private String out;
    private Map<String, String> urlParams = new HashMap<String, String>();
    private Map<String, String> requestProperties = new HashMap<String, String>();
    private int timout;
    private final String urlStr;

    public WebClient(String urlStr) {
        this.urlStr = urlStr;
    }

    public WebClient setTimeout(Integer timeout) {
        this.timout = timeout;
        return this;
    }

    public WebClient setOut(String out){
        this.out = out;
        return this;
    }

    public WebClient setContentType(String type) {
        return addRequestProperty("Content-Type", type);
    }

    public WebClient setContentTypeJson() {
        return setContentType("application/json");
    }

    public WebClient addRequestProperty(String key, String value) {
        requestProperties.put(key, value);
        return this;
    }

    public WebClient addUrlParameter(String key, String value) {
        urlParams.put(key, value);
        return this;
    }

    public Response doGet() throws IOException {
        method = "GET";
        return execute();
    }

    public Response doPost() throws IOException {
        method = "POST";
        return execute();
    }

    public Response doPut() throws IOException {
        method = "PUT";
        return execute();
    }
    private Response execute() throws IOException {
        String urlParams = buildUrlParams();
        String requestUrl = urlStr + urlParams;
        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setConnectTimeout(timout);
        con.setRequestMethod(method);
        buildProperties(con);
//        con.connect();
        if (out != null)
            writeOut(con);
        int responseCode = con.getResponseCode();
        try {
            final String response = readResponse(con);
            return Response.buildNormal(responseCode, response);
        } catch (IOException e) {
            return Response.buildError(responseCode, e.getLocalizedMessage());
        }
    }

    private String buildUrlParams() throws UnsupportedEncodingException {
        StringBuilder paramsStr = new StringBuilder();
        boolean first = true;
        final Set<Map.Entry<String, String>> params = urlParams.entrySet();
        for (Map.Entry<String, String> entry : params) {
            if (first) {
                paramsStr.append("?");
                first = false;
            } else {
                paramsStr.append("&");
            }
            paramsStr
                    .append(entry.getKey())
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return paramsStr.toString();
    }

    private void buildProperties(HttpURLConnection con) {
        for (Map.Entry<String, String> entry : requestProperties.entrySet()) {
            con.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private void writeOut(HttpURLConnection con) throws IOException {
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        writeOutputStream(os, out);
    }

    public static void writeOutputStream(final OutputStream os, final String out1) throws IOException {
        DataOutputStream wr = new DataOutputStream(os);
        wr.writeBytes(out1);
        wr.flush();
        wr.close();
    }

    private String readResponse(HttpURLConnection con) throws IOException {
        InputStream is = con.getInputStream();
        return readInputStream(is);
    }

    public static String readInputStream(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        byte[] buffer = new byte[BUFFER_SIZE];
        int size = 0;
        while ((size = is.read(buffer)) > 0) {
            builder.append(new String(buffer, 0, size));
        }

        is.close();
        return builder.toString();
    }

    private String readResponseOld() throws IOException {
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuilder response = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        return response.toString();
        return null;
    }

    public static class Response {

        public Integer code;
        public String response;
        public String message;

        private static Response buildNormal(Integer code, String response) {
            Response r = new Response();
            r.code = code;
            r.response = response;
            return r;
        }
        private static Response buildError(Integer code, String message) {
            Response r = new Response();
            r.code = code;
            r.message = message;
            return r;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "code=" + code +
                    ", response='" + response + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
