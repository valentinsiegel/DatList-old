package fr.siegel.datlist;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import datlist.siegel.fr.datlist.R;
import fr.siegel.datlist.adapters.MyAdapter;


public class MainActivity extends AppCompatActivity {

    //First We Declare Titles And Icons For Our Navigation drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String titles[] = null;
    int icons[] = {R.drawable.ic_list, R.drawable.ic_settings};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String name = "Valentin Siegel";
    String email = "valentinsgl@gmail.com";
    int avatar = R.drawable.ic_avatar;
    RecyclerView recyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter adapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager layoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout drawer;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle drawerToggle;                  // Declaring Action Bar drawer Toggle
    private Toolbar toolbar;                              // Declaring the Toolbar Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        titles = new String[]{getResources().getString(R.string.drawer_my_list), getResources().getString(R.string.drawer_my_settings)};

        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        recyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        adapter = new MyAdapter(titles, icons, name, email, avatar);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        recyclerView.setAdapter(adapter);                              // Setting the adapter to RecyclerView

        layoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        recyclerView.setLayoutManager(layoutManager);                 // Setting the layout Manager


        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);        // drawer object Assigned to the view
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open_drawer, R.string.drawer_close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // drawer Toggle Object Made
        drawer.setDrawerListener(drawerToggle); // drawer Listener set to the drawer toggle
        drawerToggle.syncState();               // Finally we set the drawer toggle sync State

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}