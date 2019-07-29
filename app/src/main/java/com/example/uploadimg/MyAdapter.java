package com.example.uploadimg;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/*class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    private ItemClickListener itemClickListener;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListner){
        this.itemClickListener=itemClickListner;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}*/

public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageViewHolder>  {
    private Context mContext;
    private List<Upload> mUploads;


    private DatabaseReference mDataReference;



    public MyAdapter(Context context, List<Upload> uploads)
    {
        mContext=context;
        mUploads=uploads;


    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(mContext).inflate(R.layout.layout_images, viewGroup,false);
        return  new ImageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        final Upload uploadCur=mUploads.get(i);
        imageViewHolder.img_description.setText(uploadCur.getImgName());
        imageViewHolder.desc.setText(uploadCur.getDescription());
        imageViewHolder.timeAndDate.setText(uploadCur.getTime());


        imageViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                   // Toast.makeText(mContext,"Long Click:"+ mUploads.get(position),Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder adb;
                    AlertDialog ad;
                    adb=new AlertDialog.Builder(mContext);
                    mDataReference= FirebaseDatabase.getInstance().getReference("uploads/");
                    adb.setTitle("Your Response Please");
                    //adb.setMessage("Accept Or Reject Report");
                    adb.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(mContext,"Clicked Yes",Toast.LENGTH_SHORT).show();
                            HashMap<String,Object>hashMap=new HashMap<>();
                            hashMap.put("imgName","Accepted");
                            mDataReference.child(uploadCur.getUid()).updateChildren(hashMap);
                            Intent intent=new Intent(mContext,MainActivity.class);
                            mContext.startActivity(intent);
                        }
                    });

                    adb.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          //  Toast.makeText(mContext,"Clicked No",Toast.LENGTH_SHORT).show();
                            HashMap<String,Object>hashMap=new HashMap<>();
                            hashMap.put("imgName","Rejected");
                            mDataReference.child(uploadCur.getUid()).updateChildren(hashMap);
                            Intent intent=new Intent(mContext,MainActivity.class);
                            mContext.startActivity(intent);
                        }
                    });

                   adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                          //  Toast.makeText(mContext,"Clicked Exit",Toast.LENGTH_SHORT).show();
                        }
                    });

                    ad=adb.create();
                    ad.setMessage("Accept Or Reject Report");

                    ad.show();
                }
               /* else
                    Toast.makeText(mContext," "+ mUploads.get(position),Toast.LENGTH_SHORT).show();*/
            }
        });

        Picasso.get()
                .load(uploadCur.getImgUrl())
                .placeholder(R.drawable.imagepreview)
                .fit()
                .centerCrop()
                .into(imageViewHolder.image_view);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView img_description;
        public ImageView image_view;
        public TextView timeAndDate;
        public TextView desc;
        private ItemClickListener itemClickListener;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            img_description=itemView.findViewById(R.id.img_description);
            image_view=itemView.findViewById(R.id.image_view);
            timeAndDate=itemView.findViewById(R.id.timedate);
            desc=itemView.findViewById(R.id.desc);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListner){
            this.itemClickListener=itemClickListner;
        }

        @Override
        public void onClick(View v) {

            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
