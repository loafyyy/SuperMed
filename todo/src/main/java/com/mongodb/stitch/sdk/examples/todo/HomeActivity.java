package com.mongodb.stitch.sdk.examples.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private ImageButton USButton;
    private ImageButton ChinaButton;
    private ImageButton FranceButton;
    private ImageButton GermanyButton;
    private ImageButton ItalyButton;
    private ImageButton SpainButton;
    private ImageButton ThailandButton;

    private TextView travelingFrom;
    private TextView travelingTo;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        USButton = findViewById(R.id.US_button);
        USButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    travelingFrom.setText("US");
                    count++;
                }
                else if (count == 1) {
                    travelingTo.setText("US");
                    goToNextPage();
                }

            }
        });

        ChinaButton = findViewById(R.id.China_button);
        ChinaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    travelingFrom.setText("China");
                    count++;
                }
                else if (count == 1) {
                    travelingTo.setText("China");
                    goToNextPage();
                }
            }
        });

        FranceButton = findViewById(R.id.France_button);
        FranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    travelingFrom.setText("France");
                    count++;
                }
                else if (count == 1) {
                    travelingTo.setText("France");
                    goToNextPage();
                }

            }
        });

        GermanyButton = findViewById(R.id.Germany_button);
        GermanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    travelingFrom.setText("Germany");
                    count++;
                }
                else if (count == 1) {
                    travelingTo.setText("Germany");
                    goToNextPage();
                }
            }
        });

        ItalyButton = findViewById(R.id.Italy_button);
        ItalyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    travelingFrom.setText("Italy");
                    count++;
                }
                else if (count == 1) {
                    travelingTo.setText("Italy");
                    goToNextPage();
                }
            }
        });

        SpainButton = findViewById(R.id.Spain_button);
        SpainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    travelingFrom.setText("Spain");
                    count++;
                }
                else if (count == 1) {
                    travelingTo.setText("Spain");
                    goToNextPage();
                }
            }
        });

        ThailandButton = findViewById(R.id.Thailand_button);
        ThailandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    travelingFrom.setText("Thailand");
                    count++;
                }
                else if (count == 1) {
                    travelingTo.setText("Thailand");
                    goToNextPage();
                }
            }
        });

        travelingFrom = findViewById(R.id.traveling_from);
        travelingTo = findViewById(R.id.traveling_to);
    }

    private void goToNextPage() {
        String fromCountry = travelingFrom.getText().toString();
        String toCountry = travelingTo.getText().toString();

        // go to next activity with extras
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("from", fromCountry);
        myIntent.putExtra("to", toCountry);
        startActivity(myIntent);
    }

}
