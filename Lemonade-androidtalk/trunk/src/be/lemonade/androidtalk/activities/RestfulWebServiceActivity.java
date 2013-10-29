package be.lemonade.androidtalk.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import be.lemonade.androidtalk.R;
import be.lemonade.androidtalk.controllers.Controller;
import be.lemonade.androidtalk.controllers.RestfulWebServiceActivityController;

/**
 * Actividad de ejemplo que ilustra como consumir un web service REST
 * @author rafael
 *
 */
public class RestfulWebServiceActivity extends Activity{
	
	private Controller controller;
	
	private EditText user;
	private EditText pass;

	private ListView issues;
	
	private Button issuesButton;	
	
	ProgressDialog fetchingIssuesDialog;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//muestro la pantalla definida en activity_restful_web_service.xml
		setContentView(R.layout.activity_restful_web_service);
		
		//the issues list is retrieved
		issues = (ListView)findViewById(R.id.issues);
		
		//the issues get button is retrieved
		issuesButton = (Button)findViewById(R.id.getIssuesButton);
		
		//the user edit text is retrieved
		user = (EditText)findViewById(R.id.userEditText);

		//the pass edit text is retrieved
		pass = (EditText)findViewById(R.id.passEditText);
		
		//a progress dialog is created (this was not defined in any xml file, i just create it here)
		fetchingIssuesDialog = new ProgressDialog(this);
		fetchingIssuesDialog.setMessage("Fetching issues...");
		fetchingIssuesDialog.setCancelable(false);

		//the controller is created
		controller = new RestfulWebServiceActivityController(this);
		
		//the views and layout behaviour initialized
		controller.initialize();
	}
	
	/**
	 * Returns the user edit text
	 * @return user edit text
	 */
	public EditText getUser() {
		return user;
	}

	/**
	 * Returns the password edit text
	 * @return password edit text
	 */
	public EditText getPass() {
		return pass;
	}

	/**
	 * Returns the issues list view
	 * @return issues list view
	 */
	public ListView getIssues() {
		return issues;
	}

	/**
	 * Returns the button for getting the issues
	 * @return issues button
	 */
	public Button getIssuesButton() {
		return issuesButton;
	}
	
	/**
	 * Returns the fetching issues dialog
	 * @return fetching issues dialog
	 */
	public ProgressDialog getFetchingIssuesDialog(){
		return fetchingIssuesDialog;
	}
}