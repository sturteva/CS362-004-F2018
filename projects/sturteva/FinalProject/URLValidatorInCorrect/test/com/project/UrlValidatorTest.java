package com.project;//import com.project.UrlValidator;
//import com.project.UrlValidator;
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

//Testing my commit

//<editor-fold desc="Various References and Research Topic sources -- i.e. help>
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
        assertFalse(urlVal.isValid("http:://www.facebook.com"));

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
//</editor-fold>

    //<editor-fold desc="Partition Testing Functions" >
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
    
    private ResultSet randomConnector()
    {
    	String connector;
    	boolean valid;
    	//array of default accepted schemes
     	String[] validAuths = new String[] {
                 "http", "https", "ftp"
         };
     	//Make a random number between 0 and 3
     	int random = (int)(Math.random() * 3);
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
    
    //TODO: Reed
    private ResultSet randomHost()
    {
        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultSet rp = new ResultSet(s1, b);
        if (useTestData) {
            rp.item = "www.google.com:8081";
            rp.valid = true;
        }
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
        int numTests = 10; //100000;
        boolean overallResults = true; // pass
        int exceptionCount =0;
        java.util.ArrayList<String> errorList = new java.util.ArrayList<>(50);
        java.util.ArrayList<String> urlList = new java.util.ArrayList<>(50);

        if (useTestData)
        {
            urlList.add("http://www.facebook.com");
            urlList.add("http://");
            urlList.add("http://www.facebook.com:8081");
            urlList.add("http://www.facebook.com:8081/somepath");
            urlList.add("http://www.facebook.com/somepath");
            urlList.add("http://www.facebook.com/somepath/morepath");
            urlList.add("http://www.facebook.com?params2=values");
            urlList.add("http://www.facebook.com/path1/path2?qparam1=34&qparam2=678");

            numTests = urlList.size();

        }

        L("Starting Random Tests");
        //UrlValidator urlVal = new UrlValidator();
        UrlValidator urlVal = new UrlValidator(null, null, 1);
        for (int i=0; i< numTests; i++)
        {
            ResultSet schema = randomSchema();
            ResultSet connector = randomConnector();
            ResultSet host = randomHost();
            ResultSet path = randomPath();
            ResultSet query = randomQuery();
            String testURL = schema.item + connector.item + host.item + path.item + query.item;
            boolean expectedValue = schema.valid && connector.valid && host.valid && query.valid;

            if (useTestData)
            {
                testURL = urlList.get(i);
                expectedValue = true;
            }
            //L("Testing URL: " + testURL);


            boolean assertionFailed;// = false;
            try { // I don't think this works because the 'Exception' that is thrown is processed by the junit assert
                boolean thisIsBSthatJUnitCannotProperlyTrapATestedValue = urlVal.isValid(testURL);
                assertionFailed = !(expectedValue == thisIsBSthatJUnitCannotProperlyTrapATestedValue);

                if (assertionFailed) // assertionFailed but didn't throw an error
                {
                    overallResults = false;

                    String message = ("Error No: " + errorList.size()) + ": expected " + (expectedValue ? "true" : "false") +
                            " but received " + (!expectedValue ?  "true" : "false") + " for url '"
                            + testURL + "'";
                    errorList.add(message);
                    L(message);
                }
                else
                    L((expectedValue?"Valid Expected and Valid Obtained: ": "Failed Expected and Failed Obtained: ") + testURL);

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
    public void testJavaKnowlege()
    {
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

        for (int i=0; i < 100; i++)
        {
            //ResultSet R = randomSchema();
            //L(""+R.valid + ": " + R.item);
            ResultSet path = randomPath();
            L(""+path.valid + ": " + path.item);
        }
    }

//</editor-fold>

} // ->end public class UrlValidatorTest extends TestCase
