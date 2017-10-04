package com.myapplication.firs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

public class MainActivity extends Activity {
    private final static String FILENAME = "sample.txt";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText=findViewById(R.id.editText);
            try {
                InputStream inputStream = openFileInput(FILENAME);

                if (inputStream != null) {
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isr);
                    String line;
                    StringBuilder builder = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        builder.append(line + "\n");
                    }

                    inputStream.close();
                    mEditText.setText(builder.toString());
                }
            } catch (Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
    }

    public void close(View view) {
        mEditText.setText(mEditText.getText() + "!");
        this.finish();
    }

    public void save(View view) {
        fileSave();
    }

    private void fileSave(){
        try {
            OutputStream outputStream = openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(mEditText.getText().toString());
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
