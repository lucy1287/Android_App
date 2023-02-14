package com.example.inputoutputapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inputoutputapp.databinding.ActivityRootFileBinding;
import com.example.inputoutputapp.databinding.ActivityViewBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RootFileActivity extends AppCompatActivity
{
    private File file;
    private List myList;
    ListView listView;
    ActivityRootFileBinding binding;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityRootFileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myList = new ArrayList();
        listView  = findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, myList );
        listView.setAdapter(arrayAdapter);

        String rootSD = Environment.getExternalStorageDirectory().toString();
        file = new File( rootSD );
        File[] list = file.listFiles();

        for( int i=0; i<list.length; i++)
        {
            myList.add( list[i].getName());
        }
        arrayAdapter.notifyDataSetChanged();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                File temp_file = new File(file, (String) myList.get(position));

                if( !temp_file.isFile()) {
                    file = new File(file, (String) myList.get(position));
                    File[] list = file.listFiles();

                    myList.clear();

                    for (int i = 0; i < list.length; i++) {
                        myList.add(list[i].getName());
                    }
                    arrayAdapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

//    protected void onListItemClick(ListView l, View v, int position, long id)
//    {
//        super.onListItemClick(l, v, position, id);
//
//        File temp_file = new File(file, (String) myList.get(position));
//
//        if( !temp_file.isFile())
//        {
//            file = new File( file, (String) myList.get( position ));
//            File list[] = file.listFiles();
//
//            myList.clear();
//
//            for( int i=0; i< list.length; i++)
//            {
//                myList.add( list[i].getName() );
//            }
//            Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show();
//            listView.setAdapter(new ArrayAdapter(this,
//                    android.R.layout.activity_list_item, myList ));
//
//        }
//
//    }


    @Override
    public void onBackPressed() {
        String parent = file.getParent().toString();
        file = new File( parent ) ;
        File[] list = file.listFiles();
        listView = findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, myList );
        listView.setAdapter(arrayAdapter);

        myList.clear();

        for( int i=0; i< list.length; i++)
        {
            myList.add( list[i].getName() );
        }
        arrayAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                File temp_file = new File(file, (String) myList.get(position));

                if( !temp_file.isFile()) {
                    file = new File(file, (String) myList.get(position));
                    File[] list = file.listFiles();

                    myList.clear();

                    for (int i = 0; i < list.length; i++) {
                        myList.add(list[i].getName());
                    }
                    arrayAdapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

