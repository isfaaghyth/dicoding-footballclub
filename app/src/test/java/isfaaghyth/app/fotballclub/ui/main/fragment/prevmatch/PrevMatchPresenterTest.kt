package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import io.reactivex.Single
import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.network.Routes
import isfaaghyth.app.fotballclub.utils.reactive.TestSchedulerProvider
import kotlinx.coroutines.experimental.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class PrevMatchPresenterTest {

    @Mock
    private lateinit var baseView: BaseView

    @Mock
    private lateinit var view: PrevMatchView

    @Mock
    private lateinit var routes: Routes

    private lateinit var presenter: PrevMatchPresenter

    private lateinit var scheduler: TestSchedulerProvider

    @Mock
    private lateinit var matches: MatchEvent

    private lateinit var singleMatches: Single<MatchEvent>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        singleMatches = Single.just(matches)
        presenter = PrevMatchPresenter(view, scheduler)
        `when`(routes.prevMatch("4328")).thenReturn(singleMatches)
    }

    @Test
    fun getPrevMatch() {
        launch { verify(view).showLoading() }
        presenter.getPrevMatch()
        launch { verify(view).onPrevMatchData(matches) }
        launch { verify(view).hideLoading() }
    }

}