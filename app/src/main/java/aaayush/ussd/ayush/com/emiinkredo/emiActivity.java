package aaayush.ussd.ayush.com.emiinkredo;

import android.content.Intent;
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
    private String uid;
    private dbHelper dbh ;
    private String title;
    private String body;
    private int backPressed=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);

        if (getIntent()!=null && getIntent().hasExtra( Intent.EXTRA_TEXT)) {
            uid = getIntent().getStringExtra( Intent.EXTRA_TEXT);
        }

        mTitleField = (EditText) findViewById(R.id.field_title);
        mBodyField = (EditText) findViewById(R.id.destination);
        findViewById(R.id.button3).setVisibility(View.GONE);
        tv=(TextView)findViewById(R.id.tv);
        em=(TextView)findViewById(R.id.emii);
        TextView t=(TextView)findViewById(R.id.tv);
        t.setText("hello "+uid);

        dbh = new dbHelper(this);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(1);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dbViewActivity.class));
            }
        });
    }

    public void submitPost(){
        if(calculate(0)){
        mBodyField.setVisibility(View.GONE);
        mTitleField.setVisibility(View.GONE);
        findViewById(R.id.button1).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.GONE);
        findViewById(R.id.button2).setVisibility(View.GONE);

        backPressed=0;


        em.setText("applied");
        if(dbh.insert(uid,body,title,1)) {
            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Could not Insert", Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.button3).setVisibility(View.VISIBLE);
        }
    }

    public boolean calculate(int d){

        title = mTitleField.getText().toString();
        body = mBodyField.getText().toString();

        backPressed=0;

        if (TextUtils.isEmpty(title)) {
            mTitleField.setError("REQUIRED");
            return false;
        }

        if (Integer.parseInt(title)>9999999){
            mTitleField.setError("sum too large");
            return false;
        }

        if (Integer.parseInt(title)<=0){
            mTitleField.setError("What are you trying to do?");
            return false;
        }

        if (TextUtils.isEmpty(body)) {
            mBodyField.setError("REQUIRED");
            return false;
        }

        if (Integer.parseInt(body)>60){
            mBodyField.setError("max tenure is 60 months");
            return false;
        }

        if (Integer.parseInt(body)<=0){
            mBodyField.setError("wanna be a time traveller, huh?");
            return false;
        }

        Double emi_value=getval(title,body);
        int tada=Integer.parseInt(body);
        Double x;

        String srrrr= ("EMI:"+String.format("%.2f", emi_value)+" \tTotal:"+String.format("%.2f", emi_value*Integer.parseInt(body)));
        for(int i=(Math.max(1,(tada-3)));i<=tada+3; i++){
            x=getval(title, String.valueOf(i));
            srrrr=srrrr.concat("\nTenure:"+i+" \tEMI: "+String.format("%.2f", x)+"\t Total: "+String.format("%.2f", emi_value*i));
            //Toast.makeText(this,"for"+i,Toast.LENGTH_SHORT).show();
        }
        /*mBodyField.setVisibility(View.GONE);
        mTitleField.setVisibility(View.GONE);*/

        em.setText(srrrr);
        //using this to insert all calculations into the db
        if(d!=0){                                                                                   //to avoid duplicate entries..
            if(dbh.insert(uid,body,title,0)) {
            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Could not Insert", Toast.LENGTH_SHORT).show();
        }}

    return true;
    }

    public double getval(String a,String b){
        int P=Integer.parseInt(a);
        int n=Integer.parseInt(b);
        float c= (float) (36.0/1200.0);
        float d=c+1;
        return ((P*c*Math.pow(d,n)/(Math.pow(d,n)-1)));
    }

    @Override
    public void onBackPressed(){
        backPressed++;
        if(backPressed>=2) finish();
        Toast.makeText(this,"tap again to exit",Toast.LENGTH_SHORT).show();
    }

}
