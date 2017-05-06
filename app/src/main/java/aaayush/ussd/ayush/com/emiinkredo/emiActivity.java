package aaayush.ussd.ayush.com.emiinkredo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class emiActivity extends AppCompatActivity {

    private EditText mTitleField;
    private EditText mBodyField;
    private EditText text;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);

        mTitleField = (EditText) findViewById(R.id.field_title);
        mBodyField = (EditText) findViewById(R.id.destination);
        tv=(TextView)findViewById(R.id.tv);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    public void calculate(){

        final String title = mTitleField.getText().toString();
        final String body = mBodyField.getText().toString();

        if (TextUtils.isEmpty(title)) {
            mTitleField.setError("REQUIRED");
            return;
        }

        if (Integer.parseInt(title)>9999999){
            mTitleField.setError("sum too large");
            return;
        }

        if (TextUtils.isEmpty(body)) {
            mTitleField.setError("REQUIRED");
            return;
        }

        if (Integer.parseInt(body)>24){
            mTitleField.setError("max time is 24 months");
            return;
        }

        Double emi_value=getval(title,body);

        tv.setText(String.format("%.2f", emi_value));
        /*mBodyField.setVisibility(View.GONE);
        mTitleField.setVisibility(View.GONE);*/


    }

    public double getval(String a,String b){
        int P=Integer.parseInt(a);
        int n=Integer.parseInt(b);
        float c= (float) (36.0/1200.0);
        float d=c+1;
        return ((P*c*Math.pow(d,n)/(Math.pow(d,n)-1)));
    }
}
