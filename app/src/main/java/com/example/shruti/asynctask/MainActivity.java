package com.example.shruti.asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btngenerate, btncancel;
    TextView op;
    EditText temp, hum, activity, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngenerate=(Button) findViewById(R.id.button);
        btncancel= (Button) findViewById(R.id.button2);
        temp= (EditText) findViewById(R.id.editText);
        hum= (EditText) findViewById(R.id.editText2);
        activity= (EditText) findViewById(R.id.editText3);
        count= (EditText) findViewById(R.id.editText4);
        op= (TextView) findViewById(R.id.textView10);
        op.setMovementMethod(new ScrollingMovementMethod(){

        });

        btngenerate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RandomGenerator num= new RandomGenerator(temp,hum,activity,op,count);
                num.execute();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private class RandomGenerator extends AsyncTask<Void, String, Void> {
       // ProgressDialog prog;
        EditText temp, hum, activity;
        TextView op;
        float t, h, a;
        int n;

        RandomGenerator(EditText temp, EditText hum, EditText activity, TextView op, EditText n){
            this.temp= temp;
            this.hum= hum;
            this.activity= activity;
            this.op= op;
            this.n= Integer.parseInt(n.getText().toString());
        }

        @Override
        protected Void doInBackground(Void... p) {
            Void val=null;
            try {
                for (int i=1; i<=n; i++){
                    t= 10+ (float)(Math.random()*(100-10)+1);
                    h= 20+(float)(Math.random()*(100-20)+1);
                    a= 50+ (int)(Math.random()*(250-50)+1);
                    String s= "Output"+i+":\n Temperature:"+t+"F\n Humidity:" +h+ "%\n Activity:"+a+"\n";
                    publishProgress(s);
                    try{
                        Thread.sleep(1000);
                    }
                    catch (Exception e){

                    }

                }
            }
            catch (Exception e){

            }
            return val;
        }

        @Override
        protected void onPreExecute(){
            //prog= ProgressDialog.show(MainActivity.this,"Progress Dialog","Display");
        }


        @Override
        public void onProgressUpdate(String...p){
            temp.setText(Float.toString(t)+"F");
            hum.setText(Float.toString(h)+"%");
            activity.setText(Float.toString(a));
            op.append(p[0]);
            super.onProgressUpdate(p);
        }
    }

}
