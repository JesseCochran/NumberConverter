package com.example.bihextextconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView textResult;
    private TextView decResult;
    private TextView hexResult;
    private TextView binaryResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        textResult = findViewById(R.id.textResult);
        decResult = findViewById(R.id.decimalResult);
        hexResult = findViewById(R.id.hexDecimalResult);
        binaryResult = findViewById(R.id.binaryResult);
        Spinner conversionSpinner = findViewById(R.id.conversionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.conversion_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);

        conversionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle spinner item selection if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }
//    private void displayResults(String text, String hexadecimal, String binary) {
//        textResult.setText("Text: " + text);
//        hexResult.setText("Hexadecimal: " + hexadecimal);
//        binaryResult.setText("Binary: " + binary);
//    }

    public void convert(View view) {
        String input = inputText.getText().toString();

        // Get the selected conversion type from the spinner
        String conversionType = ((Spinner) findViewById(R.id.conversionSpinner)).getSelectedItem().toString();

        // Convert based on the selected type
        if ("Text".equals(conversionType)) {
            // Do nothing, text is already entered
            textResult.setText("Text: " + input);
        } else if ("Decimal".equals(conversionType)) {
            convertToDecimal(input);
        } else if ("Hexadecimal".equals(conversionType)) {
            convertToHexadecimal(input);
        } else if ("Binary".equals(conversionType)) {
            convertToBinary(input);
        }
    }

    private void convertToDecimal(String input) {
        try {
            int decimalValue = Integer.parseInt(input, 10);
            decResult.setText("Decimal: " + decimalValue);

            // Convert to Hexadecimal
            hexResult.setText("Hexadecimal: " + Integer.toHexString(decimalValue).toUpperCase());

            // Convert to Binary
            binaryResult.setText("Binary: " + Integer.toBinaryString(decimalValue));

            // Convert to Text
            textResult.setText("Text: " + input);
        } catch (NumberFormatException e) {
            decResult.setText("Invalid Input");
            hexResult.setText("");
            binaryResult.setText("");
            textResult.setText("");
        }
    }

    private void convertToHexadecimal(String input) {
        try {
            int decimalValue = Integer.parseInt(input, 16);
            decResult.setText("Decimal: " + decimalValue);

            // Convert to Binary
            binaryResult.setText("Binary: " + Integer.toBinaryString(decimalValue));

            // Convert to Text
            textResult.setText("Text: " + input);
        } catch (NumberFormatException e) {
            decResult.setText("");
            binaryResult.setText("");
            textResult.setText("");
        }
    }

    private void convertToBinary(String input) {
        try {
            int decimalValue = Integer.parseInt(input, 2);
            decResult.setText("Decimal: " + decimalValue);

            // Convert to Hexadecimal
            hexResult.setText("Hexadecimal: " + Integer.toHexString(decimalValue).toUpperCase());

            // Convert to Text
            textResult.setText("Text: " + input);
        } catch (NumberFormatException e) {
            decResult.setText("");
            hexResult.setText("");
            textResult.setText("");
        }
    }

}

//        }
//    }

//    private String textToBinary(String text) {
//        StringBuilder binary = new StringBuilder();
//        for (char character : text.toCharArray()) {
//            String binaryString = Integer.toBinaryString(character);
//            // Pad with zeros to ensure 8 bits for each character
//            binary.append(String.format("%8s", binaryString).replace(' ', '0'));
//        }
//        return binary.toString();
//    }
//}