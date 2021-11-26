package pl.coderslab.medicalregistration.controller;

import org.jsoup.select.Elements;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.medicalregistration.utils.ArticleService;

import java.io.IOException;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/seekArticle")
    public String seekArticle() {
        return "allarticles";
    }

    @GetMapping("/articles/getall")
    public String getArticles(Model model, @Param("keyWord") String keyWord) throws IOException {
        if (keyWord == null) {
            keyWord = "Fizjoterapia";
        }
        Elements elements;
        elements = articleService.getArticleElements(keyWord);
        model.addAttribute("elements", elements);
        return "allarticles";

    }


}
