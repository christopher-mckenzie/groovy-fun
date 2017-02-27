package chapter2.groovy_baseball

import chapter2.bean.Stadium

/**
 * Created by req85404 on 02/13/2017.
 * Calls out  to google's api to get the latitude and longitude of each baseball</br>
 * stadium. At  this point the stadium would require name, city,  and state.
 */
class BaseballGeocoder {
    def base = 'http://maps.googleapis.com/maps/api/geocode/xml?'
    //Parameters defined at http://maps.googleapis.com/maps/api/geocode/output?parameters

    def fillInLatLng(Stadium stadium){
        //sensor  param -> whose value is true if request is coming from a GPS-enabled device
        def url = base + [sensor: false, address: [stadium.name, stadium.city, stadium.state].collect {
            URLEncoder.encode(it, 'UTF-8') //eliminate illegal characters (spaces,  etc..)
        }.join(',') //collection of strings now seperated by ,
        ].collect { k, v -> "$k=$v" }.join('&') //join each pair seperated by = and each param with an &

        println "geopcoder url: $url"
        //XmlSlurper creates a dom tree of response
        def response = new XmlSlurper().parse(url)
        //In DOM tree result is the root element and geometry is child of root, then location is a child of geometry and so on
        //result[0] gets index 0 of result
        //everything in xml is a String
        stadium.latitude = response.result [ 0 ].geometry.location.lat.toDouble ( )
        stadium.longitude = response.result [ 0 ].geometry.location.lng.toDouble ( )

        println "geocoder result: $stadium"
        return stadium
    }
}
