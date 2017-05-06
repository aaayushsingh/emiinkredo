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
    private TextView em;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);

        mTitleField = (EditText) findViewById(R.id.field_title);
        mBodyField = (EditText) findViewById(R.id.destination);
        tv=(TextView)findViewById(R.id.tv);
        em=(TextView)findViewById(R.id.emii);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    }

    public void submitPost(){
        mBodyField.setVisibility(View.GONE);
        mTitleField.setVisibility(View.GONE);
        findViewById(R.id.button1).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.GONE);

        em.setText("applied");

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
            mBodyField.setError("REQUIRED");
            return;
        }

        if (Integer.parseInt(body)>60){
            mBodyField.setError("max tenure is 60 months");
            return;
        }

        Double emi_value=getval(title,body);
        int tada=Integer.parseInt(body);
        Double x;

        String srrrr= ("EMI:"+String.format("%.2f", emi_value)+" \tTotal:"+String.format("%.2f", emi_value*Integer.parseInt(body)));
        for(int i=(Math.max(0,(tada-3)));i<=tada+3; i++){
            x=getval(title, String.valueOf(i));
            srrrr=srrrr.concat("\nTenure:"+i+" \tEMI: "+String.format("%.2f", x)+"\t Total: "+String.format("%.2f", emi_value*i));
            //Toast.makeText(this,"for"+i,Toast.LENGTH_SHORT).show();
        }
        /*mBodyField.setVisibility(View.GONE);
        mTitleField.setVisibility(View.GONE);*/

        em.setText(srrrr);


    }

    public double getval(String a,String b){
        int P=Integer.parseInt(a);
        int n=Integer.parseInt(b);
        float c= (float) (36.0/1200.0);
        float d=c+1;
        return ((P*c*Math.pow(d,n)/(Math.pow(d,n)-1)));
    }
}
