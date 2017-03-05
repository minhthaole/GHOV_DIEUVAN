package group.vulner.ghov_dieuvan.view.taodonhangmoi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.variable.Quan;

/**
 * Created by TuTV on 2/9/2017.
 */

public class CustomSpinnerAdapterQuan extends BaseAdapter {
    private Context context;
    private int resource;
    private ArrayList<Quan> list;

    public CustomSpinnerAdapterQuan(Context context, int resource, ArrayList<Quan> objects) {
        this.context = context;
        this.resource = resource;
        this.list = objects;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false);
        TextView tvHienThiTinhThanh_Spinner = (TextView) view.findViewById(R.id.tvHienThiTinhThanh_Spinner);
        tvHienThiTinhThanh_Spinner.setText(list.get(position).getTen_quan());
        return view;
    }
}
