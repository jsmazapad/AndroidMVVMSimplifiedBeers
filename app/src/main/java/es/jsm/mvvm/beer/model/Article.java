package es.jsm.mvvm.beer.model;

import java.util.Date;

public class Article {
        private String title;
        private String link;
        private String description;
        private String author;
        private String pubDateString;
        private String origLink;

        public Article(String title, String link, String description, String author, String pubDateString, String origLink) {
                this.title = title;
                this.link = link;
                this.description = description;
                this.author = author;
                this.pubDateString = pubDateString;
                this.origLink = origLink;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getLink() {
                return link;
        }

        public void setLink(String link) {
                this.link = link;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getPubDateString() {
                return pubDateString;
        }

        public void setPubDateString(String pubDate) {
                this.pubDateString = pubDate;
        }

        public String getOrigLink() {
                return origLink;
        }

        public void setOrigLink(String origLink) {
                this.origLink = origLink;
        }
}
