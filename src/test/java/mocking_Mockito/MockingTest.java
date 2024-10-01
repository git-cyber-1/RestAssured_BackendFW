package mocking_Mockito;

import org.mockito.Mockito;

class PANCard{
	/*
	 * this business method is to validate schema of PanCrad
	 */
	public  String isValid(String pancard)
	{
		if(pancard.matches("[A-Z]{5}[0-9]{4}[A-Z]")==true)
		{
			return "valid panCard";
		}
		else
		{
			return "is invalid";
		}
	}
	
	/*
	 * this method is for mocking pan card details i.e tester can only use this pancard details while testing
	 * 
	 */
   public static PANCard getMockObject()
   {
	
	PANCard mocObject = Mockito.mock(PANCard.class);
	                    Mockito.when(mocObject.isValid("ABCDE1234A")).thenReturn("VALID PANCARD");
	                    Mockito.when(mocObject.isValid("EBCDE1534B")).thenReturn("VALID PANCARD");
	                    Mockito.when(mocObject.isValid("ZBCDE8234C")).thenReturn("VALID PANCARD");
	
	return mocObject;
    }
   }

public class MockingTest {

	public static void main(String[] args) {
		
		     PANCard pobj = PANCard.getMockObject();
		     System.out.println(pobj.isValid("ABCDE1234D"));
		
	}
	
}
