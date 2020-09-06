package com.example.controlesbasicos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    TabHost tbhConversores;
    ValoresTodos misvalores=new ValoresTodos() ;
    EditText txtcantidad;
    EditText txtUnidades;
    EditText txtUnidades2;
    EditText txtcantidadArea;


    TextView ResultadoTV;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        txtcantidad = findViewById(R.id.txtCantidad);
        txtUnidades = findViewById(R.id.txtUnidades);
        txtUnidades2 = findViewById(R.id.txtUnidades2);
        txtcantidadArea = findViewById(R.id.txtCantidadArea);

        ResultadoTV = findViewById(R.id.ResultadoTV);
        tbhConversores = findViewById(R.id.tbhConversores);
        tbhConversores.setup();


        tbhConversores.addTab(tbhConversores.newTabSpec("Universal").setContent(R.id.tabMulticonver).setIndicator("",getDrawable(R.drawable.peque)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Area").setContent(R.id.tabArea).setIndicator("",getDrawable(R.drawable.area)));

        tbhConversores.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

                txtcantidad.getText().clear();
                txtUnidades.getText().clear();
                txtUnidades2.getText().clear();
                txtcantidadArea.getText().clear();

                ResultadoTV.setText("");
            }
        });
    }


    //Boton de CONVERSOR Area

    public void Convertir(View view){
        try {

            TextView tmpVal = (TextView) findViewById(R.id.txtCantidadArea);
            double cantidad = Double.parseDouble(tmpVal.getText().toString());


            int de = 0, a = 0;
            double resp = 0;
            switch (tbhConversores.getCurrentTabTag()){

                case "Area":
                    misvalores.val   = (Spinner) findViewById(R.id.AreaActualSP);
                    de = misvalores.val .getSelectedItemPosition();
                    misvalores.val  = (Spinner) findViewById(R.id.AreaCambiarSP);
                    a = misvalores.val .getSelectedItemPosition();
                    resp = misvalores.datos [0][a] / misvalores.datos [0][de];
                    break;

            }

            tmpVal = (TextView) findViewById(R.id.ResultadoTV);
            tmpVal.setText(String.format("Por la cantidad de: "+ cantidad  + " Usted recivira " + resp  ));
        }catch (Exception err){
            TextView temp = (TextView) findViewById(R.id.ResultadoTV);
            Toast.makeText(getApplicationContext(),"Error: Ingrese la cantidad",Toast.LENGTH_LONG).show();

        }
    }
}