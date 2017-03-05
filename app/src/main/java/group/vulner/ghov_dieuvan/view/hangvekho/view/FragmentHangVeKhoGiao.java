package group.vulner.ghov_dieuvan.view.hangvekho.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.hangvekho.Presenter.SesstionAdapterFragment;

/**
 * Created by TuTV on 2/28/2017.
 */

public class FragmentHangVeKhoGiao extends Fragment {
    SesstionAdapterFragment sesstionAdapterFragment;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hang_ve_kho, container, false);
        sesstionAdapterFragment = new SesstionAdapterFragment(getFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.vp_view_pager);
        viewPager.setAdapter(sesstionAdapterFragment);

        tabLayout = (TabLayout) view.findViewById(R.id.tb_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
