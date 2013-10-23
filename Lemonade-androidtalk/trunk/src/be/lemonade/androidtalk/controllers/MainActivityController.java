package be.lemonade.androidtalk.controllers;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivityController {

	//Model View Activity
	private List<String> menu;
	private ListView listView;
	private final Activity activity;
	
	public MainActivityController(List<String> menu, ListView listView, Activity activity){
		this.menu =  menu;
		this.listView = listView;
		this.activity = activity;
	}
	
	public void initialize(){
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, menu);
        
        listView.setAdapter(adapter);
		
        OnItemClickListener onRestaurantClickHandler = new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
            	//on click behavior.
            }
        };

        listView.setOnItemClickListener(onRestaurantClickHandler);
		
	}
}
