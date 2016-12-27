package in.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {
    TextView c,t,p,h,tmin,tmax,m;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WEATHER");
        c=(TextView) findViewById(R.id.city) ;
        t=(TextView) findViewById(R.id.temp) ;
        p=(TextView) findViewById(R.id.pressure) ;
        h=(TextView) findViewById(R.id.humidity) ;
        tmin=(TextView) findViewById(R.id.temp_min) ;
        tmax=(TextView) findViewById(R.id.temp_max) ;
        m=(TextView) findViewById(R.id.main) ;



        new Weather_asynk().execute();

    }



    private class Weather_asynk extends AsyncTask<String,String,JSONObject>{




        @Override
        protected JSONObject doInBackground(String... strings) {
            key=getIntent().getExtras().getString("value");
            Log.i("name"," "+key);




            String url="http://api.openweathermap.org/data/2.5/weather?q="+key+"&appid="+"YOUR APP ID";
            Log.d("URL",""+url);

            JSONObject json=JsonParsing.getJSONfromURL(url);
            Log.d("JSON",""+json);
            return json;


        }

        protected void onPostExecute(JSONObject jsonObj) {
            try {
                JSONArray jsonArray = jsonObj.getJSONArray("weather");
                Log.d("jsonarray", ">" + jsonArray);
                int length=jsonArray.length();
                Log.d("length",""+length);
                String a=key.toUpperCase();
                Log.d("yahooo",""+a);

                c.setText(a);


                for(int i=0;i<length;i++){
                    JSONObject json=jsonArray.getJSONObject(i);
                    String description=json.getString("main");
                    //Log.d("description", ">" + description);
                    m.setText(description);



                }

                JSONObject json2=jsonObj.getJSONObject("main");
                Log.i("main",""+json2);
                int l=json2.length();
                Log.d("len",""+l);
                String temp=json2.getString("temp");
                //Log.d("temp",""+temp);
                t.setText(temp+" 'C");
                String pressure=json2.getString("pressure");
                // Log.d("temp",""+pressure);
                p.setText(pressure+" hpa");
                String humidity=json2.getString("humidity");
                //Log.d("temp",""+humidity);
                h.setText(humidity+" %");
                String temp_min=json2.getString("temp_min");
                //Log.d("temp",""+temp_min);
                tmin.setText(temp_min+" 'C");
                String temp_max=json2.getString("temp_max");
                //Log.d("temp",""+temp_max);
                tmax.setText(temp_max+" 'C");








            }catch (Exception e){
                e.printStackTrace();
            }
            super.onPostExecute(jsonObj);
        }


    }

}



