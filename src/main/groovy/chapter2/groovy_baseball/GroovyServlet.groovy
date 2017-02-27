package chapter2.groovy_baseball

/**
 * Created by req85404 on 02/27/2017.
 * sample "groovlet"
 */

response.contentType = 'text/xml'
def month = params.month
def day = params.day
def year = params.year

m = month.toInteger() < 10 ? '0' + month : month
d = day.toInteger() < 10 ? '0' + day : day
y = tear.toInteger() + ''

dao = new BaseballGameDataDao(month:m, day:d, year:y)
results = dao.getGames()
html.games {
    results.each { g ->
        game(
                outcome:"$g.away $g.aScore, $g.home $g.hScore",
                lat:g.stadium.latitude,
                lng:g.stadium.longitude
        )
    }
}
