package com.anamaniac.companyassistant.presenter;

import android.content.Context;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.Incomemodel;
import com.anamaniac.companyassistant.view.income;
import java.util.ArrayList;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.DetailsViewHolder> {
    private List<Incomemodel> incomelist= new ArrayList<>();
    Context mContext;

    public IncomeAdapter(Context context){context = mContext;}

    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        int id = R.layout.recycler_view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(id,parent,shouldAttachToParentImmediately);
        return new DetailsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position)
    {
        Incomemodel value = incomelist.get(position);
        Integer Gains = value.getGains();
        Integer Loss = value.getLoss();
        Integer total = Gains + Loss;
        String toDisplay = Gains + "\n" + Loss + "\n" + total;
        holder.display.setText(toDisplay);

    }
    public void setIncomelist(List<Incomemodel> incomelist)
    {
        this.incomelist = incomelist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return incomelist.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder
    {
        public TextView display;
        public DetailsViewHolder(View view) {
            super(view);
            display = view.findViewById(R.id.DisplayInfo);
        }
    }
}
