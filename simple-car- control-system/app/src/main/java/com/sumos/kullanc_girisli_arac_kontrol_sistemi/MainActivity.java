package com.sumos.kullanc_girisli_arac_kontrol_sistemi;


//verileri listeleme, simle ve düzenleme işlemini yapamadım.

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database ;
    private EditText Marka , Model, Fiyat , Yil;
    private Button Ekle, Sil, Goruntule;
    private String Marka_Adi,Model_Adi, Fiyat_bilgisi, Uretim_Yili;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Marka = (EditText) findViewById(R.id.markatxt);
        Model = (EditText) findViewById(R.id.modeltxt);
        Fiyat = (EditText) findViewById(R.id.fiyattxt);
        Yil = (EditText) findViewById(R.id.yiltxt);
        Ekle= (Button) findViewById(R.id.eklebtn);
        Sil= (Button) findViewById(R.id.silbtn);
        Goruntule= (Button) findViewById(R.id.araclarbtn);

    }

    public void  sqlClick (View v) {

        switch (v.getId()){

            case R.id.eklebtn:
                Marka_Adi = Marka.getText().toString();
                Model_Adi = Model.getText().toString();
                Fiyat_bilgisi = Fiyat.getText().toString();
                Uretim_Yili = Yil.getText().toString();

                if (!TextUtils.isEmpty(Marka_Adi)) {
                    if (!TextUtils.isEmpty(Model_Adi)) {
                        if (!TextUtils.isEmpty(Fiyat_bilgisi)) {
                            if (!TextUtils.isEmpty(Uretim_Yili)) { //kaydet


                                try {

                                    database = this.openOrCreateDatabase("arac_kontrol",MODE_PRIVATE,null);

                                    database.execSQL("CREATE TABLE IF NOT EXISTS ARAC_BILGILERI (id INTEGER PRIMARY KEY,Marka_Adi VARCHAR , Model_Adi VARCHAR , Fiyat VARCHAR , Uretim_Yili VARCHAR )");


                                    String sqlSorgusu = "INSERT INTO ARAC_BILGILERI (Marka_Adi,Model_Adi ,Fiyat,Uretim_Yili) VALUES(?,?,?,?)";
                                    SQLiteStatement statement = database.compileStatement(sqlSorgusu);
                                    statement.bindString(1,Marka_Adi);
                                    statement.bindString(2,Model_Adi);
                                    statement.bindString(3,Fiyat_bilgisi);
                                    statement.bindString(4,Uretim_Yili);
                                    statement.execute();

                                    Toast.makeText(getApplicationContext(),"kayıt Başarıyla Eklendi", Toast.LENGTH_LONG).show();

                                }
                                catch (Exception e){
                                    e.printStackTrace();

                                }

                            }else Toast.makeText(getApplicationContext(),"Lütfen Yıl Bilgisi Giriniz.", Toast.LENGTH_LONG).show();
                        }else Toast.makeText(getApplicationContext(),"Lütfen Fiyat Bilgisi Giriniz.", Toast.LENGTH_LONG).show();
                    }else Toast.makeText(getApplicationContext(),"Lütfen Model Bilgisi Giriniz.", Toast.LENGTH_LONG).show();

                } else Toast.makeText(getApplicationContext(),"Lütfen Marka Bilgisi Giriniz.", Toast.LENGTH_LONG).show();
                Ekle.setEnabled(true);

                break;

                case R.id.araclarbtn:
                   Button Goruntule = (Button)findViewById(R.id.araclarbtn);
                   Goruntule.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Goruntule = new Intent(MainActivity.this , araclar.class);
                           startActivity(Goruntule);
                       }
                   });





        }}



}

