package edu.institution.midterm;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import edu.institution.actions.asn5.Utilities;
import edu.institution.asn2.LinkedInUser;
import jdk.internal.joptsimple.internal.Strings;



public class PartManagerImplTest {
	
	private static String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator;
	private static String FILE_NAME = "bom.json";
	
	

	@Test
	public void costPartTest(){
		


		PartManager parstManager = new PartManagerImpl(); 
		
		parstManager.importPartStore(PATH + FILE_NAME );
		
		
		//290B7266J1  Expected Cost:415.16
	    Assert.assertEquals((float)415.16, parstManager.costPart("290B7266J1").getPrice(), 0);       //Url: https://stackoverflow.com/questions/7554281/junit-assertions-make-the-assertion-between-floats   
		
	    //290B7266J2  Expected Cost:532.20 
	    Assert.assertEquals((float)532.20, parstManager.costPart("290B7266J2").getPrice(), 0); 
	    
	    
	    //290B7266J6  Expected Cost:334.10 
	    Assert.assertEquals((float)334.10, parstManager.costPart("290B7266J6").getPrice(), 0); 
	    
	    //20-0001 Expected Cost:96.39 
	    Assert.assertEquals((float)96.39, parstManager.costPart("20-0001").getPrice(), 0); 
	    
	    //20-0015 Expected Cost:70.46 
	    Assert.assertEquals((float)70.46, parstManager.costPart("20-0015").getPrice(), 0); 
	    
		
	}//removeDuplicatesTest 
    
	
	

			
		
}//





	

	 
	
	
	

