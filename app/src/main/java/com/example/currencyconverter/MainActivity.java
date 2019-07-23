package com.example.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner from, to;
    EditText amount;
    Button clear, swap, convert;
    TextView result;
    String[] currency;
    ArrayAdapter<String> spAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();

    }

    private void initializer() {
        currency = getResources().getStringArray(R.array.currency);
        from = (Spinner) findViewById(R.id.from);
        to = (Spinner) findViewById(R.id.to);
        amount = (EditText) findViewById(R.id.amount);
        clear = (Button) findViewById(R.id.clear);
        swap = (Button) findViewById(R.id.swap);
        convert = (Button) findViewById(R.id.convert);
        result = (TextView) findViewById(R.id.result);
        spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currency);
        from.setAdapter(spAdapter);
        to.setAdapter(spAdapter);
    }

    public void clear(View view) {
        amount.setText("");
        result.setText("");
        from.setSelection(0);
        to.setSelection(0);
    }

    public void swap(View view) {
        int index1 = from.getSelectedItemPosition();
        int index2 = to.getSelectedItemPosition();
        float value = Float.parseFloat(amount.getText().toString());
        float fRes = Float.parseFloat(result.getText().toString());

        from.setSelection(index2);
        to.setSelection(index1);

        result.setText(value+"");
        amount.setText(fRes+"");
    }

    public void convert(View view) {
        int index1 = from.getSelectedItemPosition();
        int index2 = to.getSelectedItemPosition();
        float value = Float.parseFloat(amount.getText().toString());

        float ratio[] = {1.0f, 73.14f, 0.69881f, 44.72f, 0.61095f, 0.93188f, 0.96680f, 80.55f};
        float fValue = value / ratio[index1] * ratio[index2];
        result.setText(fValue+"");
    }
}