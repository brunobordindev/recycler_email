package com.example.recyclerviewcursotiagoaguiar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerviewcursotiagoaguiar.model.Emails;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private EmailAdapter emailAdapter;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAdapter = new EmailAdapter(new ArrayList<>(Emails.fakeEmails()));

        RecyclerView rv = findViewById(R.id.recycler_view_name);
        rv.setAdapter(emailAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHandler(0,
                        ItemTouchHelper.LEFT)
        );

        helper.attachToRecyclerView(rv);

        emailAdapter.setListener(new EmailAdapter.EmailAdapterListener() {
            @Override
            public void onItemClick(int position) {
                enableActionMode(position);
            }

            @Override
            public void onItemLongClick(int position) {
                enableActionMode(position);
            }
        });
    }

    private void enableActionMode(int position) {
        if (actionMode == null)
            actionMode = startSupportActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.action_delete){
                        emailAdapter.deleteEmails();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });

        emailAdapter.toggleSelection(position);
        //pegar a quantidade de itens selecionados
        final int size = emailAdapter.selectedItems.size();
        if (size == 0){
            actionMode.finish();
        }else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback{

        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();

            //ordena a posicao atual, se nao trocaria a ordem mas se voce excluir, excluiria quem estava naquela posicao.
            Collections.swap(emailAdapter.getEmails(),from, to);
            //pegar possicao atual para qual quero mover(origem 'de - from' e destino ''para - to)'
            emailAdapter.notifyItemMoved(from, to);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            //puxando pro lado esquerdo ele deleta e arruma a posicao
            emailAdapter.getEmails().remove(viewHolder.getAdapterPosition());
            //reajusta recyclerView para nao ficar um buraco
            emailAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }

}