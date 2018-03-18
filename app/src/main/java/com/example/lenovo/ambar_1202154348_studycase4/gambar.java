package com.example.lenovo.ambar_1202154348_studycase4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class gambar extends AppCompatActivity {
    EditText source; ImageView gambarnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);
        //Mendefinisikan gambar dan edittext
        source  = findViewById(R.id.sourcegambar);
        gambarnya = findViewById(R.id.gambarnya);
    }

    //method ketika tombol ditekan
    public void carigambar(View view) {
        //Menloading gambar dari internet menuju imageview
        Picasso.with(gambar.this).load(source.getText().toString())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(gambarnya);
    }
}


