package r.vehciletowing.com.vehicletowrto;


	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	public class Validation 
	{
		// validating email id
		public boolean isValidEmail(String email) 
		{
			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
		
		// validating mobile no
		public boolean isValidMobile(String mobile) 
		{
			String Mobile_PATTERN = "^[0-9]{10}$";

			Pattern pattern = Pattern.compile(Mobile_PATTERN);
			Matcher matcher = pattern.matcher(mobile);
			return matcher.matches();
		}
		
					
		// validating Aadhar 
		public boolean isValidAadhar(String aadhar) 
		{
			String AAdhar_PATTERN = "^[0-9]{12}$";

			Pattern pattern = Pattern.compile(AAdhar_PATTERN);
			Matcher matcher = pattern.matcher(aadhar);
			return matcher.matches();
		}

					
		// validating only Charater
		public boolean isValidOnlyChar(String Char) 
		{
			String CHAR_PATTERN = "[A-Za-z ]+";

			Pattern pattern = Pattern.compile(CHAR_PATTERN);
			Matcher matcher = pattern.matcher(Char);
			return matcher.matches();
		}
			

	}

