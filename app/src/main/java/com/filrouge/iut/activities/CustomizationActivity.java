package com.filrouge.iut.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.filrouge.iut.R;
import com.filrouge.iut.models.Card;
import com.filrouge.iut.utils.CardManager;

import java.util.UUID;

public class CustomizationActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText cardNameInput;
    private EditText cardDescriptionInput;
    private ImageView cardImagePreview;
    private Button selectImageButton;
    private Button createCardButton;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        // Configurer la barre d'action
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Create Custom Card");
        }

        // Initialiser les vues
        cardNameInput = findViewById(R.id.card_name_input);
        cardDescriptionInput = findViewById(R.id.card_description_input);
        cardImagePreview = findViewById(R.id.card_image_preview);
        selectImageButton = findViewById(R.id.select_image_button);
        createCardButton = findViewById(R.id.create_card_button);

        // Configurer les événements
        selectImageButton.setOnClickListener(v -> openGallery());
        createCardButton.setOnClickListener(v -> createCustomCard());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
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
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
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

        Toast.makeText(this, "Custom card created successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}