package com.ting.open.bmobtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ting.open.bmobtest.bean.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by lt on 2016/8/17.
 */
public class MainActivity extends Activity {

    private Button btInsert;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBmob();

    }

    private void initBmob() {
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, "0c3a81adc04b7a3ea9b8cc3d6826f683");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    /**
     * insert data
     * @param view
     */
    public void insertData(View view) {
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAge("19");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    toast("添加数据成功，返回objectId为："+objectId);
                }else{
                    toast("创建数据失败：" + e.getMessage());
                }
            }
        });
    }


    /**
     * retrieve data
     * @param view
     */
    public void retrieveData(View view){
       //查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("b9bd7644dc", new QueryListener<Person>() {
            @Override
            public void done(Person object,BmobException e) {
                if(e==null){
                    toast("查询成功");
                }else{
                    toast("查询失败：" + e.getMessage());
                }
            }
        });
    }

    /**
     * modify data
     * @param view
     */
    public void modifyData(View view){
       //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
        final Person p2 = new Person();
        p2.setAge("北京朝阳");
        p2.update("b9bd7644dc", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    toast("更新成功:"+ p2.getUpdatedAt());
                }else{
                    toast("更新失败：" + e.getMessage());
                }
            }

        });
    }

    /**
     * delete data
     * @param view
     */
    public void deleteData(View view){
        final Person p2 = new Person();
        p2.setObjectId("b9bd7644dc");
        p2.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    toast("删除成功:"+p2.getUpdatedAt());
                }else{
                    toast("删除失败：" + e.getMessage());
                }
            }

        });
    }

    private void toast(String msg){
        Log.d(TAG,"toast: " + msg);
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
