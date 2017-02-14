package chapter2

import chapter2.bean.Stadium
import groovy.sql.Sql

/**
 * Created by req85404 on 02/14/2017.
 */
class GetGroovyBaseballGameData {
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
    GetGroovyBaseballGameData(){
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
}
