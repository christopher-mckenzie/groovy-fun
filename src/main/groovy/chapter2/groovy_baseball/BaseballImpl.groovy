package chapter2.groovy_baseball

import chapter2.bean.Stadium

/**
 * Created by req85404 on 02/13/2017.
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