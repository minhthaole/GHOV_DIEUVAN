package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import group.vulner.ghov_dieuvan.R;

/**
 * Created by TuTV on 3/6/2017.
 */

public class FragmentDialogChonTatCaHangHoan extends android.support.v4.app.DialogFragment {
    Button btnDismissDiaLog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_xac_nhan_tat_ca_hanghoan, container, false);
        getDialog().setTitle("This is title dialog");
        btnDismissDiaLog = (Button) view.findViewById(R.id.btn_dissmiss_fragment_dialog);
        btnDismissDiaLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
