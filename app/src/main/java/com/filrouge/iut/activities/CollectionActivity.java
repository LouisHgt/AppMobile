package com.filrouge.iut.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filrouge.iut.R;
import com.filrouge.iut.adapters.CardAdapter;
import com.filrouge.iut.models.Card;
import com.filrouge.iut.utils.CardManager;

import java.util.List;

public class CollectionActivity extends AppCompatActivity implements CardAdapter.CardClickListener {

    private RecyclerView recyclerView;
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        // Configurer la barre d'action pour afficher le bouton de retour
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Collection");
        }

        // Configurer le RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Obtenir toutes les cartes
        List<Card> cards = CardManager.getInstance().getCollection().getCards();

        // Configurer l'adapter
        adapter = new CardAdapter(cards, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCardClick(Card card, int position) {
        // Afficher les détails de la carte (pour le prototype, simplement afficher un message)
        if (card.isUnlocked()) {
            // Ici, vous pourriez implémenter un zoom sur la carte
        }
    }

    @Override
    public void onCardLongClick(Card card, int position) {
        // Fonctionnalité supplémentaire pour un appui long
    }
}