package com.anamaniac.companyassistant.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.Usersmodel;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewAdapter extends RecyclerView.Adapter<ProfileViewAdapter.DetailsViewHolder> {
    private List<Usersmodel> userlist= new ArrayList<>();
    Context mContext;

    public ProfileViewAdapter(Context context){ context = mContext;
    }

    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        int id = R.layout.recyclerview_2;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(id,parent,shouldAttachToParentImmediately);
        return new DetailsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position)
    {
        Usersmodel value = userlist.get(position);
        holder.namedisplay.setText(value.getFirstname());
        holder.surnamedisplay.setText(value.getSurname());


    }

    public void setUserlist (List<Usersmodel> userlist){
        this.userlist =userlist;
        notifyDataSetChanged();
    }
    private EmployeesInterface clicklistener;
    public void setClicklistener(EmployeesInterface itemclicklistener)
    {
        this.clicklistener = itemclicklistener;
    }


    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView namedisplay;
        public TextView surnamedisplay;
        public DetailsViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            namedisplay = view.findViewById(R.id.NameDisplay);
            surnamedisplay = view.findViewById(R.id.SurnameDisplay);
        }

        @Override
        public void onClick(View v) {
            clicklistener.onClick(v,getAdapterPosition());
        }
    }
}
