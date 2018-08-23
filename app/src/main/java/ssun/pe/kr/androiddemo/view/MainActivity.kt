package ssun.pe.kr.androiddemo.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ssun.pe.kr.androiddemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, MainFragment(), MainFragment.TAG)
                .commit()
    }
}
