package in.nilnet.nilneteducation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import in.nilnet.nilneteducation.fragments.CoursesFragment;
import in.nilnet.nilneteducation.fragments.DashboardFragment;
import in.nilnet.nilneteducation.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);

        Fragment dashboardFragment = new DashboardFragment();
        Fragment coursesFragment = new CoursesFragment();
        Fragment profileFragment = new ProfileFragment();

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigation = (NavigationView) findViewById(R.id.navigation_id);

        loadFragment(dashboardFragment);
        //TODO : Performance hit. Need to find another work around to bring drawer to front.
        drawerLayout.bringToFront();

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                String[] id = getResources().getResourceName(item.getItemId()).split("\\/");
                drawerLayout.close();
                if(id[1].equals("navigation_dashboard")) {
                    return loadFragment(dashboardFragment);
                }
                else if(id[1].equals("navigation_courses")) {
                    return loadFragment(coursesFragment);
                }
                else if(id[1].equals("navigation_profile")) {
                    return loadFragment(profileFragment);
                }
                return false;
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment!=null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

