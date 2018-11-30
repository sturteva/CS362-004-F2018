package com.project;//import com.project.UrlValidator;
//import com.project.UrlValidator;
import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

//Testing my commit

//<editor-fold desc="Various References and Research Topic sources -- i.e. help">
///  Comment:  class not found error  'Class not found: "com.project.UrlValidatorTest"'
//  When the project definition does not have an output folder defined I consistently
//  ran into this error.  Got to "Project Structure ctrl-alt-shift-s
//  Project Settings->modules  set "Use Module Compile Output Path"
//  and set the project output to an actual location on your system.


// Reference to the constructor UrlValidator(schemes, authorityValidator, allowAllSchemes)
//  https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/UrlValidator.html#UrlValidator(org.apache.commons.validator.routines.RegexValidator,%20long)
//  The isValidAuthority function contains the definition of authority as "An authority is the combination of hostname and port"
//  scheme is never defined but it sounds like we can build the url how we want and just look for the error in the associated isValid blocks

//</editor-fold>


public class UrlValidatorTest extends TestCase {

    // constructor
    public UrlValidatorTest(String testName) {
        super(testName);
    }
    // Just a generic Log function
    private void L(String message) {System.out.println(message);}

//<editor-fold desc="Manual Testing Functions">

    public void testManualTestNull() {
        long allowAllSchemes = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllSchemes);
//You can use this function to implement your manual testing	   
        assertFalse(urlVal.isValid(null));
        //assertTrue(urlVal.isValid("http://www.facebook.com"));
        //assertTrue(urlVal.isValid("http://www.facebook.com/"));
        //assertFalse(urlVal.isValid("facebook.com"));
        //assertFalse(urlVal.isValid("htp://www.facebook.com"));
        //assertFalse(urlVal.isValid("http:://www.facebook.com"));

    }


    public void testManualGood() {
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        assertTrue(urlVal.isValid("http://www.facebook.com"));
    }


    public void testManualWithBack() {
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        assertTrue(urlVal.isValid("http://www.facebook.com/"));

    }


    public void testManualNoWWW() {
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        assertFalse(urlVal.isValid("facebook.com"));
    }


    public void testManualBadScheme() {
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        assertFalse(urlVal.isValid("htp://www.facebook.com"));
    }


    public void testManualDoubleColon() {
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        assertFalse(urlVal.isValid("http:://www.facebook.com"));
    }

    public void testManualHTTPSgood(){
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null,null,allowAllScheme);
        assertTrue(urlVal.isValid("https://www.facebook.com"));
    }

    public void testManualFTPgood(){
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null,null,allowAllScheme);
        assertTrue(urlVal.isValid("ftp://www.facebook.com"));
    }
    public void testManualNoDotCom(){
        long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null,null, allowAllScheme);
        assertFalse(urlVal.isValid("https://facebook"));
    }

    // Default constructor should evaluate http,https, ftp as valid urls
    public void testDefaultConstructorFor_UrlValidator() {
        UrlValidator urlVal = new UrlValidator();
        boolean rValue = urlVal.isValid("http:://www.facebook.com");
        rValue &= urlVal.isValid("https:://www.facebook.com");
        rValue &= urlVal.isValid("ftp:://www.facebook.com");
        assertTrue(rValue);
    }
//</editor-fold>

    //<editor-fold desc="Partition Testing Functions" >
    //DONE: Reed
    public void testSchemePartition() {
    	System.out.println("Starting tests for Scheme partition");
    	long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        boolean pass =  urlVal.isValid("http://testscheme.com"); //good scheme
        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Pass): http://testscheme.com");
        }
        else {
        	System.out.println("Error: Expected pass Obtained fail on http://testscheme.com");
        }
        
        pass &= !urlVal.isValid("zttp://testscheme.com"); //bad scheme
        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Fail): zttp://testscheme.com");
        }
        else {
        	System.out.println("Error: Expected fail Obtained pass on zttp://testscheme.com");
        }
        assertTrue(pass); //check if both tests passed 
    }


    //DONE: Reed
    public void testAuthorityPartition() {
    	System.out.println("Starting tests for Authority partition");
    	long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        boolean pass = urlVal.isValid("http://www.google.com"); //good authority

        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Pass): http://www.google.com");
        }
        else {
        	System.out.println("Error: Expected pass Obtained fail on http://www.google.com");
        }
        
        pass &= !urlVal.isValid("http://ww∑.google.com"); //bad authority, contains invalid character
        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Fail): http://ww∑.google.com");
        }
        else {
        	System.out.println("Error: Expected fail Obtained pass on http://ww∑.google.com");
        }
        assertTrue(pass); //check if both tests passed 
    }
    
    //DONE: Reed
    public void testPathPartition() {
    	System.out.println("Starting tests for Path partition");
    	long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        boolean pass = urlVal.isValid("http://www.google.com/gmail"); //good Path  // FYI intelliJ caught the conversion of pass&= to just pass=
        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Pass): http://www.google.com/gmail");
        }
        else {
        	System.out.println("Error: Expected pass Obtained fail on http://www.google.com/gmail");
        }
        
        pass &= !urlVal.isValid("http://www.google.com/©mail"); //bad Path, contains invalid character
        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Fail): http://www.google.com/©mail");
        }
        else {
        	System.out.println("Error: Expected fail Obtained pass on http://www.google.com/©mail");
        }
        assertTrue(pass); //check if both tests passed 

    }
    
    //DONE: Reed
    public void testQueryPartition() {
    	System.out.println("Starting tests for Query partition");
    	long allowAllScheme = 1;
        UrlValidator urlVal = new UrlValidator(null, null, allowAllScheme);
        boolean pass =  urlVal.isValid("https://store.google.com/?utm_source=hp_header&utm_medium=google_oo&utm_campaign=GS100042"); //good Query
        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Pass): https://store.google.com/?utm_source=hp_header&utm_medium=google_oo&utm_campaign=GS100042");
        }
        else {
        	System.out.println("Error: Expected pass Obtained fail on https://store.google.com/?utm_source=hp_header&utm_medium=google_oo&utm_campaign=GS100042");
        }
        
        pass &= !urlVal.isValid("https://store.google.com/?utm_source=hp_header&utm_medium=google_oo&utm_campaign=®GS100042"); //bad Query, contains invalid character
        //check if isValid behaved correctly and print to console
        if (pass) {
        	System.out.println("Valid Expected and Valid Obtained (Fail): https://store.google.com/?utm_source=hp_header&utm_medium=google_oo&utm_campaign=®GS100042");
        }
        else {
        	System.out.println("Error: Expected fail Obtained pass on https://store.google.com/?utm_source=hp_header&utm_medium=google_oo&utm_campaign=®GS100042");
        }
        assertTrue(pass); //check if both tests passed 

    }
    
    //</editor-fold>
    public void testIsValid() {
        //You can use this function for programming based testing

    }

    //<editor-fold desc="Program Based Testing">


    //<editor-fold desc="Overview Program Based Testing" >
    //Overview:  The goal is to use a program to generate random inputs for the generating of the url's.
    //The sections of the url will be <scheme><connector><host><path><query>.  These sections correlate
    //to the following components of the following URL.
    //
    //https://oregonstate.instructure.com:8081/groups/3138861/collaborations?user=kruegerd&user2=sturteva&user3=millreed
    //      <scheme>          https
    //      <connector>       ://
    //      <host>            oregonstate.instructure.com:8081
    //      <path>            /groups/3138861/collaborations
    //      <query>           ?user=kruegerd&user2=sturteva&user3=millreed
    //</editor-fold>

    private boolean useTestData = true;

    //DONE: Reed
    private ResultSet randomSchema() {
        // see for example schemes https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/UrlValidator.html
    	String scheme;
        boolean valid;
    	//array of default accepted schemes
     	String[] validAuths = new String[] {
                 "http", "https", "ftp"
         };
     	//Make a random number between 0 and 6
     	int random = (int)(Math.random() * 6);
     	//if we get 0, 1, or 2, select corresponding valid scheme, else create a random one
     	if(random <= 2) {
     		scheme = validAuths[random];
     		valid = true;
     	}
     	else {
     		//create random scheme between 1 and 7 characters long containing any character between A and z, including non alphanumeric characters
     		int randomLength = (int)(Math.random() * 7 + 1);
     		int leftLimit = 65; // letter 'A'
     	    int rightLimit = 122; // letter 'z'
     	   // int targetStringLength = randomLength; // redundant removed

     	    StringBuilder buffer = new StringBuilder(randomLength);
     	    for (int i = 0; i < randomLength; i++) {
     	    	int randomChar = (int)(Math.random() * (rightLimit-leftLimit) + leftLimit); 
     	        buffer.append((char) randomChar);
     	    }
     	   scheme = buffer.toString();
     	   //check if random scheme is actually valid
            valid = (scheme.equals("http")) || (scheme.equals("https")) || (scheme.equals("ftp"));

     	}
     	//set result pair
        ResultSet rp = new ResultSet(scheme, valid);
//        if (useTestData) {
//            rp.item = "http";
//            rp.valid = true;
//        }
        return rp;
    }
    
    //DONE: Reed
    private ResultSet randomConnector()
    {
    	String connector;
    	boolean valid;

     	//Make a random number between 0 and 2
     	int random = (int)(Math.random() * 2);
     	//if we get 0 set connector to valid, else create a random one
     	if(random == 0) {
     		connector = "://";
     		valid = true;
     	}
     	else {
     		//create random connector between 0 and 5 characters long containing any character between SPACE and ?, including non alphanumeric characters
     		int randomLength = (int)(Math.random() * 5);
     		int leftLimit = 32; // character SPACE
     	    int rightLimit = 63; // character '?'

            StringBuilder buffer = new StringBuilder(randomLength);
     	    for (int i = 0; i < randomLength; i++) {
     	    	int randomChar = (int)(Math.random() * (rightLimit-leftLimit) + leftLimit); 
     	        buffer.append((char) randomChar);
     	    }
     	   connector = buffer.toString();
     	   //check if random connector is actually valid
            valid = "://".equals(connector);
     	}
     	//set result pair
        ResultSet rp = new ResultSet(connector, valid);
        if (useTestData) {
            rp.item = "://";
            rp.valid = true;
        }
        return rp;
    }
    
    //DONE: Reed
    private ResultSet randomHost()
    {
        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultSet rp = new ResultSet(s1, b);
    	String host;

    	boolean valid = true;


     	//Make a random number between 0 and 1000
     	int random = (int)(Math.random() * 1000);
     	//if we get 0, set host to null, else create a random one
     	if(random == 0) {
     		host = null;
     		valid = false;
     	}
     	else {
     		//string of some invalid characters to choose from
     		String invalidChars = "`'^å*();";
     		//string of some valid characters to choose from
     		String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.";
     		//set random length of host
     		int randomLength = (int)(Math.random() * 100);
     	    int targetStringLength = randomLength;
     	    int randomChar;
     	    StringBuilder buffer = new StringBuilder(targetStringLength);
     	    boolean includePeriod = false;
     	    for (int i = 0; i < targetStringLength; i++) {
     	    	//set a 1 in 200 chance of invalid character
     	    	int randomValid = (int)(Math.random() * 200);
     	    	if (randomValid == 0) {
     	    		int character = (int)(Math.random()*invalidChars.length());
     	    		randomChar = invalidChars.charAt(character);
     	    		valid = false;
     	    	}
     	    	else {
     	    		int character = (int)(Math.random()*validChars.length());
     	    		randomChar = validChars.charAt(character);
     	    		//if first or last character of host is a '.' then it is invalid
     	    		if ((i == targetStringLength - 1 || i == 0) && randomChar == '.') {
     	    			valid = false;
     	    		}
     	    		//if we have a '.' that is not the first or last character, it is a valid domain
     	    		else if(randomChar == '.') {
     	    			includePeriod = true;
     	    		}
     	    	}
     	        buffer.append((char) randomChar);
     	    }
     	    //if we don't have any period in the host name, it is invalid
     	    if (!includePeriod) {
     	    	valid = false;
     	    }
     	   host = buffer.toString();

     	}
     	//set result pair
         rp = new ResultSet(host, valid);

        return rp;
    }
    //Done: Andrew
    private ResultSet randomPath()
    {


        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultSet[] arr = {
        new ResultSet("/../", false),
        new ResultSet("/..", false),
        new ResultSet("//", false),
        new ResultSet("/random1", true),
        new ResultSet("/foo/bar", true),
        new ResultSet("foo/bar/foo", true),
        new ResultSet("", true),
        new ResultSet("/foo//bar",false),
        new ResultSet("/foo/",true)};

      //  if (useTestData) {
        //    rp.item = "/find/something/very/interesting";
          //  rp.valid = true;
       // }
        return arr[(int)(Math.random() * 9)];
    }

    //Done: Dan
    //  Random Query
    //    1) Forget the ?
    //    2)  Generate 10 character [a-Z0-9]  strings as parameters and values starting with [a-Z]
    //    3)  Randomly Generate =,& insertions
    //      probablities 75% accurate expectation ?<param>=<value>&<param2>=<value2>...
    //      25% failures will test between 1-5 param/value pairs and randomly insert ?,=,&
    //      a quick string check will determine if the querry part is correct.

    private ResultSet randomQuery()
    {
        StringBuilder s1;// = new StringBuilder();
        ResultSet rValue = new ResultSet("testItem", true);
        String[] arr = new String[] {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z"
        };
        String[] ops = new String[]{ "?", "=", "&" };

        boolean makeGoodString = (Math.random()*100 > 25);

        if (makeGoodString) {
            s1 = new StringBuilder("?");

            int numPairs = (int)(Math.random()*6)+1;

            for (int j=0; j< numPairs; j++) {
                int ranChar = (int)(Math.random()*(arr.length - 10) + 10);
                s1.append(arr[ranChar]);
                for (int i = 0; i < 9; i++) {
                    s1.append(arr[(int) (Math.random() * arr.length)]);
                }
                s1.append("=");
                ranChar =(int) (Math.random()*(arr.length - 10) + 10);
                s1.append(arr[ranChar]);
                for (int i = 0; i < 9; i++) {
                    s1.append(arr[(int) (Math.random() * arr.length)]);
                }
                if (j != (numPairs -1))
                {
                    s1.append("&");
                }
            }
            rValue.item = s1.toString();
            rValue.valid = true;
            return rValue;
        }
        else { // make a possible faulty string
            s1 = new StringBuilder();
            s1.append(ops[(int) (Math.random() * 3)]); // random ?,=,&
            int numPairs = (int)(Math.random()*6+1);

            for (int j=0; j< numPairs; j++) {

                int ranChar =(int) (Math.random()*(arr.length - 10) + 10);
                s1.append(arr[ranChar]);
                for (int i = 0; i < 9; i++) {
                    s1.append(arr[(int) (Math.random() * arr.length)]);
                }
                s1.append(ops[(int) (Math.random() * 3)]); // random ?,=,&
                ranChar = (int)( Math.random()*(arr.length - 10) + 10);
                s1.append(arr[ranChar]);
                for (int i = 0; i < 9; i++) {
                    s1.append(arr[(int) (Math.random() * arr.length)]);
                }

                if (j != (numPairs -1))
                {
                    s1.append("&");
                }
            }
            rValue.item = s1.toString();
            rValue.valid = true;

            rValue.valid = s1.charAt(0) == '?';



            int index = 0; // 0 means = is expected next other wise 1 is expected next
            for (int i=0; i < rValue.item.length() && rValue.valid; i++) {
                if(rValue.item.charAt(i) == '=' && index != 1 )
                    rValue.valid = false;
                else if (rValue.item.charAt(i) == '=' )
                    index = 1;
                else if (rValue.item.charAt(i) == '&' && index != 0 )
                    rValue.valid = false;
                else if (rValue.item.charAt(i) == '&' )
                    index = 0;
            }
            return rValue;
        }
        //boolean b = true; // true or false based on whether s1 is valid

      //  (Math.random() * ((max - min) + 1)) + min;

//        ResultSet rp = new ResultSet(s1, b);
//
//        if (useTestData) {
//            rp.item = "?param1=something&param2=very&param3=interesting";
//            rp.valid = true;
//        }
    }

    //https://junit.org/junit4/javadoc/4.12/org/junit/rules/ErrorCollector.html
    // video: https://youtu.be/OP25PTavvyQ
    // https://airbrake.io/blog/java-exception-handling/assertionerror-java
    //public ErrorCollector ourErrors = new ErrorCollector();

    public void testRandomURLs(){
        int numTests = 100; //100000;
        boolean overallResults = true; // pass
        int exceptionCount =0;
        java.util.ArrayList<String> errorList = new java.util.ArrayList<>(50);
        java.util.ArrayList<String> urlList = new java.util.ArrayList<>(50);

        /// Pattern Objects from UrlValidator

        String AUTHORITY_CHARS_REGEX = "\\p{Alnum}\\-\\."; // allows for IPV4 but not IPV6
        String IPV6_REGEX = "[0-9a-fA-F:]+"; // do this as separate match because : could cause ambiguity with port prefix

        // userinfo    = *( unreserved / pct-encoded / sub-delims / ":" )
        // unreserved    = ALPHA / DIGIT / "-" / "." / "_" / "~"
        // sub-delims    = "!" / "$" / "&" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "="
        // We assume that password has the same valid chars as user info
        String USERINFO_CHARS_REGEX = "[a-zA-Z0-9%-._~!$&'()*+,;=]";
        // since neither ':' nor '@' are allowed chars, we don't need to use non-greedy matching
        String USERINFO_FIELD_REGEX =
                USERINFO_CHARS_REGEX + "+" + // At least one character for the name
                        "(?::" + USERINFO_CHARS_REGEX + "*)?@"; // colon and password may be absent
        String AUTHORITY_REGEX =
                "(?:\\[("+IPV6_REGEX+")\\]|(?:(?:"+USERINFO_FIELD_REGEX+")?([" + AUTHORITY_CHARS_REGEX + "]*)))(?::(\\d*))?(.*)?";
        //             1                          e.g. user:pass@          2                                         3       4
        Pattern AUTHORITY_PATTERN = Pattern.compile(AUTHORITY_REGEX);
        Pattern URL_PATTERN = Pattern.compile( "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?");
        Pattern SCHEME_PATTERN = Pattern.compile("^\\p{Alpha}[\\p{Alnum}\\+\\-\\.]*");
        Pattern PATH_PATTERN = Pattern.compile("^(/[-\\w:@&?=+,.!*'%$_;\\(\\)]*)?$");
        Pattern QUERY_PATTERN = Pattern.compile("^(\\S*)$");
        //DomainValidator junk = new DomainValidator(true);
        //L("Starting Random Tests");
        // Default constructor has a bug
        //UrlValidator urlVal = new UrlValidator();
        // This constructor is invalid
        // UrlValidator urlVal = new UrlValidator(validAuths, null, allowAllSchemes ); if allowedAllSchemes is false it doesn't work
        String[] validAuths = new String[] { // copied from randomSchemes
                "http", "https", "ftp"
        };
        long allowAllSchemes = 1 ; //0 false 1 True
        UrlValidator urlVal = new UrlValidator(null, null, allowAllSchemes ); // this should be used if you want to specify your own regex patterns


        for (int i=0; i< numTests; i++)
        {
            ResultSet schema = new ResultSet("http",true); // all other schemes crash
            ResultSet connector = randomConnector();
            ResultSet host = randomHost(); //
            //ResultSet host = new ResultSet("www.facebook.com", true);
            ResultSet path = randomPath();
            //ResultSet path = new ResultSet("/a/valid/path", true);
            //ResultSet path = new ResultSet("", true);
            //ResultSet query = randomQuery();
            ResultSet query = new ResultSet("", true);
            String testURL = schema.item + connector.item + host.item + path.item + query.item;
            boolean expectedValue = schema.valid && connector.valid  && host.valid && path.valid && query.valid;
            // Need to find if authority is going to parse to ""
            // <editor-fold desc="Regex Values pulled from UrlValidator class">
            // I could not localize the bug futher
//            String URL_REGEX =
//                    "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?";
//            //        12            3  4          5       6   7        8 9
//
//            URL_REGEX =
//                    "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?";
//            //        12            3  4          5       6   7        8 9
//            testURL = "http://www.facebook.com";
//            Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
//            Matcher urlMatcher =  URL_PATTERN.matcher(testURL);
//            int PARSE_URL_AUTHORITY = 4;
//            String authority = urlMatcher.group(PARSE_URL_AUTHORITY);
            //</editor-fold>


//            if (useTestData)
//            {
//                testURL = urlList.get(i);
//                expectedValue = true;
//            }
           // testURL = "https://www.facebook.com";
            //L("Testing URL: " + testURL);
//            if (authority == "" )
//            {
//                L("Known Issue: Authority is not parseable for URL String: " + testURL);
//                break;
//            }


            boolean assertionFailed;// = false;
            try { // I don't think this works because the 'Exception' that is thrown is processed by the junit assert
                boolean thisIsBSthatJUnitCannotProperlyTrapATestedValue = urlVal.isValid(testURL);
                assertionFailed = !(expectedValue == thisIsBSthatJUnitCannotProperlyTrapATestedValue);

                if (assertionFailed) // assertionFailed but didn't throw an error
                {
                    overallResults = false;

                    String message = (i+":✖ Error No: " + errorList.size()) + ": expected " + (expectedValue ? "true" : "false") +
                            " but received " + (!expectedValue ?  "true" : "false") + " for url '"
                            + testURL + "'";
                    errorList.add(message);
                    L(message);
                }
                else
                    L((expectedValue?""+i+":✔ Valid Expected and Valid Obtained: ": ""+i+":✔ Failed Expected and Failed Obtained: ") + testURL);

            } catch (Exception genericExc) // URL failed and threw an error
            {
                String message = ("Error No: " + errorList.size()) + ": expected " + (expectedValue ? "true" : "false") +
                        " but received " + (!expectedValue ?  "true" : "false") + " for url '"
                        + testURL + "'" + "\nException Thrown: " + genericExc.toString();
                errorList.add(message);
                L(message);
                overallResults = false;
                exceptionCount++;
            }

        }// end for
        L(numTests + " random tests were run and " + errorList.size() + " failed and there were " + exceptionCount + " that resulted in " +
                "UrlValidator runtime exceptions.");

        assertTrue(overallResults); // will fail test if even 1 run is false
    }//testRandomURLs
    //</editor-fold>

    //<editor-fold desc = "Utility Functions used to test the 'test' functions">
    // empty function to run test java snipits in when checking on java code structure.
    public void atestJavaKnowlege() {
//
//        java.util.ArrayList<String> errorList = new java.util.ArrayList<>(50);
//        errorList.add("Message 1");
//        errorList.add("Message 2");
//        errorList.add("Message 3");
//        for (int i=0; i< errorList.size(); i++)
//        {
//            String message = "" + (i+1) + ": " + errorList.get(i);
//            L(message);
//        }
//

        // verify parts are generated correctly
        for (int i = 0; i < 100; i++) {
            ResultSet R;
//             R = randomSchema();
//            L("Schema:" + R.valid + ": " + R.item);
//            R = randomConnector();
//            L("Connector:" + R.valid + ": " + R.item);
            R = randomHost();
            L("Host:" + R.valid + ": " + R.item);
//            R = randomPath();
//            L("Path:" + R.valid + ": " + R.item);
//            R = randomQuery();
//            L("Query:" + R.valid + ": " + R.item);
        }
    }

    // Collect the list of failed random tests from the console and double check them.
    //      put them in the errorList and break on it inspect to make sure it is a valid failure
    public void testHandEvaluateInvidiualRandomFailures() // apparnetly test functions have to start with word test
    {
        java.util.ArrayList<String> errorList = new java.util.ArrayList<>(50);
        //  These errors were resolved
        // The following errors only occured with the default UrlValidator constructor
//        errorList.add("ftp://www.google.com");
//        errorList.add("ftp://djyDZfETG0jnCb8ZkDB2aPR3PN0us.DTy6DCVd7PXpoBM4X179XOUIVTc5l?eJ1AEUMTAs=UANWpOqzFG&ZNUTruiuWz=heHFOy70MH&GF6v2TPJwu=PU1bygbFGt");
//        errorList.add("FTP://djyDZfETG0jnCb8ZkDB2aPR3PN0us.DTy6DCVd7PXpoBM4X179XOUIVTc5l?eJ1AEUMTAs=UANWpOqzFG&ZNUTruiuWz=heHFOy70MH&GF6v2TPJwu=PU1bygbFGt");
        // end ---default constructor test list ---
        // this one failed when not using the default constructor -- same problem
        //errorList.add("https://nmgMdyAeNonGceEdb7GthzKKgI.T4WW/..?SiXNLEiioZ=oehGeK2MiX&QhqoKKbYWQ=xaKsDs0Z1S&T6jwT5R46i=PPCBt8fFyG&QDCJHD5WiI=dWy9L9NsIr&xAQzuHvl8m=VogWVy96Vg");
        // end --- tested urls

        // -- this one causes an exception
        errorList.add("I:///foo/?IwTEZwocNg=CtP1G31QS1&KDCyjbQl13=Old8WPSlSz&DZn8Z7bVkC=ot0fvxXWQp&j22QWRAR0a=r6I0MIHST1");
        // <editor-fold desc="Regex Values pulled from UrlValidator class">
        String URL_REGEX =
                "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?";
        //        12            3  4          5       6   7        8 9

        URL_REGEX =
                "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?";
        //        12            3  4          5       6   7        8 9
        String testURL = "http://www.facebook.com";
        Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
        Matcher urlMatcher =  URL_PATTERN.matcher(testURL);
        int PARSE_URL_AUTHORITY = 4;
        String authority = urlMatcher.group(PARSE_URL_AUTHORITY);
        // somehow the Regex is changing to  "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\?([^#]*))?(#(.*))?";
        //</editor-fold>
        //UrlValidator urlVal = new UrlValidator();
        String[] validAuths = new String[] { // copied from randomSchemes
                "http", "https", "ftp"
        };
        long allowAllSchemes = 1 ; // false
        UrlValidator urlVal = new UrlValidator(validAuths, null, allowAllSchemes ); // this should be used if you want to specify your own regex patterns

        for (int i = 0; i < errorList.size(); i++)
        {
            boolean rValue = urlVal.isValid((errorList.get(i))); // set break point here and evaluate
            L("rValue: " + rValue);
        }

    }
//</editor-fold>

} // ->end public class UrlValidatorTest extends TestCase
