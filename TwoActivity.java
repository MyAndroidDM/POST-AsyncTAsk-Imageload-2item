package bawei.com.homework20170428;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by huanhuan on 2017/4/28.
 */

public class TwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_activity);
        TextView tv = (TextView) findViewById(R.id.tv);
        String title = getIntent().getStringExtra("title");
        tv.setText(title);
    }
}
