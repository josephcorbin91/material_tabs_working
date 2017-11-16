package info.androidhive.materialtabs.Dashboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import info.androidhive.materialtabs.Dashboard.model.MenuItemModel;
import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.utility.Utils;


public class MenuDrawerItemAdapter extends RecyclerView.Adapter<MenuDrawerItemAdapter.MyViewHolder> {
    List<MenuItemModel> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;
    public static MyViewHolder holderView;

    public MenuDrawerItemAdapter(Context context, List<MenuItemModel> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_menu_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MenuItemModel currentItem = data.get(position);
        holderView = holder;
        try {
            holder.txtMenuText.setTypeface(Utils.setFontStyle(mContext,"Helvetica_Regular"));
            holder.txtMenuText.setText(currentItem.getName());
            holder.imgvwMenuIcon.setImageResource(currentItem.getImageId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMenuText;
        private ImageView imgvwMenuIcon;
        private RelativeLayout layoutMenuItemRow;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtMenuText = itemView.findViewById(R.id.txtTitleMenuItem);
            imgvwMenuIcon = itemView.findViewById(R.id.imgvwIconMenuItem);
            layoutMenuItemRow = itemView.findViewById(R.id.layoutMenuItemRow);
        }
    }
}
