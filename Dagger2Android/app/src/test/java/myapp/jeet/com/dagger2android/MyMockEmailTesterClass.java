package myapp.jeet.com.dagger2android;

/**
 * Created by Admin on 8/17/2017.
 */

public class MyMockEmailTesterClass {
	private EmailValidater mEmailValidater;
	public MyMockEmailTesterClass(EmailValidater emailValidater)
	{
		this.mEmailValidater=emailValidater;
	}
	public boolean checkValidEmail(String email)
	{
		boolean isEmailValid=mEmailValidater.isEmailvalid( email );
		return isEmailValid;
	}
}
