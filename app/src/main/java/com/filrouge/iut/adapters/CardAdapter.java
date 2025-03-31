package com.filrouge.iut.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.filrouge.iut.R;
import com.filrouge.iut.models.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cards;
    private CardClickListener listener;

    public interface CardClickListener {
        void onCardClick(Card card, int position);
        void onCardLongClick(Card card, int position);
    }

    public CardAdapter(List<Card> cards, CardClickListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.bind(card);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCardClick(card, position);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onCardLongClick(card, position);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView cardName;
        private ImageView cardImage;
        private RatingBar cardLevel;
        private View cardOverlay;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.card_name);
            cardImage = itemView.findViewById(R.id.card_image);
            cardLevel = itemView.findViewById(R.id.card_level);
            cardOverlay = itemView.findViewById(R.id.card_overlay);
        }

        public void bind(Card card) {
            cardName.setText(card.getName());

            // Pour le prototype, utilisez une image placeholder
            cardImage.setImageResource(R.drawable.card_placeholder);

            // Afficher le niveau de la carte (0-5)
            cardLevel.setRating(card.getLevel());

            // Si la carte n'est pas débloquée, afficher un overlay gris
            if (!card.isUnlocked()) {
                cardOverlay.setVisibility(View.VISIBLE);
            } else {
                cardOverlay.setVisibility(View.GONE);
            }
        }
    }
}