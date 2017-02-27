package chapter2.groovy_baseball

import chapter2.bean.Stadium

/**
 * Created by req85404 on 02/13/2017.
 * <h3>Info:</h3>
 * Given a date results in all MLB games returned for that date. The games are  also marked</br>
 * by latitudde and longitude of the home team stadium,
 */
class BaseballImpl {
    def stadiums =  {
        //<< append method
        stadiums <<
                new Stadium(name: 'Angel Stadium', city: 'Anaheim', state: 'CA', team: 'ana')
        stadiums <<
                new Stadium(name: 'Chase Field', city: 'Phoenix', state: 'AZ', team: 'ari')
    }
    static main(def args){

    }
}