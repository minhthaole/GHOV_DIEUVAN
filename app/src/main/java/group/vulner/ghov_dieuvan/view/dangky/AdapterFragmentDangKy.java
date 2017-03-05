package group.vulner.ghov_dieuvan.view.dangky;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.FragmentThongTinNhanVien;
import group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.FragmentThongTinTaiKhoan;
import group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.giadinh.FragmentThongTinGiadinh2;


public class AdapterFragmentDangKy extends FragmentStatePagerAdapter {
    private Context context;
    private int numItem;

    public AdapterFragmentDangKy(Context _context, FragmentManager fm, int _numItem) {
        super(fm);
        context = _context;
        numItem = _numItem;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentThongTinNhanVien(context);
            case 1:
                return new FragmentThongTinGiadinh2(context);
            case 2:
                return new FragmentThongTinTaiKhoan(context);
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return numItem;
    }

    @Override
    public String getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Thông tin nhân viên";
            case 1:
                return "Thông tin gia đình";
            case 2:
                return "Thông tin tài khoản";
        }
        return "";
    }
}
