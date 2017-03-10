package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHenGiao;
import group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHoan;
import group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangVeKhoGiao;

/**
 * Created by TuTV on 2/28/2017.
 */

public class SesstionAdapterFragment extends FragmentPagerAdapter {
    public SesstionAdapterFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                Log.e("tag hang hoang222", String.valueOf(new FragmentHangHoan().getTag()));
                return FragmentHangHoan.newInstace(0);
            }
            case 1:
                return FragmentHangHenGiao.newInstace(1);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: {
                return "Hàng hoàn";
            }
            case 1: {
                return "Hàng hẹn giao";
            }
        }
        return null;
    }
}
