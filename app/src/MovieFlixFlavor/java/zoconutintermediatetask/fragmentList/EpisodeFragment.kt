package zoconutintermediatetask.fragmentList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import zoconutintermediatetask.adapter.EpisodeRecyclerviewAdapter
import zoconutintermediatetask.model.Podcast
import com.example.zoconutintermediatetask.R
import com.example.zoconutintermediatetask.databinding.FragmentEpisodeBinding
import com.google.firebase.database.*

class EpisodeFragment : Fragment() {
    private lateinit var binding : FragmentEpisodeBinding
    private lateinit var database : DatabaseReference
    private lateinit var arrPodcast : ArrayList<Podcast>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_episode, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrPodcast = arrayListOf<Podcast>()

        binding.episodeRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.episodeRecyclerview.setHasFixedSize(true)
//        binding.recentRecyclerview.adapter = RecentRecyclerViewAdapter(requireContext(),arrPodcast)
        getContactData()
    }

    private fun getContactData() {
        database = FirebaseDatabase.getInstance().getReference("podcast")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val podcast = userSnapshot.getValue(Podcast::class.java)
                        arrPodcast.add(podcast!!)
                    }
                    binding.episodeRecyclerview.adapter = EpisodeRecyclerviewAdapter(requireContext(),arrPodcast)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}