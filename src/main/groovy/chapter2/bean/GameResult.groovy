package chapter2.bean

/**
 * Created by req85404 on 02/22/2017.
 */
class GameResult {
    String home;
    String away;
    String hScore;
    String aScore;
    Stadium stadium;

    String toString() { "$home $hScore, $away $aScore" }
}
