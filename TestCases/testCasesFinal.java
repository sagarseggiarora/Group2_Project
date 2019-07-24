import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.project.Boundary.UserTicketDAO;
import com.project.Controller.Validations;
import com.project.Entity.Tickets_Group2;

class testCasesFinal {

	@Test
	void emailMatch() {
	
	Validations v = new Validations();
	assertTrue(v.validateEmail("parth@gmail.com"));
	
	}
	@Test
	void emailNotMatch()
	{
		Validations v = new Validations();
		assertFalse(v.validateEmail("parthgmail.com"));
	}
	@Test
	void phoneLengthAndNumberic()
	{
		Validations v = new Validations();
		assertTrue(v.validatePhone("7787519473"));
	}
	@Test
	void phoneLengthAndNumbericFalse()
	{
		Validations v = new Validations();
		assertFalse(v.validatePhone("77875194AA"));
	}
	@Test
	void checkIfStringNotEmpty()
	{
		Validations v = new Validations();
		assertTrue(v.isNotEmpty("Parth"));
	}
	@Test
	void checkIfStringEmpty()
	{
		Validations v = new Validations();
		assertFalse(v.isNotEmpty(""));
	}
	@Test
	void checkIfTicketNumberNumeric()
	{
		Validations v = new Validations();
		assertTrue(v.validateTicketNum("05678"));
	}
	@Test
	void checkIfTicketNumberNotNumeric()
	{
		Validations v = new Validations();
		assertFalse(v.validateTicketNum("AAA555"));
	}
	
	@Test
	void checkifUsernamePasswordExists()
	{
		UserTicketDAO ug = new UserTicketDAO();
		assertTrue(ug.validate("sagar","sagar123"));
		
	}
	
	@Test
	void checkifUsernamePasswordExistsIsFalse()
	{
		UserTicketDAO ug = new UserTicketDAO();
		assertFalse(ug.validate("sagar", "sagar"));
		
	}
	//System Testing

	

}
