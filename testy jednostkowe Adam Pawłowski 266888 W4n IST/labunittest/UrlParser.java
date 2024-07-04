package labunittest;

import java.util.HashMap;
import java.util.Map;

public class UrlParser {

    final String[] schemes = new String[]{"http://", "https://", "ftp://"};

    private String urlToParse;

    UrlObject parseUrl(String url) {
        this.urlToParse = url;
        UrlObject urlObject = new UrlObject();
        urlObject.setScheme(parseScheme(urlToParse));
        urlObject.setFullAddress(parseFullAddress(urlToParse));
        urlObject.setPath(parseFullPath(urlToParse));
        urlObject.setParameters(parseParameters(urlToParse));

        return urlObject;
    }

    private String parseScheme(String url) {
        String result = null;
        for (String scheme : schemes) {
            if (url.startsWith(scheme)) {
                result = scheme;
                urlToParse = urlToParse.substring(scheme.length());
                break;
            }
        }
        if (result == null) throw new RuntimeException();
        return result;
    }

    private String parseFullAddress(String urlToParse) {
        int index = urlToParse.indexOf("/");
        if (index == -1) {
            this.urlToParse = "";
            return urlToParse;
        } else {
            this.urlToParse = urlToParse.substring(index);
            return urlToParse.substring(0, index);
        }
    }

    private String parseFullPath(String urlToParse) {
        int index = urlToParse.indexOf("?");
        if (index == -1) {
            this.urlToParse = "";
            return urlToParse;
        } else {
            this.urlToParse = urlToParse.substring(index + 1);
            return urlToParse.substring(0, index);
        }
    }

    private Map<String, String> parseParameters(String urlToParse) {
        Map<String, String> paramsMap = new HashMap<>();
        if (urlToParse.isEmpty()) {
            return paramsMap;
        }
        String[] params = urlToParse.split("&");
        for (String param : params) {
            String[] pair = param.split("=");
            paramsMap.put(pair[0], pair[1]);
        }
        return paramsMap;
    }
}
