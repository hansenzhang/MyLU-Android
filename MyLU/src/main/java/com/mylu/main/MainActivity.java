package com.mylu.main;

import android.content.Intent;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by hansen on 1/6/14.
 */
public class MainActivity extends FragmentActivity implements EventListFragment.Callbacks {
    private String[] mDrawerItems = {"Item 1", "Item 2", "Item 3"};
    private String[] mDrawerFragmentId = {"com.mylu.util.FragmentOne", "com.mylu.main.EventAddActivity", "com.mylu.main.EventListActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_1, mDrawerItems);

        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        final ListView navList = (ListView) findViewById(R.id.left_drawer);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
                drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        Intent intent = null;
                        switch (pos) {
                            case 0:
                                intent = new Intent(getBaseContext(), EventAddActivity.class);
                                break;
                            case 1:
                                intent = new Intent(getBaseContext(), EventListActivity.class);
                                break;
                            case 2:
                                intent = new Intent(getBaseContext(), ProfileActivity.class);
                                break;
                            default:

                        }
                        startActivity(intent);
                        //FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        //tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this, mDrawerFragmentId[pos]));
                        //tx.commit();
                    }
                });
                drawer.closeDrawer(navList);
            }
        });

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this, "com.mylu.main.EventListFragment"));
        tx.commit();
    }

    @Override
    public void onItemSelected(String id) {
        //This should load the fragment once I have it working....
    }
}
