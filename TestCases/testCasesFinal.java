import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.project.Boundary.UserTicketDAO;
import com.project.Controller.Validations;
import com.project.Entity.Logs_Group2;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;

class testCasesFinal {
	Validations v;
	UserTicketDAO ug;
	
	@BeforeEach
	void setup() throws Exception {
		v = new Validations();
		ug = new UserTicketDAO();
	}
	
	@Test
	void emailMatch() 
	{
		assertTrue(v.validateEmail("parth@gmail.com"));
	}
	@Test
	void emailNotMatch()
	{
		assertFalse(v.validateEmail("parthgmail.com"));
	}
	@Test
	void phoneLengthAndNumberic()
	{
		assertTrue(v.validatePhone("7787519473"));
	}
	@Test
	void phoneLengthAndNumbericFalse()
	{
		assertFalse(v.validatePhone("77875194AA"));
	}
	@Test
	void checkIfStringNotEmpty()
	{
		assertTrue(v.isNotEmpty("Parth"));
	}
	@Test
	void checkIfStringEmpty()
	{
		assertFalse(v.isNotEmpty(""));
	}
	@Test
	void checkIfTicketNumberNumeric()
	{
		assertTrue(v.validateTicketNum("05678"));
	}
	@Test
	void checkIfTicketNumberNotNumeric()
	{
		assertFalse(v.validateTicketNum("AAA555"));
	}
	
	@Test
	void checkifUsernamePasswordExists()
	{
		assertTrue(ug.validate("sagar","sagar123"));
		
	}
	
	@Test
	void checkifUsernamePasswordExistsIsFalse()
	{
		assertFalse(ug.validate("sagar", "sagar"));
		
	}
	
	//unit testing for Logs_Group2 entity class
	
	@Test
	void getCommentTest() {
		Logs_Group2 l= new Logs_Group2();
		l.setComment("Issue Resolved");
				
		assertEquals(l.getComment(),"Issue Resolved");
	}
	
	@Test
	void getSubmitTest() {
		Logs_Group2 l= new Logs_Group2();
		l.setSubmitted_by("test");
				
		assertNotEquals(l.getSubmitted_by(),"test1");
		
	}
	
	//unit testing for Tickets_Group2 entity class
	
	@Test
	void getStatusTest() {
		Tickets_Group2 t= new Tickets_Group2();
		t.setStatus("open");
				
		assertEquals(t.getStatus(),"open");
	}
	
	@Test
	void getEmailTest() {
		Tickets_Group2 t= new Tickets_Group2();
		t.setEmail("sagar@sagar.com");
				
		assertNotEquals(t.getStatus(),"sagar.com");
		
	}
	
	//unit testing for User_Group2 entity class
	
		@Test
		void getFnameTest() {
			User_Group2 u= new User_Group2();
			u.setFirst_name("Sagar");
					
			assertEquals(u.getFirst_name(),"Sagar");
	
		}
		
		@Test
		void getLnameTest() {
			User_Group2 u= new User_Group2();
			u.setLast_name("Arora");
					
			assertNotEquals(u.getFirst_name(),"Sagar");
	
		}

}
