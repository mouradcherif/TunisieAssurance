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


public class AdapterContract extends RecyclerView.Adapter<AdapterContract.ContractViewHolder>{

    private Context context;
    private ArrayList<ModelContract> contractList;

    public AdapterContract(Context context, ArrayList<ModelContract> contractList) {
        this.context = context;
        this.contractList = contractList;
    }

    @NonNull
    @Override
    public ContractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_contract,parent,false);
        ContractViewHolder vh = new ContractViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContractViewHolder holder, int position) {

        ModelContract modelContract = contractList.get(position);
        String id = modelContract.getId();
        String ref = modelContract.getRef();
        String datedebut = modelContract.getDatedebut();
        String datefin = modelContract.getDatefin();
        String redevence = modelContract.getRedevence();


        holder.contractRef.setText(ref);
        holder.contractDatedebut.setText(datedebut);
        holder.contractDatefin.setText(datefin);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddEditClient.class);
                intent.putExtra("id", id);
                intent.putExtra("ref", ref);
                intent.putExtra("datedebut", datedebut);
                intent.putExtra("datefin",datefin);
                intent.putExtra("redevence",redevence);

                intent.putExtra("isEditMode",true);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contractList.size();
    }

    class ContractViewHolder extends RecyclerView.ViewHolder{

        ImageView clientImage ;
        LinearLayout cardView;
        TextView contractRef,contractDatefin,contractDatedebut;


        public ContractViewHolder(@NonNull View itemView) {
            super(itemView);

            clientImage = itemView.findViewById(R.id.client_image);
            cardView = itemView.findViewById(R.id.CardView);
            contractRef = itemView.findViewById(R.id.item_ref);
            contractDatefin = itemView.findViewById(R.id.item_datefin);
            contractDatedebut = itemView.findViewById(R.id.item_datedebut);
        }
    }
}