package event.imy.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventHelper.getInstance().register(this);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_1);
        tv = (TextView) findViewById(R.id.tv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostEventActivity.class);
                startActivity(intent);
            }
        });
    }
    @Register
    public void onEvent(User user) {
        Log.i("MainActivity", "event:" + user.getName());
        tv.setText(user.getName());
    }

    @Override
    protected void onDestroy() {
        EventHelper.getInstance().unregister(this);
        super.onDestroy();
    }
}
