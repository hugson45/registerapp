package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private Button pokaz_obrazek;
    private Button rejestruj;
    private EditText nazwa, email, haslo, ponowhaslo;
    private CheckBox zgoda;
    private RadioGroup rgGlowny;
    private RelativeLayout parent;

    private Spinner panstwa;



    public void showSnackbar() {
        Snackbar.make(parent, "Brak niektórych danych", Snackbar.LENGTH_LONG)
                .setAction("Ponów", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Sprawdz();
                    }
                })
                .setActionTextColor(Color.RED)
                .setTextColor(Color.YELLOW)
                .show();

    }
    public void showSnackbartrue() {
        Snackbar.make(parent, "Rejestracja zakonczona", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .setTextColor(Color.YELLOW)
                .show();

    }

    public boolean Sprawdz() {

        int checkedbutton = rgGlowny.getCheckedRadioButtonId();

        String strUserName = nazwa.getText().toString();
        String strEmail = email.getText().toString();
        String strPassword = haslo.getText().toString();
        String strRetPassword = ponowhaslo.getText().toString();
        String name = null;

        if (TextUtils.isEmpty(strUserName)) {
            nazwa.setError("Wpisz nazwe");
            return false;
        }
        if (TextUtils.isEmpty(strEmail)) {
            email.setError("Wpisz email");
            return false;
        }
        if (TextUtils.isEmpty(strPassword)) {
            haslo.setError("Wpisz haslo");
            return false;
        }
        if (TextUtils.isEmpty(strRetPassword)) {
            ponowhaslo.setError("Wpisz haslo ponownie");
            return false;
        }

        if (zgoda.isChecked()== false) {
            Toast.makeText(MainActivity.this, "Nie zaznaczyles zgody", Toast.LENGTH_SHORT).show();
            return false;
        }


        if(panstwa.getSelectedItem() == "") {
            Toast.makeText(this, "Wybierz państwo", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (checkedbutton == R.id.man) {
            Toast.makeText(MainActivity.this, "Mezczyzna", Toast.LENGTH_SHORT).show();
            return true;
        } else if (checkedbutton == R.id.woman) {
            Toast.makeText(this, "Kobieta", Toast.LENGTH_SHORT).show();
            return true;
        } else if (checkedbutton == R.id.other) {
            Toast.makeText(this, "Inne", Toast.LENGTH_SHORT).show();
            return true;
        } else if (checkedbutton == -1) {

            Toast.makeText(this, "Wybierz Płeć", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        rejestruj = findViewById(R.id.rejestruj);
        nazwa = findViewById(R.id.nazwa);
        email = findViewById(R.id.email);
        haslo = findViewById(R.id.haslo);
        ponowhaslo = findViewById(R.id.ponowhaslo);
        zgoda = findViewById(R.id.zgoda);
        pokaz_obrazek = findViewById(R.id.pokaz_obrazek);
        rgGlowny = findViewById(R.id.rgGlowny);
        parent = findViewById(R.id.parent);
        panstwa = findViewById(R.id.panstwa);

        ArrayList<String> miejscowosci = new ArrayList<>();
        miejscowosci.add("");
        miejscowosci.add("Polska");
        miejscowosci.add("Niemcy");
        miejscowosci.add("Litwa");
        miejscowosci.add("Czechy");
        miejscowosci.add("Ukraina");

        ArrayAdapter<String> panstwaAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                miejscowosci

        );
        panstwa.setAdapter(panstwaAdapter);



        pokaz_obrazek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Kliknales LOGO", Toast.LENGTH_SHORT).show();
            }
        });

        rejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Sprawdz() == true)
                {
                    showSnackbartrue();
                }else
                {
                    showSnackbar();
                }
               // Sprawdz();
               // showSnackbar();
               // showSnackbartrue();
            }
        });


    }
}