package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import group.vulner.ghov_dieuvan.R;

import static group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHenGiao.lstDonHang_HHG;

/**
 * Created by TuTV on 3/7/2017.
 */

public class DialogChonTatCaHangHenGiao extends android.support.v4.app.DialogFragment {
    //    ArrayList<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_hoan = new ArrayList<>();
//    NhanVienGiaoHang_Hoan nhanVienGiaoHang_hoan = new NhanVienGiaoHang_Hoan();
    Context context;
    Button btnCancelDiaLogHangHenGiao, btnSubmitDialogHangHenGiao, btnSubmitAllDialogHangHenGiao;
    ListView lv_hien_thi_xac_nhan_tat_ca_hanghengiao;
    ExpandableListViewHangHenGiao.AsyntaskXacNhanDonHangHenGiaoA asyntaskXacNhanDonHangHenGiaoA;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialogfragment_nhan_tat_ca_hanghengiao, container, false);
//
//        Bundle bundle=new Bundle();
//        String dataChecked =bundle.getString("idCheckBox");
//        Log.e("id checked", dataChecked.toString());
//

        getDialog().setTitle("This is title dialog");



        btnCancelDiaLogHangHenGiao = (Button) view.findViewById(R.id.btn_cancel_dialog_hanghengiao);
        btnSubmitDialogHangHenGiao = (Button) view.findViewById(R.id.btn_submit_dialog_hanghengiao);
        btnCancelDiaLogHangHenGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSubmitDialogHangHenGiao = (Button) view.findViewById(R.id.btn_submit_dialog_hanghengiao);
        btnSubmitDialogHangHenGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Xác nhận hàng hẹn giao", Toast.LENGTH_SHORT).show();

            }
        });
        CustomAdapterXacNhanTatCaHangHenGiao arrayAdapter = new CustomAdapterXacNhanTatCaHangHenGiao(getContext(), R.layout
                .custom_listview_nhan_tat_ca_hanghengiao, lstDonHang_HHG);
        lv_hien_thi_xac_nhan_tat_ca_hanghengiao = (ListView) view.findViewById(R.id.lv_hien_thi_xac_nhan_tat_ca_hanghengiao);
        lv_hien_thi_xac_nhan_tat_ca_hanghengiao.setAdapter(arrayAdapter);
        return view;
    }
}
