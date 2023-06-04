package zoconutintermediatetask.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import zoconutintermediatetask.fragmentList.AuthorsFragment
import zoconutintermediatetask.fragmentList.EpisodeFragment
import zoconutintermediatetask.fragmentList.RecentFragment
import zoconutintermediatetask.fragmentList.TopicFragment
import com.example.zoconutintermediatetask.R
import zoconutintermediatetask.adapter.ViewPagerAdapter
import com.example.zoconutintermediatetask.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        supportActionBar?.hide()


        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(RecentFragment(), "Recent")
        adapter.addFragment(TopicFragment(), "Topic")
        adapter.addFragment(AuthorsFragment(), "Authors")
        adapter.addFragment(EpisodeFragment(), "Episode")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onBackPressed() {
        onDestroy()
        super.onBackPressed()
    }
}