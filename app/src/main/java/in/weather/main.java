package in.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONObject;

public class main extends Activity {
    EditText ed;
    String s;
    ImageButton bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("HOME");


        ed = (EditText) findViewById(R.id.edit);
        bt = (ImageButton) findViewById(R.id.button);
    }

    public void submit(View v) {
        s = ed.getText().toString();
        Log.d("s", "" + s);
        Intent i=new Intent(main.this,MainActivity.class);
        i.putExtra("value",s);
        startActivity(i);
    }
}