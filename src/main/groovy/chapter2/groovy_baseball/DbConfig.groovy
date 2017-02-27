package chapter2.groovy_baseball

import chapter2.bean.Stadium
import groovy.sql.Sql

/**
 * Created by req85404 on 02/13/2017.
 */
class DbConfig {

    def populateDatabase(List<Stadium> stadiums){
        Sql db = getInstance()
        createTable db
        BaseballGeocoder geocoder = new BaseballGeocoder()
        stadiums.each { stadium ->
            stadium = geocoder.fillInLatLng(stadium)
            db.execute """
            insert into stadium(name, city, state, team, latitude, longitude)
            values(${stadium.name}, ${stadium.city}, ${stadium.state}, ${stadium.team}, ${stadium.latitude}, ${
                stadium.longitude
            });
            """
        }
    }

    def createTable(Sql db){
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
    }

    Sql getInstance(){
        Sql db = Sql.newInstance(
                'jdbc:mysql://localhost:3306/baseball',
                '...username...',
                '...password...',
                'com.mysql.jdbc.Driver'
        )
        return db;
    }

}
