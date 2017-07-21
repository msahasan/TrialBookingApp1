package sg.com.atos.trialbookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBook=(Button) findViewById(R.id.btnBooking);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMktgPromo();
            }
        });
    }
    protected void goMktgPromo(){
        Intent intent=new Intent(MainActivity.this,MarkingPromo.class);
        MainActivity.this.startActivity(intent);
    }
}
