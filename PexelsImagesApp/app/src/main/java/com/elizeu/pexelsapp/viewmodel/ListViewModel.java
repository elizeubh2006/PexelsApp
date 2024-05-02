package com.elizeu.pexelsapp.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.elizeu.pexelsapp.model.PexelsSearchResultModel;
import com.elizeu.pexelsapp.model.PexelsService;
import com.elizeu.pexelsapp.model.PexelsImagesModel;


import java.util.List;



import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers; 

public class ListViewModel extends ViewModel {
    public MutableLiveData<PexelsSearchResultModel> pxImages = new MutableLiveData<PexelsSearchResultModel>();
    public MutableLiveData<Boolean> pexelsImageLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private PexelsService pxService = PexelsService.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh() {
        fetchPexelsImages();
    }

    private void fetchPexelsImages() {
        loading.setValue(true);
        disposable.add(
                pxService.getPxImages()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PexelsSearchResultModel>(){

                            @Override
                            public void onSuccess(PexelsSearchResultModel pexelsSearchResultModel) {
                                pxImages.setValue(pexelsSearchResultModel);
                                pexelsImageLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                pexelsImageLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
