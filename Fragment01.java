package bawei.com.homework20170428.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bawei.com.homework20170428.News;
import bawei.com.homework20170428.R;
import bawei.com.homework20170428.TwoActivity;
import bawei.com.homework20170428.listener.ResponeListener;
import bawei.com.homework20170428.task.MyAsyncTask;

/**
 * Created by huanhuan on 2017/4/28.
 */

public class Fragment01 extends Fragment implements ResponeListener{

    private ListView listView;

    private List<News.DataBean> list=new ArrayList<News.DataBean>();
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_01, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        listView = (ListView) view.findViewById(R.id.fragment_one_listView);

        adapter = new MyAdapter();
        listView.setAdapter(adapter);

        getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), TwoActivity.class);
                intent.putExtra("title",list.get(position).getTITLE());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        //http://www.93.gov.cn/93app/data.do?channelId=0&startNum=0
       Map<String,String> map=new HashMap<String,String>();
        map.put("channelId","0");
        map.put("startNum","0");
        new MyAsyncTask(this).execute("http://www.93.gov.cn/93app/data.do",map);

    }

    @Override
    public void success(String string) {

        News news = JSON.parseObject(string, News.class);
        list.addAll(news.getData());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFial() {

    }
class MyAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getIMAGEURL()!=null){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH1 vh1=null;
        VH2 vh2=null;
        if (convertView==null){
            if (getItemViewType(position)==0){
                vh1=new VH1();
                convertView=View.inflate(getActivity(),R.layout.lv_item1,null);
                vh1.iv1 = (ImageView) convertView.findViewById(R.id.iv1);
                vh1. tv_title1 = (TextView) convertView.findViewById(R.id.tv_title1);
                vh1. tv_fm = (TextView) convertView.findViewById(R.id.tv_fm);
                convertView.setTag(vh1);
            }else {
                vh2=new VH2();
                convertView=View.inflate(getActivity(),R.layout.lv_item2,null);

                vh2.tv_title2 = (TextView) convertView.findViewById(R.id.tv_title2);
                vh2.tv_fm2 = (TextView) convertView.findViewById(R.id.tv_fm2);
                convertView.setTag(vh2);
            }
        }else {
                if (getItemViewType(position)==0){
                    vh1= (VH1) convertView.getTag();
                }else {
                    vh2= (VH2) convertView.getTag();
                }
        }
        if (getItemViewType(position)==0){
            ImageLoader.getInstance().displayImage(list.get(position).getIMAGEURL(),vh1.iv1);
            vh1.tv_title1.setText(list.get(position).getTITLE());
            vh1.tv_fm.setText(list.get(position).getFROMNAME());
        }else {
            vh2.tv_title2.setText(list.get(position).getTITLE());
            vh2.tv_fm2.setText(list.get(position).getFROMNAME());
        }
        return convertView;
    }
}

    class VH1{
        ImageView iv1;
        TextView tv_title1;
        TextView tv_fm;

    }
    class VH2{
        TextView tv_title2;
        TextView tv_fm2;
    }
}
