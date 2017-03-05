package group.vulner.ghov_dieuvan.view.hangvekho.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group.vulner.ghov_dieuvan.R;

public class SuaGhiChu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ghi_chu);
        EditText edtSuaGhiChu = (EditText) findViewById(R.id.edt_sua_ghi_chu);
        Button btnCapNhatGhiChu = (Button) findViewById(R.id.btn_cap_nhat_ghi_chu);
        btnCapNhatGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SuaGhiChu.this, "Ahihi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
