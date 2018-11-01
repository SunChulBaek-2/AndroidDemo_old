import android.content.Intent

// Intent.getStringExtra(name)을 Intent[name] 으로 맵핑
// Intent.putExtra(name, value)를 Intent[name] = value 로 맵핑
operator fun Intent?.get(name: String?): String? = this?.getStringExtra(name)
operator fun Intent?.set(name: String?, value: String?): Intent? = this?.putExtra(name, value)