package ssun.pe.kr.androiddemo.presentation.main

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = SparseArray<Fragment>()

    override fun getItem(position: Int): Fragment = fragments[position]?.let {
        it
    } ?: when (position) {
        0 -> ShopFragment.newInstance(position)
        else -> ImageFragment.newInstance(position)
    }.apply {
        fragments.put(position, this)
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> "쇼핑"
        else -> "이미지"
    }
}