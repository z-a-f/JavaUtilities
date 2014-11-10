package cc.zafar.parseutilities;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Collection of methods to work with Parse.com. Make sure that Parse API is installed or is in
 * the build path.
 *
 * @author Zafar Takhirov
 * @see <a href = "https://www.parse.com/">Parse website</a>
 */
public final class ParseUtil {
	/** Class constructor. The class is private to avoid initialization */
	private ParseUtil () {

	}

	/**
	 * Initialization routine for Parse.
	 *
	 * @param context
	 * 	Context of the calling activity
	 * @param applicationId
	 * 	Application ID provided by Parse.com
	 * @param clientKey
	 * 	Client key provided by Parse.com
	 */
	public static void init (Context context, String applicationId, String clientKey) {
		// Log.d("ZAFAR", "Init invoked");
		Parse.initialize(context, applicationId, clientKey);
		// ParseUser.logOut();
	}

	/**
	 * Checks if any user is logged in. The method uses getCurrentUser()
	 *
	 * @return Returns true if any user is logged in
	 *
	 * @see {@link #isLoggedIn(String)}
	 */
	public static boolean isLoggedIn () {
		return (ParseUser.getCurrentUser() != null);
	}

	/**
	 * Checks if the specified user is logged in.
	 *
	 * @return Returns true if the specified user is logged in is logged in
	 *
	 * @see {@link #isLoggedIn()}
	 */
	public static boolean isLoggedIn (String username) {
		return (isLoggedIn() && ParseUser.getCurrentUser().getUsername().matches(username));
	}

	/**
	 *
	 * @param v View parameter is used if register method to show a message
	 * @param username  Username string
	 * @param password  Password string
	 * @return  Returns true if the registration was successful and the current user is logged in
	 */
	public static boolean register (final View v, String username, String password) {
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		// user.setEmail("email@example.com");

		// other fields can be set just like with ParseObject
		// user.put("phone", "650-253-0000");

		user.signUpInBackground(new SignUpCallback() {
			public void done (ParseException e) {
				if (e == null) {
					// Hooray! Let them use the app now.
				} else {
					// Sign up didn't succeed. Look at the ParseException
					// to figure out what went wrong
				}
			}
		});
		return isLoggedIn(username);
	}

	/**
	 *
	 * @param v View parameter in case login method needs to use it to show a message
	 * @param username  Username string
	 * @param password  Password string
	 * @return  Returns true if the login was successful
	 */
	public static boolean login (View v, String username, String password) {
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			public void done (ParseUser user, ParseException e) {
				if (user != null) {
					// TODO: We are in!

				} else {
					// TODO: Signup failed. Look at the ParseException to see what happened.

				}
			}
		});
		return (ParseUser.getCurrentUser() != null
		        && ParseUser.getCurrentUser().getUsername().matches(username));
	}

	/**
	 * This is a test method - it creates a sample entry in the database
	 */
	public static void test () {
		Log.d("ZAFAR", "Test invoked");
		ParseUser user = new ParseUser();
		user.setUsername("my name");
		user.setPassword("my pass");
		user.setEmail("email@example.com");

		// other fields can be set just like with ParseObject
		user.put("phone", "650-555-0000");

		user.signUpInBackground(new SignUpCallback() {
			public void done (ParseException e) {
				if (e == null) {
					// Hooray! Let them use the app now.
					Log.d("ZAFAR", "Login OK");
				} else {
					// Sign up didn't succeed. Look at the ParseException
					// to figure out what went wrong
					Log.d("ZAFAR", "Login failed: " + e.toString());
				}
			}
		});
	}

}
