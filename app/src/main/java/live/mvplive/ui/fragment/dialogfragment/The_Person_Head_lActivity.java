package live.mvplive.ui.fragment.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import live.mvplive.R;
import live.mvplive.ui.util.Image_Tools;

/**
 * Created by Administrator on 2017/4/19 0019.
 * 头像更改
 */

public class The_Person_Head_lActivity extends DialogFragment {

    private View view;
    private Context context;
    private Dialog dialog;

    public final int PHOTO_REQUEST = 1;
    public final int CAMERA_REQUEST = 2;
    public final int PHOTO_CLIP = 3;


    public static The_Person_Head_lActivity new_fragment() {
        The_Person_Head_lActivity person_head_lActivity = new The_Person_Head_lActivity();
        Bundle build = new Bundle();
        person_head_lActivity.setArguments(build);
        return person_head_lActivity;
    }

    private void initDialogStyle(View view) {
        dialog = new Dialog(getActivity(), R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    private RelativeLayout the_person_head_r;
    //照相工具
    private Image_Tools imageTools;


    private LinearLayout person_head_linear_layout;
    private LinearLayout person_head_linear_layout_one;


    //相机
    private TextView the_person_head_camera;
    //手机相册
    private TextView the_person_head_phone;
    //取消
    private TextView the_person_head_return;

    //图片地址
    private String head = null;
    //获得处理后的图片
    private Bitmap photo;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(context).inflate(R.layout.the_person_head_xml, null);
        initDialogStyle(view);
        imageTools = new Image_Tools(getActivity());


        person_head_linear_layout_one = (LinearLayout) view.findViewById(R.id.person_head_linear_layout_one);
        person_head_linear_layout = (LinearLayout) view.findViewById(R.id.person_head_linear_layout);
        person_head_linear_layout.setOnClickListener(lister);
        //初始化
        find();
        return dialog;
    }

    private void find() {
        the_person_head_r = (RelativeLayout) view.findViewById(R.id.the_person_head_r);
        //相机
        the_person_head_camera = (TextView) view.findViewById(R.id.the_person_head_camera);
        the_person_head_camera.setOnClickListener(lister);
        //手机相册
        the_person_head_phone = (TextView) view.findViewById(R.id.the_person_head_phone);
        the_person_head_phone.setOnClickListener(lister);
        //取消
        the_person_head_return = (TextView) view.findViewById(R.id.the_person_head_return);
        the_person_head_return.setOnClickListener(lister);
    }

    private View.OnClickListener lister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //相机
                case R.id.the_person_head_camera:
                    imageTools.getPicFromCamera();
                    break;
                //手机相
                case R.id.the_person_head_phone:
                    Log.e("相册", "相册");
                    imageTools.getPicFromPhoto();
                    break;

                case R.id.person_head_linear_layout:
                    break;
                //取消
                case R.id.the_person_head_return:
                    dialog.dismiss();
//                    person_head_linear_layout_one.setVisibility(View.GONE);
                    break;

                default:
            }
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult", "onActivityResult");
        switch (requestCode) {
            case 2:
                Log.e("ffffffffff", "2");
                imageTools.Image_successful(resultCode);
                break;
            case 1:
                Log.e("fffffffffffffff", "1");
                imageTools.Image_Packaged_cut(data);
                break;
            case 3:
                Log.e("ffffffffffffffffff", "3");
                photo = imageTools.Image_To_deal_with1(data);
                head = imageTools.image_path();
                if (TextUtils.isEmpty(head) == false) {
                    //上传服务器
                    Intent intent = new Intent();
                    intent.putExtra("head", "sadadasdasdsadasdsa");
                    onActivityResult(2222, 2222, intent);
                    dialog.dismiss();

                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 选择本地获取照片
     */
    public final void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        getActivity().startActivityForResult(intent, PHOTO_REQUEST);
        getTargetFragment().onActivityResult(2000,2000,new Intent());
    }


}
