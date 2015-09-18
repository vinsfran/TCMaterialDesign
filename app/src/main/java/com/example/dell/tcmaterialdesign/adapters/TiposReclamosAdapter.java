package com.example.dell.tcmaterialdesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.tcmaterialdesign.R;
import com.example.dell.tcmaterialdesign.domain.TiposReclamos;
import com.example.dell.tcmaterialdesign.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

public class TiposReclamosAdapter extends RecyclerView.Adapter<TiposReclamosAdapter.MyViewHolder> {
    private List<TiposReclamos> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public TiposReclamosAdapter(Context context, List<TiposReclamos> l) {
        this.mList = l;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("LOG", "onCreateViewHolder");
        View v = mLayoutInflater.inflate(R.layout.item_tipo_reclamo, viewGroup, false);
        return (new MyViewHolder(v));
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("LOG", "onBindViewHolder");
        myViewHolder.tvCodigo.setText("" + mList.get(position).getCodTipoReclamo() );
        myViewHolder.tvNombre.setText( mList.get(position).getNombreTipoReclamo() );

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(TiposReclamos tipoReclamo, int position){
        mList.add(tipoReclamo);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvCodigo;
        public TextView tvNombre;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvCodigo = (TextView) itemView.findViewById(R.id.tv_codigo);
            tvNombre = (TextView) itemView.findViewById(R.id.tv_nombre);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }
    }
}
