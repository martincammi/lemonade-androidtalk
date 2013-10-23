package be.lemonade.androidtalk.activities;

import java.util.ArrayList;
import java.util.List;

import be.lemonade.androidtalk.R;
import be.lemonade.androidtalk.R.id;
import be.lemonade.androidtalk.R.layout;
import be.lemonade.androidtalk.R.menu;
import be.lemonade.androidtalk.controllers.MainActivityController;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	//Model View Controller
	private List<String> menu = new ArrayList<String>();
	private ListView listView;
	private MainActivityController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		menu.add("Other Apps");
		menu.add("Google Api");
		menu.add("Webservice");
		menu.add("Database");
		
        listView = (ListView) findViewById(R.id.rest_list_view);
        controller = new MainActivityController(menu, listView, this);
        controller.initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
