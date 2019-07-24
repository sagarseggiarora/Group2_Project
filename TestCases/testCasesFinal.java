import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.Boundary.UserTicketDAO;
import com.project.Controller.Validations;
import com.project.Entity.Tickets_Group2;

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
	//System Testing

	

}
