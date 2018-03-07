package live.mvplive.ui.util;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.LayoutInflater;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Administrator on 2017/3/30 0030.06:35
 * 图片裁取得工具类
 */

public class Image_Tools {

    public Activity activity;
    private Context context;
    //图片存储路径
    public String Phone_URI = "/sdcard/";
    public String PHOTO_FILE_NAME = "PHOTOIMAGE_ANSWER.jpg";

    public final int PHOTO_REQUEST = 1;
    public final int CAMERA_REQUEST = 2;
    public final int PHOTO_CLIP = 3;
    //图片地址
    private String filePath = null;

    public Bitmap photo;

    public Image_Tools(Activity activity) {
        this.activity = activity;
    }

    public Image_Tools(Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * 图片路径获取
     */
    public String image_path() {
        filePath = Phone_URI + "/"
                + PHOTO_FILE_NAME;
        return filePath;

    }


    /**
     * 图片选择
     */
    public void myDialog() {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        //  View view = layoutInflater.inflate(R.layout.activity_main, null); //R.layout.activty_main自定义的布局文件
        new AlertDialog.Builder(activity)
                // .setView(view)
                .setTitle("封面获取方式").setPositiveButton("相机", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPicFromCamera();

            }
        }).setNegativeButton("本地相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPicFromPhoto();
            }
        }).show();
    }

    /**
     * 选择照相机
     */
    public final void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Phone_URI, PHOTO_FILE_NAME)));
        activity.startActivityForResult(intent, CAMERA_REQUEST);
    }

    /**
     * 选择本地获取照片
     */
    public final void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        activity.startActivityForResult(intent, PHOTO_REQUEST);
    }

    /**
     * 图片裁剪
     */
    public void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, PHOTO_CLIP);
    }


    /**
     * 转换图片成圆形
     *
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    /**
     * 拍照成功
     * 2
     */
    public void Image_successful(int resultCode) {
        switch (resultCode) {
            case -1:// -1表示拍照成功
                File file = new File(Environment.getExternalStorageDirectory()
                        + "/" + PHOTO_FILE_NAME);
                if (file.exists()) {
                    photoClip(Uri.fromFile(file));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 图片打包裁剪
     * 1
     */
    public void Image_Packaged_cut(Intent data) {
        if (data != null) {
            photoClip(data.getData());
        }
    }

    /**
     * 图片转换
     * 3
     */
    public Bitmap Image_To_deal_with(Intent data) {
        if (data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                photo = extras.getParcelable("data");
                // 将bitmap转换为File
                Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
                //图片质量
                int quality = 100;
                OutputStream stream = null;
                try {
                    stream = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/"
                                    + PHOTO_FILE_NAME);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                photo.compress(format, quality, stream);
                // 异步上传文件
                filePath = image_path();
            }
        }
        return photo;
    }

    /**
     * 图片转换  转换成圆形
     * 3
     */
    public Bitmap Image_To_deal_with1(Intent data) {
        if (data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                photo = extras.getParcelable("data");
                // 转成圆形图片
//                photo = toRoundBitmap(photo);
                // 将bitmap转换为File
                Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
                //图片质量
                int quality = 100;
                OutputStream stream = null;
                try {
                    stream = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/"
                                    + PHOTO_FILE_NAME);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                photo.compress(format, quality, stream);
                // 异步上传文件
                filePath = image_path();
            }
        }
        return photo;
    }


    /**
     * 网络图片转换成bitmap
     *
     * @param url
     * @return
     */
    public Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * 指定图片的切边，对图片进行圆角处理
     *
     * @param bitmap  需要被切圆角的图片
     * @param roundPx 要切的像素大小
     * @return
     */
    public Bitmap fillet(int type, Bitmap bitmap, int roundPx) {
        try {
            // 其原理就是：先建立一个与图片大小相同的透明的Bitmap画板
            // 然后在画板上画出一个想要的形状的区域。
            // 最后把源图片帖上。
            final int width = bitmap.getWidth();
            final int height = bitmap.getHeight();

            Bitmap paintingBoard = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(paintingBoard);
            canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);

            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);

//            if( TOP == type ){
            clipTop(canvas, paint, roundPx, width, height);
//            }else if( LEFT == type ){
            clipLeft(canvas, paint, roundPx, width, height);
//            }else if( RIGHT == type ){
            clipRight(canvas, paint, roundPx, width, height);
//            }else if( BOTTOM == type ){
            clipBottom(canvas, paint, roundPx, width, height);
//            }else{
            clipAll(canvas, paint, roundPx, width, height);
//            }
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            //帖子图
            final Rect src = new Rect(0, 0, width, height);
            final Rect dst = src;
            canvas.drawBitmap(bitmap, src, dst, paint);
            return paintingBoard;
        } catch (Exception exp) {
            return bitmap;
        }
    }

    private void clipLeft(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(offset, 0, width, height);
        canvas.drawRect(block, paint);
        final RectF rectF = new RectF(0, 0, offset * 2, height);
        canvas.drawRoundRect(rectF, offset, offset, paint);
    }

    private void clipRight(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, 0, width - offset, height);
        canvas.drawRect(block, paint);
        final RectF rectF = new RectF(width - offset * 2, 0, width, height);
        canvas.drawRoundRect(rectF, offset, offset, paint);
    }

    private void clipTop(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, offset, width, height);
        canvas.drawRect(block, paint);
        final RectF rectF = new RectF(0, 0, width, offset * 2);
        canvas.drawRoundRect(rectF, offset, offset, paint);
    }

    private void clipBottom(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, 0, width, height - offset);
        canvas.drawRect(block, paint);
        final RectF rectF = new RectF(0, height - offset * 2, width, height);
        canvas.drawRoundRect(rectF, offset, offset, paint);
    }

    private void clipAll(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectF, offset, offset, paint);
    }

    /**
     * -------------------------------------图片压缩---------------------------------------------------
     */
    //质量压缩
    public Bitmap quality_of_compressed(Bitmap bit) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = Integer.valueOf(100);
        bit.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight()
                + "bytes.length=  " + (bytes.length / 1024) + "KB"
                + "quality=" + quality);
        return bm;
    }

    // /采样率压缩
    public void sampling_rate_compression() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bm = BitmapFactory.decodeFile(Environment
                .getExternalStorageDirectory().getAbsolutePath()
                + "/DCIM/Camera/test.jpg", options);
        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());

    }
    //缩放法压缩（martix）

    public void scaling_method_of_compression(Bitmap bit) {
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        Bitmap bm = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(),
                bit.getHeight(), matrix, true);
        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
    }
    //RGB_565法

    public void rgb_compersion() {
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap bm = BitmapFactory.decodeFile(Environment
                .getExternalStorageDirectory().getAbsolutePath()
                + "/DCIM/Camera/test.jpg", options2);

        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());

    }
    //createScaledBitmap

    public void createScaledBitmap(Bitmap bit) {
        Bitmap bm = Bitmap.createScaledBitmap(bit, 150, 150, true);
        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024) + "KB宽度为"
                + bm.getWidth() + "高度为" + bm.getHeight());
    }
    //计算图片大小

    public void big(Bitmap bit) {
        Bitmap bm = Bitmap.createScaledBitmap(bit, 150, 150, true);
        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024) + "KB宽度为"
                + bm.getWidth() + "高度为" + bm.getHeight());
    }


}

