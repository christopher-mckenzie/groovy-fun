package chapter2

import chapter2.bean.Stadium
import chapter2.groovy_baseball.BaseballGeocoder
import chapter2.groovy_baseball.DbConfig
import groovy.sql.Sql
import spock.lang.Specification

import javax.swing.ImageIcon
import javax.swing.WindowConstants as WC
import java.awt.BorderLayout as BL

/**
 * Created by cmcke on 2/12/2017.
 */
class Chapter2Spec  extends Specification{
    List<Stadium> stadiums =  new ArrayList<>()

    def 'geocoder result test'(){
        given:
            //<< append method
            stadiums << new Stadium ( name: 'Angel Stadium', city: 'Anaheim', state: 'CA', team: 'ana' )
            stadiums << new Stadium(name: 'Chase Field', city: 'Phoenix', state: 'AZ', team: 'ari')

        when:
            stadiums.each { stadium ->
                new BaseballGeocoder().fillInLatLng(stadium)
            }
        then:
            assert stadiums.get(0).latitude > 0.0
    }

    def 'db configuration _ create table'(){
        Sql db;
        DbConfig config = new  DbConfig()
        given:
            db = config.getInstance()
            //<< append method
            stadiums << new Stadium ( name: 'Angel Stadium', city: 'Anaheim', state: 'CA', team: 'ana' )
            stadiums << new Stadium(name: 'Chase Field', city: 'Phoenix', state: 'AZ', team: 'ari')

        when:
            config.populateDatabase stadiums

        then:
        //test results ->
        assert db.rows('select * from stadium').size() == stadiums.size()
        db.eachRow('select latitude, longitude from stadium') { row ->
            assert row.latitude > 25 && row.latitude < 48
            assert row.longitude > -123 && row.longitude < -71
        }
    }
}
