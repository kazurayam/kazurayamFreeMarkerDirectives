package com.kazurayam.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadAllLinesDirectiveTest extends Base {

    public ReadAllLinesDirectiveTest() throws IOException {
        super();
    }

    @Test
    public void execute() throws IOException, TemplateException {
        // custom directive "repeat" is as a shared variable.
        // See Base.java

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("readAllLinesDemo.ftl");

        /* Merge data-model with template */
        Writer out = new StringWriter();
        temp.process(model, out);

        String output = out.toString();
        assertNotNull(output);
        assertTrue(output.contains("<tr><td>0</td><td>publishedDate,uri,title,link,description,author</td></tr>"));
        System.out.println(output);
        /*
    <tr><td>0</td><td>publishedDate,uri,title,link,description,author</td></tr>
    <tr><td>1</td><td>Thu Mar 10 20:00:00 JST 2022,31596,"OOO Until TBD? Majority of Canadian Office Workers Want Remote Work to Stay ",https://press.aboutamazon.com/news-releases/news-release-details/ooo-until-tbd-majority-canadian-office-workers-want-remote-work,"Half of Canadian office workers say working mostly/entirely remote is their ideal scenario; only one-quarter prefer mostly/entirely in office Ability to work remotely and flexible work hours are now more important to office workers than workplace culture, development/growth opportunities and","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>2</td><td>Sat Mar 05 10:00:00 JST 2022,31591,Amazon travaille en collaboration avec des ONG et ses employés pour offrir un soutien immédiat au peuple ukrainien,https://press.aboutamazon.com/news-releases/news-release-details/amazon-travaille-en-collaboration-avec-des-ong-et-ses-employes,"Comme beaucoup d'entre vous à travers le monde, nous observons ce qui se passe en Ukraine avec horreur, inquiétude et cœur lourds. Bien que nous n’ayons pas d'activité commerciale directe en Ukraine, plusieurs de nos employés et partenaires sont originaires de ce pays ou entretiennent un lien","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>3</td><td>Fri Mar 04 02:45:00 JST 2022,31586,Amazon Announces Partnerships with Universities and Colleges in Texas to Fully Fund Tuition for Local Hourly Employees,https://press.aboutamazon.com/news-releases/news-release-details/amazon-announces-partnerships-universities-and-colleges-texas,"Amazon employees in the U.S. will benefit from new Career Choice partnerships with more than 140 Universities and Colleges including several colleges and universities in Texas as well as national non-profit online providers Southern New Hampshire University , Colorado State University – Global,","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>4</td><td>Thu Mar 03 22:00:00 JST 2022,31576,Amazon Boosts Upskilling Opportunities for Hourly Employees by Partnering with More Than 140 Universities and Colleges to Fully Fund Tuition,https://press.aboutamazon.com/news-releases/news-release-details/amazon-boosts-upskilling-opportunities-hourly-employees,"Amazon employees in the U.S. will benefit from new Career Choice partnerships with Southern New Hampshire University , Colorado State University–Global, Western Governors University , National University , and numerous local universities Amazon also partners with GEDWorks and Smart Horizons to","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>5</td><td>Wed Mar 02 18:00:00 JST 2022,31571,AWS and Bundesliga Debut Two New Match Facts Giving Fans Insight into Germany’s Top Football Players and Teams,https://press.aboutamazon.com/news-releases/news-release-details/aws-and-bundesliga-debut-two-new-match-facts-giving-fans-insight,"The two new stats, “Set Piece Threat” and “Skill,” powered by AWS machine learning and analytics services, enhance the football fan experience and give invaluable strategic insights to teams SEATTLE --(BUSINESS WIRE)--Mar. 2, 2022-- Amazon Web Services (AWS), an Amazon.com , Inc.","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>6</td><td>Tue Mar 01 23:07:00 JST 2022,31551,AWS et la LNH dévoilent la nouvelle statistique Probabilité de mise au jeu afin de rapprocher les amateurs de hockey de l'action sur la glace,https://press.aboutamazon.com/news-releases/news-release-details/aws-et-la-lnh-devoilent-la-nouvelle-statistique-probabilite-de,"Une nouvelle statistique issue de l'apprentissage automatique permet de prédire quel joueur a le plus de chances de remporter une mise au jeu avant que la rondelle ne tombe SEATTLE – 1er mars 2022 – Amazon Web Services, Inc. (AWS), une entreprise Amazon.com, Inc.","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>7</td><td>Tue Mar 01 23:00:00 JST 2022,31546,Amazon Luna Cloud Gaming Service Now Available to Everyone in Mainland U.S. with Unique Offer for Amazon Prime Members,https://press.aboutamazon.com/news-releases/news-release-details/amazon-luna-cloud-gaming-service-now-available-everyone-mainland,"All customers in mainland United States can game with Amazon Luna on devices they already own Prime Members can try Luna for free: new Prime Gaming Channel offers rotating selection of free titles, including Immortals Fenyx Rising Introducing Retro Channel and Jackbox Games Channel, expanding","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>8</td><td>Tue Mar 01 17:01:00 JST 2022,31541,AWS and the NHL Unveil New Face-off Probability Stat to Bring Hockey Fans Closer to the Action on the Ice,https://press.aboutamazon.com/news-releases/news-release-details/aws-and-nhl-unveil-new-face-probability-stat-bring-hockey-fans,"New machine learning stat predicts which player is more likely to win a face-off before the puck is dropped SEATTLE --(BUSINESS WIRE)--Mar. 1, 2022-- Amazon Web Services, Inc. (AWS), an Amazon.com, Inc. company (NASDAQ: AMZN), and the National Hockey League (NHL) today announced Face-off","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>9</td><td>Wed Feb 23 21:00:00 JST 2022,31531,Amazon Canada lance un nouveau programme qui enseigne le codage par le biais de la musique d'artistes autochtones,https://press.aboutamazon.com/news-releases/news-release-details/amazon-canada-lance-un-nouveau-programme-qui-enseigne-le-codage,"Amazon Canada fait équipe avec TakingITGlobal, un organisme qui engage les jeunes activement, dans le cadre d'un nouveau programme qui permet d'apprendre des compétences technologiques tout en favorisant la justice sociale Les étudients remixeront des chansons d'artistes autochtones et courront la","Amazon.com, Inc. - Press Room News Releases"</td></tr>
    <tr><td>10</td><td>Wed Feb 23 21:00:00 JST 2022,31566,Amazon Canada launches new program to teach coding through music from Indigenous artists,https://press.aboutamazon.com/news-releases/news-release-details/amazon-canada-launches-new-program-teach-coding-through-music,"Amazon Canada partners with youth empowerment charity TakingITGlobal for a new program that builds technology skills while promoting social justice Students will remix music from Indigenous artists and enter for the chance to win $5,000 scholarships funded by Amazon Music Your Voice is Power aims","Amazon.com, Inc. - Press Room News Releases"</td></tr>

         */
    }
}
