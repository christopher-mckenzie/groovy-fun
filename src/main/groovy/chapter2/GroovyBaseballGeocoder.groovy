package chapter2

import chapter2.bean.Stadium

/**
 * Created by req85404 on 02/13/2017.
 */
class GroovyBaseballGeocoder {
    def base = 'http://maps.googleapis.com/maps/api/geocode/xml?'

    def fillInLatLng(Stadium stadium){
        def url = base + [sensor: false, address: [stadium.name, stadium.city, stadium.state].collect {
            URLEncoder.encode(it, 'UTF-8')
        }.join(',')
        ].collect { k, v -> "$k=$v" }.join('&')
        //XmlSlurper creates a dom tree of response
        def response = new XmlSlurper().parse(url)
        //In DOM tree result is the root element and geometry is child of root, then location is a child of geometry and so on
        //result[0] gets index 0 of result
        //everything in xml is a String
        stadium.latitude = response.result [ 0 ].geometry.location.lat.toDouble ( )
        stadium.longitude = response.result [ 0 ].geometry.location.lng.toDouble ( )
        return stadium
    }
}
