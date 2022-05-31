package com.example.architecturepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.architecturepractice.viewmodels.DogRandomViewModel;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageViewDog;
    private Button mButtonNextDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewDog = findViewById(R.id.image_view);
        mButtonNextDog = findViewById(R.id.random_btn);

        DogRandomViewModel dogRandomViewModel = new DogRandomViewModel(getApplication());
        observerDogVM(dogRandomViewModel);

        mButtonNextDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogRandomViewModel.fetchDogData();
            }
        });
    }
    public void observerDogVM(DogRandomViewModel vm){
        vm.getDogRandomLiveData().observe(this, dogRandomResponse -> {
            if (dogRandomResponse.getStatus().equals("success")){
                Picasso.get().load(dogRandomResponse.getMessage()).into(mImageViewDog);
            }
        });
    }
}