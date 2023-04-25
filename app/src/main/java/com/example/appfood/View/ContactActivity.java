package com.example.appfood.View;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.appfood.R;

public class ContactActivity  extends AppCompatActivity implements OnMapReadyCallback {
    private Toolbar toolbar;
    private FirebaseFirestore db;
    private TextView txtdiachi,txtsdt,txtnoidung;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        toolbar = findViewById(R.id.toolbar);
        txtdiachi=findViewById(R.id.txtdiachi);
        txtsdt = findViewById(R.id.txtsdt);
        txtnoidung = findViewById(R.id.txtnoidung);

        db =FirebaseFirestore.getInstance();



        db.collection("ThongTinCuaHang").document("aM4AQTWTMkf5G3bQ1Vnf")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {

                txtdiachi.setText("Địa chỉ : "+documentSnapshot.getString("diachi"));
                txtsdt.setText("Liên hệ : "+documentSnapshot.getString("sdt"));
                txtnoidung.setText("Nội Dung : "+documentSnapshot.getString("noidung"));


            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // GG maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) { //đọc vị trí gg map
        LatLng latLng = new LatLng(10.86564,106.600591); // vĩ độ trên ggmaps
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title("HUFLIT");
        options.snippet("Trường Đại học Ngoại ngữ - Tin học TP. HCM (HUFLIT) - Cơ sở Hóc Môn"); // option hiển thị thông tin vị trí lấy từ gg map
        googleMap.addMarker(options);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18)); // đọc camera


    }
}
