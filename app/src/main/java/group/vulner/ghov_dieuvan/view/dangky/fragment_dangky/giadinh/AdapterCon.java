package group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.giadinh;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import group.vulner.ghov_dieuvan.R;

public class AdapterCon extends BaseAdapter {
    private Context mContext;
    private List<ThongTinGiaDinh> lstMembers;
    private FragmentManager fragmentManager;

    public AdapterCon(Context context, List<ThongTinGiaDinh> _lstMembers, FragmentManager _fragmentManager) {
        mContext = context;
        lstMembers = _lstMembers;
        fragmentManager = _fragmentManager;
    }


    @Override
    public int getCount() {
        return lstMembers.size();
    }

    @Override
    public Object getItem(int position) {
        return lstMembers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_con, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvGioiTinh = (TextView) convertView.findViewById(R.id.tvGioiTinh);
            viewHolder.tvNgaySinh = (TextView) convertView.findViewById(R.id.tvNgaySinh);
            viewHolder.btnXoa = (ImageView) convertView.findViewById(R.id.btnXoa);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.member = lstMembers.get(position);
        viewHolder.tvName.setText(viewHolder.member.name);
        viewHolder.tvGioiTinh.setText(viewHolder.member.gioitinh);
        viewHolder.tvNgaySinh.setText(viewHolder.member.ngay_sinh);
        viewHolder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lstMembers.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    private class ViewHolder {
        protected ThongTinGiaDinh member;
        protected TextView tvName;
        protected TextView tvGioiTinh;
        protected TextView tvNgaySinh;
        protected ImageView btnXoa;
    }
}
