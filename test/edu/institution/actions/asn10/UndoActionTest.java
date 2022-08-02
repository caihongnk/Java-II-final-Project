package edu.institution.actions.asn10;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn3.AddUserAction;
import edu.institution.actions.asn3.DeleteUserAction;
import edu.institution.actions.asn3.ListUserAction;
import edu.institution.actions.asn3.SerializedUserRepository;
import edu.institution.actions.asn4.AddConnectionAction;
import edu.institution.actions.asn4.ListConnectionAction;
import edu.institution.actions.asn4.RemoveConnectionAction;
import edu.institution.actions.asn5.Utilities;
import edu.institution.asn2.LinkedInUser;
import jdk.internal.joptsimple.internal.Strings;





public class UndoActionTest {
	
	private static String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator;
	private static String FILE_NAME = "LinkedInUsers.dat"; 
	
	@Test
	public void  undoActionTest(){

		Scanner scanner = new Scanner(System.in);
		
		UserRepository userRepository = new SerializedUserRepository(); 
		
		userRepository.init(PATH, FILE_NAME);
	 
		LinkedInUser loggedInUser = new LinkedInUser("Jim","123");
		
		
		
		MenuAction listUserAction = new ListUserAction();
		listUserAction.process(scanner, userRepository, loggedInUser);

		
		MenuAction addUserAction = new AddUserAction();
		addUserAction.process(scanner, userRepository, loggedInUser);
		addUserAction.process(scanner, userRepository, loggedInUser);
		
		listUserAction.process(scanner, userRepository, loggedInUser);
		
		MenuAction undoAction = new UndoAction();
		undoAction.process(scanner, userRepository, loggedInUser);
		
		listUserAction.process(scanner, userRepository, loggedInUser);
		
		MenuAction DeleteUserAction = new DeleteUserAction();
		DeleteUserAction.process(scanner, userRepository, loggedInUser);
		undoAction.process(scanner, userRepository, loggedInUser);
		
		listUserAction.process(scanner, userRepository, loggedInUser);
		
		

	}//removeDuplicatesTest 
    
	
	






	

	 
	
	
	
}