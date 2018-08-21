package ssun.pe.kr.androiddemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ssun.pe.kr.androiddemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        MainFragment().let { fragment ->
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, fragment, MainFragment.TAG)
                    .commit()
        }
    }
}
