package group.vulner.ghov_dieuvan.view.login;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.controller.Security;
import group.vulner.ghov_dieuvan.model.file.SharepreferenceManager;
import group.vulner.ghov_dieuvan.model.login.DataResponeLogin;
import group.vulner.ghov_dieuvan.view.MainActivity;
import group.vulner.ghov_dieuvan.view.dangky.ActivityDangKy;


public class ActivityLogin extends AppCompatActivity {

    private static String LOGIN_FAILED = "failed";
    private String nameClass = getClass().getSimpleName();
    private Context context;
    private EditText edtEmail, edtPassword;
    private Button btnDangNhap;
    private TextView tvWarning, btnDangKy;
    private CheckBox cbShowPassword;
    private String email;


    private SharepreferenceManager savefile;


    private void init() {
        savefile = new SharepreferenceManager(context);
        cbShowPassword.setChecked(false);
        hideWarning();
        edtPassword.setTypeface(edtEmail.getTypeface());
        email = savefile.getEmail();
        edtEmail.setText(email);

        SpannableString content = new SpannableString(getResources().getString(R.string.sign_up));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        btnDangKy.setText(content);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;


        savefile = new SharepreferenceManager(context);
//        if (!savefile.getSession("giá trị mặc định").equals("giá trị mặc định")) {
//            Intent intent=new Intent(ActivityLogin.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent);
//        }
        edtEmail = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        cbShowPassword = (CheckBox) findViewById(R.id.cbShowPassword);
        tvWarning = (TextView) findViewById(R.id.tvWarning);
        btnDangKy = (TextView) findViewById(R.id.btnDangKy);
//        getSupportActionBar().setTitle(getResources().getString(R.string.name_company));
//        setStatusBarColor(findViewById(R.id.statusBarBackground),getResources().getColor(R.color.colorPrimaryDark));


        init();
        cbShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cbShowPassword.isChecked()) {
                    cbShowPassword.setChecked(false);
                } else {
                    cbShowPassword.setChecked(true);
                }

                if (cbShowPassword.isChecked()) {
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    edtPassword.setTypeface(edtEmail.getTypeface());
                } else {
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edtPassword.setTypeface(edtEmail.getTypeface());
                }

            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            public static final int MY_REQUEST_CALL_PHONE = 123;

            @Override
            public void onClick(View v) {
                // check username
                //check password
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                            MY_REQUEST_CALL_PHONE);
                }

                final String email = edtEmail.getText().toString();
                savefile.saveEmail(email);
                if ("".equals(email) || "".equals(edtPassword.getText().toString())) {
                    if ("".equals(email)) {
                        showWarning(getResources().getString(R.string.please_input_taikhoan));
                    } else if ("".equals(edtPassword.getText().toString())) {
                        showWarning(getResources().getString(R.string.please_input_matkhau));
                    }
                } else {
                    if (!Utils.checkEmail(email)) {
                        showWarning(getResources().getString(R.string.email_username));
                    } else {
                        //check connection
                        if (!Utils.checkInternetIsConnected(context)) {
                            showWarning(getResources().getString(R.string.check_internet));
                        } else {
//                            Log.e("Login", urlLogIn);
                            LogInAsyncTask logInAsyncTask = new LogInAsyncTask(context, edtEmail.getText().toString(), edtPassword.getText().toString());
                            logInAsyncTask.execute();
                            showWarning(getResources().getString(R.string.empty_str));
                            savefile.saveEmail(email);
                        }

                    }
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ActivityDangKy.class));
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(Utils.PERMISSION_REQUEST, 2909);
        }
    }

    public void setStatusBarColor(View statusBar, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = this.getActionBarHeight();
            int statusBarHeight = getStatusBarHeight();
            //action bar height
            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }

    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void showWarning(String warningStr) {
        tvWarning.setVisibility(View.VISIBLE);
        tvWarning.setText(warningStr);
    }

    private void hideWarning() {
        tvWarning.setVisibility(View.INVISIBLE);
    }

    private class LogInAsyncTask extends AsyncTask<Void, Void, DataResponeLogin> {
        private ProgressDialog progressDialog;
        private Context mContext;
        private String email;
        private String password;
        private Security sec;

        public LogInAsyncTask(Context context, String _email, String _password) {
            this.mContext = context;
            this.progressDialog = new ProgressDialog(this.mContext);
            savefile = new SharepreferenceManager(this.mContext);
            this.email = _email;
            this.password = _password;
            sec = new Security();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(mContext.getResources().getString(R.string.loading_data));
            try {
                this.progressDialog.show();
            }
            catch (Exception e) {

            }
        }

        @Override
        protected DataResponeLogin doInBackground(Void... url) {
            String urlLogin = Utils.URL_GHOV_API_LOGIN_NHANVIEN_MOBILE;
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost postRequest = new HttpPost(urlLogin);
                MultipartEntity multiPartEntity = new MultipartEntity();
                // =================name file upload===========
                //add nội dung truyền lên
                multiPartEntity.addPart("email", new StringBody(Utils.encodeBase64(email)));
                multiPartEntity.addPart("password", new StringBody(Utils.encodeBase64(sec.md5(password))));
                // xét nội dung truyền lên
                postRequest.setEntity(multiPartEntity);
                // client.setTimeout(500000);
                // ====== create response from server
                HttpResponse response = client.execute(postRequest);

                Log.i("response", "" + response.getStatusLine().getStatusCode());
                if (response == null
                    || response.getStatusLine().getStatusCode() != 200) {
                    return null;
                } else {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    String rp = out.toString();
//                    Log.i("response", "" + rp);
                    out.close();
                    DataResponeLogin data = new DataResponeLogin(rp);
                    return data;
                }
            }
            catch (Exception e) {
                Log.i("" + nameClass, "Exception:" + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(DataResponeLogin dataRespone) {
            super.onPostExecute(dataRespone);
            if (dataRespone != null) {
                if (dataRespone.checkStatusSuccess()) {
                    savefile.saveSession(dataRespone.session_id);
                    savefile.saveTenNhanVien(dataRespone.ho_ten);
                    savefile.saveIdThanhPho(dataRespone.id_thanh_pho);
                    savefile.saveIdQuan(dataRespone.id_quan);
                    savefile.saveIdPhuong(dataRespone.id_phuong);
                    startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                    savefile.getSession("Giá trị mặc định");
                    Log.e("session trả về", savefile.getSession("Giá trị mặc định"));
                } else {
                    Toast.makeText(context, dataRespone.warning, Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(context, "Đăng nhập lỗi, kiểm tra lại ứng dụng", Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            }
            catch (Exception e) {

            }
        }
    }


}
