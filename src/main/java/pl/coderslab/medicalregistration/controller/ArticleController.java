package pl.coderslab.medicalregistration.controller;

import com.google.gson.Gson;
import org.jsoup.select.Elements;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalregistration.utils.ArticleService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/articles/getall")
    public String getArticles(@Param("keyWord") String keyWord, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        if (keyWord == null) {
            keyWord = "Fizjoterapia";
        }
        Elements elements;
        elements = articleService.getArticleElements(keyWord);
        Gson gson = new Gson();



        return "dupa";

    }


}
