package com.naldana.pokedesk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.naldana.pokedesk.utilities.Fetch;
import com.naldana.pokedesk.utilities.FetchAll;

public class MainActivity extends AppCompatActivity {

    public static EditText mPokemonNumber;
    public static Button mSearchButton;
    public static TextView mResultText;
    public static TextView mType;
    public static RecyclerView rvContacts;
    public static ContactsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        FetchAll proccess=new FetchAll();
        proccess.execute();
        adapter = new ContactsAdapter(FetchAll.mpokemons);
        rvContacts.setAdapter(adapter);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fetch process= new Fetch();
                process.execute();
            }
        });
        }






    void bindView() {
        mPokemonNumber = findViewById(R.id.et_pokemon_number);
        mSearchButton = findViewById(R.id.bt_search_pokemon);
        mResultText = findViewById(R.id.tv_result);
        mType = findViewById(R.id.tv_pokemon_type);
        rvContacts = findViewById(R.id.rv_pokemon_list);

    }



}