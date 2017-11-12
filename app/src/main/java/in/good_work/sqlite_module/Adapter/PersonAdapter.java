package in.good_work.sqlite_module.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.good_work.sqlite_module.Model.Person;
import in.good_work.sqlite_module.R;
import in.good_work.sqlite_module.common.ItemClickListener;

/**
 * Created by Alex on 06.11.2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {
    private ArrayList<Person> items = new ArrayList<>();
    private ItemClickListener<Person> listener;

    public void setInterface(ItemClickListener<Person> i) {
        this.listener = i;
    }

    public void addAll(List<Person> person){
        int pos = getItemCount();
        this.items.addAll(person);
        notifyItemRangeChanged(pos, this.items.size());
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        return new PersonViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        holder.bind(items.get(position));
        holder.setListener(this.listener);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            listener.onItemClicked(items.get(position), true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}

