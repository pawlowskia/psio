package labunittest;

import java.util.HashMap;
import java.util.Map;

public class UrlObject {
    String scheme;
    String fullAddress;
    String path;
    Map<String, String> parameters;

    public UrlObject() {
        scheme = "http://";
        fullAddress = "domain.com";
        path = "";
        parameters = new HashMap<>();
    }

    public UrlObject(String scheme, String fullAddress, String path, Map<String, String> parameters) {
        this.scheme = scheme;
        this.fullAddress = fullAddress;
        this.path = path;
        this.parameters = parameters;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
