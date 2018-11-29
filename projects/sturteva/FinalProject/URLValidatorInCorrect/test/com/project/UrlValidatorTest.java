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

    public void testYourFirstPartition() {
        UrlValidator urlVal = new UrlValidator(null, null, 1);

        assertFalse(urlVal.isValid(null)); //No input

    }


    public void testYourSecondPartition() {
        //You can use this function to implement your Second Partition testing

    }
    //You need to create more test cases for your Partitions if you need to

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

    private ResultPair randomSchema() {
        // see for example schemes https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/UrlValidator.html
    	String scheme;
    	Boolean valid;
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
     	    int targetStringLength = randomLength;
     	    
     	    StringBuilder buffer = new StringBuilder(targetStringLength);
     	    for (int i = 0; i < targetStringLength; i++) {
     	    	int randomChar = (int)(Math.random() * (rightLimit-leftLimit) + leftLimit); 
     	        buffer.append((char) randomChar);
     	    }
     	   scheme = buffer.toString();
     	   //check if random scheme is actually valid
     	    if (scheme != "http" && scheme != "https" && scheme != "ftp"){
     	        valid = false;
     	    }
     	    else {
     	        valid = true;
     	    }
     	    
     	}
     	//set result pair
        ResultPair rp = new ResultPair(scheme, valid);
        if (useTestData) {
            rp.item = "http";
            rp.valid = true;
        }
        return rp;
    }
    
    //TODO: Reed
    private ResultPair randomConnector()
    {
        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultPair rp = new ResultPair(s1, b);
        if (useTestData) {
            rp.item = "://";
            rp.valid = true;
        }
        return rp;
    }
    //TODO: Reed
    private ResultPair randomHost()
    {
        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultPair rp = new ResultPair(s1, b);
        if (useTestData) {
            rp.item = "www.google.com:8081";
            rp.valid = true;
        }
        return rp;
    }
    //TODO: Andrew
    private ResultPair randomPath()
    {


        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultPair[] arr = {
        new ResultPair("/../", false),
        new ResultPair("/..", false),
        new ResultPair("//", false),
        new ResultPair("/random1", true),
        new ResultPair("/foo/bar", true),
        new ResultPair("foo/bar/foo", true),
        new ResultPair("", true),
        new ResultPair("/foo//bar",false),
        new ResultPair("/foo/",true)};

      //  if (useTestData) {
        //    rp.item = "/find/something/very/interesting";
          //  rp.valid = true;
       // }
        return arr[(int)(Math.random() * 9)];
    }

    //TODO: Dan
    //  Random Query
    //    1) Forget the ?
    //    2)  Generate 10 character [a-Z0-9]  strings as parameters and values starting with [a-Z]
    //    3)  Randomly Generate =,& insertions
    //      probablities 75% accurate expectation ?<param>=<value>&<param2>=<value2>...
    //      25% failures will test between 1-5 param/value pairs and randomly insert ?,=,&
    //      a quick string check will determine if the querry part is correct.
    private ResultPair randomQuery()
    {
        StringBuilder s1;// = new StringBuilder();
        ResultPair rValue = new ResultPair("testItem", true);
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

//        ResultPair rp = new ResultPair(s1, b);
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
            ResultPair schema = randomSchema();
            ResultPair connector = randomConnector();
            ResultPair host = randomHost();
            ResultPair path = randomPath();
            ResultPair query = randomQuery();
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
            ResultPair R = randomQuery();
            L(""+R.valid + ": " + R.item);
            ResultPair path = randomPath();
            L(""+path.valid + ": " + path.item);
        }
    }
    //</editor-fold>


}
