package com.filrouge.iut.models;

import java.util.ArrayList;
import java.util.List;

public class CardCollection {
    private List<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCardById(String id) {
        for (Card card : cards) {
            if (card.getId().equals(id)) {
                return card;
            }
        }
        return null;
    }

    public void unlockCard(String id) {
        Card card = getCardById(id);
        if (card != null && !card.isUnlocked()) {
            card.setUnlocked(true);
        }
    }

    public void levelUpCard(String id) {
        Card card = getCardById(id);
        if (card != null && card.isUnlocked()) {
            card.levelUp();
        }
    }

    public List<Card> getUnlockedCards() {
        List<Card> unlockedCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isUnlocked()) {
                unlockedCards.add(card);
            }
        }
        return unlockedCards;
    }

    public List<Card> getLockedCards() {
        List<Card> lockedCards = new ArrayList<>();
        for (Card card : cards) {
            if (!card.isUnlocked()) {
                lockedCards.add(card);
            }
        }
        return lockedCards;
    }
}