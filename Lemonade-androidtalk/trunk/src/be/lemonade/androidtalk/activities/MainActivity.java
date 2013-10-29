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

/**
 * Actividad principal
 * @author Martin Cammi
 *
 */
public class MainActivity extends Activity {

	//Model View Controller
	private List<String> menu = new ArrayList<String>();
	private ListView listView;
	private MainActivityController controller;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//muestro la pantalla definida en activity_main.xml
		setContentView(R.layout.activity_main);
		
		//agrego los items del menu (definidos en un array de strings en string_arrays.xml)
		for(String menuItem: getResources().getStringArray(R.array.main_activity_menu_items))
			menu.add(menuItem);
		
		//obtengo un widget (list view) definido en el layout (activity_main.xml)
        listView = (ListView) findViewById(R.id.rest_list_view);
        
        //creo e inicializo un controlador para la actividad
        controller = new MainActivityController(menu, listView, this);
        controller.initialize();
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		//agrego un meno definido en main.xml para que aparezca al apretar la tecla de menu del telefono
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}