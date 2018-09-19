package isfaaghyth.app.fotballclub.utils

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
object DelimeterUtil {
    fun comma(str: String?): String? = str?.trim()?.replace(";", "\n")
}