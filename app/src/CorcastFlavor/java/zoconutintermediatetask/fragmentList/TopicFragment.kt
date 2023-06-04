package zoconutintermediatetask.fragmentList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import zoconutintermediatetask.adapter.TopicRecyclerViewAdapter
import zoconutintermediatetask.model.Podcast
import com.example.zoconutintermediatetask.R
import com.example.zoconutintermediatetask.databinding.FragmentTopicBinding
import com.google.firebase.database.*
import zoconutintermediatetask.adapter.RecentRecyclerViewAdapter

class TopicFragment : Fragment() {
    private lateinit var binding : FragmentTopicBinding
    private lateinit var database : DatabaseReference
    private lateinit var arrPodcast : ArrayList<Podcast>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_topic, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrPodcast = arrayListOf<Podcast>()

        binding.topicRecyclerview.layoutManager = LinearLayoutManager(requireContext())
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
                    binding.topicRecyclerview.adapter = TopicRecyclerViewAdapter(requireContext(),arrPodcast)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}