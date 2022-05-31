package com.example.architecturepractice.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.architecturepractice.services.APIClient;
import com.example.architecturepractice.services.DogService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository {
    private DogService dogService;

    private MutableLiveData<DogRandomResponse> dogLiveData = new MutableLiveData<>();

    public MutableLiveData<DogRandomResponse> getDogLiveData() {
        return dogLiveData;
    }

    public DogRepository(){
        dogService = APIClient.getRetrofitInstance().create(DogService.class);
    }

    public LiveData<DogRandomResponse> getRandomDogData(){
        dogService.fetchRandomDog().enqueue(new Callback<DogRandomResponse>() {
            @Override
            public void onResponse(Call<DogRandomResponse> call, Response<DogRandomResponse> response) {
                dogLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DogRandomResponse> call, Throwable t) {
                dogLiveData.setValue(null);
            }
        });

        return dogLiveData;
    }
}
