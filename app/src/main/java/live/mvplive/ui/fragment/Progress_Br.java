package live.mvplive.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import live.mvplive.R;
import live.mvplive.widget.MyProgress;

/**
 * Created by Administrator on 2017/11/24.
 */

public class Progress_Br extends AppCompatActivity{

    private MyProgress myProgress  ;
    private Handler mHandler;

    private  boolean is_start;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.progress_viewxml);

        myProgress=(MyProgress) findViewById(R.id.progress_b);
        myProgress.setOnClickListener(listener);
        myProgress.setText1("下载");

        mHandler =  new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                // TODO Auto-generated method stub
                myProgress.setProgress(msg.what);
                return false;
            }
        });
    }

    android.view.View.OnClickListener listener=new android.view.View.OnClickListener(){
        @Override
        public void onClick(View view) {
           if(!is_start){
               is_start=true;
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       // TODO Auto-generated method stub
                       for(int i = 0;i <=50; i++){
                           mHandler.sendEmptyMessage(i * 2);
                           try {
                               Thread.sleep(80);
                           } catch (InterruptedException e) {
                               // TODO Auto-generated catch block
                               e.printStackTrace();
                           }
                       }
                   }
               }).start();
           }else{
               is_start=false;
               myProgress.setText1("继续");

           }
        }
    };


}
