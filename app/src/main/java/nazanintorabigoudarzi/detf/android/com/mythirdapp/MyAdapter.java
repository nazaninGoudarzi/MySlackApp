package nazanintorabigoudarzi.detf.android.com.mythirdapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nazanin on 8/22/2017.
 */

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);

        holder.textViewTitle.setText(listItem.getTitle());
        holder.textViewTag.setText(listItem.getTag());
        Picasso.with(context).load(listItem.getImage()).placeholder(R.drawable.profile).into(holder.imageViewPro);
        if (listItem.getIcon()==true){
            Picasso.with(context).load(String.valueOf(listItem.getIcon())).placeholder(R.drawable.tick).into(holder.imageViewIcon);
        }else if (listItem.getIcon()==false){
            Picasso.with(context).load(String.valueOf(listItem.getIcon())).placeholder(R.drawable.multiply).into(holder.imageViewIcon);
        }

       /* holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(context,Full_Information.class);
                context.startActivity(intent2);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewTag;
        public ImageView imageViewPro;
        public ImageView imageViewIcon;
        public RelativeLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewTag = (TextView) itemView.findViewById(R.id.textViewTags);
            imageViewPro = (ImageView) itemView.findViewById(R.id.imageView);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
            linearLayout = (RelativeLayout)itemView.findViewById(R.id.linearLayout);
        }

    }

}
