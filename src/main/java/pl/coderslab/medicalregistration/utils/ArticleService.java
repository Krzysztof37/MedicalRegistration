package pl.coderslab.medicalregistration.utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ArticleService {


    public Elements getArticleElements(String keyWord) throws IOException {
        String formattedKeyWord = keyWord.replaceAll(" ", "+");
        Document doc = Jsoup.connect("https://scholar.google.com/scholar?as_ylo=2017&q=" + formattedKeyWord + "&hl=pl&num=60&as_sdt=0,5&as_vis=1").get();


        Elements finalList = doc.select(".gs_rt").select("a");

        return finalList;
    }


}
