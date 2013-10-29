package be.lemonade.androidtalk.controllers;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import be.lemonade.androidtalk.activities.RestfulWebServiceActivity;

/**
 * Controlador para la actividad principal, aqui se encuentra definido el comportamiento de los widgets mostrados en la pantalla de la misma.
 * @author Martin Cammi
 *
 */
public class MainActivityController extends Controller{

	//Model View Activity
	private List<String> menu;
	private ListView listView;
	
	/**
	 * Constructor. Inicializa el controlador.
	 * @param lista de opciones
	 * @param list view
	 * @param activity
	 */
	public MainActivityController(List<String> menu, ListView listView, Activity activity){
		super(activity);
		this.menu =  menu;
		this.listView = listView;
	}

	/*
	 * (non-Javadoc)
	 * @see be.lemonade.androidtalk.controllers.Controller#initialize()
	 */
	public void initialize(){
		ArrayAdapter<String> adapter;
		
		//creo una adaptador para llenar el lista view con los elementos de menu
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menu);
        
		//lleno el list view de text views, cada uno con un elemento de la lista de strings (menu)
        listView.setAdapter(adapter);
		
        //creo un handler para el evento de un click en cualquier widget del listview
        OnItemClickListener onRestaurantClickHandler = new OnItemClickListener() {
        	
        	/*
        	 * (non-Javadoc)
        	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
        	 */
            public void onItemClick(AdapterView parent, View view, int position, long id) {
            	TextView textView;
            	
            	//como se que en el list view solo agregue text views, casteo el view donde se registro el evento click
            	textView = (TextView)view;
            	
            	//dependiendo del texto que habia en el text view clickeado arranco la actividad correspondiente
            	if(textView.getText().equals("Rest Web Service"))
            		startActivity(RestfulWebServiceActivity.class);
            }
        };

        //asigno el handler al list view
        listView.setOnItemClickListener(onRestaurantClickHandler);
	}
}