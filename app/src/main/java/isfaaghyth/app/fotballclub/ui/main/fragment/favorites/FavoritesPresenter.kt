package isfaaghyth.app.fotballclub.ui.main.fragment.favorites

import isfaaghyth.app.fotballclub.base.BasePresenter

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class FavoritesPresenter(val view: FavoritesView) : BasePresenter<FavoritesView>() {
    init { super.attachView(view) }
}