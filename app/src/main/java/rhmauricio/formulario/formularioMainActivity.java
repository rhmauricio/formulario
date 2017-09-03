package rhmauricio.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.PendingIntent.getActivity;

public class formularioMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button  button;
    EditText editText;
    Spinner spinner;
    Spinner opciones;
    boolean ban=false,ban1=false,ban2=false;
    private String nombre,contrasena,errorcontrasena, mail,sexo="",ciudad="",fecha,hobbies="";
    private EditText ename, econtraseña,ercontraseña,email;
    private TextView tresult;
    private RadioButton rman,rwoman;
    private CheckBox crun,cnadar,ccicla,ctv;
    private Button bsave;
    private  String verify="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_main);
        bsave=(Button)findViewById(R.id.bsave);
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        button.setOnClickListener(this);
        bsave.setOnClickListener(this);
        opciones=(Spinner)findViewById(R.id.spinner);
        ename = (EditText) findViewById(R.id.ename);
        ercontraseña = (EditText) findViewById(R.id.ercontraseña);
        econtraseña = (EditText) findViewById(R.id.econtraseña);
        email = (EditText) findViewById(R.id.email);
        rman = (RadioButton) findViewById(R.id.rman);
        rwoman = (RadioButton) findViewById(R.id.rwoman);
        crun = (CheckBox) findViewById(R.id.crun);
        cnadar = (CheckBox) findViewById(R.id.cnadar);
        ccicla = (CheckBox) findViewById(R.id.ccicla);
        ctv = (CheckBox) findViewById(R.id.ctv);

        rman.setOnClickListener(this);
        rwoman.setOnClickListener(this);
        crun.setOnClickListener(this);
        ccicla.setOnClickListener(this);
        cnadar.setOnClickListener(this);
        ctv.setOnClickListener(this);

        final ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opciones.setAdapter(adapter1);

        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               ciudad=adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    @Override
    public void onClick(View view) {

        AlertDialog ventana = new AlertDialog.Builder(this).create();

        ventana.setTitle("saved data");

        if (view==button){
        final Calendar c = Calendar.getInstance();
        int año = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    editText.setText(i2+"/"+(i1+1)+"/"+i);
            }
        }
        ,dia,mes,año);
        datePickerDialog.show();

    }else if(view==bsave){

            verify = email.getText().toString();
            if(editText.getText().toString().isEmpty() | ename.getText().toString().isEmpty() | econtraseña.getText().toString().isEmpty() | ercontraseña.getText().toString().isEmpty()
                    | sexo.isEmpty() | hobbies.isEmpty() | email.getText().toString().isEmpty() | ciudad.equals("city")  ){
                ban=false;
                ban1=false;

            }

            if (verify.contains(".com") && verify.contains("@")) {
                ban=true;
            }else if( verify.contains("@") && verify.contains(".co")){
                ban=true;
            } else{
                ban=false;
                Toast t= Toast.makeText(getApplication().getApplicationContext(),"Email error\n",Toast.LENGTH_SHORT);
                t.show();
            }


            if(rman.isChecked()){
                sexo="Man";
            }else if(rwoman.isChecked()){
                sexo="Woman";
            }

            contrasena = econtraseña.getText().toString();
            errorcontrasena = ercontraseña.getText().toString();
            if(contrasena.equals(errorcontrasena)){
                ban1=true;

            }else {
                contrasena="";
                Toast t= Toast.makeText(getApplication().getApplicationContext(),"password error",Toast.LENGTH_SHORT);
                t.show();
                ban1=false;
            }

            if (crun.isChecked()) {
                if(hobbies.contains("Run")){
                    hobbies =hobbies.replace("Run","");
                }
                hobbies += "Run";
                ban2=true;
            }
            if (ccicla.isChecked()) {
                if(hobbies.contains( "Cycling")){
                    hobbies="";
                }
                hobbies += "Cycling ";
                ban2=true;
            }
            if (cnadar.isChecked()) {
                if(hobbies.contains("Swim")){
                    hobbies="";
                }
                hobbies += "Swim ";
                ban2=true;
            }
            if (ctv.isChecked()) {
                if(hobbies.contains("Watch TV")){
                    hobbies="";
                }
                hobbies += "Watch TV ";
                ban2=true;
            }

            if(ban && ban1 && ban2) {
                nombre = ename.getText().toString();
                fecha = editText.getText().toString();
                mail = email.getText().toString();
                ventana.setMessage("Name: " + nombre + " \nPassword: " + contrasena + "\nEmail: " + mail + "\nGender: " + sexo + "\nDate of birth" + fecha + "\nCity: " + ciudad + "\nHobbies: " + hobbies);
                ventana.show();
                hobbies="";
                ban=false;
                ban1=false;

            }else{
                Toast t= Toast.makeText(getApplication().getApplicationContext(),"Verify the data ",Toast.LENGTH_LONG);
                t.show();
                ban=false;
                ban1=false;
            }
            }


    }
}

