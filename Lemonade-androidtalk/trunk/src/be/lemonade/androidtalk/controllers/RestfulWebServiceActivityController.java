package be.lemonade.androidtalk.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import be.lemonade.androidtalk.R;
import be.lemonade.androidtalk.activities.RestfulWebServiceActivity;
import be.lemonade.androidtalk.webservices.HttpRequestAsyncTask;

/**
 * Controller for the RestfulWebServiceActivity
 * @author rafael
 *
 */
public class RestfulWebServiceActivityController extends Controller {
	
	/**
	 * Constructor. Initializes the controller with the activity being controlled.
	 * @param activity
	 */
	public RestfulWebServiceActivityController(Activity activity){
		super(activity);
	}

	/*
	 * (non-Javadoc)
	 * @see be.lemonade.androidtalk.controllers.Controller#initialize()
	 */
	@Override
	public void initialize() {
		final RestfulWebServiceActivity activity;
		
		//the controlled activity is retrieved
		activity = (RestfulWebServiceActivity)getActivity();
		
		//a click listener is set in order to trigger the rest call when the issues button is clicked
		activity.getIssuesButton().setOnClickListener(new OnClickListener() {
			
			/*
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View view) {
				HttpRequestAsyncTask httpRequestAsyncTask;

				//basic user and pass validation
				if(activity.getUser().getText().length() == 0 || activity.getPass().getText().length() == 0)
					Toast.makeText(activity, activity.getResources().getString(R.string.missing_data_warning), Toast.LENGTH_SHORT).show();
				else{

					//if the user and pass were supplied
					
					//i show the loading dialog
					activity.getFetchingIssuesDialog().show();
					
					//a async http request task is created to fetch the issues
					httpRequestAsyncTask = new HttpRequestAsyncTask(){
						
					
						/*
						 * (non-Javadoc)
						 * @see be.lemonade.androidtalk.webservices.HttpRequestAsyncTask#execute(java.lang.String)
						 */
						@Override
						protected void execute(String response) {
							ArrayAdapter<String> adapter;
							List<String> issues;
							JSONObject jsonObject;
							JSONArray jsonIssuesArray;
							JSONObject jsonIssueObject;
							
							try{
								
								//a list is created for all the issues
								issues = new ArrayList<String>();
								
								//the json response is parsed
								jsonObject = new JSONObject(response);
								
								//the issues array is retrieved from the json object
								jsonIssuesArray = jsonObject.getJSONArray("issues");
								
								//for every issue a te
								for(int i = 0; i < jsonIssuesArray.length(); i++){
									
									//the issue is obtained
									jsonIssueObject = jsonIssuesArray.getJSONObject(i);
									
									//the subject of the issue is stored in a list
									issues.add(jsonIssueObject.getString("subject"));
								}
								
								//an adapter is created to fill the list view with the issues
								adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, issues);
						        
								//the adapter is set to the list view in the activity
						        activity.getIssues().setAdapter(adapter);
								
								//i hide the issues fetching dialog
								activity.getFetchingIssuesDialog().hide();
							}
							catch(JSONException e){
								e.printStackTrace();
								
								//an error message is displayed in the loading dialog
								activity.getFetchingIssuesDialog().setMessage(activity.getString(R.string.issues_fetch_failed));
								
								//the dialog is made closable
								activity.getFetchingIssuesDialog().setCancelable(true);
							}							
						}
					};
					
					//the http call is made
					httpRequestAsyncTask.execute(activity.getString(R.string.issues_restful_ws_url) + "?key=9d40ef16de3d4f5f3aef6c777f7b690c7b90c95b");
				}

			}
		});
	}
}
