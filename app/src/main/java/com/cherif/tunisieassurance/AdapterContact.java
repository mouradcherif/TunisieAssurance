package com.cherif.tunisieassurance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ClientViewHolder>{

    private Context context;
    private ArrayList<ModelClient> clientList;

    public AdapterContact(Context context, ArrayList<ModelClient> clientList) {
        this.context = context;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_contact,parent,false);
        ClientViewHolder vh = new ClientViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {

        ModelClient modelClient = clientList.get(position);
        String id = modelClient.getId();
        String name = modelClient.getName();
        String region = modelClient.getRegion();
        String phone = modelClient.getPhone();
        String email = modelClient.getEmail();



        holder.clientName.setText(name);
        holder.clientRegion.setText(region);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddEditClient.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("region", region);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);

                intent.putExtra("isEditMode",true);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    class ClientViewHolder extends RecyclerView.ViewHolder{

        ImageView clientImage ;
        LinearLayout cardView;
        TextView clientName,clientRegion;


        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            clientImage = itemView.findViewById(R.id.client_image);
            cardView = itemView.findViewById(R.id.CardView);
            clientName = itemView.findViewById(R.id.item_name);
            clientRegion = itemView.findViewById(R.id.item_region);

        }
    }
}
