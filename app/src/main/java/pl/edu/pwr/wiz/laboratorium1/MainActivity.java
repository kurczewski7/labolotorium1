package pl.edu.pwr.wiz.laboratorium1;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
//import static pl.edu.pwr.wiz.laboratorium1.R.id.image1;
//import static pl.edu.pwr.wiz.laboratorium1.R.id.welcome;

public class MainActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
   //private TextView welcome=(TextView) findViewById(R.id.welcome);
    private Toolbar toolbar;
    //final private  ImageView obrazek;
    private Button przycisk;
    private FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        final ImageView obrazek = (ImageView) this.findViewById(R.id.image1);
        przycisk = (Button) this.findViewById(R.id.button1);
        fab = (FloatingActionButton) this.findViewById(R.id.fab);
        //TextView welcome=(TextView)this.findViewById(R.id.welcome);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // @TODO wyświetlać Snackbar
                //Snackbar.make(view, R.string.snacktext, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                Toast.makeText(getApplicationContext(), R.string.snacktext,Toast.LENGTH_LONG).show();
            }
        });

        // @TODO wyswietlac i ukrywac obrazek
        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.w("Labolatorium1","wciśnięto przycisk");

                if (obrazek.getAlpha() >= (float)0.9) {
                    obrazek.setAlpha((float)0.1);
                } else {
                    obrazek.setAlpha((float)0.9);
                }

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        TextView welcome=(TextView) findViewById(R.id.welcome);
       // welcome.getBackground();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
             /* Pobierz dane o aktualnych kolorach do nowej aktywnosci */
            int textColor = welcome.getCurrentTextColor();


            ColorDrawable cd = (ColorDrawable) welcome.getBackground();
            int backgroundColor;
            if (cd != null) {
                backgroundColor = cd.getColor();
            } else {
                backgroundColor = WHITE;
            }

            // @TODO otworz aktywnosc z ustawieniami i przeslij do niej aktualne kolory - hint uzyj funkcji startActivityForResult
            //setContentView(R.layout.activity_settings);
            Intent i = new Intent(this, SettingsActivity.class);
            i.putExtra("textColor", textColor);
            i.putExtra("backgroundColor", backgroundColor);
            startActivityForResult(i, SettingsActivity.CHANGE_SETTINGS);

            return true;
            //     if (id==R.id.action_about}
            //Toast toast=Toast.makeText(getApplicationContext(),"Super aplikacja ")
            //        toast.show();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         TextView welcome=(TextView) findViewById(R.id.welcome);
        // Check which request we're responding to
        if (requestCode == SettingsActivity.CHANGE_SETTINGS) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {
                // @TODO Pobierz dane powrotne z Intentu 'data'
                int textColor=data.getIntExtra("textColor",BLACK);
                int backgroundColor=data.getIntExtra("backgroundColor",WHITE);

                welcome.setTextColor(textColor);
                welcome.setBackgroundColor(backgroundColor);
                String txt=data.getStringExtra("txt");
                Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_LONG).show();



                // @TODO Zmien kolor tekstu w TextView o id welcome

                // Wyswietlamy info, ze dane zostaly zapisane
                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
