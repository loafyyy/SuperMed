package com.mongodb.stitch.sdk.examples.todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;

import com.mongodb.BasicDBObject;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.auth.StitchAuth;
import com.mongodb.stitch.android.core.auth.StitchAuthListener;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.bson.Document;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // mongo
    private StitchAppClient _client;
    private RemoteMongoClient _mongoClient;

    // views
    private EditText drugSearchEditText;
    private Button drugSearchButton;
    private RecyclerView drugSearchRecyclerView;
    private DrugAdapter drugAdapter;

    private Context mContext;

    private String fromCountry;
    private String toCountry;

    private TextView fromCountryTV;
    private TextView toCountryTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_todo_list);

        mContext = this;
        fromCountry = getIntent().getStringExtra("from");
        toCountry = getIntent().getStringExtra("to");

        Log.i("from", fromCountry);
        Log.i("to", toCountry);

        fromCountryTV = (TextView) findViewById(R.id.from_name);
        toCountryTV = (TextView) findViewById(R.id.to_name);
        fromCountryTV.setText(fromCountry);
        toCountryTV.setText(toCountry);


        this._client = Stitch.getDefaultAppClient();
        _mongoClient = this._client.getServiceClient(RemoteMongoClient.Factory, "mongodb-atlas");

        setupLogin();
    }

    private void initTodoView() {

        drugSearchEditText = findViewById(R.id.drug_search_ET);
        drugSearchButton = findViewById(R.id.drug_search_button);
        drugSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String drugToSearch = drugSearchEditText.getText().toString();
                makeQuery(drugToSearch);
            }
        });
        drugSearchRecyclerView = findViewById(R.id.drug_search_rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        drugSearchRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void makeQuery(String drugToSearch) {
        RemoteMongoCollection<Document> mongoClient = getItemsCollection();
        Document searchQuery = new Document();

        searchQuery.put(fromCountry + "-brands", drugToSearch);
        RemoteFindIterable<Document> cursor = mongoClient.find(searchQuery);

        cursor.into(new ArrayList<Document>())
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            //iterate through all the returned docs and add them to the local
                            //collection
                            for (Document doc : (ArrayList<Document>) task.getResult()) {

                                // localCollection.insertOne(doc);
                                DrugItem drug = new DrugItem(doc);

                                List<String> brands = null;
                                List<String> pictures = drug.getUSPicture();
                                if (toCountry.equals("US")) {
                                    brands = drug.getUSBrands();
                                    pictures = drug.getUSPicture();
                                } else if (toCountry.equals("China")) {
                                    brands = drug.getChinaBrands();
                                    pictures = drug.getFrancePicture();
                                } else if (toCountry.equals("Germany")) {
                                    brands = drug.getGermanyBrands();
                                    pictures = drug.getFrancePicture();
                                } else if (toCountry.equals("France")) {
                                    brands = drug.getFranceBrands();
                                    pictures = drug.getFrancePicture();
                                } else if (toCountry.equals("Italy")) {
                                    brands = drug.getItalyBrands();
                                    pictures = drug.getFrancePicture();
                                } else if (toCountry.equals("Spain")) {
                                    brands = drug.getSpainBrands();
                                    pictures = drug.getFrancePicture();
                                } else if (toCountry.equals("Thailand")) {
                                    brands = drug.getThailandBrands();
                                    pictures = drug.getFrancePicture();
                                }

                                List<DrugPreview> drugPreviews = new ArrayList<>();


                                for (int i = 0; i < brands.size(); i++) {
                                    String brand = brands.get(i);
                                    DrugPreview drugPreview;
                                    if (pictures != null) {
                                        String picture = pictures.get(i);
                                        drugPreview = new DrugPreview(brand, picture);
                                    }
                                    else {
                                        drugPreview = new DrugPreview(brand, "");
                                    }
                                    drugPreviews.add(drugPreview);
                                }

                                drugAdapter = new DrugAdapter(mContext, drugPreviews);
                                drugSearchRecyclerView.setAdapter(drugAdapter);

                            }
                        } else {
                            Log.e("Stitch", "Error adding item", task.getException());
                        }
                    }
                });
    }

    private RemoteMongoCollection<Document> getItemsCollection() {
        return _mongoClient.getDatabase("Drugs").getCollection("drugsformongo");
    }

    private void setupLogin() {
        if (_client.getAuth().isLoggedIn()) {
            initTodoView();
            return;
        }

        // Anonymous login
        _client.getAuth().loginWithCredential(new AnonymousCredential()).addOnCompleteListener(new OnCompleteListener<StitchUser>() {
            @Override
            public void onComplete(@NonNull final Task<StitchUser> task) {
                if (task.isSuccessful()) {
                    initTodoView();
                } else {
                    Log.e("MainActivity", "Error logging in anonymously", task.getException());
                }
            }
        });
    }
}
