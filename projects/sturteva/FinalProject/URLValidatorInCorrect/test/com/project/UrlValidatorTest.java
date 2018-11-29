package com.project;//import com.project.UrlValidator;
//import com.project.UrlValidator;
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

//Testing my commit
///  Comment:  class not found error  'Class not found: "com.project.UrlValidatorTest"'
//  When the project definition does not have an output folder defined I consistently
//  ran into this error.  Got to "Project Structure ctrl-alt-shift-s
//  Project Settings->modules  set "Use Module Compile Output Path"
//  and set the project output to an actual location on your system.


public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }
   
   
   public void testManualTestNull()
   {
      long allowAllSchemes = 1;
      UrlValidator urlVal = new UrlValidator(null,null,allowAllSchemes);
//You can use this function to implement your manual testing	   
	   assertFalse(urlVal.isValid(null));
	   //assertTrue(urlVal.isValid("http://www.facebook.com"));
	   //assertTrue(urlVal.isValid("http://www.facebook.com/"));
	   //assertFalse(urlVal.isValid("facebook.com"));
	   //assertFalse(urlVal.isValid("htp://www.facebook.com"));
	   assertFalse(urlVal.isValid("http:://www.facebook.com"));

   }

   public void testManualGood(){
       long allowAllScheme = 1;
       UrlValidator urlVal = new UrlValidator(null,null, allowAllScheme);
       assertTrue(urlVal.isValid("http://www.facebook.com"));
   }

   public void testManualWithBack(){
       long allowAllScheme = 1;
       UrlValidator urlVal = new UrlValidator(null,null,allowAllScheme);
       assertTrue(urlVal.isValid("http://www.facebook.com/"));

   }

   public void testManualNoWWW(){
       long allowAllScheme = 1;
       UrlValidator urlVal = new UrlValidator(null,null,allowAllScheme);
        assertFalse(urlVal.isValid("facebook.com"));
   }

   public void testManualBadScheme(){
       long allowAllScheme = 1;
       UrlValidator urlVal = new UrlValidator(null,null,allowAllScheme);
        assertFalse(urlVal.isValid("htp://www.facebook.com"));
   }

   public void testManualDoubleColon(){
       long allowAllScheme = 1;
       UrlValidator urlVal = new UrlValidator(null,null,allowAllScheme);
       assertFalse(urlVal.isValid("http:://www.facebook.com"));
   }


   
   public void testYourFirstPartition()
   {
	UrlValidator urlVal = new UrlValidator(null,null,1);

	assertFalse(urlVal.isValid(null)); //No input

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
