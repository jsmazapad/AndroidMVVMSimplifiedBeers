package es.jsm.mvvm.beer.ui.privateviews.news;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;

import com.google.android.material.slider.Slider;

import java.util.Collections;
import java.util.List;

import es.jsm.mvvm.beer.config.GeneralConfig;
import es.jsm.mvvm.beer.config.SortListConfig;
import es.jsm.mvvm.beer.core.data.repositories.responses.ListResponse;
import es.jsm.mvvm.beer.model.Article;
import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.repositories.BeersRepository;
import es.jsm.mvvm.beer.ui.privateviews.baseprivate.BasePrivateRecyclerViewModel;
import es.jsm.mvvm.beer.ui.privateviews.beers.BeersFragmentDirections;

public class NewsViewModel extends BasePrivateRecyclerViewModel<Article, Article> {




    public NewsViewModel(Application app) {
        super(app);

    }

    @Override
    public List<Article> transformResponse(ListResponse<Article> response) {
        List<Article> list = response.getResultList();
        //Ordenamos según criterio de configuración el listado
        Collections.sort(list, SortListConfig.NEWS_COMPARATOR);
        return list;
    }

    @Override
    public void onItemSelected(int position, NavController navController, Context context) {
        if (isLoading.getValue() == null || !isLoading.getValue()) {
            Article article = elements.getValue().get(position);
            navController.navigate(NewsFragmentDirections.actionNavNewsToNavArticle(article.getLink()));
        }
    }

    @Override
    public void setConstructorParameters(Object... args) {

    }

    @Override
    public void callRepositoryForData() {

        BeersRepository.getFeeds(apiResponseRepositoryHolder);
    }

}
