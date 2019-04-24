package com.naldana.pokedesk.utilities;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.naldana.pokedesk.ContactsAdapter;
import com.naldana.pokedesk.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class FetchAll extends AsyncTask<Void,Void,Void> {
    private String data="";
    private String data2="";
    private String data3="";
    private String idpokemon="";
    public static final String POKEMON_API_BASE_URL = "https://pokeapi.co/api/v2/pokemon";
    public static ArrayList<Pokemon> mpokemons = new ArrayList<>();
    private static int lastContactId = 0;
    @Override
    protected Void doInBackground(Void... voids) {
            for (int num=0;num<=20;num++){
                idpokemon=""+ ++lastContactId;
                Uri builtUri = Uri.parse(POKEMON_API_BASE_URL)
                    .buildUpon()
                    .appendPath(idpokemon)
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line!=null ){
                    line=bufferedReader.readLine();
                    data=data+line;

                    JSONObject reader=new JSONObject(data);
                    JSONObject especie = reader.getJSONObject("species");
                    String nombrepokemon = especie.getString("name");
                    data2 = nombrepokemon;

                    JSONArray types1 = reader.getJSONArray("types");

                    if (types1.length()>1) {
                        JSONObject type = types1.getJSONObject(0);
                        JSONObject typex = types1.getJSONObject(1);
                        JSONObject type2 = type.getJSONObject("type");
                        JSONObject type2x = typex.getJSONObject("type");
                        String tipopokemon1 = type2.getString("name");
                        String tipopokemon2 = type2x.getString("name");
                        data3 = tipopokemon1+"/"+tipopokemon2;


                    }else
                    {
                        JSONObject type = types1.getJSONObject(0);
                        JSONObject type2 = type.getJSONObject("type");
                        String tipopokemon1 = type2.getString("name");
                        data3 = tipopokemon1;

                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
                mpokemons.add(new Pokemon(data2, data3));
                data="";
                data2="";
                data3="";
                idpokemon="";

            }


        return null;

        }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.mResultText.setText(this.data2);
        MainActivity.mType.setText(this.data3);
        MainActivity.rvContacts.setLayoutManager(new LinearLayoutManager(MainActivity.rvContacts.getContext()));


    }


}