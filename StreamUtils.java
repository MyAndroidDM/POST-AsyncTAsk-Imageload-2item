package bawei.com.homework20170428;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by huanhuan on 2017/4/28.
 */

public class StreamUtils {
    public static String readInput(InputStream inputStream){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        try {
            while ((len=inputStream.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
