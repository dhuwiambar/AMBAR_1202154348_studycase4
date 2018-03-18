package com.example.lenovo.ambar_1202154348_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Mahasiswa extends AppCompatActivity {
    ListView namamahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);
        //Mendefinisikan listview
        namamahasiswa = findViewById(R.id.namamahasiswa);
    }

    //Method ketika tombol ditekan
    public void loadmahasiswa (View view) {
        //Memulai AsyncTask
        new getData(namamahasiswa).execute();
    }

    //Sub-class Asynctask
    class getData extends AsyncTask<String, Integer, String> {
        ListView namamahasiswa;
        ArrayAdapter adapternya;
        ArrayList<String> listItems;
        ProgressDialog dlg;

        //Constructor ketika AsyncTask diinisialisasi
        public getData(ListView namamahasiswa) {
            this.namamahasiswa = namamahasiswa;
            dlg = new ProgressDialog(Mahasiswa.this);
            listItems = new ArrayList<>();
        }

        //Method sebelum AsyncTask dimulai
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Menampilkan ProgressDialog
            dlg.setTitle("Loading Nama");
            dlg.setIndeterminate(false);
            dlg.setCancelable(true);

            dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dlg.dismiss();
                    getData.this.cancel(true);
                }
            });
            dlg.setMax(100);
            dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dlg.setProgress(0);
            dlg.show();
        }

        //Method ketika AsynTask dilakukan
        @Override
        protected String doInBackground(String... strings) {
            //Membuat adapter
            adapternya = new ArrayAdapter<>(Mahasiswa.this, android.R.layout.simple_list_item_1, listItems);
            //Menyimpan array pada variabel
            String [] mahasiswa = getResources().getStringArray(R.array.Mahasiswa);
            //Menyimpan array ke dalam
            for(int i=0; i<mahasiswa.length;i++){
                final long percentage = 100L*i/mahasiswa.length;
                final String namanya = mahasiswa[i];
                try{
                    Runnable changingmessage = new Runnable() {
                        @Override
                        public void run() {
                            dlg.setMessage((int)percentage+"% - Adding "+namanya);
                        }
                    };
                    runOnUiThread(changingmessage);
                    Thread.sleep(250);
                    listItems.add(mahasiswa[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            namamahasiswa.setAdapter(adapternya);
            dlg.dismiss();
        }

    }
}

