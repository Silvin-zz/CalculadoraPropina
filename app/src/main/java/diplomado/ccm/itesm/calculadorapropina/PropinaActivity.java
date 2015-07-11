package diplomado.ccm.itesm.calculadorapropina;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class PropinaActivity extends Activity {


    /** Variables **/

    private double   currentBillTotal;
    private int      currentCustomPercent;
    private EditText tip10EditText;
    private EditText tip15EditText;
    private EditText tip20EditText;
    private EditText total10EditText;
    private EditText total15EditText;
    private EditText total20EditText;
    private EditText tipCustomEditText;
    private EditText totalCustomEditText;
    private EditText billEditText;
    private TextView customTextView;
    private SeekBar  customSeekBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propina);
        this.initVariables();
    }



    /** Initialize variables **/
    private void initVariables(){

        tip10EditText           = (EditText) findViewById(R.id.tip10EditText);
        tip15EditText           = (EditText) findViewById(R.id.tip15EditText);
        tip20EditText           = (EditText) findViewById(R.id.tip20EditText);
        total10EditText         = (EditText) findViewById(R.id.total10EditText);
        total15EditText         = (EditText) findViewById(R.id.total15EditText);
        total20EditText         = (EditText) findViewById(R.id.total20EditText);
        tipCustomEditText       = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText     = (EditText) findViewById(R.id.totalCustomEditText);
        billEditText            = (EditText) findViewById(R.id.billEditText);
        customSeekBar           = (SeekBar)  findViewById(R.id.customSeekBar);
        customTextView          = (TextView) findViewById(R.id.customTipTextView);

        currentBillTotal        = 0.0;
        currentCustomPercent    = 18;

        billEditText.addTextChangedListener(billEditTextWatcher);
        customSeekBar.setOnSeekBarChangeListener(customSeekListener);
        Log.d("Calculadora", "Inicializando Variables");

    }


    TextWatcher billEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try{

                Log.d("Calculadora", "Calculando el monto: " + s.toString());
                currentBillTotal  = Double.parseDouble(s.toString());

            }catch (NumberFormatException e){

                currentBillTotal  = 0.0;
            }

            updateStandar();
            updateCustom();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    SeekBar.OnSeekBarChangeListener  customSeekListener  = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            Log.d("Calculadora", "Procesando progress = " + String.valueOf(progress));
            currentCustomPercent = progress;
            updateCustom();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    private void updateStandar() {

        Log.d("Calculadora", "Realizando calculo de valores");
        double tenPercentTip        = currentBillTotal * .1;
        double tenPercentTotal      = currentBillTotal + tenPercentTip;

        double fifteenPercentTip    = currentBillTotal * .15;
        double fifteenPercentTotal  = currentBillTotal + fifteenPercentTip;

        double twentyPercentTip     = currentBillTotal * .20;
        double twentyPercentTotal   = currentBillTotal + twentyPercentTip;



        tip10EditText.setText(String.format("%.02f", tenPercentTip));
        total10EditText.setText(String.format("%.02f", tenPercentTotal));

        tip15EditText.setText(String.format("%.02f", fifteenPercentTip));
        total15EditText.setText(String.format("%.02f", fifteenPercentTotal));

        tip20EditText.setText(String.format("%.02f", twentyPercentTip));
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));

    }






    private void updateCustom() {

        Log.d("Calculadora", "Realizando calculo de totales");
        customTextView.setText(currentCustomPercent + " %" );
        double customTipAmount      = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount    = currentBillTotal + customTipAmount;
        tipCustomEditText.setText(String.format("%.02f", customTipAmount));
        totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    }






}
