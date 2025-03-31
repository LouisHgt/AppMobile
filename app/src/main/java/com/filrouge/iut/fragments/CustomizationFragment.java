package com.filrouge.iut.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.filrouge.iut.R;
import com.filrouge.iut.models.Card;
import com.filrouge.iut.utils.CardManager;

import java.util.UUID;

public class CustomizationFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText cardNameInput;
    private EditText cardDescriptionInput;
    private ImageView cardImagePreview;
    private Button selectImageButton;
    private Button createCardButton;

    private Uri selectedImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customization, container, false);

        // Initialiser les vues
        cardNameInput = view.findViewById(R.id.card_name_input);
        cardDescriptionInput = view.findViewById(R.id.card_description_input);
        cardImagePreview = view.findViewById(R.id.card_image_preview);
        selectImageButton = view.findViewById(R.id.select_image_button);
        createCardButton = view.findViewById(R.id.create_card_button);

        // Configurer les événements
        selectImageButton.setOnClickListener(v -> openGallery());
        createCardButton.setOnClickListener(v -> createCustomCard());

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            cardImagePreview.setImageURI(selectedImageUri);
        }
    }

    private void createCustomCard() {
        String name = cardNameInput.getText().toString().trim();
        String description = cardDescriptionInput.getText().toString().trim();

        if (name.isEmpty()) {
            cardNameInput.setError("Please enter a name");
            return;
        }

        if (description.isEmpty()) {
            cardDescriptionInput.setError("Please enter a description");
            return;
        }

        if (selectedImageUri == null) {
            Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Pour le prototype, on utilise simplement l'URI comme chaîne
        String imageReference = selectedImageUri.toString();

        // Créer une nouvelle carte personnalisée
        Card customCard = new Card(
                "custom_" + UUID.randomUUID().toString(),
                name,
                description,
                imageReference
        );

        customCard.setCustom(true);
        customCard.setUnlocked(true);
        customCard.setLevel(1);

        // Ajouter à la collection
        CardManager.getInstance().addCustomCard(customCard);

        Toast.makeText(getContext(), "Custom card created successfully!", Toast.LENGTH_SHORT).show();

        // Réinitialiser le formulaire
        cardNameInput.setText("");
        cardDescriptionInput.setText("");
        cardImagePreview.setImageResource(R.drawable.card_placeholder);
        selectedImageUri = null;
    }
}