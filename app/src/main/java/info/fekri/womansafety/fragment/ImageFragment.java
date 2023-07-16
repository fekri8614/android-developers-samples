package info.fekri.womansafety.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import info.fekri.womansafety.R;

public class ImageFragment extends Fragment {

    private static final String KEY_IMAGE_RES = "com.google.samples.gridtopager.key.imageRes";

    public static ImageFragment newInstance(int drawableRes) {
        ImageFragment fragment = new ImageFragment();
        Bundle argument = new Bundle();
        argument.putInt(KEY_IMAGE_RES, drawableRes);
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image, container, false);

        Bundle arguments = getArguments();
        int imageRes = arguments.getInt(KEY_IMAGE_RES);

        view.findViewById(R.id.image).setTransitionName(String.valueOf(imageRes));

        Glide.with(this)
                .load(imageRes)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        getParentFragment().startPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        getParentFragment().startPostponedEnterTransition();
                        return false;
                    }
                })
                .into((ImageView) view.findViewById(R.id.image));
        return view;
    }
}