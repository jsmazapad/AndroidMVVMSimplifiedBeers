package es.jsm.mvvm.beer.data.network.deserializers;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.List;

import es.jsm.mvvm.beer.model.Article;



public class ArticlesXMLConverter implements Converter<FeedXMEntity> {
    @Override
    public FeedXMEntity read(InputNode node) throws Exception {
        System.out.println("converter");

       List <Article> articles = new ArrayList<>();

        //Avanzamos hasta la lista real
        node.getNext();
        node.getNext();
        node.getNext();

        InputNode itemNode = node.getNext("item");
        while (itemNode != null) {


            String title = itemNode.getNext("title").getValue();
            String link  = itemNode.getNext("link").getValue();
            String description  = itemNode.getNext("description").getValue();
            String author  = itemNode.getNext("author").getValue();
            String pubDateString  = itemNode.getNext("pubDate").getValue();
            String origLink  = itemNode.getNext("origLink").getValue();

            Article article = new Article(title, link, description, author, pubDateString, origLink);

            articles.add(article);

            //Iteramos al siguiente elemento
            itemNode = node.getNext("featureMember");
        }


        //propertyValue.foodList = (propertyValueItems);
        //propertyValue.setText(propertyValueText);

        return new FeedXMEntity(articles);
    }

    @Override
    public void write(OutputNode node, FeedXMEntity value) throws Exception {

    }

}
