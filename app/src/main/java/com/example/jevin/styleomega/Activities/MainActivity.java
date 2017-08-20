package com.example.jevin.styleomega.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.jevin.styleomega.Database.DBHandler;
import com.example.jevin.styleomega.Model.Item;
import com.example.jevin.styleomega.Others.ItemAdapter;
import com.example.jevin.styleomega.R;

public class MainActivity extends BaseActivity {

    DBHandler db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    populateItems();
                    return true;

                case R.id.navigation_cart:
                    populateItemCart();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        populateItems();

    }

    public void populateItems(){

        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        pop();

        ItemAdapter adapter=new ItemAdapter(this, db.getAllItems());
        rv.setAdapter(adapter);
    }

    public void pop(){
        db = new DBHandler(this);

        Item i = new Item("1","shirt",100);
        db.addItem(i);

        Item i2 = new Item("2","short",200);
        db.addItem(i2);

       /* Item i3 = new Item("13","shirt",100);
        db.addItem(i3);

        Item i4 = new Item("24","short",200);
        db.addItem(i4);

        Item i23 = new Item("32","short",200);
        db.addItem(i23);

        Item i33 = new Item("133","shirt",100);
        db.addItem(i33);

        Item i43 = new Item("234","short",200);
        db.addItem(i43);*/

    }

    public void populateItemCart(){

/*
        String [] list = {"Shirt" , "Short" , "Dress"};

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ListView listView = (ListView) findViewById(R.id.listViewItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
            }
        });
*/

    }

}
