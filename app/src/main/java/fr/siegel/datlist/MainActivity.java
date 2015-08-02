package fr.siegel.datlist;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import datlist.siegel.fr.datlist.R;
import fr.siegel.datlist.adapters.NavigationDrawerAdapter;

public class MainActivity extends AppCompatActivity {

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String name = "Valentin Siegel";
    String email = "valentinsgl@gmail.com";
    int avatar = R.drawable.ic_avatar;
    RecyclerView recyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter adapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager layoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout drawerLayout;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle drawerToggle;                  // Declaring Action Bar drawerLayout Toggle
    private Toolbar toolbar;                              // Declaring the Toolbar Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        INITIALIZE TOOLBAR
         */
        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);


        /*
        INITIALIZE Resources FOR THE NAVIGATION DRAWER
         */
        Resources resources = getResources();
        String[] drawerTitles = resources.getStringArray(R.array.drawer_list);
        TypedArray drawerIcons = resources.obtainTypedArray(R.array.drawer_icons);


        /*
        INITIALIZE RECYCLER VIEW
         */
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        recyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        adapter = new NavigationDrawerAdapter(drawerTitles, drawerIcons, name, email, avatar);       // Creating the Adapter of NavigationDrawerAdapter class(which we are going to see in a bit)

        recyclerView.setAdapter(adapter);                              // Setting the adapter to RecyclerView
        layoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        recyclerView.setLayoutManager(layoutManager);                 // Setting the layout Manager

        /*
        HANDLE ON CLICK LISTENER FOR THE NAVIGATION DRAWER
         */
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    drawerLayout.closeDrawers();

                    if (recyclerView.getChildAdapterPosition(child) > 0) {
                        Toast.makeText(MainActivity.this, "The Item Clicked is: " + recyclerView.getChildAdapterPosition(child), Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);        // drawerLayout object Assigned to the view
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open_drawer, R.string.drawer_close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawerLayout is opened( As I dont want anything happened whe drawerLayout is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawerLayout is closed
            }


        }; // drawerLayout Toggle Object Made
        drawerLayout.setDrawerListener(drawerToggle); // drawerLayout Listener set to the drawerLayout toggle
        drawerToggle.syncState();               // Finally we set the drawerLayout toggle sync State

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