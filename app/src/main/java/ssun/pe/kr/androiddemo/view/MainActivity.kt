package ssun.pe.kr.androiddemo.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.view.presenter.MainPresenter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        MainFragment().let { view ->
            MainPresenter(view)

            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, view, MainFragment.TAG)
            transaction.commit()
        }
    }
}
