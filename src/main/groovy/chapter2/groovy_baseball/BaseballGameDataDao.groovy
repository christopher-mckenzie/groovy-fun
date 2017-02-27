package chapter2.groovy_baseball

import chapter2.bean.GameResult
import chapter2.bean.Stadium
import groovy.sql.Sql

import java.util.regex.Matcher

/**
 * Created by req85404 on 02/14/2017.
 */
class BaseballGameDataDao {
    def day
    def month
    def year

    String base = 'http://gd2.mlb.com/components/game/mlb'
    Map stadiumMap = [:]

    Map abbreviations = [
            ana: 'Los Angeles (A)', ari:'Arizona',      atl:'Atlanta',
            bal:'Baltimore',        bos:'Boston',       cha:'Chicago (A)',
            chn:'Chicago (N)',      cin:'Cincinnati',   cle:'Cleveland',
            col:'Colorado',         det:'Detroit',      flo:'Florida',
            hou:'Houston',          kca:'Kansas City',  lan:'Los Angeles (N)',
            mil:'Milwaukee',        min:'Minnesota',    nya:'New York (A)',
            nyn:'New York (N)',     oak:'Oakland',      phi:'Philadelphia',
            pit:'Pittsburgh',       sdn:'San Diego',    sea:'Seattle',
            sfn:'San Francisco',    sln:'St. Louis',    tba:'Tampa Bay',
            tex:'Texas',            tor:'Toronto',      was:'Washington'
    ]

    BaseballGameDataDao(){
        fillInStadiumMap()
    }

    def fillInStadiumMap(){
        Sql db = Sql.newInstance(
                'jdbc:h2:build/baseball',
                'org.h2.Driver'
        )
        db.eachRow("select * from stadium") { row ->
            Stadium stadium = new Stadium(
                    name:row.name,
                    team:row.team,
                    latitude:row.latitude,
                    longitude:row.longitude
            )
            stadiumMap[stadium.team] = stadium
        }
        db.close()
    }
    def getGame(away, home, num){
        println "${abbreviations[away]} at ${abbreviations[home]} on ${month}/${day}/${year}"
        def url = base + "year_${year}/month_${month}/day_${day}/"
        def game = "gid_${year}_${month}_${day}_${away}mlb_${home}mlb_${num}/boxscore.xml"

        def boxscore = new XmlParser().parse("$url$game") //parse xml -> turns xml into DOM
        def awayName = boxscore.@away_fname //@ represents an attribute of xml DOM
        def awayScore = boxscore.linescore[0].@away_team_runs
        def homeName = boxscore.@home_fname
        def homeScore = boxscore.linescore[0].@home_team_runs
        println "$awayName $awayScore, $homeName $homeScore (game $num)"
        GameResult result = new GameResult(
                home:homeName,
                away:awayName,
                hScore:homeScore,
                aScore: awayScore,
                stadium:stadiumMap[home]
        )
        return result
    }
    def getGames(){
        def gameResults = []
        println "Games for ${month}/${day}/${year}"
        String url = base + "year_$year/month_$month/day_$day/"
        String gamePage = url.toURL().text
        def pattern = /\"gid_${year}_${month}_${day}_(\w*)mlb_(\w*)mlb_(\d)/

        Matcher m = gamePage =~ pattern // =~ returns an instance of jva.util.regex.Matchere
        if(m) {
            m.count.times { line ->
                String away = m[line][1] //extracted from the Matcher
                String home = m[line][2]
                String num = m[line][3]
                try {
                    GameResult gr = this.getGame(away, home, num)
                    gameResults << gr
                } catch (Exception e){
                    println "${abbreviations[away]} at ${abbreviations[home]} not started yet"
                }
            }
        }
        return gameResults
    }
}
