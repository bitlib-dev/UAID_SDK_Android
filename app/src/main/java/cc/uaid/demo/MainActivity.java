package cc.uaid.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cc.bitlib.uaid.UAID;
import cc.bitlib.uaid.listener.TokenListener;

public class MainActivity extends AppCompatActivity {

    private Button getToken;
    private TextView resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getToken = findViewById(R.id.get_token);
        resp = findViewById(R.id.resp);
        getToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UAID uaid = UAID.getInstance(getApplicationContext(), "bf49d4a3f4ff7e0aefe1efe97c57729d");
                uaid.setDebug();
                uaid.getToken(new TokenListener() {
                    @Override
                    public void onTokenSuccess(int resultCode, String msg, final String operatorType, final String token, final String auth, final int os) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resp.setText("operatorType:" + operatorType + ",token:" + token + ",auth:" + auth + ",os:" + os);
                            }
                        });
                        System.out.println("operatorType:" + operatorType + ",token:" + token + ",auth:" + auth + ",os:" + os);
                    }

                    @Override
                    public void onTokenFail(final int code, final String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resp.setText("code=" + code + ",msg=" + msg);
                            }
                        });
                        System.out.println("code=" + code + ",msg=" + msg);
                    }
                }, null, 5000);
            }
        });
    }
}
