package event.imy.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 4399-蒋明伟 on 2017/8/4.
 */

public class SecondPostEventActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_post);
        View viewById = findViewById(R.id.btn_1);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventHelper.getInstance().postEvent(new User("你大爷"));
            }
        });
    }
}
