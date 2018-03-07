package live.mvplive.ui.util;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/12/21 0021.
 *
 *图片加载器
 */

public class GlideImageLoader implements BannerLayout.ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
//        Glide.with(context).load(path).centerCrop().into(imageView);
    }
}
