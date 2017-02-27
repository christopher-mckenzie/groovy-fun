package chapter2

import groovy.swing.SwingBuilder
import javax.swing.ImageIcon
import javax.swing.WindowConstants
import java.awt.BorderLayout

/**
 * Created by cmcke on 2/12/2017.
 */
class GoogleUrl_HelloWorld {

    static main( def args) {
        String base = 'http://chart.apis.google.com/chart?'
        //create a map where keys and values are separated by a colon
        def params = [cht: 'p3', chs: '250x100', chd: 't:60,40', ch1: 'Hello|World']
        //collect -> take k and v and combine them separated by an equals to create a list of strings
        //join -> take result of collect and join elements separated by an  &
        String queryString = params.collect { k, v -> "$k=$v" }.join '&'

        SwingBuilder.edtBuilder {
            frame(title: 'Hello, Chart!', pack: true, visible: true, defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE) {
                label(icon: new ImageIcon("$base$queryString".toURL()), constraints: BorderLayout.CENTER)
            }
        }
        println 'Pass url prams: ' + queryString
    }
}
