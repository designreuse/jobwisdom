package org.liufeng.weixin.pojo;

import java.util.List;
/**
 * 群发消息Json格式
 * @author Lzh
 *
 */
public class Article {
private List<Thumb> articles;

/**
 * @return the articles
 */
public List<Thumb> getArticles() {
	return articles;
}

/**
 * @param articles the articles to set
 */
public void setArticles(List<Thumb> articles) {
	this.articles = articles;
}
public Article() {
	// TODO Auto-generated constructor stub
}
}
