
//package my.edu.tarc.ass2.Dashboard
//
//import ImageSliderAdapter
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Build
//import android.os.Bundle
//import androidx.activity.OnBackPressedCallback
//import androidx.activity.viewModels
//import androidx.annotation.RequiresApi
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.FragmentContainerView
//import androidx.fragment.app.FragmentManager
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.Navigation
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.NavHostFragment
//import androidx.viewpager2.widget.ViewPager2
//import com.github.mikephil.charting.charts.BarChart
//import com.github.mikephil.charting.data.BarData
//import com.github.mikephil.charting.data.BarDataSet
//import com.github.mikephil.charting.data.BarEntry
//import com.github.mikephil.charting.data.Entry
//import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter
//import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
//import com.github.mikephil.charting.formatter.ValueFormatter
//import kotlinx.coroutines.launch
//import my.edu.tarc.ass2.Bill
//import my.edu.tarc.ass2.Profile.ProfileFragment
//import my.edu.tarc.ass2.Profile.ProfileViewModel
//import my.edu.tarc.ass2.R
//import my.edu.tarc.ass2.databaseDao
//import my.edu.tarc.ass2.databinding.ActivityDashboardBinding
//import java.sql.Connection
//import java.text.DecimalFormat
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//import java.util.Locale
//import java.util.Timer
//import java.util.TimerTask
//
//
////import com.mysql.cj.jdbc.Driver
//
//
//class Dashboard : AppCompatActivity() {
//    private val dashboardViewModel: DashboardViewModel by viewModels()
//    private val profileViewModel: ProfileViewModel by viewModels()
//    //private lateinit var dashboardViewModel: DashboardViewModel
//    private lateinit var binding: ActivityDashboardBinding
//    private lateinit var sharedPre: SharedPreferences
//    private lateinit var viewPager: ViewPager2
//    private lateinit var timer: Timer
//    lateinit var imageList: List<Int>
//    private lateinit var connection: Connection
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        binding = ActivityDashboardBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        //Initialize ViewModel
//        //dashboardViewModel= ViewModelProvider(this).get(DashboardViewModel::class.java)
//
//        //onclicks
//
//
//        binding.hotlinePic.setOnClickListener(){
//            val fragment = HotlineFragment()
//            supportFragmentManager.beginTransaction()
//                .add(android.R.id.content, fragment)//add(R.id.overlapped_container, overlappedFragment)
//                .addToBackStack(null)
//                .commit()
//        }
//
//        binding.profilePic.setOnClickListener(){
//
////
//        }
//
//        binding.buttonAppliances.setOnClickListener(){
//
//        }
//
//        binding.buttonViewBill.setOnClickListener(){
//            val navController = Navigation.findNavController(this, R.id.dashboard)
//            //navController.navigate(R.id.)
//
//        }
//
//        viewPager = findViewById(R.id.viewPager)
//
//        //announcement slider
//        imageList = ArrayList<Int>()
//        imageList = imageList + R.drawable.about
//        imageList = imageList + R.drawable.developer
//        imageList = imageList + R.drawable.about
//        val adapter = ImageSliderAdapter(imageList )
//        viewPager.adapter = adapter
//        timer = Timer()
//        timer.scheduleAtFixedRate(AutoScrollTask(adapter.itemCount), 4000, 4000)
//
//        //set decimal places
//        val decimalFormat = DecimalFormat("#.00")
//
//
//        //back press
//        val backPressedCallback = object: OnBackPressedCallback(true)
//        {
//            override fun handleOnBackPressed()
//            {
//                val builder = AlertDialog.Builder(this@Dashboard)
//                builder.setMessage(getString(R.string.exit_message))
//                    .setPositiveButton(getString(R.string.exit),{_,_ -> finish() })
//                    .setNegativeButton(getString(R.string.cancel),{_,_ ->  })
//
//                builder.create().show()
//            }
//        }
//        onBackPressedDispatcher.addCallback(backPressedCallback)
//
//        //show system date time
//        val current = LocalDateTime.now()
//        val ldt = LocalDateTime.parse(current.toString() )
//        val formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.ENGLISH)
//        val output = ldt.format(formatter)
//        binding.displayDateTime.text = output.toString()
//        val monthDisplay =  current.monthValue
//        var yearDisplay =  current.year
//        if (monthDisplay == 1){
//            yearDisplay -= 1
//        }
//        binding.displayMonth.text = current.month.toString() + "  " + current.year
//
//
//        //binding with database
//        lifecycleScope.launch {
//
//            //val loginEmail = sharedPre.getString(getString(R.string.LoginEmail),"")
//            //sharedPre=getSharedPreferences(Context.MODE_PRIVATE)
//            //val accNo = loginEmail.let { profileViewModel.getAccNumber(it.toString()) }
//            val accNo = 123412341111
//            //bar chart
//            val barChart: BarChart= binding.barChart
//            val labels = mutableListOf<String>()
//            val values = mutableListOf<Double>()
//            val dataEntryList: List<Int> =  dashboardViewModel.getBarMonth(accNo)
//            val dataEntryList1: List<Double> =  dashboardViewModel.getBarOverallUsage(accNo)
//
//            /*binding.startingMonth.text = dataEntryList[0].toString()
//            binding.endingMonth.text = dataEntryList[dataEntryList.size-1].toString()*/
//
//            val pairs = mutableListOf<Pair<Int, Double>>()
//            for (i in dataEntryList.indices) {
//                val pair = Pair(dataEntryList[i], dataEntryList1[i])
//                pairs.add(pair)
//            }
//
//            for (i in pairs) {
//                labels.add(i.first.toString())
//                values.add(i.second)
//            }
//
//            val entries = mutableListOf<BarEntry>()
//            for (i in values.indices) {
//                entries.add(BarEntry(labels[i].toFloat(), values[i].toFloat()))
//            }
//            println( entries)
//            val dataSet = BarDataSet(entries, "Overall Usage (kW)")
//            val barData = BarData(dataSet)
//            //barData.barWidth = 0.5f
//            barChart.data = barData
//            barChart.getDescription().setEnabled(false)
//            val xAxis =barChart.xAxis
//            xAxis.valueFormatter = OriginalXAxisValueFormatter(labels)
//            barChart.setDrawGridBackground(false)
//            barChart.setPinchZoom(false)
//            xAxis.setDrawGridLines(false)
//            xAxis.setDrawLabels(false)
//            val yAxisLeft = barChart.axisLeft
//            yAxisLeft.setDrawGridLines(false)
//            yAxisLeft.setDrawLabels(false)
//            val yAxisRight = barChart.axisRight
//            yAxisRight.setDrawGridLines(false)
//            yAxisRight.setDrawLabels(false)
//            barChart.invalidate()
//
//
////            //jdbc
////            val url = "jdbc:mysql://files.000webhost.com:21/id20710696_gol_database?max_allowed_packet=16777216"
////            val username = "guardianoflighting"
////            val password = "Ipad5210*"
////            val databaseManager = JDBCDatabase(url, username, password)
////            databaseManager.connect()
////            if (::connection.isInitialized) {
////                val getOverallUsage= databaseManager.performSelectQueryBill("OverallUsage", 1234123411 ,4 , 2023)
////                binding.displayOverallUsage.text = getOverallUsage.toString()
////
////            } else {
////                println("ENDED")
////            }
////            databaseManager.disconnect()
//            //databaseManager.performInsertQuery()
//            //databaseManager.performUpdateQuery()
//            //databaseManager.performDeleteQuery()
//
//
//
//            //get user acc
//            val getOverallUsage = dashboardViewModel.getOverallUsage(accNo ,(monthDisplay-1),yearDisplay)
//            binding.displayOverallUsage.text = getOverallUsage.toString()
//
//            val getBillStatus = dashboardViewModel.getBillStatus(accNo,(monthDisplay-1),yearDisplay)
//            binding.displayBillStatus.text = getBillStatus
//
//            val getPaymentDue = dashboardViewModel.getPaymentDue(accNo,(monthDisplay-1),yearDisplay)
//            binding.displayPaymentDue.text = getPaymentDue
//
//            val getOptimumUsage = "adf"
//            binding.displayOptimumUsage.text = getOptimumUsage
//
//            val getTotalAmount = dashboardViewModel.getTotalAmount(accNo,(monthDisplay-1),yearDisplay)
//            val formattedNumber = decimalFormat.format(getTotalAmount)
//            binding.displayTotalAmount.text = "RM" + formattedNumber.toString()
//
//
//            // val getAddress = dashboardViewModel.getAddress()
//        }
//
//
//    }
//    inner class AutoScrollTask(private val numPages: Int) : TimerTask() {
//        override fun run() {
//            runOnUiThread {
//                val currentItem = viewPager.currentItem
//                val nextPage = if (currentItem == numPages - 1) 0 else currentItem + 1
//                viewPager.setCurrentItem(nextPage, true)
//            }
//        }
//    }
//
//    class OriginalXAxisValueFormatter(private val labels: List<String>) : ValueFormatter() {
//        override fun getFormattedValue(value: Float): String {
//            val index = value.toInt()
//
//            return if (index >= -1 && index < labels.size) {
//                labels[index]

//
//            } else {
//                ""
//            }
//        }
//    }
//
//
//}