package group.vulner.ghov_dieuvan.view.taodonhangmoi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.variable.ThanhPho;

/**
 * Created by TuTV on 2/9/2017.
 */

public class CustomSpinnerAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private ArrayList<ThanhPho> list;

    public CustomSpinnerAdapter(Context context, int resource, ArrayList<ThanhPho> list) {
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false);
        TextView tvHienThiTinhThanh_Spinner = (TextView) view.findViewById(R.id.tvHienThiTinhThanh_Spinner);
        tvHienThiTinhThanh_Spinner.setText(list.get(position).getTen_thanh_pho());
        return view;
    }
}
