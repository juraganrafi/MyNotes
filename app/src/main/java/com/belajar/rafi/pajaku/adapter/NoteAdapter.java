package com.belajar.rafi.pajaku.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.belajar.rafi.pajaku.CustomOnItemClickListener;
import com.belajar.rafi.pajaku.FormAddUpdateActivity;
import com.belajar.rafi.pajaku.R;
import com.belajar.rafi.pajaku.entity.Note;

import java.util.LinkedList;

/**
 * Created by rafi on 12/19/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewholder> {
    private LinkedList<Note> listNotes;
    private Activity activity;

    public  NoteAdapter(Activity activity){
        this.activity = activity;
    }

    public LinkedList<Note> getListNotes(){
        return listNotes;
    }

    public void setListNotes(LinkedList<Note> listNotes) {
        this.listNotes = listNotes;
    }
    @Override
    public NoteViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewholder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewholder holder, int position) {
        holder.tvVehicle.setText(getListNotes().get(position).getVehicle());
        holder.tvBbnkb.setText(getListNotes().get(position).getBbnkb());
        holder.tvPkb.setText(getListNotes().get(position).getPkb());
        holder.tvSwdkllaj.setText(getListNotes().get(position).getSwdkllaj());
        holder.tvAdmSTNK.setText(getListNotes().get(position).getAdmSTNK());
        holder.tvAdmTNKB.setText(getListNotes().get(position).getAdmTNKB());
        holder.tvJumlah.setText(getListNotes().get(position).getJumlah());
        holder.tvDate.setText(getListNotes().get(position).getDate());
        holder.tvItemNotif.setText(getListNotes().get(position).getNotif());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getListNotes().get(position));
                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListNotes().size();
    }

    public class NoteViewholder extends RecyclerView.ViewHolder{

        TextView tvVehicle, tvBbnkb, tvPkb, tvSwdkllaj, tvAdmSTNK, tvAdmTNKB, tvJumlah, tvDate, tvItemNotif, tvDatePick, tvTimePick;
        CardView cvNote;
        public NoteViewholder(View itemView) {
            super(itemView);
            tvVehicle   = (TextView)itemView.findViewById(R.id.tv_item_vehicle);
            tvBbnkb     = (TextView)itemView.findViewById(R.id.tv_item_bbnkb);
            tvPkb       = (TextView)itemView.findViewById(R.id.tv_item_pkb);
            tvSwdkllaj  = (TextView)itemView.findViewById(R.id.tv_item_swdkllaj);
            tvAdmSTNK   = (TextView)itemView.findViewById(R.id.tv_item_admstnk);
            tvAdmTNKB   = (TextView)itemView.findViewById(R.id.tv_item_admtnkb);
            tvJumlah    = (TextView)itemView.findViewById(R.id.tv_item_jumlah);
            tvDate      = (TextView)itemView.findViewById(R.id.tv_item_date);
            cvNote      = (CardView)itemView.findViewById(R.id.cv_item_note);
            // + Date Time
            tvItemNotif = (TextView)itemView.findViewById(R.id.tv_item_notif);
            tvDatePick  = (TextView)itemView.findViewById(R.id.tv_item_datepick);
            tvTimePick  = (TextView)itemView.findViewById(R.id.tv_item_timepick);
        }
    }
}
