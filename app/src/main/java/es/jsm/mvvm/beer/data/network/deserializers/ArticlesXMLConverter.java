package es.jsm.mvvm.beer.data.network.deserializers;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.List;

import es.jsm.mvvm.beer.data.network.deserializers.xmlwrapper.FeedXMEntity;
import es.jsm.mvvm.beer.model.Article;



public class ArticlesXMLConverter implements Converter<FeedXMEntity> {

    //Estructura del documento hasta llegar a los items
    public static final String CONVERTER = "converter";
    public static final String CHANNEL = "channel";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String DESCRIPTION = "description";
    public static final String LANGUAGE = "language";
    public static final String LAST_BUILD_DATE = "lastBuildDate";
    public static final String GENERATOR = "generator";
    public static final String DOCS = "docs";
    public static final String LINK1 = "link";
    public static final String INFO = "info";
    public static final String LINK2 = "link";
    //Estructura de item
    public static final String ITEM = "item";
    public static final String ITEM_TITLE = "title";
    public static final String ITEM_LINK = "link";
    public static final String ITEM_DESCRIPTION = "description";
    public static final String ITEM_AUTHOR = "author";
    public static final String ITEM_PUB_DATE = "pubDate";
    public static final String ITEM_GUID = "guid";
    public static final String ITEM_ORIG_LINK = "origLink";

    @Override
    public FeedXMEntity read(InputNode node) throws Exception {
        System.out.println(CONVERTER);

       List <Article> articles = new ArrayList<>();

        //Avanzamos hasta la lista real

        InputNode nextNode = node.getNext(CHANNEL);
        nextNode.getNext(TITLE);
         nextNode.getNext(LINK);
        nextNode.getNext(DESCRIPTION);
         nextNode.getNext(LANGUAGE);
        nextNode.getNext(LAST_BUILD_DATE);
        nextNode.getNext(GENERATOR);
        nextNode.getNext(DOCS);
        nextNode.getNext(LINK1);
        nextNode.getNext(INFO);
         nextNode.getNext(LINK2);

         //Iteramos sobre items hasta que se terminen
        InputNode itemNode = nextNode.getNext(ITEM);
        while (itemNode != null) {


            String title = itemNode.getNext(ITEM_TITLE).getValue();
            String link  = itemNode.getNext(ITEM_LINK).getValue();
            String description  = itemNode.getNext(ITEM_DESCRIPTION).getValue();
            String author  = itemNode.getNext(ITEM_AUTHOR).getValue();
            String pubDateString  = itemNode.getNext(ITEM_PUB_DATE).getValue();
            itemNode.getNext(ITEM_GUID);
            String origLink  = itemNode.getNext(ITEM_ORIG_LINK).getValue();

            Article article = new Article(title, link, description, author, pubDateString, origLink);

            articles.add(article);

            //Iteramos al siguiente elemento
            itemNode = node.getNext("item");
        }


        //propertyValue.foodList = (propertyValueItems);
        //propertyValue.setText(propertyValueText);

        return new FeedXMEntity(articles);
    }

    @Override
    public void write(OutputNode node, FeedXMEntity value) throws Exception {

    }

}
