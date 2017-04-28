package bawei.com.homework20170428.task;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import bawei.com.homework20170428.StreamUtils;
import bawei.com.homework20170428.listener.ResponeListener;

/**
 * Created by huanhuan on 2017/4/28.
 */

public class MyAsyncTask extends AsyncTask<Object,Void,String> {
    ResponeListener responeListener;
    private String result;

    public MyAsyncTask(ResponeListener responeListener){
        this.responeListener=responeListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        String path= (String) params[0];
        Map map= (Map) params[1];
        try {
            URL url=new URL(path);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
//            conn.setConnectTimeout(50000);
//            conn.setReadTimeout(50000);
            conn.setDoOutput(true);

            StringBuilder sb = new StringBuilder();
            Set<String> set = map.keySet();
            for (String key: set) {
                sb.append(key);
                sb.append("=");
                sb.append(map.get(key));
                sb.append("&");
                }

            sb=sb.deleteCharAt(sb.length()-1);
            OutputStream os =  conn.getOutputStream();
            os.write(sb.toString().getBytes());
            os.flush();


            if (conn.getResponseCode()==200){
                InputStream is = conn.getInputStream();
                result = StreamUtils.readInput(is);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println("---s--- = " + s);
        if (responeListener!=null){
            if (TextUtils.isEmpty(result)){
                responeListener.onFial();
            }else {
                responeListener.success(result);
            }
        }else {
            responeListener.onFial();
        }
    }

}
