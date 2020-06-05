package com.example.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText alcoholPrice, gasPrice, alcoholKml, gasKml;
    private TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextInputEditTexts from view
        alcoholPrice = findViewById(R.id.alcoholPrice);
        gasPrice = findViewById(R.id.gasPrice);
        alcoholKml = findViewById(R.id.alcoholKml);
        gasKml = findViewById(R.id.gasKml);

        textResult = findViewById(R.id.textResult);

    }

    public void calculate(View view) {
        String alcoholPriceText = alcoholPrice.getText().toString();
        String gasPriceText = gasPrice.getText().toString();
        String alcoholKmlText = alcoholKml.getText().toString();
        String gasKmlText = gasKml.getText().toString();

        // if all fields are filled
        if(isFilled(alcoholPriceText) && isFilled(gasPriceText) && isFilled(alcoholKmlText) && isFilled(gasKmlText)) {
            Double alcoholPriceValue = stringToDouble(alcoholPriceText);
            Double gasPriceValue = stringToDouble(gasPriceText);
            Double alcoholKmlValue = stringToDouble(alcoholKmlText);
            Double gasKmlValue = stringToDouble(gasKmlText);

            /* First divide km/l of alcohol by km/l of gas knowing exactly the proportion
             * you can drive, with alcohol, compared with gas (ex: 0.68 means you can drive
             * 68% with alcohol of the 100% you would drive if you use gas
             * */
            Double exactProportionValue = alcoholKmlValue / gasKmlValue;


            /* if the alcohol value is less than the gas value multiplied with this exactPorportion
             * then, it worth to use gas, otherwise, it worth use gas
             * */
            if(alcoholPriceValue < gasPriceValue * exactProportionValue) {
                textResult.setText("You better use ALCOHOL, it's paying off!");
            } else {
                textResult.setText("You better use GAS, it's paying off!");
            }
        } else if(isFilled(alcoholPriceText) && isFilled(gasPriceText)) {
            Double alcoholPriceValue = stringToDouble(alcoholPriceText);
            Double gasPriceValue = stringToDouble(gasPriceText);
            // if alcoholPriceValue / gasPriceValue >= 0.7 is better to use gas
            if(alcoholPriceValue / gasPriceValue < 0.7) {
                textResult.setText("You better use ALCOHOL, it's paying off!");
            } else {
                textResult.setText("You better use GAS, it's paying off!");
            }
        } else {
            textResult.setText("Fill in at least all required fields first");
        }
        ScrollView s = findViewById(R.id.mainScrollView);
        s.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public Boolean isFilled(String value) {
        if (value == null || value.equals("")) return false;
        return true;
    }

    public Double stringToDouble(String s) {
        return Double.parseDouble(s);
    }
}