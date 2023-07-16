package info.fekri.womansafety.fragment;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Map;

import info.fekri.womansafety.MainActivity;
import info.fekri.womansafety.R;
import info.fekri.womansafety.adapter.ImagePagerAdapter;

public class ImagePagerFragment extends Fragment {

    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewPager = (ViewPager) inflater.inflate(R.layout.fragment_pager, container, false);
        viewPager.setAdapter(new ImagePagerAdapter(this));
        viewPager.setCurrentItem(MainActivity.currentPosition);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                MainActivity.currentPosition = position;
            }
        });

        prepareSharedElementTransition();

        if (savedInstanceState == null) {
            postponeEnterTransition();
        }

        return viewPager;
    }

    private void prepareSharedElementTransition() {
        Transition transition = TransitionInflater.from(getContext())
                .inflateTransition(R.transition.image_shared_element_transition);
        setSharedElementEnterTransition(transition);

        setEnterSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        Fragment currentFragment = (Fragment) viewPager.getAdapter()
                                .instantiateItem(viewPager, MainActivity.currentPosition);
                        View view = currentFragment.getView();

                        if (view == null) {
                            return;
                        }

                        sharedElements.put(names.get(0), view.findViewById(R.id.image));
                    }
                });
    }
}