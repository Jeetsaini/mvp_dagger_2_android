package myapp.jeet.com.dagger2android;

import android.provider.ContactsContract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Mockito.verify;

/**
 * Created by Admin on 8/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailValidaterTest {
	@Mock
	public EmailValidater mMeEmailValidater;
    @InjectMocks
	MyMockEmailTesterClass mMyMockEmailTesterClass;

	@Test
	public void testUsingJUnitEmail()
	{
		String email="abc";
		EmailValidater emailValidater=new EmailValidater();
		boolean isValidEmail=emailValidater.isEmailvalid( email );
		assertFalse(isValidEmail);
	}

	@Test
	public void testUsingMockito()
	{
		MyMockEmailTesterClass myMockEmailTesterClass;
		myMockEmailTesterClass=new MyMockEmailTesterClass( mMeEmailValidater );
		Mockito.when( mMeEmailValidater.isEmailvalid( "abc" ) ).thenReturn( false );
		Mockito.when( mMeEmailValidater.isEmailvalid( "jitenderandroid@gmail.com" ) ).thenReturn( true );
		boolean isValid=myMockEmailTesterClass.checkValidEmail( "abc" );
		boolean isValidNew=myMockEmailTesterClass.checkValidEmail( "jitenderandroid@gmail.com" );
		verify(mMeEmailValidater).isEmailvalid( "abc" );
		assertFalse( isValid );
		assertTrue( isValidNew );
	}

	@Test
	public void personTestUsingHemcrest()
	{
		Person person=new Person( "jeet","ballbagarh" );
		assertThat( person,hasProperty("name") );
		assertThat( person.getName(),is( "jeet" ) );
	}
}
