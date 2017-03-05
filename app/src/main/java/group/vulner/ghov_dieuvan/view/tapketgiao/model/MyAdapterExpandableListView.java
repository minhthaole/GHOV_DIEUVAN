package group.vulner.ghov_dieuvan.view.tapketgiao.model;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group.vulner.ghov_dieuvan.R;

/**
 * Created by TuTV on 2/20/2017.
 */

public class MyAdapterExpandableListView extends BaseExpandableListAdapter {
    private Context context;
    private List<String> lstIdNhanVienGiao;
    private HashMap<String, List<String>> listHashMapCacDonHangNhanVienPhaiGiao;

    public MyAdapterExpandableListView(Context context, List<String> lstIdNhanVienGiao, HashMap<String, List<String>> listHashMapCacDonHangNhanVienPhaiGiao) {
        this.context = context;
        this.lstIdNhanVienGiao = lstIdNhanVienGiao;
        this.listHashMapCacDonHangNhanVienPhaiGiao = listHashMapCacDonHangNhanVienPhaiGiao;

    }

    @Override
    public int getGroupCount() {
        return lstIdNhanVienGiao.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMapCacDonHangNhanVienPhaiGiao.get(lstIdNhanVienGiao.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lstIdNhanVienGiao.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMapCacDonHangNhanVienPhaiGiao.get(lstIdNhanVienGiao.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.danh_sach_ten_nhan_vien_giao, parent, false);

        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_danh_sach_ten_nhan_vien_giao);
        textView.setText(title);
        textView.setTypeface(null, Typeface.BOLD);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String title= (String) this.getChild(groupPosition,childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.danh_sach_cach_don_hang_chi_tiet, parent, false);
        }
        TextView textView= (TextView) convertView.findViewById(R.id.tv_danh_sach_cac_don_hang_chi_tiet);
        Button btnXacNhanTapKetGia= (Button) convertView.findViewById(R.id.btn_xac_nhan_tap_ket_giao);
        btnXacNhanTapKetGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,""+title, Toast.LENGTH_SHORT).show();
            }
        });
        textView.setText(title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
