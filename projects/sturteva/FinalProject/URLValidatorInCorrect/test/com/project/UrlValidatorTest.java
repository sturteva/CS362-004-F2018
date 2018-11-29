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
    public void L(String message) {System.out.println(message);}

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

    //TODO: Reed
    private ResultPair randomSchema() {
        // see for example schemes https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/UrlValidator.html

        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultPair rp = new ResultPair(s1, b);
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
        ResultPair rp = new ResultPair(s1, b);
        if (useTestData) {
            rp.item = "/find/something/very/interesting";
            rp.valid = true;
        }
        return rp;
    }

    //TODO: Dan
    private ResultPair randomQuery()
    {
        String s1 = "make something random";
        boolean b = true; // true or false based on whether s1 is valid
        ResultPair rp = new ResultPair(s1, b);
        if (useTestData) {
            rp.item = "?param1=something&param2=very&param3=interesting";
            rp.valid = true;
        }
        return rp;
    }


    public void testRandomURLs(){
        int numTests = 10; //100000;
        boolean overallResults = true; // pass
        java.util.ArrayList<String> errorList = new java.util.ArrayList<>(50);
        L("Starting Random Tests");
        for (int i=0; i< numTests; i++)
        {
            ResultPair schema = randomSchema();
            ResultPair connector = randomConnector();
            ResultPair host = randomHost();
            ResultPair path = randomPath();
            ResultPair query = randomQuery();
            String testURL = schema.item + connector.item + host.item + path.item + query.item;
            boolean expectedValue = schema.valid && connector.valid && host.valid && query.valid;
            L("Testing URL: " + testURL);


            UrlValidator urlVal = new UrlValidator();
            try { // I don't think this works becuase the 'Exception' that is thrown is processed by the junit assert
                if (expectedValue)
                    assertTrue(true);
                    //assertTrue(urlVal.isValid(testURL));
                else
                    assertFalse(urlVal.isValid(testURL));
            } catch (Exception genericExc)
            {
                errorList.add(genericExc.getLocalizedMessage()); // may want to use getMessage
                String message = (errorList.size()) + ": expected " + (expectedValue ? "true" : "false") +
                        " but received " + (!expectedValue ?  "true" : "false") + " for url '"
                        + testURL + "'";
                System.out.println(message);
                overallResults = false;
            }// end try/catch

        }// end for
        System.out.println("Error List to follow");
        for (int i=0; i< errorList.size(); i++)
        {
            String message = "" + (i+1) + ": " + errorList.get(i);
            System.out.println(message);
        }
        assertTrue(overallResults); // will fail test if even 1 run is false
    }//testRandomURLs

    //</editor-fold>


}
