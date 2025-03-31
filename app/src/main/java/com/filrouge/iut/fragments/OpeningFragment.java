package com.filrouge.iut.fragments;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.filrouge.iut.R;
import com.filrouge.iut.models.Card;
import com.filrouge.iut.utils.CardManager;

import java.util.List;

public class OpeningFragment extends Fragment {

    private ImageView packImage;
    private LinearLayout revealedCardsContainer;
    private Button btnOpenPack;
    private TextView instructionText;
    private View openPackContainer;
    private View cardsRevealedContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opening, container, false);

        packImage = view.findViewById(R.id.pack_image);
        revealedCardsContainer = view.findViewById(R.id.revealed_cards_container);
        btnOpenPack = view.findViewById(R.id.btn_open_pack);
        instructionText = view.findViewById(R.id.instruction_text);
        openPackContainer = view.findViewById(R.id.open_pack_container);
        cardsRevealedContainer = view.findViewById(R.id.cards_revealed_container);

        // Configurez l'image du paquet
        packImage.setImageResource(R.drawable.card_pack);

        // Configurez le bouton d'ouverture
        btnOpenPack.setOnClickListener(v -> openPack());

        // Configurez le bouton de retour à la vue principale
        Button btnBackToOpen = view.findViewById(R.id.btn_back_to_open);
        btnBackToOpen.setOnClickListener(v -> resetPackOpening());

        return view;
    }

    private void openPack() {
        // Animation de déchirement du paquet
        ObjectAnimator animator = ObjectAnimator.ofFloat(packImage, "translationX", 0f, -1000f);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                btnOpenPack.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Masquer le conteneur d'ouverture du paquet
                openPackContainer.setVisibility(View.GONE);
                // Afficher le conteneur des cartes révélées
                cardsRevealedContainer.setVisibility(View.VISIBLE);
                revealCards();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        animator.start();
    }

    private void revealCards() {
        // Obtenez 3 cartes aléatoires
        List<Card> cards = CardManager.getInstance().openPack();

        // Affichez chaque carte
        revealedCardsContainer.removeAllViews();

        for (Card card : cards) {
            View cardView = getLayoutInflater().inflate(R.layout.item_card_reveal, revealedCardsContainer, false);

            TextView cardName = cardView.findViewById(R.id.card_name);
            TextView cardDescription = cardView.findViewById(R.id.card_description);
            TextView cardLevel = cardView.findViewById(R.id.card_level);
            ImageView cardImage = cardView.findViewById(R.id.card_image);

            cardName.setText(card.getName());
            cardDescription.setText(card.getDescription());
            cardLevel.setText("Level " + card.getLevel());

            // Utilisez un placeholder par défaut pour le prototype
            cardImage.setImageResource(R.drawable.card_placeholder);

            revealedCardsContainer.addView(cardView);

            // Animation de révélation de la carte
            Animator cardAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.card_reveal_animator);
            cardAnimator.setTarget(cardView);
            cardAnimator.start();
        }
    }

    private void resetPackOpening() {
        // Masquer le conteneur des cartes révélées
        cardsRevealedContainer.setVisibility(View.GONE);
        // Afficher le conteneur d'ouverture du paquet
        openPackContainer.setVisibility(View.VISIBLE);
        // Réinitialiser l'état du paquet
        packImage.setTranslationX(0f);
        btnOpenPack.setEnabled(true);
    }
}