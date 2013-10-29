package be.lemonade.androidtalk.controllers;

import android.app.Activity;
import android.content.Intent;

/**
 * Controller base class.
 * @author rafael
 *
 */
public abstract class Controller {
	
	private Activity activity;
	
	/**
	 * Constructor. Initializes the controller with the invoking activity
	 * @param activity
	 */
	public Controller(Activity activity){
		this.activity = activity;
	}

	/**
	 * Returns the activity being controlled.
	 * @return activity
	 */
	public Activity getActivity() {
		return activity;
	}
	
	/**
	 * Starts a new instance of the activity which class is received
	 * @param activity class
	 */
	public void startActivity(Class<? extends Activity> activityClass){
		activity.startActivity(new Intent(activity, activityClass));
	}
	
	/**
	 * Initializes the controlled views and layout behaviour.
	 */
	public abstract void initialize();
}