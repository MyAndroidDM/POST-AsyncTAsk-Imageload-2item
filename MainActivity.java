package bawei.com.homework20170428;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import bawei.com.homework20170428.base.BaseActivity;
import bawei.com.homework20170428.fragment.Fragment01;
import bawei.com.homework20170428.fragment.Fragment02;
import bawei.com.homework20170428.fragment.Fragment03;

public class MainActivity extends BaseActivity{

    private List<Fragment> list=new ArrayList<Fragment>();
    private RadioGroup rg;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
private int selectIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creatFragment();
        showFragment(0);
        rg = (RadioGroup) findViewById(R.id.rg);
        radioButton1 = (RadioButton) findViewById(R.id.fram_introduce);
        radioButton2 = (RadioButton) findViewById(R.id.fram_zhize);
        radioButton3 = (RadioButton) findViewById(R.id.fram_myselfe);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fram_introduce:
                        selectIndex=0;
                        break;

                    case R.id.fram_zhize:
                        selectIndex=1;
                        break;
                    case R.id.fram_myselfe:
                        selectIndex=2;
                        break;
                }
                showFragment(selectIndex);
            }
        });


    }

    //初始化UI

    //创建fragment
    private void creatFragment(){

        Fragment01 fragment01 = new Fragment01();
        Fragment02 fragment02 = new Fragment02();
        Fragment03 fragment03 = new Fragment03();

        list.add(fragment01);
        list.add(fragment02);
        list.add(fragment03);
    }
    private void showFragment(int position){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!list.get(position).isAdded()){
            transaction.add(R.id.fram,list.get(position));
        }
        for (int i = 0; i <list.size() ; i++) {
            if (position==i){
                transaction.show(list.get(i));
            }else {
                transaction.hide(list.get(i));
            }
        }
        transaction.commit();
    }
}
