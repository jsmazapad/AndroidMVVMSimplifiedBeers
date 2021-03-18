package es.jsm.mvvm.beer.data.network.deserializers;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.util.List;

import es.jsm.mvvm.beer.core.data.network.responses.XMLObjectResponse;
import es.jsm.mvvm.beer.model.Article;

@Root(strict = false)
@Convert(value = ArticlesXMLConverter.class)
public class FeedXMEntity implements XMLObjectResponse<Article> {
    private List<Article> items;

    public FeedXMEntity(List<Article> items) {
        this.items = items;
    }

    @Override
    public List<Article> getItems() {
        return items;
    }

    public void setItems(List<Article> items) {
        this.items = items;
    }

}
