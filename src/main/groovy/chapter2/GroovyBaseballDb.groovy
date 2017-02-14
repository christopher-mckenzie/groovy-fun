package chapter2

import chapter2.bean.Stadium
import groovy.sql.Sql

/**
 * Created by req85404 on 02/13/2017.
 */
class GroovyBaseballDb {

    def populateDatabase(List<Stadium> stadiums){
        Sql db = Sql.newInstance(
                'jdbc:mysql://localhost:3306/baseball',
                '...username...',
                '...password...',
                'com.mysql.jdbc.Driver'
        )
        db.execute "drop table if exists stadium;"
        db.execute '''
            create table stadium(
                id int not null auto_increment,
                name varchar(200) not null,
                city varchar(200) not null,
                state char(2) not null,
                team char(3) not null,
                latitude double,
                longitude double,
                primary key(id)
            );
        '''
        GroovyBaseballGeocoder geocoder = new GroovyBaseballGeocoder()
        stadiums.each { stadium ->
            stadium = geocoder.fillInLatLng(stadium)
            db.execute """
            insert into stadium(name, city, state, team, latitude, longitude)
            values(${stadium.name}, ${stadium.city}, ${stadium.state}, ${stadium.team}, ${stadium.latitude}, ${
                stadium.longitude
            });
            """
        }
//        test results ->
//        assert db.rows('select * from stadium').size() == stadiums.size()
//        db.eachRow('select latitude, longitude from stadium'){ row ->
//            assert row.latitude > 25 && row.latitude < 48
//            assert row.longitude > -123 && row.longitude < -71
//        }

    }
}
