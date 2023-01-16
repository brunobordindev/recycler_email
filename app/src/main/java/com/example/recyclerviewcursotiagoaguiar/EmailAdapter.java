package com.example.recyclerviewcursotiagoaguiar;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewcursotiagoaguiar.model.Email;

import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder>{

    private EmailAdapterListener listener;
    private final List<Email> emails;
    final SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int currentSelectedPosition;

    EmailAdapter(List<Email> emails) {
        this.emails = emails;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setListener(EmailAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.email_item,
                parent,
                false
        );
        return new EmailViewHolder(view);
    }

    //reciclar as views e posicao
    @Override
    public void onBindViewHolder(@NonNull EmailAdapter.EmailViewHolder holder, int position) {
        Email email = emails.get(position);
        holder.bind(email);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItems.size() > 0 && listener != null){
                    listener.onItemClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listener != null)
                    listener.onItemLongClick(position);
                    return true;
            }
        });

        if (currentSelectedPosition == position) currentSelectedPosition = -1;
    }

    //qtdade de elemento que sua lista tem
    @Override
    public int getItemCount() {
        return emails.size();
    }

    void deleteEmails() {
        Log.i("Teste", "delete email");
    }

    void toggleSelection(int position) {
        currentSelectedPosition = position;
        if (selectedItems.get(position)){
            selectedItems.delete(position);
            emails.get(position).setSelected(false);
        }else {
            selectedItems.put(position, true);
            emails.get(position).setSelected(true);
        }
        notifyItemChanged(position);
    }

    class EmailViewHolder extends RecyclerView.ViewHolder{

        TextView txtUser;
        TextView txtIcon;
        TextView txtSubject;
        TextView txtPreview;
        TextView txtDate;
        ImageView imgStar;

        EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txt_user);
            txtIcon = itemView.findViewById(R.id.txt_icon);
            txtSubject = itemView.findViewById(R.id.txt_subject);
            txtPreview = itemView.findViewById(R.id.txt_preview);
            txtDate = itemView.findViewById(R.id.txt_date);
            imgStar = itemView.findViewById(R.id.img_star);

        }

        void bind(Email email) {
            int hash = email.getUser().hashCode();
            txtIcon.setText(String.valueOf(email.getUser().charAt(0)));
            txtIcon.setBackground(oval(Color.rgb(hash, hash/2, 0), txtIcon));
            txtUser.setText(email.getUser());
            txtSubject.setText(email.getSubject());
            txtPreview.setText(email.getPreview());
            txtDate.setText(email.getDate());

            //Deixando negrito o texto
            txtUser.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);
            txtSubject.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);
            txtDate.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);

            imgStar.setImageResource(email.isStared()
                    ? R.drawable.ic_baseline_star_24_black
                    : R.drawable.ic_baseline_star_border_24);


        }

    }

    private static ShapeDrawable oval(@ColorInt int color, View view){
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(view.getHeight());
        shapeDrawable.setIntrinsicWidth(view.getWidth());
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    interface EmailAdapterListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }
}
