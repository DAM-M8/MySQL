package com.example.lauralopez.accesbd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class AccesBD extends AppCompatActivity {

    private static final String url = "jdbc:mysql://192.168.121.3:3306/BDProva"; //Revisar la IP en cada PC. S'ha d'indicar la IP on hi ha el MySQL
    private static final String user = "usuari";    //Abans d'executar l'app cal verificar que tinguem el servei de MySQL en execució
    private static final String pass = "123456";
    Button btnFetch,btnClear;
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_acces_bd);
        txtData = (TextView) this.findViewById(R.id.txtData);
        btnFetch = (Button) findViewById(R.id.btnFetch);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnFetch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                ConnectMySql connectMySql = new ConnectMySql();
                connectMySql.execute("");
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtData.setText("");
            }
        });

    }

    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(AccesBD.this, "Esperant resposta...", Toast.LENGTH_SHORT)
                    .show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Accés correcte a la BD");

                String result = "Accés correcte a la BD\n";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select nom from Programadors where pais like 'Estats Units'");

                while (rs.next()) {
                    result += rs.getString(1).toString() + "\n";
                }
                res = result;
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            txtData.setText(result);
        }
    }

}
