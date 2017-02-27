package chapter2.groovy_baseball

import chapter2.bean.GameResult
import groovy.xml.MarkupBuilder

/**
 * Created by req85404 on 02/27/2017.
 */
class BaseballXml {

    def buildXml(List<GameResult> results){
        MarkupBuilder builder = new MarkupBuilder()
        builder.games { //games is not a method but rather here it is a node
            results.each { g ->
                game ( //creates a game node with the following attributes
                        outcome:"$g.away $g.aScore, $g.home $g.hScore",
                        lat:g.stadium.latitude,
                        lng:g.stadium.longitude
                )
            }
        }
        /** sample output
         * <games>
         *     <game outcome='Boston Red Sox 4, Colorado Rockies 3'
         *       lat='39.7564956' lng='-104.9940163'/>
         * </games>
         */
    }
}
