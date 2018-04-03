package edu.sfsu.com.activitiesdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private int counter = 0;
    private Button launchActivityButton;
    private Button counterButton;
    private TextView textView;
    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = getIntent().getIntExtra("counter", 0);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(Integer.toString(counter));

        launchActivityButton = (Button) findViewById(R.id.launchActivityButton2);
        counterButton = findViewById(R.id.counterButton2);

        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textView.setText(Integer.toString(counter));
            }
        });

        launchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                myIntent.putExtra("counter", counter);
                startActivityForResult(myIntent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                counter = data.getIntExtra("counter", counter);
                textView.setText(Integer.toString(counter));
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("counter", counter);
        setResult(RESULT_OK, intent);
        finish();
    }
}
