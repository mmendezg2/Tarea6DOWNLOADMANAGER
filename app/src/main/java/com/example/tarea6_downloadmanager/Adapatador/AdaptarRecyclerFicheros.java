package com.example.tarea6_downloadmanager.Adapatador;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea6_downloadmanager.Modelos.ficheros;
import com.example.tarea6_downloadmanager.R;

import java.util.List;

public class AdaptarRecyclerFicheros extends RecyclerView.Adapter<AdaptarRecyclerFicheros.ViemHolder> implements View.OnClickListener {

    private Context ContextitoFiche;
    public List<ficheros> Listafiche;


    public AdaptarRecyclerFicheros(Context contextitoFiche, List<ficheros> listafiche) {
        ContextitoFiche = contextitoFiche;
        Listafiche = listafiche;
    }

    @NonNull
    @Override
    public AdaptarRecyclerFicheros.ViemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ContextitoFiche);
        View view=inflater.inflate(R.layout.activity_lista_ficheros,null);
        view.setOnClickListener(this);
        ViemHolder viewHoder=new ViemHolder(view);

        return viewHoder;
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptarRecyclerFicheros.ViemHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.fichero1.setText(Listafiche.get(position).getDescripcionFich());
        holder.fecha.setText(Listafiche.get(position).getFecha());
        holder.tema.setText(Listafiche.get(position).getTema());
        holder.num.setText( Listafiche.get(position).getNum());

        holder.imagenPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 =
                        Toast.makeText(ContextitoFiche.getApplicationContext(),
                                "Toast por defecto" +Listafiche.get(position).getNum(), Toast.LENGTH_SHORT);
                toast1.show();

                DescargarDocumentos(position,Listafiche.get(position).getDescripcionFich());




            }
        });

    }

    @Override
    public int getItemCount() {
        return Listafiche.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imgpdf:
                                break;
        }
    }

    public class ViemHolder extends RecyclerView.ViewHolder {

        private TextView fichero1, fecha, tema, num;
        ImageView imagenPDF;


        public ViemHolder(View view) {
            super(view);
            fichero1=(TextView) view.findViewById(R.id.txtDecripcion);
            fecha=(TextView) view.findViewById(R.id.txtFecha);
            tema=(TextView) view.findViewById(R.id.txtTema);
            num=(TextView) view.findViewById(R.id.lblnum);
            imagenPDF=(ImageView) view.findViewById(R.id.imgpdf);

        }
    }




    public void DescargarDocumentos( int position, String nombrepdf){

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Listafiche.get(position).getUrl()));
        request.setDescription("Archivo PDF");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle(nombrepdf);
        request.setVisibleInDownloadsUi(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(ContextitoFiche.getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "file.pdf");
        DownloadManager manager = (DownloadManager) ContextitoFiche.getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            manager.enqueue(request);
        } catch (Exception e) {
            Toast.makeText(ContextitoFiche.getApplicationContext(),"Error: "  + e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }


}
