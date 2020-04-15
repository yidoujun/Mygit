//package com.ifind.webmagic;

//import com.ifind.dao.ArticleDao;
//import com.ifind.entity.Article;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import us.codecraft.webmagic.ResultItems;
//import us.codecraft.webmagic.Task;
//import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 从processor获取数据并持久化到数据库
 */
//@Service("baiduXuShuPipeline")
//public class BaiduXuShuPipeline implements Pipeline {
//
//    @Autowired
//    private Article articleDao;
//
//
//    @Override
//    public void process(ResultItems resultItems, Task task) {
//        String articleTitle = resultItems.get("articleTitle");
//        String articleLike = resultItems.get("articleLike");
//        String articleReadNum = resultItems.get("articleReadNum");
//        String articleAuthor = resultItems.get("articleAuthor");
//        String articleReference = resultItems.get("articleReference");
//        String articlePub = resultItems.get("articlePub");
//
//        Article article = new Article();
//        article.setArticleTitle(articleTitle);
//        article.setArticleLike(articleLike);
//        article.setArticleReadNum(articleReadNum);
//        article.setArticleAuthor(articleAuthor);
//        article.setArticleReference(articleReference);
//        article.setArticlePub(articlePub);
//        /**
//         * 保存到数据库
//         */
//        if (article.getArticleAuthor() != null){
//            System.out.println(article.toString());
//            articleDao.insertArticle(article);
//        }
//    }
//}
