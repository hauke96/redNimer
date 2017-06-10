package de.hauke_stieler.rednimer.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.hauke_stieler.rednimer.AppContext.ServiceLocator;
import de.hauke_stieler.rednimer.DayOverview.View.DayOverview;
import de.hauke_stieler.rednimer.R;

public class MainActivity extends AppCompatActivity {

    static{
        // Create all registrations for services
        ServiceLocator.registerAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, DayOverview.newInstance())
                    .commit();
        }
    }
}
