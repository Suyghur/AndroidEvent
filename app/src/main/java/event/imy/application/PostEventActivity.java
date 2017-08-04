package event.imy.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 4399-蒋明伟 on 2017/8/3.
 */

public class PostEventActivity  extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventHelper.getInstance().register(this);
        setContentView(R.layout.activity_post);
        View viewById = findViewById(R.id.btn_1);
        tv = (TextView) findViewById(R.id.tv);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostEventActivity.this, SecondPostEventActivity.class);
                startActivity(intent);
            }
        });
    }
    @Register
    public void onEvent(User user) {
        Log.i("PostEventActivity", "event:" + user.getName());
        tv.setText(user.getName());
    }
    @Override
    protected void onDestroy() {
        EventHelper.getInstance().unregister(this);
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

}
