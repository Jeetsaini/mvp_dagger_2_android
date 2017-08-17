package myapp.jeet.com.dagger2android;

import org.junit.Before;
import org.mockito.Mock;

/**
 * Created by Admin on 8/17/2017.
 */

public class EmailValidater {

	public boolean isEmailvalid(String email)
{
	String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	java.util.regex.Matcher m = p.matcher(email);
	return m.matches();
}
}
