package com.bwie.day0421_homework_vp_xutil_db_demo;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.BuildConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by 陈鑫 on 2017/4/21.
 */

public class MyApplication extends Application {

    DbManager.DaoConfig config;

    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        initData();

    }

    public void initData(){
        config = new DbManager.DaoConfig();

        config.setDbName("news.db");

        config.setDbVersion(1);

        config.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

            }
        });
    }

    private void initImageLoader(){

        String path = Environment.getExternalStorageDirectory() + "/imageload" ;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800)//缓存图片最大的长和宽
                .threadPoolSize(2)//线程池的数量
                .threadPriority(4)
                .memoryCacheSize(2*1024*1024)//设置内存缓存区大小
                .diskCacheSize(20*1024*1024)//设置sd卡缓存区大小
//                .diskCache(new UnlimitedDiskCache(new File(path)))//自定义缓存目录
                .writeDebugLogs()//打印日志内容
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(config);
    }


    public DisplayImageOptions getOption(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_empty) // resource or drawable
                .showImageOnFail(R.mipmap.ic_error) // resource or drawable
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();
        return  options;
    }
}
