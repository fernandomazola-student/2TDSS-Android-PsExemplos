package com.example.logonrm.ps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spArea;
    private TextView lblCidade;
    private TextView lblSalario;
    private CheckBox chkInteressado;
    private EditText edtEmail;
    private Button btnEnviar;

    private List<VagaBean> vagas;
    private ArrayAdapter<VagaBean> adpVaga;
    private VagaBean selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spArea = (Spinner) findViewById(R.id.spArea);
        lblCidade = (TextView) findViewById(R.id.lblCidade);
        lblSalario = (TextView) findViewById(R.id.lblSalario);
        chkInteressado = (CheckBox) findViewById(R.id.chkInteressado);
        edtEmail = (EditText) findViewById(R.id.txtEmail);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        edtEmail.setVisibility(View.INVISIBLE);
        btnEnviar.setVisibility(View.INVISIBLE);
        spArea.setOnItemSelectedListener(this);
        chkInteressado.setChecked(false);
        criarAreas();
        carregarAreas();
    }

    private void carregarAreas() {
        adpVaga = new ArrayAdapter<VagaBean>(this,android.R.layout.simple_spinner_item,this.vagas);
        spArea.setAdapter(adpVaga);
    }

    private void criarAreas() {
        JSONArray array = null;
        try {
            array = new JSONArray(SemestralServer.getAreas());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        vagas = new ArrayList<VagaBean>();

        for (int i =0; i<array.length(); i++) {
            try {
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("id");
                String area = obj.getString("area");
                String nivel = obj.getString("nivel");
                vagas.add(new VagaBean(id, area, nivel));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void interessado(View v) {
        if(chkInteressado.isChecked()) {
            edtEmail.setVisibility(v.VISIBLE);
            btnEnviar.setVisibility(v.VISIBLE);
        } else {
            edtEmail.setVisibility(v.INVISIBLE);
            btnEnviar.setVisibility(v.INVISIBLE);
        }

    }

    public void enviar(View v) {
        adpVaga.remove(this.selected);
        chkInteressado.setChecked(false);
        edtEmail.setText("");
        edtEmail.setVisibility(v.INVISIBLE);
        btnEnviar.setVisibility(v.INVISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        VagaBean vaga = (VagaBean) parent.getItemAtPosition(position);
        this.selected = vaga;

        try {

            JSONObject obj = new JSONObject(SemestralServer.getDetalhes(vaga.getId()));
            String cidade = obj.getString("cidade");
            double salario = obj.getDouble("salario");
            lblCidade.setText(cidade);
            lblSalario.setText(String.valueOf(salario));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        VagaBean vaga = (VagaBean) parent.getSelectedItem();
        this.selected = vaga;
    }
}
