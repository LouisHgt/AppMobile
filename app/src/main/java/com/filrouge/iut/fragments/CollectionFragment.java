package com.filrouge.iut.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filrouge.iut.R;
import com.filrouge.iut.adapters.CardAdapter;
import com.filrouge.iut.models.Card;
import com.filrouge.iut.utils.CardManager;

import java.util.List;

public class CollectionFragment extends Fragment implements CardAdapter.CardClickListener {

    private RecyclerView recyclerView;
    private CardAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        // Configurer le RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Obtenir toutes les cartes
        List<Card> cards = CardManager.getInstance().getCollection().getCards();

        // Configurer l'adapter
        adapter = new CardAdapter(cards, this);
        recyclerView.setAdapter(adapter);

        return view;
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