package group.vulner.ghov_dieuvan.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.model.file.SharepreferenceManager;
import group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangVeKhoGiao;
import group.vulner.ghov_dieuvan.view.login.ActivityLogin;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.views.FragmentTaoDonHangMoi;
import group.vulner.ghov_dieuvan.view.tapketnhan.FragmentDanhSachTapKetNhan;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context;
    private SharepreferenceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        Log.e("context main", context.toString());
        manager = new SharepreferenceManager(context);
        if (manager.getSession("Giá trị mặc định").equals("Giá trị mặc định")) {
            Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
            startActivity(intent);
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.flChangeFragment, new FragmentHangVeKhoGiao());
        transaction.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Giao hàng ong vàng");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
//            case R.id.it_tao_don_hang: {
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.flChangeFragment, new FragmentTaoDonHangMoi());
//                transaction.commit();
//                getSupportActionBar().setTitle("Tạo đơn hàng");
//                break;
//            }

//            case R.id.it_quan_ly_kho: {
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.flChangeFragment, new FragmentHangVeKhoGiao());
//                transaction.commit();
//                getSupportActionBar().setTitle("Quản lý kho");
//                break;
//            }
            case R.id.it_log_in:{
                manager = new SharepreferenceManager(context);
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                manager.saveSession("Giá trị mặc định");
                startActivity(intent);
                finish();
            }
        }
//        if (id == R.id.it_tap_ket_nhan) {
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.replace(R.id.flChangeFragment, new FragmentDanhSachTapKetNhan());
//            transaction.commit();
////            getSupportActionBar().setTitle("Tập kết nhận");
//
//        } else if (id == R.id.it_log_in) {
//            Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//
//        } else if (id == R.id.it_hang_ve_kho) {
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.commit();
////            getSupportActionBar().setTitle("Hàng về kho");
//        } else if (id == R.id.nav_send) {
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.replace(R.id.flChangeFragment, new FragmentHangVeKhoGiao());
//            transaction.commit();
////            getSupportActionBar().setTitle("Hàng hoàn");
//        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
