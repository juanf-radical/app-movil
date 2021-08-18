package com.example.appmovil.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmovil.R;

import java.util.List;

public class ClientesAdaptador extends RecyclerView.Adapter<ClientesAdaptador.ViewHolder>{

    private List<Clientes> clientesList;
    private Context context;

    public ClientesAdaptador(List<Clientes> clientesList, Context context) {
        this.clientesList = clientesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_clientes,parent,false);
        return new ViewHolder(view);
/*        return null; */
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtNombre.setText(clientesList.get(position).getNombreCliente());
        holder.txtInformacion.setText(clientesList.get(position).getInformacionCliente());
        Glide.with(context)
                .load(clientesList.get(position).getFoto())
                .centerCrop()
                .into(holder.imgFoto);
    }

    @Override
    public int getItemCount() {
        return clientesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView txtNombre;
        private TextView txtInformacion;

        public ViewHolder(View itemView) {
            super(itemView);

            imgFoto = itemView.findViewById(R.id.img_foto);
            txtNombre = itemView.findViewById(R.id.txt_nombre);
            txtInformacion = itemView.findViewById(R.id.txt_informacion);
        }
    }
}
