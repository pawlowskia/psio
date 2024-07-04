package labunittest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUrlParser {
    //testing parseScheme
    @Test
    public void testParseScheme() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("https://getmyweather.com/byCity?city=London");
        assertEquals("Result", "https://", tmp.scheme);
    }

    @Test(expected = RuntimeException.class)
    public void testParseSchemeThrowsExeption() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("hsptt://getmyweather.com/byCity?city=London");
    }

    //testing parseFullAdress
    @Test
    public void testParseFullAdress() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("https://getmyweather.com/byCity?city=London");
        assertEquals("Result", "getmyweather.com", tmp.fullAddress);
    }

    @Test
    public void testParseFullAdress2() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("https://getmyweather.com?byCity?city=London");
        assertEquals("Result", "getmyweather.com", tmp.fullAddress);
        //the program isn't supposed to catch this, but .com?byCity?city=London cannot be a domain so i'd check it and throw some exeption
        //System.out.println(tmp.scheme + " " + tmp.fullAddress + " " + tmp.path + " " + tmp.parameters.toString());
    }

    //testing parseFullPath
    @Test
    public void testParseFullPath() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("https://getmyweather.com/byCity?city=London");
        assertEquals("Result", "/byCity", tmp.path);
    }

    @Test
    public void testParseFullPath2() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("https://getmyweather.com/byCity");
        assertEquals("Result", "/byCity", tmp.path);
    }

    //testing parseParameters
    @Test
    public void testParseParameters() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("https://getmyweather.com/byCity?city=London"); //at this point string will consist only of the part after '?'
        assertEquals("Result", "{city=London}", tmp.parameters.toString());
    }

    @Test
    public void testParseParameters2() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("http://getmyweather.com/byCoordinates?lat={lat}&lon={lon}");
        assertEquals("Result", "{lon={lon}, lat={lat}}", tmp.parameters.toString());
    }

    @Test
    public void testParseParameters3() {
        UrlParser tester = new UrlParser();
        UrlObject tmp = tester.parseUrl("http://getmyweather.com/byCoordinates?city");
        //again the program doesn't catch these types of mistakes, but I'd check for them anyway
    }
}
