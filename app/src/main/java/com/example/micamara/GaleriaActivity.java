package com.example.micamara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

public class GaleriaActivity extends AppCompatActivity {
    String []archivos;
    RecyclerView rv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        archivos=fileList();
        rv1 = findViewById(R.id.rvGaleria);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(linearLayoutManager);
        rv1.setAdapter(new AdapterFotos());
    }

    private class AdapterFotos extends RecyclerView.Adapter<AdapterFotos.AdaptadorFotosHolder> {
        @NonNull
        @Override
        public AdaptadorFotosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorFotosHolder(getLayoutInflater().inflate(R.layout.layout_foto,parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorFotosHolder holder, int position) {
            holder.imprimir(position);
        }

        @Override
        public int getItemCount() {
            return archivos.length;
        }

        class AdaptadorFotosHolder extends RecyclerView.ViewHolder{

            ImageView iv1;
            TextView tv1;

            public AdaptadorFotosHolder(@NonNull View itemView) {
                super(itemView);
                tv1=itemView.findViewById(R.id.tvFoto);
                iv1=itemView.findViewById(R.id.ivFoto);
            }

            public void imprimir(int position) {
                tv1.setText("nombre del archivo"+archivos[position]);
                try{
                    FileInputStream fileInputStream=openFileInput(archivos[position]);
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    iv1.setImageBitmap(bitmap);
                    fileInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}