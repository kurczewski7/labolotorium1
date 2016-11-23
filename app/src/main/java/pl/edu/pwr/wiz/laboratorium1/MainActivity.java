package pl.edu.pwr.wiz.laboratorium1;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // @TODO wyświetlać Snackbar

            }
        });

        // @TODO wyswietlac i ukrywac obrazek
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
             /* Pobierz dane o aktualnych kolorach do nowej aktywnosci */
            TextView welcome = (TextView) findViewById(R.id.welcome);
            int textColor = welcome.getCurrentTextColor();

            ColorDrawable cd = (ColorDrawable) welcome.getBackground();
            int backgroundColor;
            if(cd != null) {
                backgroundColor = cd.getColor();
            } else {
                backgroundColor = WHITE;
            }

            // @TODO otworz aktywnosc z ustawieniami i przeslij do niej aktualne kolory - hint uzyj funkcji startActivityForResult

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == SettingsActivity.CHANGE_SETTINGS) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {
                // @TODO Pobierz dane powrotne z Intentu 'data'

                // @TODO Zmien kolor tekstu w TextView o id welcome

                // Wyswietlamy info, ze dane zostaly zapisane
                String txt = (String) data.getStringExtra("txt");
                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
