package my.edu.tarc.ass2.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.ActivityDashboardBinding

private lateinit var binding: ActivityDashboardBinding
class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hotlinePic.setOnClickListener(){
            val fragment = HotlineFragment()
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit()

        }

        binding.profilePic.setOnClickListener(){
            val navController = Navigation.findNavController(view)
            val action = FragmentDirections.actionFragmentToAnotherFragment()
            navController.navigate(action)
        }





    }
}